import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class SendMessage {
    private static String token;
    private static String chatID;

    public static boolean sendToTelegram(String strTeamGol, String strHome, String strGuest, int ptsHome, int ptsGuest) {
        if(strHome.equals("Home") || strGuest.equals("Guest")) return false;

        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String textMessage = "🚀" + strTeamGol + ": GOL!🚀 Punteggio: " + getPrintableScore(strHome, strGuest, ptsHome, ptsGuest);

        urlString = String.format(urlString, token, chatID, textMessage);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public static boolean sendToTelegram(int quarto, String strHome, String strGuest, int ptsHome, int ptsGuest){
        if(strHome.equals("Home") || strGuest.equals("Guest")) return false;

        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String textMessage = "🕒E' iniziato il ";
        if(quarto == 2) textMessage += "secondo";
        else if(quarto == 3) textMessage += "terzo";
        else if(quarto == 4) textMessage += "quarto";
        textMessage += " tempo.";
        if (quarto == 1) {
            textMessage = "🕒E' appena iniziata la partita: " + strHome + " vs " + strGuest;
        }
        else if (quarto == 0) textMessage = "🏅PARTITA TERMINATA🏅" + " Punteggio finale: " + getPrintableScore(strHome, strGuest, ptsHome, ptsGuest);

        urlString = String.format(urlString, token, chatID, textMessage);
        System.out.println("URL: " + urlString);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public static boolean sendToTelegram(String strTeam, String strHome, String strGuest, int ptsHome, int ptsGuest, int temp){
        if(strHome.equals("Home") || strGuest.equals("Guest")) return false;

        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String textMessage = "❌" + strTeam + ": GOL ANNULLATO❌. Punteggio attuale: " + getPrintableScore(strHome, strGuest, ptsHome, ptsGuest);

        urlString = String.format(urlString, token, chatID, textMessage);
        System.out.println("URL: " + urlString);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void readConfig() throws IOException {
        Properties config = new Properties();
        config.load(new FileInputStream("config.properties"));
        SendMessage.token = config.getProperty("token_bot");
        SendMessage.chatID = config.getProperty("chat_id");
    }

    private static String getPrintableScore(String strHome, String strGuest, int ptsHome, int ptsGuest){
        return strHome + " " + ptsHome + " - " + ptsGuest + " " + strGuest;
    }

}