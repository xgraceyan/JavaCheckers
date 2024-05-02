/**
 * CheckerDriver to scan in user input and set up game respectively. 
 * Author: Siya Arora
 **/
import java.util.Scanner;
public class CheckerDriver {
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in); 
	       	String nameS; 
	        System.out.println("Enter player name. (Do not enter in name 'computer' for game to function): ");

	        while(sc.nextLine().equals("computer"))
	        {
	            System.out.println("Error. Please do not enter in 'computer' as your name. Please try again and enter your name. ");

	        }
	        nameS = sc.nextLine(); 

	        Human h = new Human(nameS, true); 
	        Checkerboard c = new Checkerboard(); 
	        
	        h.start();  // Start the player's turn
	        c.initializeBoard();  // Initialize the checkerboard
	        c.displayBoard();  // Display the checkerboard
	        c.playGame();  // Start the game

}
}
