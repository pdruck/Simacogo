package simacogo;

/**
 * A class that represents a Game of Simicogo.
 * @author Patrick Drucker
 */
public class Game {
	static Player[] players;
	static char[] pieces;
	static int numPlies;
	State currentState;
	
	/**
	 * Creates a Game with a Human and Computer player.  Initializes Human to player0 and Computer to player1.
	 * Doesn't initialize numPlies.
	 */
	public Game() {
		players = new Player[2];
		pieces = new char[2];
		players[0] = new HumanPlayer();
		players[1] = new ComputerPlayer();
		pieces[0] = players[0].getPiece();
		pieces[1] = players[1].getPiece();
		currentState = new State();
		players[0].setOpponent(players[1]);
		players[1].setOpponent(players[0]);
	}
	
	/**
	 * Creates a Game with a Human and Computer player.  Initializes Human to player0 and Computer to player1.
	 * @param numPlies 	the number of plies that the computer should search through (depth of minimax algorithm)
	 */
	public Game(int numPlies) {
		//this = new Game();
		//Game.numPlies = numPlies;
		
		players = new Player[2];
		pieces = new char[2];
		players[0] = new HumanPlayer();
		players[1] = new ComputerPlayer();
		pieces[0] = players[0].getPiece();
		pieces[1] = players[1].getPiece();
		currentState = new State();
		players[0].setOpponent(players[1]);
		players[1].setOpponent(players[0]);
		Game.numPlies = numPlies;
	}
	
	/**
	 * @return pieces[0] 	the piece of the Player that will move first (Human)
	 */
	public static char getFirstMovePiece() {
		return pieces[0];
	}
	
	/**
	 * @return pieces
	 */
	public static char[] getPieces() {
		return pieces;
	}
	
	/**
	 * @return players
	 */
	public Player[] getPlayers() {
		return players;
	}
	
	/**
	 * @return players[0] 	the human player
	 */
	public static Player getHumanPlayer() {
		return players[0];
	}
	
	/**
	 * @return players[1] 	the computer player
	 */
	public static Player getComputerPlayer() {
		return players[1];
	}
	
	/**
	 * @return numPlies
	 */
	public static int getNumPlies() {
		return numPlies;
	}

	/**
	 * Sets the currentState to where the Human Player chooses to drop a piece.
	 * @param player 		the Player who is taking this turn (human)
	 * @param columnNumber 	the column where a piece will be dropped
	 */
	public void takeTurnHuman(Player player, int columnNumber) {
		currentState = player.dropPiece(currentState, columnNumber);
	}
	
	/**
	 * Sets the currentState to where the Computer chooses to drop a piece.
	 * @param player the Player who is taking this turn (computer)
	 */
	public void takeTurnComputer(Player player) {
		currentState = player.dropPiece(currentState, -1);
	}
	
	/**
	 * @return currentState
	 */
	public State getCurrentState() {
		return currentState;
	}
}
