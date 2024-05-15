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
	
	/**
	 * Method that prompts user for a checker move and moves the checker accordingly 
	 * 
	 * @param board - CheckerBoard
	 */
	public void playGame(CheckerBoard board) {
		// first step is to get the indices of piece to be moved 
		Checker toMove = startChecker(board); 
		
		// second step is to get the indices of where you want to move the piece 
		Scanner scan = new Scanner(System.in);
		System.out.print("\nTo where would you like to move this piece? Enter x-coordinate: ");
		int xMove = scan.nextInt(); 
		System.out.print("Enter y-coordinate: ");
		int yMove = scan.nextInt(); 
		// checks to see if the checker can be moved to the new location, loops until it can.
		regularMove(board, toMove, xMove, yMove);
		while (getTurn()) {
			System.out.print("\nIf you would like to change the checker you are moving, enter -1. \nOtherwise, please enter valid coordinates. Enter x-coordinate: "); 
			xMove = scan.nextInt(); 
			if (xMove == -1) {
				toMove = startChecker(board); 
				System.out.print("Your checker was successfully changed. You are now moving a piece from {" + toMove.getX() + ", " + toMove.getY() + "}.\nPlease enter the x-coordinate you would like to move to: ");
				xMove = scan.nextInt(); 
			}
			System.out.print("Enter y-coordinate: ");
			yMove = scan.nextInt(); 
			regularMove(board, toMove, xMove, yMove);
		} 
		System.out.print("Successfully moved to: {" + xMove + ", " + yMove + "} ");
	}
	
}
