package simacogo;

/**
 * An extension of the Game class that has methods to get user input and print out the Game board, scores, and game winner.
 * @author Patrick Drucker
 */
public class HumanPlayer extends Player {
	/**
	 * Constructor for a HumanPlayer.  Initializes its piece to 'O'.
	 */
	public HumanPlayer() {
		piece = 'O';
	}
	
	/* (non-Javadoc)
	 * @see simicogo.Player#dropPiece(simicogo.State, int)
	 */
	@Override
	public State dropPiece(State currentState, int columnNumber) {
		return currentState.dropPiece(columnNumber-1);
	}
}
