import java.util.ArrayList;

/*
* Determins if a proposed solution to a game of Black Hole is correct.
*/
public class BHCheck {

    // Stores the Layout of the Black Hole Game
    protected BHLayout game;
    // Stores the proposed solution to the game
    protected ArrayList<Integer> pSolution;
    // Stores the piles which still contain cards which need to be added to the hole.
    protected ArrayList<Integer> pilesRemaining = new ArrayList<Integer>();
    // Stores the total number of cards remaining which still require to be added to the hole.
    protected int cardRemaining = 0;
    // Stores the current card at the top of the hole.
    protected int holeCard;

    /**
     * Constructor for creating checker for Black Hole.
     * 
     * @param game the layout of the game being checked if solution is present.
     * @param pSolution the proposed solution being checked.
     */
    public BHCheck(BHLayout game, ArrayList<Integer> pSolution){
        this.game = game;
        this.pSolution = pSolution;

        for (int i = 0; i < this.game.numPiles(); i++) {
            int pileSize = this.game.pileSize(i);
            // adds the totals of cards in each pile to an arraylist
            this.pilesRemaining.add(pileSize);
            // Cacluates the total number of cards required to be added to the hole.
            this.cardRemaining += pileSize;
            this.pilesRemaining.add(this.game.pileSize(i));
        }

        // Gets the hole card.
        this.holeCard = this.game.holeCard();
    }

    /**
     * Runs the checker and determines if proposed solution is correct.
     */
    public boolean run() {
        try {
            // Runs over the proposed solution set
            for (int i = 0; i < this.pSolution.size(); i+=2) {
                // the current pill the solution is taking a card from
                int pile = this.pSolution.get(i);
                // the card the solution is attempting to move to the whole.
                int currentCard = this.pSolution.get(i + 1);

                // Checks that solutions card is at the top of it's pile in the game layout
                if (this.game.cardAt(pile, this.pilesRemaining.get(pile) - 1) != currentCard) {
                    return false;
                }
                // Checks the currentCard is adjacent to the current whole card.
                else if (!this.game.adjacent(currentCard, this.holeCard)) {
                    return false;
                }

                // reduces card remaining that are required to be added to the pile
                this.cardRemaining--;
                // adds the current card to the top of the hole.
                this.holeCard = currentCard;
                // reduces the amount of cards in the pile the card was taken from
                this.pilesRemaining.set(pile, this.pilesRemaining.get(pile) - 1);

                // indicates that no cards remain and solution has no more entires otherwise 
                if (this.cardRemaining == 0 && i + 2 >= this.pSolution.size()) {
                    return true;
                }
                // in the event that solution has entires remaining
                // indicating it is too long to be solution of this game.
                else if (this.cardRemaining == 0) {
                    return false;
                }
            }
            // In event solution lacks enough card to be solution
            if (this.cardRemaining > 1) {
                return false;
            }
            else {
                return true;
            }
        }
        // Array out of bounds has occured
        catch (Exception e) {
            return false;
        }
    }
}