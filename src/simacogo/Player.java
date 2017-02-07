package simacogo;

/**
 * An abstract class that is used to define the actions of a Player.  Player can be either Human or Computer.
 * @author Patrick Drucker
 */
public abstract class Player {
	public char piece;						// the piece that the player is using: 'X' or 'O'
	public Player opponent;					// opponent of this player
	
	/**
	 * Abstract method that returns the state that you get from dropping a piece in a specific column.
	 * @param currentState	the current state of the board
	 * @param columnNumber	the column number where the piece will be dropped
	 * @return 				the State resulting from dropping a piece into specified column
	 */
	abstract public State dropPiece(State currentState, int columnNumber);
	
	/**
	 * @return piece
	 */
	public char getPiece() {
		return piece;
	}
	
	/**
	 * @return opponent this Player's opponent
	 */
	public Player getOpponent() {
		return opponent;
	}
	
	/**
	 * @param opponent the opponent of this Player
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}
}
