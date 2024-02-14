import java.io.FileWriter;
import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;

public class FileManager {
    // points:   home and guest
    private int ptsHome;
    private int ptsGuest;

    public FileManager(){
        ptsHome = 0;
        ptsGuest = 0;

        try (FileWriter myWriter = new FileWriter("points.txt")) {
            Score score = new Score(0, 0);
            myWriter.write(score.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred opening the file.");
            e.printStackTrace();
        }
    }

    public void add1Home(){
        act(0);
    }
    public void add1Guest(){
        act(1);
    }
    public void remove1Home(){
        if(ptsHome > 0) {
            act(2);
        }else{
            System.out.println("The score can't be a negative number.");
        }
    }
    public void remove1Guest(){
        if(ptsGuest > 0) {
            act(3);
        }else{
            System.out.println("The score can't be a negative number.");
        }
    }

    /**
     * Prints in the file the actual points
     * @param action 0 = +1 Home | 1 = +1 Guest | 2 = -1 Home | 3 = -1 Guest
     */
    private void act(int action){
        try (FileWriter myWriter = new FileWriter("points.txt")) {
            if(action == 0) {   // change score
                ptsHome++;
            }else if(action == 1){
                ptsGuest++;
            }else if(action == 2){
                ptsHome--;
            }else if(action == 3){
                ptsGuest--;
            }
            // write in the file
            Score pts = new Score(ptsHome, ptsGuest);
            myWriter.write(pts.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int getPtsHome() {
        return ptsHome;
    }

    public int getPtsGuest() {
        return ptsGuest;
    }
}
