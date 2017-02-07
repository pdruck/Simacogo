package simacogo;

import java.io.IOException;

/**
 * Simacogo is basically a combination of Connect-Four and Go and played on a 9x9 grid. 
 * The players take turns dropping their respective pieces into a column (1-9).  2 points are given for two
 * of a player's pieces that are next to each other (up,down,left,right) and 1 point is given for two of a player's pieces
 * that are oriented diagonally (up and to the right, down and to the right, up and to the left, 
 * down and to the left). The game is over when the entire board is filled and the winner is the 
 * person with the most points.
 * Assignment 2 - Foundations of Artificial Intelligence at DePaul University - Jonathan Gemmell
 * @author Patrick Drucker
 */
public class Simacogo {
	
	/**
	 * Runs a Game of Simacogo between a Human and Computer Player.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		PrintGame print = new PrintGame();
		boolean finished = false;
		while(!finished) {
			finished = print.takeTurn();
		}
		print.printWinner();
	}
}
