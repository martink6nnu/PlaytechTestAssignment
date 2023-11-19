import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        Casino casino = new Casino();
        HashMap<String, Match> matchHashMap = new HashMap<>(); //HashMap to hold match objects, accessed by keys
        HashMap<String, Player> playerHashMap = new HashMap<>(); //HashMap to hold player objects, accessed by keys
        ArrayList<String> playerKeys = new ArrayList<>();

        matchDataDAO.openReader();
        String line;
        while ((line = matchDataDAO.getLine()) != null) { //construct Match instances and add to matchHashMap
            String[] lineSplit = line.split(",");
            String matchUUID = lineSplit[0];
            double oddsA = Double.parseDouble(lineSplit[1]);
            double oddsB = Double.parseDouble(lineSplit[2]);
            String winner = lineSplit[3];
            matchHashMap.put(matchUUID, new Match(matchUUID, oddsA, oddsB, winner));
        }
        matchDataDAO.closeReader();
        playerDataDAO.openReader();
        while ((line = playerDataDAO.getLine()) != null) {
            String[] lineSplit = line.split(",");
            String playerUUID = lineSplit[0];
            String transactionType = lineSplit[1];
            int transactionAmount = Integer.parseInt(lineSplit[3]);
            if (!playerKeys.contains(playerUUID)) {
                playerKeys.add(playerUUID);
                playerHashMap.put(playerUUID, new Player(playerUUID));
            }
            if (line.endsWith(",")) { //if line ends with ",", it is either a withdrawal or a deposit
                // if player has been constructed, check for type of transaction (can't be "BET")
                if (transactionType.equals("WITHDRAW")) {
                    Withdraw playerWithdraw = new Withdraw(playerHashMap.get(playerUUID), transactionAmount);
                    TransactionManager.transactionWithdraw(playerHashMap.get(playerUUID), playerWithdraw, casino);
                } else if (transactionType.equals("DEPOSIT")) {
                    Deposit playerDeposit = new Deposit(playerHashMap.get(playerUUID), transactionAmount);
                    TransactionManager.transactionDeposit(playerHashMap.get(playerUUID), playerDeposit);
                }
            } else { // if line does not end with ",", must be bet.
                String matchUUID = lineSplit[2];
                String placedPrediction = lineSplit[4];
                Bet betPlaced = new Bet(playerHashMap.get(playerUUID), matchHashMap.get(matchUUID), transactionAmount, placedPrediction);
                TransactionManager.transactionBet(playerHashMap.get(playerUUID), betPlaced, casino);
            }
        }
        playerDataDAO.closeReader();
        Collections.sort(playerKeys); //for output lists to be ordered by player ID
        resultsDAO.compilePlayerAndMatchText(playerHashMap, playerKeys);
        resultsDAO.compileCasinoText(casino);
        resultsDAO.writeToFile();
    }
}