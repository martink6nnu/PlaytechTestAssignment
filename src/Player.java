import java.util.ArrayList;

import static java.lang.Math.round;

public class Player {
    //Player class to store and access information to easily add to results file.

    private long coins;
    private final String UUID;
    private boolean isLegal;
    private int wins = 0; //to track total wins for getting win rate of bets
    private int bets = 0; //to track total bets for getting win rate of bets
    private final ArrayList<Transaction> listOfTransactions = new ArrayList<>(); // to store list for the purpose of accessing first illegal operation and rolling back all Bet operations.

    public Player(String newUUID) {
        this.UUID = newUUID;
        this.coins = 0;
        this.isLegal = true;
    }

    public void addTransaction(Transaction transaction) {
        this.listOfTransactions.add(transaction);
    }

    public void setIllegal() { // Used when player performs illegal actions. Also rolls back all Bets.
        this.isLegal = false;
    }

    public void addCoins(int addedCoins) { // Used when player deposits coins or wins a bet.
        this.coins += addedCoins;
    }

    public void removeCoins(int removedCoins) { // Used when player withdraws or loses a bet
        this.coins -= removedCoins;
    }

    public long getCoins() {
        return this.coins;
    }

    public String getUUID() {
        return this.UUID;
    }

    public boolean isLegal() { // checked before transactions to see if they can be performed.
        return this.isLegal;
    }

    public void addWin() {
        this.wins += 1;
    }

    public void addBet() {
        this.bets += 1;
    }

    public double getRatio() { // for adding to Results string.
        double ratio = (double) this.wins / this.bets;
        return round(ratio * 100) / 100.0;
    }

    public String getIllegalTransaction() {
        if (!this.isLegal())
            return this.listOfTransactions.get(listOfTransactions.size() - 1).toString();
        else {
            return "Player is legal.";
        }
    }

    public ArrayList<Transaction> getListOfTransactions() {
        return this.listOfTransactions;
    }
}
