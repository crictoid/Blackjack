// Mark Bulmer - CSC 251 - Project 2 - Blackjack
// 9-29-2022

class BlackJack extends CardGame {
// creates the Blackjack method which is a subclass of CardGame
    enum Winner {DEALER, PLAYER, TIE}
    // fixed constants which will tell us if the player won, the dealer won, or if the game was tied.
    private Winner winner;
    // will be used to store and recall the winner information.
    final int DEALER_HIT_STAY_THRESHOLD = 16;
    // the dealer will not ask for a hit if he has a score of at least 16.
    final String DEALER_NAME = "Dealer";
    // sets the dealer's name as "Dealer".

    private final BlackJackPlayer player = new BlackJackPlayer("Player");
    // creates a new player for the game.
    // I couldn't get this part to run appropriately because it said I needed
    // a constructor, which I thought was already at the bottom. If I try to create a new
    // one, they interfere with one another. I am guessing it is a misplaced colon
    // or semicolon somewhere, but I couldn't make the determination.
    private final BlackJackPlayer dealer = new BlackJackPlayer(DEALER_NAME);
    // creates the dealer's position in the blackjack game, using the already defined dealer name.

    public BlackJack.Winner getWinner() {
        return winner;
    }
    // allows us to return the winner property.
    public BlackJackPlayer getPlayer() {
        return player;
    }
    // allows us to return the player property.
    public BlackJackPlayer getDealer() {
        return dealer;
    }
    // allows us to return the dealer property.

    private void setWinner(Winner winner) {
        this.winner = winner;
    }
    // the Winner of the current game is store in the winner property.

    public void dealCardWithHit(Player p) {
        dealCard(p);
        // deals a card to the player
        BlackJackPlayer bjp = (BlackJackPlayer)p;
        int hits = bjp.getNumberHits();
        // updates number of hits to display for user
        bjp.setNumberHits(++hits);
        // increases number of hits by one
    }
    // deals a card to the player with they ask for a hit

    public void determineOutcome(Player p, Player d) {
    // the method is used to determine the winner of the game.
        BlackJackPlayer bjp = (BlackJackPlayer)p;
        BlackJackPlayer bjd = (BlackJackPlayer)d;
        // creates our two players
        if (bjp.isBust())
            setWinner(Winner.DEALER);
        else if (bjd.isBust())
            setWinner(Winner.PLAYER);
        else if (bjd.getCurrentScore() > bjp.getCurrentScore())
            setWinner(Winner.DEALER);
        else if(bjp.getCurrentScore() > bjd.getCurrentScore())
            setWinner(Winner.PLAYER);
        else
            setWinner(Winner.TIE);
        // checks score and sets the winner
    }

    public BlackJack(int numCards) {
        super(numCards);
    }
    // I think that this determines how many cards are left from which to draw,
    // though I am not positive.

    public static class BlackJackPlayer extends Player {
        // stores the information in the current Blackjack game outside the CardGame class.
        final int BUST_SCORE = 21;
        // sets a "Bust" as 21 points.

        private int numberHits;
        // stores the number of hits the player has taken.
        private boolean bust;



        // determines if the player has busted or not.

        public int getNumberHits() {
            return numberHits;
        }

        // returns the number of hits.
        public boolean isBust() {
            return bust;
        }
        // returns if bust or not.

        private void setNumberHits(int numberHits) {
            this.numberHits = numberHits;
        }

        // sets the number of hits.
        private void setBust(boolean bust) {
            this.bust = bust;
        }
        // sets if bust or not.

        public boolean checkBust() {
            // determining of the player has busted.
            boolean bust = false;
            if (getCurrentScore() > BUST_SCORE) {
                bust = true;
                setBust(bust);
                // determines if the player has busted, or gone over 21.
            }
            return bust;
            // printing the information to the user
        }

        public void BlackJackPlayer(){
// I was having a lot of issues with this and why it wasn't working correctly. I think
            // Maybe I have a some syntax errors somewhere that I'm overlooking.
        }

        public BlackJackPlayer(String name) {
           super(name);
        }
        // constructor for BlackJackPlayer class
    }
}
