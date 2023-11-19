import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class playerDataDAO {
    //reads "player_data.txt"
    private static BufferedReader reader;
    public static void openReader() throws IOException{
        reader = new BufferedReader(new FileReader("./resources/player_data.txt"));
    }
    public static String getLine() throws IOException {
        return reader.readLine();
    }
    public static void closeReader() throws IOException {
        reader.close();
    }

}
