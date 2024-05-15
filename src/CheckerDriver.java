/**
 * CheckerDriver to scan in user input and set up game respectively. 
 * Author: Siya Arora
 **/

import java.util.Scanner;
public class CheckerDriver {
	public static void main(String[] args) {
		Display d = new Display(); 
        	d.showBoard();
		 Scanner sc = new Scanner(System.in); 
	        System.out.println("Enter player name. (Do not enter in name 'computer' for game to function): ");
					String nameS = sc.nextLine();
	        while(nameS.equals("computer"))
	        {
	            System.out.println("Error. Please do not enter in 'computer' as your name. Please try again and enter your name. ");
							nameS = sc.nextLine(); 
	        }
		System.out.println("Alright, your name is " + nameS + ". ");

	        Human h = new Human(nameS, true); 
	        CheckerBoard c = new CheckerBoard(); 
	        h.playGame(c); 
	        h.startChecker(c);
	}
}
