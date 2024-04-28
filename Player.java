/**
 * Player class that will be extended for Human and Computer classes
 * Author: Leka Ekambaram
 */
public class Player {

	private String name;
	private boolean isOnTurn;
	
	/**
	 * Player constructor
	 * Initializes player's name and if it is their turn
	 * @param playerName
	 */
	public Player(String playerName, boolean turn) {
		name = playerName; //player's name inputted (if computer, name will be "computer")
		isOnTurn = turn;
	}
	
	/**
	 * Method for checking if player is human
	 * @return false if the player is a computer and true otherwise
	 */
	public boolean isHuman() {
		return !name.equals("computer");
	}
	
	/**
	 * moves checker to designated index. If move isn't valid, it'll still be the player's turn
	 * @param c
	 * @param newX
	 * @param newY
	 */
	public void regularMove(Checker c, int newX, int newY) {
		isOnTurn = !moveChecker(c, newX, newY); //turn ends if move is made
	}
	
	/**
	 * moves checker and captures other checker. If a double jump is possible, it will perform it.
	 * @param c - Checker object to move
	 * @param o - Checker object to be captured
	 */
	public void attackingMove(Checker c, Checker o) {
		int otherX = o.getX();
		int otherY = o.getY();
		int moveX = 0;
		if (otherY == c.getY() + 1) {//sets index based on o's x-index
			if (otherX == c.getX() + 1) {
				moveX = otherX + 1;
			}
			else if (otherX == c.getX() - 1) {
				moveX = otherX - 1;
			}
			if (moveX != 0) {
				moveChecker(c, moveX, otherY + 1);
			}
			
		}
		//if a double jump is possible
		if (getChecker(c.getX() + 1, c.getY() + 1) != null) {
			attackingMove(c, getChecker(c.getX() + 1, c.getY() + 1));
		}
		else if (getChecker(c.getX() - 1, c.getY() + 1) != null) {
			attackingMove(c, getChecker(c.getX() - 1, c.getY() + 1));
		}
	}
}
