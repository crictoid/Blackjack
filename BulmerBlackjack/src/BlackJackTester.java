// Mark Bulmer - CSC 251 - Project 2 - Blackjack
// Couldn't quite get the name entry but worked out most of it.

import java.util.Scanner;

public class BlackJackTester {
// creates the main class, BlackJackTester
    public static void main(String[] args) {
        // ready to make our constant data types.
        final int CARDS_IN_DECK = 52;
        // sets the number of cards in the deck to the standard 52.
        final int FIRST_CARD_IN_HAND = 0;
        // value for the first card drawn
        final int SECOND_CARD_IN_HAND = 1;
        // value for the second card drawn
        final String CHOICE_HIT = "H";
        // allows the user to choose to hit and draw another card
        final String CHOICE_STAY = "S";
        // allows the user to choose to stay and refrain from drawing
        final int NUMBER_OF_SHUFFLES = 5;
        // an arbitrary number of times to randomize the deck.
        boolean playerHit;
        // has the player chosen to hit or not?
        boolean validChoice;
        // did the player choose "H" or "S"? If not, the entry is invalid.
        String choice;
        // stores the player's input on whether they hit or stayed.
        Scanner input = new Scanner(System.in);
        // obtains the input from the choice string

        System.out.println("\nWelcome to Black-Jack\n");

        BlackJack game1 = new BlackJack(CARDS_IN_DECK);
        // creates a new Blackjack game with 52 new cards.
        BlackJack.BlackJackPlayer player = game1.getPlayer();
        // creates a new player.
        BlackJack.BlackJackPlayer dealer = game1.getDealer();
        // creates a new dealer.
        for (int shuffles = 1; shuffles <= NUMBER_OF_SHUFFLES; shuffles++) {
            // Sets the amount to shuffle the deck a sufficient amount of times, five in our case.
            // If the desired number of shuffles hasn't been reached, it will shuffle again.
            game1.shuffleDeck();
            // Shuffles the deck.
        }
        game1.dealCard(player);
        // deals a card to the player.
        game1.dealCard(dealer);
        // deals a card to the dealer.

        System.out.println("\nThe " + dealer.getName() + "'s first card is: ");

        System.out.println(dealer.getCard(FIRST_CARD_IN_HAND));
        // prints the first card the the dealer drew.

        System.out.println("\nThe " + dealer.getName() + "'s second card is face-down.");

        game1.dealCard(player);
        // deals a card to the player.
        game1.dealCard(dealer);
        // deals a card to the dealer.

        showCurrentHand(player);
        // shows the player's hand
        showCurrentScore(player);
        // shows the player's score

        if (player.checkBust()) {
            playerHit = false;
            // If the player has busted, then the player cannot take a hit.
        }
        else {
            playerHit = true;
            // If the player hasn't busted, then they can hit.
        }

        while (playerHit) {
            // While the player is able to hit, do this:

            do {
                System.out.print("\nWould you like to Hit(H) or Stay(S): ");
                // Print a line asking whether the player wants to hit or stay.
                choice = input.nextLine().toUpperCase();
                // Check the input, converting all lowercase entries to uppercase for simplicity.

                if (!choice.equals(CHOICE_HIT) && !choice.equals(CHOICE_STAY)) {
                    validChoice = false;
                    // If they did not choose hit and did not choose stay, the choice isn't valid.
                }
                else {
                    validChoice = true;
                    // If they did choose to hit or stay, we have a valid choice.
                }

            } while (!validChoice);
            // If the choice is not valid.

            if (choice.equals(CHOICE_HIT)) {
                // If the choice was to hit.
                game1.dealCardWithHit(player);
                // deal a card to the player

                showCurrentHand(player);
                // show the player's hand
                showCurrentScore(player);
                // show the player's current score

                if (player.checkBust()) {
                    // If the player has busted.
                    playerHit = false;
                    // Then the player cannot take a hit.
                }
                else {
                    playerHit = true;
                    // If the player hasn't busted, they can take a hit.
                }
            }
            else {
                playerHit = false;
                // The player cannot take a hit except in the above circumstances.
            }
        }

        if (!player.isBust()) {
            // while the player hasn't busted
            System.out.println("\nThe " + dealer.getName() + "'s second card is: ");
            System.out.println(dealer.getCard(SECOND_CARD_IN_HAND));
            // print the dealer's second, previously face down card

            showCurrentScore(dealer);
            // print the dealer's score

            while (dealer.getCurrentScore() <= game1.DEALER_HIT_STAY_THRESHOLD){
                // If the dealer hasn't obtained their threshold (16), they will hit.
                game1.dealCardWithHit(dealer);
                // Hits the dealer.
            }
        }

        dealer.checkBust();
        // Check if the dealer has busted.

        System.out.print("\nAfter 'Hitting' " + dealer.getNumberHits() + " time(s),");
        // Shows the number of times the dealer hits.
        showCurrentHand(dealer);
        // Shows the dealer's hand.
        showCurrentScore(dealer);
        // Shows the dealer's current score.

        game1.determineOutcome(player, dealer);
        // Compares the scores of the player and dealer and determine who won.

        if (game1.getWinner() == BlackJack.Winner.DEALER)
            System.out.println("\nUnfortunately " + player.getName() + ", the " + dealer.getName() + " won this hand.");
        else if (game1.getWinner() == BlackJack.Winner.PLAYER)
            System.out.println("\n" + player.getName() + ", you have won this hand!");
        else
            System.out.println("\n" + player.getName() + ", you and the " + dealer.getName() + " have tied on this hand.");

        System.out.println("\n" + player.getName() + ", you 'Hit' " + player.getNumberHits() + " time(s).");
        System.out.println("\nThank you for playing!\n");
        // prints out the details of the game's outcome.

        input.close();
    }

    static void showCurrentHand(BlackJack.BlackJackPlayer p) {
        System.out.println("\n" + p.getName() + "'s current hand is:");
        p.displayFormattedHand();
    }
    // the method which prints the current hand for either the player or dealer.

    static void showCurrentScore(BlackJack.BlackJackPlayer p) {
        System.out.print("\n" + p.getName() + "'s current score is: ");
        System.out.println(p.getCurrentScore());
    }
    // the method which prints the current score for either the player or dealer.
}