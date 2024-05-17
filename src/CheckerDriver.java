/**
 * CheckerDriver to scan in user input and set up game respectively.
 * Author: Siya Arora
 **/

import java.util.Scanner;

public class CheckerDriver {
    /**
     * Driver method that initializes and starts the whole game
     *
     * @param args
     */
    public static void main(String[] args) {
        Display d = new Display();
        d.showBoard();
    }
}
