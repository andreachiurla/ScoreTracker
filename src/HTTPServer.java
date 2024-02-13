import java.io.IOException;
import java.net.ServerSocket;

public class HTTPServer {
    private ServerSocket serverSocket;
    private int port;
    private final FileManager fileManager;
    private Thread serverThread;
    private ServerRunnable serverRunnable;

    public HTTPServer(int port, FileManager fileManager) {
        this.port = port;
        this.fileManager = fileManager;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(this.port);
        serverRunnable = new ServerRunnable(serverSocket, fileManager);
        serverThread = new Thread(serverRunnable);
        serverThread.start();
    }

    public void stop() {
        serverRunnable.shutdown();
    }
}
