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
     * Method that appends the name of the Human who won to the original win method in Player class
     */
    public void win() {
        super.win();
        Display.setUserInput(Display.getUserInput().getText() + " " + super.getName() + " won!");
    }

}
