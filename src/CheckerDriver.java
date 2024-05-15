/**
 * CheckerDriver to scan in user input and set up game respectively. 
 * Author: Siya Arora
 **/
package Checkers;

import java.util.Scanner;
public class CheckerDriver {
	public static void main(String[] args) {
		Display d = new Display(); 
        d.showBoard();
		 Scanner sc = new Scanner(System.in); 
	       	String nameS; 
	        System.out.println("Enter player name. (Do not enter in name 'computer' for game to function): ");

	        while(sc.nextLine().equals("computer"))
	        {
	            System.out.println("Error. Please do not enter in 'computer' as your name. Please try again and enter your name. ");

	        }
	        nameS = sc.nextLine(); 

	        Human h = new Human(nameS, true); 
	        CheckerBoard c = new CheckerBoard(); 
	        playGame(c); 
	        startChecker(c);
}
}
