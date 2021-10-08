import java.util.ArrayList;

/*
* Uses Depth first search to find a solution to a given game of Worm Hole.
*/
public class WHSolve {

    // Stores the Layout of the Game of Worm Hole
    protected BHLayout game;
    // The computed solution by the algorithm.
    // Will be empty if no solution could be computed.
    ArrayList<Integer> solution = new ArrayList<Integer>();
    // Stores the piles which still contian cards which need to be added to the hole.
    protected ArrayList<Integer> pilesRemaining = new ArrayList<Integer>();
    // Stores the totla number of cards remaining which still require to be added to the hole.
    protected int cardRemaining = 0;
    // Sotres the current card at the top of the hole.
    protected int holeCard;
    // Stores the current card in the worm hole.
    protected int wormholeCard = 0;
    /**
     * Uses depth first search to attempt to find a solution to a given game of Black hole.
     */
    public WHSolve(BHLayout game) {
        this.game = game;

        for (int i = 0; i < this.game.numPiles(); i++) {
            int pileSize = this.game.pileSize(i);
            // adds the totals of cards in each pile to an arraylist
            this.pilesRemaining.add(pileSize);
            // Cacluates the total number of cards required to be added to the hole.
            this.cardRemaining += pileSize;
        }

        // Gets the hole card.
        this.holeCard = this.game.holeCard();
    }

    public ArrayList<Integer> run() {
        explore();
        return this.solution;
    }

    public boolean explore() {
        for (int i = 0; i < this.game.numPiles(); i++) {
            // -1 to get the index of the ith card
            int cardsInPile = this.pilesRemaining.get(i) - 1;
            int card;
            if (cardsInPile < 0) {
                card = 0;
            }
            else {
                card = this.game.cardAt(i, cardsInPile);
            }
            if (card > 0) {

                if (this.game.adjacent(card, this.holeCard)) {
                    int currentHole = this.holeCard;
                    this.holeCard = card;
                    cardRemaining--;
                    this.pilesRemaining.set(i, this.pilesRemaining.get(i) - 1);
                    this.solution.add(i);
                    this.solution.add(card);
                    if (explore() == true) {
                        return true;
                    }
                    // Undo the changes
                    else {
                        this.holeCard = currentHole;
                        cardRemaining++;
                        this.pilesRemaining.set(i, this.pilesRemaining.get(i) + 1);
                        this.solution.remove(this.solution.size() -1);
                        this.solution.remove(this.solution.size() - 1);
                    }  
                }

                // Adding a card to worm hole.
                if (this.wormholeCard == 0) {
                    this.wormholeCard = card;
                    this.pilesRemaining.set(i, this.pilesRemaining.get(i) - 1);
                    this.solution.add(i);
                    this.solution.add(card * -1);
                    if (explore() == true) {
                        return true;
                    }
                    else {
                        wormholeCard = 0;
                        this.pilesRemaining.set(i, this.pilesRemaining.get(i) + 1);
                        this.solution.remove(this.solution.size() -1);
                        this.solution.remove(this.solution.size() - 1);
                    }
                }
            }
        }

        // In even there is a card in the Worm Hole
        if (this.wormholeCard != 0) {
            if (this.game.adjacent(this.wormholeCard, this.holeCard)) {
                int currentHole = this.holeCard;
                this.holeCard = this.wormholeCard;
                this.wormholeCard = 0;
                cardRemaining--;
                this.solution.add(-1);
                this.solution.add(holeCard);
                if (explore() == true) {
                    return true;
                }
                else {
                    this.wormholeCard = holeCard;
                    this.holeCard = currentHole;
                    cardRemaining++;
                    this.solution.remove(this.solution.size() -1);
                    this.solution.remove(this.solution.size() - 1);
                }
            }
        }

        if (cardRemaining == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}