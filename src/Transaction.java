public abstract class Transaction {
    //abstract class so we can have ArrayList<Transaction> listOfTransactions per Player object, grouping all types of transactions
    public Player transactionPerformer;
    public int transactionAmount;
    public boolean isLegal;

    public abstract String toString();

}
