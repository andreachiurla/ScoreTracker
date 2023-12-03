import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public FileManager(){
        try (FileWriter myWriter = new FileWriter("points.txt")) {
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
