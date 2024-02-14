import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerRunnable implements Runnable {
    private final ServerSocket serverSocket;
    private final FileManager fileManager;
    private boolean shutdownReceived;

    public ServerRunnable(ServerSocket serverSocket, FileManager fileManager) {
        this.serverSocket = serverSocket;
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        while( ! shutdownReceived ) {
            try {
                Socket clientSocket = serverSocket.accept();
                try {
                    handleClientRequest(clientSocket);
                } catch (IOException e) {
                    System.err.println("Error on client: " + clientSocket + " description: " + e.getMessage());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        shutdownReceived = true;
    }

    private void handleClientRequest(Socket clientSocket) throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();
        Reader reader = new InputStreamReader(inputStream);
        Writer writer = new OutputStreamWriter(outputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        parseLineCommand result = parseLineCommand(bufferedReader);
        Map<String, String> headers = parseHeaders(bufferedReader);
        System.out.println("Command: POST");
        System.out.println("Resource: " + result.resource);
        System.out.println("Headers:");
        for(String key: headers.keySet()) {
            System.out.println(" " + key + ": " + headers.get(key));
        }
        switch(result.command) {
            case "GET": doGET(result.resource, result.protocol, headers, writer) ; break;
            case "POST": doPOST(result.resource, result.protocol, headers, writer, bufferedReader) ; break;
        }
        clientSocket.close();
    }

    private Map<String, String> parseHeaders(BufferedReader bufferedReader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        int emptyLineCount = 0;
        while(emptyLineCount < 1) {
            String line = bufferedReader.readLine();
            if( line.isEmpty() ) {
                emptyLineCount++;
            } else {
                String[] tokens = line.split(": ");
                String key = tokens[0];
                String value = tokens[1];
                headers.put(key, value);
            }
        }
        return headers;
    }

    private static parseLineCommand parseLineCommand(BufferedReader bufferedReader) throws IOException {
        String lineCommand = bufferedReader.readLine();
        String[] tokens = lineCommand.split(" ");
        String command = tokens[0];
        String resource = tokens[1];
        String protocol = tokens[2];
        parseLineCommand result = new parseLineCommand(command, resource, protocol);
        return result;
    }

    private static class parseLineCommand {
        public final String command;
        public final String resource;
        public final String protocol;

        public parseLineCommand(String command, String resource, String protocol) {
            this.command = command;
            this.resource = resource;
            this.protocol = protocol;
        }
    }

    private void status200(Writer writer) throws IOException {
        writer.write("HTTP/1.1 200 OK" + "\r\n");
        writer.write("Content-Type: text/html; charset=ISO-8859-1" + "\r\n");
        writer.write("\r\n");
    }
    private void doGET(String resource, String protocol, Map<String, String> headers, Writer writer) throws IOException {
        if(resource.equals("/")) {
            resource = "index.html";
        } else if(resource.startsWith("/")) {
            resource = resource.substring(1);
        }
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(resource);
        if(resourceStream == null) {
            error404(writer);
            return;
        }
        status200(writer);
        Reader resourceReader = new InputStreamReader(resourceStream);
        BufferedReader bufferedReader = new BufferedReader(resourceReader);
        String line;
        while( (line = bufferedReader.readLine() ) != null) {
            String modifiedLine = applyVariables(line);
            writer.write(modifiedLine);
        }
        writer.flush();
    }

    private String applyVariables(String line) {
        String modified = line;
        modified = modified.replace("${home-score}", "" + fileManager.getPtsHome());
        modified = modified.replace("${guest-score}", "" + fileManager.getPtsGuest());
        return modified;
    }

    private void error404(Writer writer) throws IOException {
        writer.write("HTTP/1.1 404 NOT-FOUND" + "\r\n");
        writer.write("Content-Type: text/html; charset=ISO-8859-1" + "\r\n");
        writer.write("\r\n");
        writer.write("Resource not found" + "\r\n");
        writer.flush();
    }

    private void doPOST(String resource, String protocol, Map<String, String> headers, Writer writer, BufferedReader bufferedReader) throws IOException {
        System.out.println("Reading payload...");
        String contentLengthHeader = headers.get("Content-Length");
        int contentLength = Integer.parseInt(contentLengthHeader);
        char[] charsPayload = new char[ contentLength ];
        int howManyChars = bufferedReader.read(charsPayload);
        String payload = new String(charsPayload, 0, howManyChars);
        System.out.println("Payload: " + payload);
        Map<String,String> form = new HashMap<>();
        String[] tokens = payload.split("&");
        for(String token: tokens) {
            String[] keyAndValue = token.split("=");
            String key = decode(keyAndValue[0]);
            String value = null;
            if(keyAndValue.length == 2) {
                value = decode(keyAndValue[1]);
            }
            form.put(key,value);
            System.out.println(" " + key + "='" + value + "'");
        }
        if( form.containsKey("home") ) {
            if(form.get("home").equals("+1")) {
                System.out.println("HOME +1");
                fileManager.add1Home();
            }
        }
        if( form.containsKey("guest") ) {
            if(form.get("guest").equals("+1")) {
                System.out.println("GUEST +1");
                fileManager.add1Guest();
            }
        }
        doGET("/index.html", protocol, headers, writer);
    }

    private String decode(String string) {
        StringBuilder builder = new StringBuilder();
        for(int index = 0; index < string.length(); index++) {
            char c = string.charAt(index);
            if(c == '%') {
                index++;
                String hexCode = string.substring(index, index + 2);
                int code = Integer.parseInt(hexCode, 16);
                index++;
                builder.append((char)code);
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
