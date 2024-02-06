import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class SendMessage {
    private static String token;
    private static String chatID;

    public static void sendToTelegram(String strTeam, String strPoints) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        //Add Telegram token (given Token is fake)
        String apiToken = "6925000016:AAFQ-IpiXFXC_iGDOpAp62ql2BlQeOnZ4P8";

        //Add chatId (given chatId is fake)
        String chatId = "-1001999704835";

        String textMessage = "🚀" + strTeam + ": GOL! - Punteggio: " + strPoints;

        urlString = String.format(urlString, apiToken, chatId, textMessage);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendToTelegram(int quarto){
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String textMessage = "🕒E' iniziato il ";
        if(quarto == 2) textMessage += "secondo";
        else if(quarto == 3) textMessage += "terzo";
        else if(quarto == 4) textMessage += "quarto";
        textMessage += " tempo.";
        if (quarto == 1) textMessage = "🕒E' appena iniziata la partita";

        urlString = String.format(urlString, token, chatID, textMessage);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readConfig() throws IOException {
        Properties config = new Properties();
        config.load(new FileInputStream("config.properties"));
        SendMessage.token = config.getProperty("token_bot");
        SendMessage.chatID = config.getProperty("chat_id");
    }

}