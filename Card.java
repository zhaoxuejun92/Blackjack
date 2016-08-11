/**
 * Card
 *
 * Represents a card in a traditional 52 deck 
 *
 * @author Xuejun Zhao, zhao630@purdue.edu
 * 
 */
public class Card {

    private String suit;
    private String rank;

    /**
     * Creates a new card of the given suit and rank
     * @param suit of the card
     * @param rank of the card
     */
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Gets the rank of the card
     * @return the rank of the card
     */
    public String getRank() {
        return this.rank;
    }

    /**
     * Gets the suit of the card
     * @return the suit of the card
     */
    public String getSuit() {
        return this.suit;
    }
}