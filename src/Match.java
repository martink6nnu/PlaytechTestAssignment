public class Match {
    // To save and get information about matches easily for BET transactions.
    private final String UUID;
    private final double oddsOfA;
    private final double oddsOfB;
    private final String result;

    public Match(String UUID, double oddsForA, double oddsForB, String result) {
        this.UUID = UUID;
        this.oddsOfA = oddsForA;
        this.oddsOfB = oddsForB;
        this.result = result;
    }

    public String getUUID() {
        return this.UUID;
    }

    public String getResult() {
        return this.result;
    }

    public double getOddsOf(String letter) {
        if (letter.equals("A")) {
            return this.oddsOfA;
        } else {
            return this.oddsOfB;
        }
    }


}
