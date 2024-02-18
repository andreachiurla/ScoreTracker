import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager {
    // points:   home and guest
    private int ptsHome;
    private int ptsGuest;

    public FileManager(){
        ptsHome = 0;
        ptsGuest = 0;

        try (FileWriter myWriter = new FileWriter("points.txt")) {
            Format score = new Format(0, 0);
            myWriter.write(score.toString(0));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred opening the file.");
            e.printStackTrace();
        }

        try (FileWriter myWriter = new FileWriter("teams.txt")) {
            myWriter.write("                Casa - Ospiti");    // 16 spaces before home (20 - 4 ("home"))
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred opening the file.");
            e.printStackTrace();
        }

        try (FileWriter myWriter = new FileWriter("quarter.txt")) {
            myWriter.write("1ST");
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
            Format pts = new Format(ptsHome, ptsGuest);
            myWriter.write(pts.toString(0));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String[] setTeams(String home, String guest){
        Format format = new Format(home, guest);
        try (FileWriter myWriter = new FileWriter("teams.txt")) {
            myWriter.write(format.toString(1));    // 16 spaces before home (20 - 4 ("home"))
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred opening the file.");
            e.printStackTrace();
        }
        return new String[] {home, guest};
    }

    public void setQuarter(int quarter){
        try (FileWriter myWriter = new FileWriter("quarter.txt")) {
            if (quarter == 1) {
                myWriter.write("1ST");
            }else if (quarter == 2) {
                myWriter.write("2ND");
            }else if (quarter == 3) {
                myWriter.write("3RD");
            }else if (quarter == 4) {
                myWriter.write("4TH");
            }else{
                myWriter.write("END");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred opening the file.");
            e.printStackTrace();
        }
    }

    public String getPointsFormatted(){
        Format format = new Format(ptsHome, ptsGuest);
        return format.toString(0);
    }

    public int getPtsHome(){
        return ptsHome;
    }

    public int getPtsGuest(){
        return ptsGuest;
    }
}
