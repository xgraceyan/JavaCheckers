import java.util.Scanner;

/**
 * Human class that extends the Player class 
 * 
 * @author - Yansy Ngai 
 */
public class Human extends Player {
	
	/**
	 * Constructor that sets Human class 
	 * 
	 * @param name of the Human player 
	 * @param turn - determines if it is Human's turn 
	 */
	public Human(String name, boolean turn) {
		super(name, turn);
	}

	public void playGame(CheckerBoard c, Display d, Computer comp, String userName) {
			if ((c.getWhiteScore() != 12 || c.getBlackScore() != 12) && !d.getTurn()) {
					System.out.println("It is " + userName + "'s turn. ");
			}
	}
	
}
