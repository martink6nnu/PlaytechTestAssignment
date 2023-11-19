public class Casino {
    //to track and influence casino funds for a set of player_data and match_data
    private long funds;
    public Casino(){
        this.funds=0;
    }
    public void addFunds(int amount) {
        funds += amount;
    }

    public void removeFunds(int amount) {
        funds -= amount;
    }

    public long getFunds() {
        return funds;
    }
}
