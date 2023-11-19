import java.util.ArrayList;

import static java.lang.Math.floor;

public class TransactionManager {

    public static void transactionWithdraw(Player player, Withdraw withdrawal, Casino casino) { //withdrawal transaction
        if (player.isLegal()) {
            if (withdrawal.isLegal()) {
                player.removeCoins(withdrawal.transactionAmount);
            } else {
                player.setIllegal();
                rollBackBets(player, casino);
            }
        }
    }

    public static void transactionDeposit(Player player, Deposit depositing) { //deposit transaction
        if (player.isLegal()) {
            player.addCoins(depositing.transactionAmount);
        }
    }

    public static void transactionBet(Player player, Bet bet, Casino casino) { //bet transaction
        if (!player.isLegal()) { // only does anything if player is legal
            return;
        }

        player.addBet(); // +1 to Player's bets to later get the win rate

        if (!bet.isLegal()) { //set player to illegal, also performing rollbacks.
            player.setIllegal();
            rollBackBets(player, casino);
            return;
        } //checks if the bet itself is legal
        Match matchBetOn = bet.getMatch(); //cast to (Bet) so we can use Bet.getMatch()
        int betAmount = bet.transactionAmount;
        String result = matchBetOn.getResult();
        String prediction = bet.getPrediction();
        if (bet.getMatch().getResult().equals("DRAW")) { // ignores cases where the result is a DRAW
            return;
        }

        if (result.equals(prediction)) { // if prediction and result match, add winnings to Player's funds and remove them from the Casino.
            int winnings;
            winnings = (int) floor(betAmount * matchBetOn.getOddsOf(result));
            player.addCoins(winnings);
            player.addWin(); // +1 to Player's wins to later get the win rate
            casino.removeFunds(winnings);
        } else {
            //if prediction and result do not match, remove transaction amount from Player and add to Casino.
            player.removeCoins(bet.transactionAmount);
            casino.addFunds(bet.transactionAmount);
        }
    }

    public static void rollBackBets(Player cheater, Casino casino) { // to roll back bets that did anything to the state of the Casino.
        ArrayList<Transaction> listOfTransactions = cheater.getListOfTransactions();
        for (int i = listOfTransactions.size() - 1; i >= 0; i--) { //to work through all transactions placed by the player.
            Transaction checkedTransaction = listOfTransactions.get(i);
            if (!(checkedTransaction instanceof Bet)) { // only does something if the Transaction is a Bet.
                continue;
            }
            Match matchBetOn = ((Bet) checkedTransaction).getMatch(); //cast to (Bet) so we can use Bet.getMatch()
            int betAmount = checkedTransaction.transactionAmount;
            String result = matchBetOn.getResult();
            String prediction = ((Bet) checkedTransaction).getPrediction();
            if (!matchBetOn.getResult().equals("DRAW")) {
                int winnings;

                if (prediction.equals(result)) {
                    winnings = (int) floor(betAmount * matchBetOn.getOddsOf(result));
                    casino.addFunds(winnings);
                } else {
                    casino.removeFunds(betAmount);
                }


            }
        }
    }
}
