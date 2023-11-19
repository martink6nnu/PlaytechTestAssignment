public class Withdraw extends Transaction {
    //made to specify withdraw transaction for TransactionManager
    public Withdraw(Player player, int transactionAmount) {
        if (player.isLegal()) {
            this.transactionPerformer = player;
            this.transactionAmount = transactionAmount;

            this.isLegal = transactionAmount <= this.transactionPerformer.getCoins(); // checks if withdrawal is legal
            this.transactionPerformer.addTransaction(this);
        }
    }

    public boolean isLegal() {
        return this.isLegal;
    }

    public String toString() { // to return in correct form for results file if the withdrawal was illegal
        return this.transactionPerformer.getUUID() + " WITHDRAW " + null + " " + transactionAmount + " " + null;
    }
}
