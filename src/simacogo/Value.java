package simacogo;

/**
 * A final class that contain static functions that can evaluate a State and the score of the Game.
 * Cannot be instantiated.
 * @author Patrick Drucker
 */
public final class Value {
	private static final int ZERO = 0;				// zero score
	private static final double DIAGONAL = 0.5;		// .5 score for each diagonal (2 pieces diagonal from each other will be counted twice)
	private static final int NEXT_TO = 1;			// 1 score for each piece next to another (2 pieces next to each other will be counted twice)

	/**
	 * Private constructor to prevent instantiation of this class.
	 */
	private Value() {
	}
	
	/**
	 * Evaluates a Player's score for the minimax algorithm (scorePlayer - scoreOpponent).
	 * @param player 	the Player whose score is being evaluated
	 * @param state 	the current state of
	 * @return			the evaluated score of the current State
	 */
	public static double evaluate(Player player, State state) {
		return Value.playerScore(player, state) - Value.playerScore(player.getOpponent(), state);
	}
	
	/**
	 * Calculates the current score of a Player in a specific State.
	 * @param player	the Player whose score is being determined
	 * @param state		the current State of the Game.
	 * @return			the total score of a Player
	 */
	public static double playerScore(Player player, State state) {
		double playerScore = 0;
		for(int row = 0; row < state.getGrid().length; row++) {
			for(int col = 0; col < state.getGrid()[row].length; col++) {
				if(state.getGrid()[row][col] == player.getPiece()) {
					playerScore += score(player.getPiece(), state, row, col);
				}
			}
		}
		return playerScore;
	}
	
	/**
	 * Returns 1 if there is a common piece ABOVE the piece at grid[row][col]; Zero otherwise.
	 * @param piece		the piece being searched for ('X' or 'O')
	 * @param state		current State
	 * @param row		current row
	 * @param col		current column
	 * @return			1 or 0
	 */
	private static int upScore(char piece, State state, int row, int col) {
		if(row != 0 && state.getGrid()[row-1][col] == piece) {
			return NEXT_TO;
		}
		else {
			return ZERO;
		}
	}
	
	/**
	 * Returns .5 if there is a common piece UP and to the RIGHT of the piece at grid[row][col]; Zero otherwise.
	 * @param piece		the piece being searched for ('X' or 'O')
	 * @param state		current State
	 * @param row		current row
	 * @param col		current column
	 * @return			.5 or 0
	 */
	private static double upRightScore(char piece, State state, int row, int col) {
		if(row != 0 && col != state.getGrid()[row].length-1 && state.getGrid()[row-1][col+1] == piece) {
			return DIAGONAL;
		}
		else {
			return ZERO;
		}
	}
	
	/**
	 * Returns 1 if there is a common piece to the RIGHT of the piece at grid[row][col]; Zero otherwise.
	 * @param piece		the piece being searched for ('X' or 'O')
	 * @param state		current State
	 * @param row		current row
	 * @param col		current column
	 * @return			1 or 0
	 */
	private static int rightScore(char piece, State state, int row, int col) {
		if(col != state.getGrid().length-1 && state.getGrid()[row][col+1] == piece) {
			return NEXT_TO;
		}
		else {
			return ZERO;
		}
	}
	
	/**
	 * Returns .5 if there is a common piece DOWN and to the RIGHT of the piece at grid[row][col]; Zero otherwise.
	 * @param piece		the piece being searched for ('X' or 'O')
	 * @param state		current State
	 * @param row		current row
	 * @param col		current column
	 * @return			.5 or 0
	 */
	private static double downRightScore(char piece, State state, int row, int col) {
		if(row != state.getGrid().length-1 && col != state.getGrid()[row].length-1 && state.getGrid()[row+1][col+1] == piece) {
			return DIAGONAL;
		}
		else {
			return ZERO;
		}
	}
	
	/**
	 * Returns 1 if there is a common piece BELOW the piece at grid[row][col]; Zero otherwise.
	 * @param piece		the piece being searched for ('X' or 'O')
	 * @param state		current State
	 * @param row		current row
	 * @param col		current column
	 * @return			1 or 0
	 */
	private static int downScore(char piece, State state, int row, int col) {
		if(row != state.getGrid().length-1 && state.getGrid()[row+1][col] == piece) {
			return NEXT_TO;
		}
		else {
			return ZERO;
		}
	}
	
	/**
	 * Returns .5 if there is a common piece DOWN and to the LEFT of the piece at grid[row][col]; Zero otherwise.
	 * @param piece		the piece being searched for ('X' or 'O')
	 * @param state		current State
	 * @param row		current row
	 * @param col		current column
	 * @return			.5 or 0
	 */
	private static double downLeftScore(char piece, State state, int row, int col) {
		if(row != state.getGrid().length-1 && col != 0 && state.getGrid()[row+1][col-1] == piece) {
			return DIAGONAL;
		}
		else {
			return ZERO;
		}
	}
	
	/**
	 * Returns 1 if there is a common piece to the LEFT of the piece at grid[row][col]; Zero otherwise.
	 * @param piece		the piece being searched for ('X' or 'O')
	 * @param state		current State
	 * @param row		current row
	 * @param col		current column
	 * @return			1 or 0
	 */
	private static int leftScore(char piece, State state, int row, int col) {
		if(col != 0 && state.getGrid()[row][col-1] == piece) {
			return NEXT_TO;
		}
		else {
			return ZERO;
		}
	}
	
	/**
	 * Returns .5 if there is a common piece UP and to the LEFT of the piece at grid[row][col]; Zero otherwise.
	 * @param piece		the piece being searched for ('X' or 'O')
	 * @param state		current State
	 * @param row		current row
	 * @param col		current column
	 * @return			.5 or 0
	 */
	private static double upLeftScore(char piece, State state, int row, int col) {
		if(row != 0 && col != 0 && state.getGrid()[row-1][col-1] == piece) {
			return DIAGONAL;
		}
		else {
			return ZERO;
		}
	}
	
	/**
	 * Adds the calculated score of every direction into a total score.
	 * @param piece		the piece being searched for ('X' or 'O')
	 * @param state		current State
	 * @param row		current row
	 * @param col		current column
	 * @return			the total score
	 */
	private static double score(char piece, State state, int row, int col) {
		return upScore(piece, state, row, col) + upRightScore(piece, state, row, col) + rightScore(piece, state, row, col) +
				downRightScore(piece, state, row, col) + downScore(piece, state, row, col) + downLeftScore(piece, state, row, col) + 
				leftScore(piece, state, row, col) + upLeftScore(piece, state, row, col);
	}
}
