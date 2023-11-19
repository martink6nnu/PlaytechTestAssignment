public class Deposit extends Transaction{
    // for Player to manually add coins to account.
    public Deposit(Player playerDepositing, int coinsDeposited){
        if (playerDepositing.isLegal()) {
            this.transactionPerformer = playerDepositing;
            this.transactionAmount = coinsDeposited;
            this.isLegal = true;
            this.transactionPerformer.addTransaction(this);
        }
    }
    public String toString(){ // in case there could be an illegal deposit transaction. I have now realized there can't be one according to the task document.
        return this.transactionPerformer.getUUID() + " DEPOSIT " + null + " " + this.transactionAmount + " " + null;
    }
}
