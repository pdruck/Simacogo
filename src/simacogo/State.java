package simacogo;

import java.util.ArrayList;

/**
 * A class that represents the State of a game of Simacogo.
 * @author Patrick Drucker
 */
public class State {
	private static final int ROWS = 9;					// number of rows in the grid
	private static final int COLUMNS = 9;				// number of columns in the grid
	public char[][] grid = new char[ROWS][COLUMNS];		// state of the grid
	char nextMovePiece;									// the piece of the Player who has the next move
	int turnNumber;										// turn number
	int columnDropped;									// the column a piece was dropped in to get to this State
	
	/**
	 * Constructor that initializes a 9x9 Simicogo grid with all '.'.
	 */
	public State() {
		columnDropped = -1;
		nextMovePiece = Game.getFirstMovePiece();
		turnNumber = 0;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				grid[row][col] = '.';
			}
		}
	}
	
	/**
	 * Copy constructor for a State.
	 * @param state 	the State to be copied into this State.
	 */
	public State(State state) {
		this.columnDropped = state.columnDropped;
		this.nextMovePiece = state.nextMovePiece;
		this.turnNumber = state.turnNumber;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				this.grid[row][col] = state.grid[row][col];
			}
		}
	}
	
	/**
	 * Constructor that creates a new State out of a given grid.
	 * @param grid
	 */
	private State(char[][] grid) {
		columnDropped = -1;
		this.nextMovePiece = Game.getFirstMovePiece();
		this.turnNumber = 0;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				this.grid[row][col] = grid[row][col];
			}
		}
	}
	
	/**
	 * Returns whether this state is a game-ending state (all spots in the grid have either an 'X' or an 'O') or not.
	 * @return true 	if this grid is in a game-ending state; false otherwise
	 */
	public boolean isTerminalState() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (grid[i][j] == '.') {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Successor function for States.  Returns an ArrayList of States that contain all States that can be reached
	 * from this State.
	 * @return nextStates 	a list of possible States that are reachable from this State
	 */
	public ArrayList<State> successors() {
		ArrayList<State> nextStates = new ArrayList<State>();
		State nextState;
		for(int col = 0; col < COLUMNS; col++) {
			nextState = dropPiece(col);
			if(nextState != null) {
				nextStates.add(nextState);
				nextState.columnDropped = col;
			}
		}
		return nextStates;
	}
	
	/**
	 * Drops a piece into a specified column number and returns the resulting State.
	 * @param columnNumber 	the column that a piece will be dropped into
	 * @return nextState 	the new State if a piece were to be dropped in columnNumber; null if column is full
	 */
	public State dropPiece(int columnNumber) {
		State nextState = new State(this.grid);
		int numAvailableSpace = numAvailableSpaces(columnNumber);
		if(numAvailableSpace != 0) {
			nextState.grid[numAvailableSpace-1][columnNumber] = nextMovePiece;
			nextState.turnNumber = this.turnNumber + 1;
			nextState.nextMovePiece = Game.getPieces()[nextState.turnNumber % 2];		// changes the nextState piece depending on turnNumber (odd or even)
		}
		else {
			nextState = null;
		}
		return nextState;
	}
	
	/**
	 * Returns an integer that represents the number of available spaces in a column.
	 * @param columnNumber 	a column number
	 * @return the number 	of available spaces in a specified column number
	 */
	public int numAvailableSpaces(int columnNumber) {
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				if(col == columnNumber && row == 0 && grid[row][col] != '.') {
					return 0;
				}
				else if(col == columnNumber && isOccupied(row, col)) {
					return row;
				}
			}
		}
		return ROWS;
	}
	
	/**
	 * Checks whether these coordinates in the grid (row, column) are already occupied or not.
	 * @param row 		row number of the grid
	 * @param column 	column number of the grid
	 * @return true 	if the position of this spot in the grid is occupied; false otherwise
	 */
	private boolean isOccupied(int row, int column) {
		if(grid[row][column] != '.') {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a column would be available to drop another piece.
	 * @param columnNumber 	number of a column
	 * @return false 		if the column is full; true otherwise
	 */
	public boolean columnIsAvailable(int columnNumber) {
		if(isOccupied(0, columnNumber-1)) {
			return false;
		}
		return true;
	}

	/**
	 * @return grid
	 */
	public char[][] getGrid() {
		return grid;
	}
	
	/**
	 * @return columnDropped
	 */
	public int getColumnDropped() {
		return columnDropped;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "";
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				s += grid[row][col] + " ";
			}
			s += "\n";
		}
		return s;
	}
}
