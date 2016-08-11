import java.util.Scanner;

public class BlackJack {

    private static final int PLAYER = 1;
    private static final int DEALER = 2;
    private static final int PUSH = 3;

    private static final String HIT_CARD_MESSAGE = "Draw another card? (Y/N): \n";

    public Deck deck;
    public Scanner scanner;
    
    public int winner;
    public int playerScore;
    public int dealerScore;


    public BlackJack() {
        deck = new Deck();
        // TO-DO: initialize other instance variables
        this.playerScore = 0;
        this.dealerScore = 0;
        scanner = new Scanner(System.in);
    }
    
   /**
     * Takes a card from the deck and returns its rank
     * @param name of the person taking the card
     * @return the rank of the card
     */
    public String takeCard(String name) {
        Card card = deck.drawCard();
        printCard(card, name);
        return card.getRank();
    }
    
    /**
     * Prints the given card
     */
    public void printCard(Card card, String name) {
        System.out.println(name + ": " + card.getSuit() + " " + card.getRank());
    }

    
    public int getValue(String rank) {
        // TO-DO
        int value;
        if(rank.equals("King")||rank.equals("Queen")||rank.equals("Jack"))
            value = 10;
        else if(rank.equals("Ace"))
            value = 11;
        else
            value = Integer.valueOf(rank);
        return value;
    }
    
    public void initGame() {
        // TO-DO
        String playerrank1 = this.takeCard("Player");
        String dealerrank1 = this.takeCard("Dealer");
        String playerrank2 = this.takeCard("Player");
        String dealerrank2 = this.takeCard("Dealer");
        this.playerScore = this.getValue(playerrank1) + this.getValue(playerrank2);
        this.dealerScore = this.getValue(dealerrank1) + this.getValue(dealerrank2);
    }
    
    public boolean hitAnotherCard() {
        // TO-DO
        boolean prompt = true;
        boolean ifhit = true;
        do {
            System.out.printf(HIT_CARD_MESSAGE);
            String s = scanner.nextLine();
            if(s.equalsIgnoreCase("Y")){
                prompt = false;
                ifhit = true;
            }
            else if (s.equalsIgnoreCase("N")){
                prompt = false;
                ifhit = false;
            }
        }while(prompt);
        return ifhit;
    }
    
    public void playerHit() {
        // TO-DO
        String playerrank = this.takeCard("Player");
        this.playerScore = this.playerScore + this.getValue(playerrank);
    }
    
    public void dealerHit() {
        // TO-DO
        if(this.dealerScore < 17){
            String dealerrank = this.takeCard("Dealer");
            this.dealerScore = this.dealerScore + this.getValue(dealerrank);
        }
    }
    
    public boolean initScoreCheck() {
        // TO-DO
        boolean ifblackjack = false;

        if(this.dealerScore == 21){
            ifblackjack = true;
            this.winner = DEALER;
        }else if(this.playerScore == 21){
            ifblackjack = true;
            this.winner = PLAYER;
        }

        return ifblackjack;
    }
    
    public void checkRoundWinner() {
       // TO-DO
        if(this.playerScore > 21){
            this.winner = DEALER;
        }
        else if(this.dealerScore > 21){
            this.winner = PLAYER;
        }
        else if(this.dealerScore == this.playerScore){
            this.winner = PUSH;
        }else if((this.playerScore <= 21) && (this.playerScore > this.dealerScore)){
            this.winner = PLAYER;
        }else if((this.dealerScore <= 21) && (this.dealerScore > 21)){
            this.winner = PLAYER;
        }else
            this.winner = DEALER;
    }
    
    public void playRound() {
        // TO-DO
        System.out.println("Starting the round...");
        System.out.println("============");
        this.initGame();
        System.out.println("Player score: "+this.playerScore);
        System.out.printf("Dealer score: "+ this.dealerScore);
        if(!this.initScoreCheck()){
            boolean ifhit = this.hitAnotherCard();
            while(ifhit){
                this.playerHit();
                System.out.println("Player score: "+this.playerScore);
                if(this.playerScore > 21){
                    System.out.println("PLAYER BUST! End of the round");
                    System.out.println("DEALER WINS!");
                    System.out.println("Player score: "+this.playerScore);
                    System.out.printf("Dealer score: "+ this.dealerScore);
                    break;
                }else{
                    this.dealerHit();
                    System.out.printf("Dealer score: "+ this.dealerScore);
                    ifhit = this.hitAnotherCard();
                }
            }
            this.checkRoundWinner();
            System.out.println("Player score: "+ this.playerScore);
            System.out.println("Dealer score: "+ this.dealerScore);
            if(winner == 1)
                System.out.printf("PLAYER WINS!");
            else if(winner == 2)
                System.out.printf("Dealer WINS!");
            else if (winner == 3)
                System.out.printf("PUSH!");
        }else if(this.initScoreCheck()){
            System.out.println("Player score: "+ this.playerScore);
            System.out.println("Dealer score: "+ this.dealerScore);
            if(winner == 1)
                System.out.printf("PLAYER WINS!");
            else if(winner == 2)
                System.out.printf("Dealer WINS!");
            else if (winner == 3)
                System.out.printf("PUSH!");
        }
    }
    
   /**
     * Main method to execute a round of BlackJack
     */
    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.playRound();
    }
}