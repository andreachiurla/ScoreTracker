import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        HTTPServer httpServer = new HTTPServer(8080, fileManager);
        try {
            httpServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Interface GUI = new Interface(fileManager);
        GUI.createFrame();
        GUI.setPointsTracker();
    }
}