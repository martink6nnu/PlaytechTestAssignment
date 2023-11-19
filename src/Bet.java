public class Bet extends Transaction {
    // for Bets to access and store information.
    private Match match;
    private String prediction;

    public Bet(Player playerDepositing, Match matchBetOn, int coinsBet, String predictionPlaced) {
        if (playerDepositing.isLegal()) {
            this.transactionPerformer = playerDepositing;
            this.transactionAmount = coinsBet;
            this.match = matchBetOn;
            this.prediction = predictionPlaced;
            this.isLegal = this.transactionPerformer.getCoins() >= this.transactionAmount;
            this.transactionPerformer.addTransaction(this);
        }
    }


    public String toString() { // to add to illegal list in case the illegal transaction is a Bet.
        return this.transactionPerformer.getUUID() + " BET " + this.match.getUUID() + " " + this.transactionAmount + " " + this.prediction;
    }

    public Match getMatch() {
        return this.match;
    }

    public String getPrediction() {
        return this.prediction;
    }

    public boolean isLegal() {
        return this.isLegal;
    }

}
