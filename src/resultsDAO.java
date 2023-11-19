import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class resultsDAO {
    //compiles and writes to "Results.txt"
    static String legitimateUsers = "";
    static String illegitimateUsers = "";
    static String casinoChanges = "";

    //compile legitimate users text
    public static void compilePlayerAndMatchText(HashMap<String, Player> listOfPlayers, ArrayList<String> playerKeys) {
        for (String key :
                playerKeys) {
            Player playerCurrentlyBeingAdded = listOfPlayers.get(key);
            if (playerCurrentlyBeingAdded.isLegal()) {
                legitimateUsers += key + " " + playerCurrentlyBeingAdded.getCoins() + " " + playerCurrentlyBeingAdded.getRatio() + "\n";
            } else {
                illegitimateUsers += playerCurrentlyBeingAdded.getIllegalTransaction() + "\n";
            }
        }
    }


    //compile casino text
    public static void compileCasinoText(Casino casino) {
        casinoChanges += casino.getFunds();
    }

    public static void writeToFile() throws IOException {
        FileWriter resultsFile = new FileWriter("src/Results.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(resultsFile);
        bufferedWriter.write(legitimateUsers + " \n" + illegitimateUsers + "\n" + casinoChanges);
        bufferedWriter.close();
    }
}
