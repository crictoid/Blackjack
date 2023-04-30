import java.util.*;

// Mark Bulmer - CSC 251 - Project 2 - Blackjack
public abstract class CardGame {
    final String SPADES = "Spades";
    final String HEARTS = "Hearts";
    final String DIAMONDS = "Diamonds";
    final String CLUBS = "Clubs";
    // sets the four suits to our cards

    final String[] suits = {SPADES, HEARTS, DIAMONDS, CLUBS};

    final int ACE_NUM = 0;
    final int JACK_NUM = 10;
    final int QUEEN_NUM = 11;
    final int KING_NUM = 12;

    final String ACE_STR = "Ace";
    final String JACK_STR = "Jack";
    final String QUEEN_STR = "Queen";
    final String KING_STR = "King";

    final int ACE_SCORE = 11;
    final int JACK_SCORE = 10;
    final int QUEEN_SCORE = 10;
    final int KING_SCORE = 10;

    // determines the names and values of face cards

    private Map<Integer, String> rankMap = new HashMap<>();
    private Map<String, Integer> scoreMap = new HashMap<>();
    // strings in which to contain the scores and ranks of cards

    private int numberOfCardsInDeck;
    private int cardsInSuit;
    private int nextCard = 0;
    // create variables which determine what cards are allowed to be drawn.

    public class Card {
        // a class to build a deck of cards.
        private String rank;
        // create the rank of cards (2-10, J, Q, K, A)
        private String suit;
        // create the suit (hearts, diamonds, spades, clubs)
        private int score;
        // the total number of points the hit card is worth.

        public String getRank() {
            return rank;
        }
        // allows us to return a card's rank

        public String getSuit() {
            return suit;
        }
        // allows us to return a card's suit

        public int getScore() {
            return score;
        }
        // allows us to return a card's score value

        private void setRank(String rank) {
            this.rank = rank;
        }
        // sets a card's rank

        private void setSuit(String suit) {
            this.suit = suit;
        }
        // sets a card's suit

        private void setScore(int score) {
            this.score = score;
        }
        // sets a card's score

        public Card(int cardNum) {
            int rankNum = cardNum % cardsInSuit;

            if (rankMap.containsKey(rankNum))
                setRank(rankMap.get(rankNum));
            else
                setRank(String.valueOf(rankNum + 1));

            setSuit( suits[cardNum / cardsInSuit]);

            if (scoreMap.containsKey(getRank()))
                setScore(scoreMap.get(getRank()));
            else
                setScore(rankNum + 1);
            // builds the deck and assigns rank and value to the numerical cards.
        }
        @Override
        public String toString() {
            return "\t" + rank + " of " + suit;
        }
        // will allow us to display the card which was hit.
    }


    private final ArrayList<Card> deck = new ArrayList<>();
    // creates our deck of cards

    public Map<Integer, String> getRankMap() {
        return Collections.unmodifiableMap(rankMap);
    }

    public int getNumberOfCardsInDeck() {
        return numberOfCardsInDeck;
        // stores the cards left in the deck
    }
    public int getCardsInSuit() {
        return cardsInSuit;
    }
    public int getNextCard() {
        return nextCard;
        // holds the next card from which to draw
    }
    public List<Card> getDeck() { return Collections.unmodifiableList(deck); }

    private void setRankMap(Map<Integer, String> rankMap) {
        this.rankMap = rankMap;
    }
    private void setScoreMap(Map<String, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }

    private void setNumberOfCardsInDeck(int numberOfCardsInDeck) {
        this.numberOfCardsInDeck = numberOfCardsInDeck;
    }
    private void setCardsInSuit(int cardsInSuit) {
        this.cardsInSuit = cardsInSuit;
    }
    public void setNextCard(int nextCard) {
        this.nextCard = nextCard;
    }
    // getters and setters for cards

    private void setDeck() {
        int cardsInDeck = getNumberOfCardsInDeck();
        deck.clear();
        for (int i = 0; i < cardsInDeck; i++)
            deck.add(new Card(i));
        // creates a new deck
    }

    public void dealCard(Player p) {
        int nextCardIndex = getNextCard();
        Card nextCard = getDeck().get(nextCardIndex);
        p.addCard2Hand(nextCard);
        setNextCard(++nextCardIndex);
        // deals a card to the player
    }

    public void shuffleDeck() {
        int cardsInDeck = getNumberOfCardsInDeck();
        int index;
        Card temp;
        for (int i = 0; i < cardsInDeck; i++) {
            index = (int) (Math.random() * cardsInDeck);
            temp = deck.get(i);
            deck.set(i, deck.get(index));
            deck.set(index, temp);
            // randomaly shuffles the deck
        }
    }
    public CardGame(int numCards){
            setNumberOfCardsInDeck(numCards);
            setCardsInSuit(numCards / suits.length);


            Map<Integer, String> rankMap = new HashMap<>();
            rankMap.put(ACE_NUM, ACE_STR);
            rankMap.put(JACK_NUM, JACK_STR);
            rankMap.put(QUEEN_NUM, QUEEN_STR);
            rankMap.put(KING_NUM, KING_STR);

            this.setRankMap(rankMap);

            Map<String, Integer> scoreMap = new HashMap<>();
            scoreMap.put(ACE_STR, ACE_SCORE);
            scoreMap.put(JACK_STR, JACK_SCORE);
            scoreMap.put(QUEEN_STR, QUEEN_SCORE);
            scoreMap.put(KING_STR, KING_SCORE);

            this.setScoreMap(scoreMap);

            setDeck();

        }








    public static class Player{
        // the player class which will store player information.
        public String name;
        // stores the player's name.
        private int currentScore;
        // stores the score of the current game.
        private final ArrayList<Card> hand = new ArrayList<>();

        public Player() {
        }

        public String getName() {
            return name;
        }
        // returns the user's name
        public int getCurrentScore() {
            return currentScore;
        }
        // returns the score of the current game
        public List<Card> getHand() {
            return Collections.unmodifiableList(hand);
        }

        private void setName(String name) {
            this.name = name;
        }
        // sets the user's name
        private void setCurrentScore(int currentScore) {
            this.currentScore = currentScore;
        }
        // set's the game's current score

        public CardGame.Card getCard(int cardIndex) {
            return hand.get(cardIndex);
        }

        public void addCard2Hand(CardGame.Card c) {
            hand.add(c);
            setCurrentScore(getCurrentScore() + c.getScore());
        }

        public void displayFormattedHand() {
            for (int i = 0; i < hand.size(); i++) {
                System.out.println(hand.get(i).toString());
            }

        }

    public Player(String name) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        // It always asks to enter your name twice, and I am not sure why.
        String playerName = input.nextLine();
    setName(name);
}
    }

    public void determineOutcome(Player p, Player d) {
        // this will determine the winner of the game.
    }
}






