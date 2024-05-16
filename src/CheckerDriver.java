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
					while(nameS.equals("computer") || nameS.length() < 3)
					{
							System.out.println("Error. Please do not enter in 'computer' as your name. And make sure your name is at least 3 characters long. Please try again and enter your name. ");
							nameS = sc.nextLine(); 
					}
		System.out.println("Alright, your name is " + nameS + ". ");
		System.out.println("Enter Favorite Number: ");
		int favNum = sc.nextInt();
		String userName = "Checker Player " + nameS.substring(0,3) + favNum; 
		System.out.println("Your username that will reference you is " + userName);
					Human h = new Human(userName, true); 
					CheckerBoard c = new CheckerBoard(); 
					Computer comp = new Computer(false);
					while (c.getWhiteScore() != 12 && c.getBlackScore() != 12) {
						h.playGame(c, d, comp, userName);
						System.out.print("..");
						comp.playGame(c, d);
					}
						System.out.print("hi");
						
	}
}
