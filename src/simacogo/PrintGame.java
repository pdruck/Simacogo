package simacogo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * An extension of the Game class that has methods to get user input and print out the Game board, scores, and game winner.
 * @author Patrick Drucker
 */
public class PrintGame extends Game {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * Constructor for PrintGame. Uses Game's default constructor and initializes the numPlies by retrieving user input.
	 * Also prints the empty board state.
	 * @throws IOException
	 */
	public PrintGame() throws IOException {
		super();
		getNumPlyInput();
		System.out.println("S I M A C O G O\n");
		printCurrentState();
	}

	/**
	 * Prints the current score of the game.
	 */
	private void printScore() {
		System.out.println("Human Score:\t" + (int)Value.playerScore(getHumanPlayer(), this.currentState));
		System.out.println("Computer Score:\t" + (int)Value.playerScore(getComputerPlayer(), this.currentState));
	}
	
	/**
	 * Prints the current State of the board.
	 */
	private void printCurrentState() {
		printScore();
		System.out.println("1 2 3 4 5 6 7 8 9");
		System.out.println(this.currentState);
	}
	
	/**
	 * Performs both the Human and Computer turns.  Returns whether the game has finished or not.
	 * @return finished		true if game has ended or quit, false otherwise
	 * @throws IOException
	 */
	public boolean takeTurn() throws IOException {
		boolean finished = false;
		boolean isTerminal = this.currentState.isTerminalState();
		int turnInput = getTurnInput();
		
		// if user wants to quit
		if(turnInput == -1) {
			System.out.print("\n");
			finished = true;
		}
		
		// Human turn
		if(!finished && !isTerminal) {
			this.takeTurnHuman(getHumanPlayer(), turnInput);
			printCurrentState();
		}
		else {
			finished = true;
		}
		
		// update isTerminal after human turn
		isTerminal = this.currentState.isTerminalState();
		
		// Computer turn
		if(!finished && !isTerminal) {
			this.takeTurnComputer(getComputerPlayer());
			printCurrentState();
		}
		else {
			finished = true;
		}
		
		return finished;
	}
	
	/**
	 * Gets user input on how many plies the computer should search in its minimax algorithm computation.
	 * Sets the numPlies instance variable.
	 * @throws IOException
	 */
	private void getNumPlyInput() throws IOException {
		String choice;
		char firstChar;
		boolean failed;
		
		do {
			failed = false;
			System.out.print("Please enter the number of plies the computer should search (1-10): ");
			choice = reader.readLine();
			if(choice.equals("")) {
				failed = true;
			}
			else {
				firstChar = choice.charAt(0);
				if(Character.isDigit(firstChar)) {
					numPlies = Character.getNumericValue(firstChar);
				}
				else {
					System.out.println("Please enter a digit as the number of plys.");
					failed = true;
				}
				if(!failed && numPlies > 10 || numPlies < 1) {
					System.out.println("Please enter a digit between 1 and 10 as the number of plys.");
					failed = true;
				}
			}
		} while(failed);
		System.out.print("\n");
	}
	
	/**
	 * Asks the user which column number they would like to drop their piece. Also gives the user the option to
	 * quit the game immediately. 
	 * @return col			the column number of which column the user wants to drop their piece; -1 to quit
	 * @throws IOException
	 */
	private int getTurnInput() throws IOException {
		int col = -1;
		String choice;
		char firstChar;
		boolean failed;
		
		do {
			failed = false;
			System.out.print("Enter a column # (QUIT to exit game): ");
			choice = reader.readLine().trim();
			if(choice.equals("")) {
				failed = true;
			}
			else {
				if(choice.toUpperCase().equals("QUIT")) {
					return -1;
				}
				else {
					firstChar = choice.charAt(0);
					
					if(Character.isDigit(firstChar) && firstChar != '0') {
						col = Character.getNumericValue(firstChar);
					}
					else {
						System.out.println("Please enter a digit (1-9) as the column #.");
						failed = true;
					}

					if(!failed && !this.currentState.columnIsAvailable(col)) {
						System.out.println("Column is full.");
						failed = true;
					}
				}
			}
		} while(failed);
		System.out.print("\n");
		return col;
	}
	
	/**
	 * Prints out the final score of the game and the winner of the game (or a draw).
	 * @throws IOException
	 */
	public void printWinner() throws IOException {
		reader.close();
		System.out.println("-----------------Game Over-----------------");
		System.out.println("                  (" + numPlies + "-ply)               ");
		printScore();
		int scoreHuman = (int)Value.playerScore(Game.getHumanPlayer(), this.currentState);
		int scoreComputer = (int)Value.playerScore(Game.getComputerPlayer(), this.currentState);
		if(scoreHuman > scoreComputer) {
			System.out.println("+-------------------------+");
			System.out.println("|      WINNER: Human      |");
			System.out.println("+-------------------------+");
		}
		else if(scoreComputer > scoreHuman) {
			System.out.println("+---------------------------+");
			System.out.println("|      WINNER: Computer     |");
			System.out.println("+---------------------------+");
		}
		else {
			System.out.println("+--------------------+");
			System.out.println("|\t DRAW\t     |");
			System.out.println("+--------------------+");
		}
	}
}
