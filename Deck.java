import java.util.Random;

/**
 * Deck
 *
 * Represents a traditional deck of 52 cards
 *
 * @author Xuejun Zhao, zho630@purdue.edu
 * 
 * 
 */

public class Deck {

    private Card [] deck;

    private String[] suits = {"Club", "Diamond", "Heart", "Spade"};
    private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    /**
     * Creates a new deck and initialize it
     */
    public Deck() {
        deck = new Card [52];
        deckInit();
    }

    /**
     * Draw a card from the deck.
     * It selects the card randomly and "eliminates" it from the deck by setting to null
     * the corresponding entry in the Deck array.
     * @return the random card
     */
    public Card drawCard() {
        Random rand = new Random();

        while (true) {
            int pos = rand.nextInt(deck.length);
            if (deck[pos] == null)
                continue;
            else {
                Card ret = deck[pos];
                deck[pos] = null;
                return ret;
            }
        }
    }

    /**
     * Initialize the deck to the 52 cards,
     * one of each rank for each suit
     */
    public void deckInit() {

        int i = 0;
        for (String suit: suits) {
            for (String rank: ranks) {
                deck[i] = new Card(suit, rank);
                i += 1;
            }
        }
    }
}