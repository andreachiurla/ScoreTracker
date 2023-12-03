import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    // points:   home and guest
    private int ptsHome;
    private int ptsGuest;

    public FileManager(){
        ptsHome = 0;
        ptsGuest = 0;

        try (FileWriter myWriter = new FileWriter("points.txt")) {
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred opening the file.");
            e.printStackTrace();
        }
    }

    public void add1Home(){
        add1(0);
    }
    public void add1Guest(){
        add1(1);
    }

    /**
     * Prints in the file the actual points
     * @param team 0 - Home; else - Guest
     */
    private void add1(int team){
        try (FileWriter myWriter = new FileWriter("points.txt")) {
            if(team == 0) {
                ptsHome++;
            }else{
                ptsGuest++;
            }
            Punteggio pts = new Punteggio(ptsHome, ptsGuest);
            myWriter.write(pts.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
