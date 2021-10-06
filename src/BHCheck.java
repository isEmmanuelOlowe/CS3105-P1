import java.util.ArrayList;


public class BHCheck {

    protected BHLayout game;
    protected ArrayList<Integer> pSolution;
    protected ArrayList<Integer> pilesRemaining = new ArrayList<Integer>();
    protected int cardRemaining;
    protected int holeCard;
    public BHCheck(BHLayout game, ArrayList<Integer> pSolution){
        this.game = game;
        this.pSolution = pSolution;
        for (int i = 0; i < this.game.numPiles(); i++) {
            this.pilesRemaining.add(this.game.pileSize(i));
        }
        //Since ace is already in the whole total - 1 cards in play
        this.cardRemaining = this.game.cardsInDeck() - 1;
        this.holeCard = this.game.holeCard();
    }

    public boolean run() {
        for (int i = 0; i < this.pSolution.size(); i+=2) {
            int j = i + 1;
            if (this.game.cardAt(this.pSolution.get(i), this.pilesRemaining.get(this.pSolution.get(i)) - 1) != this.pSolution.get(j)) {
                return false;
            }
            else if (!this.game.adjacent(this.pSolution.get(j), this.holeCard)) {
                return false;
            }
            this.cardRemaining--;
            this.holeCard = this.pSolution.get(j);
            this.pilesRemaining.set(this.pSolution.get(i), this.pilesRemaining.get(this.pSolution.get(i)) - 1);
            if (this.cardRemaining == 0) {
                return true;
            }
        }
        return true;
    }
}