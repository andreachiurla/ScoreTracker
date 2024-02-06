import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Interface GUI = new Interface();
        GUI.createFrame();
        try {
            SendMessage.readConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GUI.setHomePage();
    }
}