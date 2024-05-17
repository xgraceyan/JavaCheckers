import java.util.Scanner;

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
     *
     * @param playerName
     */
    public Player(String playerName, boolean turn) {
        name = playerName; //player's name inputted (if computer, name will be "computer")
        isOnTurn = turn;
    }

    /**
     * Method to get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method for checking if player is human
     *
     * @return false if the player is a computer and true otherwise
     */
    public boolean isHuman() {
        return !name.equals("computer");
    }

    /**
     * Method that returns whether it is the player's turn
     *
     * @return true if it is the player's turn and false otherwise
     */
    public boolean getTurn() {
        return isOnTurn;
    }

    /**
     * Method that changes userInput to announce who won
     */
    public void win() {
        Display.setUserInput("Game over!");
    }

}
