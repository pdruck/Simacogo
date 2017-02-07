package simacogo;

/**
 * A class for a Computer Player.  Contains a dropPiece method that makes use of the minimax algorithm.
 * @author Patrick Drucker
 */
public class ComputerPlayer extends Player {
	/**
	 * Constructor for a ComputerPlayer.  Initializes its piece to 'X'.
	 */
	public ComputerPlayer() {
		piece = 'X';
	}
	
	/* (non-Javadoc)
	 * @see simicogo.Player#dropPiece(simicogo.State, int)
	 */
	@Override
	public State dropPiece(State currentState, int columnNumber) {
		Minimax.minimax(currentState, Game.getNumPlies(), true, this);
		return Minimax.getResultingState();
	}
}
