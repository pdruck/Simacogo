package simacogo;

/**
 * A final class that utilizes the minimax algorithm to create a tree of States, evaluate the "score" of the leaf nodes, and send up
 * the best score and resulting state that the player can choose.
 * Cannot be instantiated.
 * @author Patrick Drucker
 */
public final class Minimax {
	static State resultingState;			// the resultant "best possible state"
	
	/**
	 * Private constructor to prevent instantiation.
	 */
	private Minimax() {
	}
	
	/**
	 * Recursive implementation of the minimax algorithm.
	 * Evaluates the leaf nodes (at numPlies depth), and sends that value up the tree.
	 * If currently in a maximizing State, will return the highest value(best score for max).
	 * If currently in a minimizing State, will return the lowest value (best score for min).  
	 * @param state		the current State that is being evaluated
	 * @param numPlies	the depth of the tree
	 * @param isMax		true if this is a maximizing state
	 * @param player	the Player making use of the minimax algorithm
	 * @return bestValue
	 */
	public static double minimax(State state, int numPlies, boolean isMax, Player player) {	
		State bestState = null;
		
		if(numPlies == 0 || state.isTerminalState()) {
			return Value.evaluate(player, state);
		}
		
		if(isMax) {
			double current = 0;
			double bestValue = Double.NEGATIVE_INFINITY;
			for(State adj : state.successors()) {
				current = minimax(adj, numPlies-1, false, player);
				if(current > bestValue) {
					bestValue = current;
					bestState = adj;
				}
			}
			resultingState = bestState;
			return bestValue;
		}
		else {
			double current = 0;
			double bestValue = Double.POSITIVE_INFINITY;
			for(State adj : state.successors()) {
				current = minimax(adj, numPlies-1, true, player);
				if(current < bestValue) {
					bestValue = current;
					bestState = adj;
				}
			}
			resultingState = bestState;
			return bestValue;
		}
	}
	
	/**
	 * @return resultingState
	 */
	public static State getResultingState() {
		return resultingState;
	}
}
