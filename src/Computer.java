import java.util.Random;

/**
 * Computer class to play checkers based on randomly generated valid moves. The Computer class extends the Player class.
 *
 * @author Sophia Zhang
 */

public class Computer extends Player {
    private Random rand;
    private final int BOARD_SIZE;
    private int startX;
    private int startY;
    private int newX;
    private int newY;

    /**
     * Constructor that sets Computer class
     *
     * @param turn - determines if it is the Computer's turn
     */
    public Computer(boolean turn) {
        super("Computer", turn);
        rand = new Random();
        BOARD_SIZE = 8;
    }

    /**
     * Method for the Computer to make a move
     *
     * @param board -- CheckerBoard
     */
    public int[] makeRandomMove(CheckerBoard board) {
        System.out.println("test");
        int x = rand.nextInt(8);
        int y = rand.nextInt(8);
        int[] indices = new int[4];
        int roundCount = 0;
        Checker toMove = board.getChecker(x, y);
        boolean found = false;

        while (!found) {
            while (toMove == null || board.isEmpty(x, y) || !toMove.getColor().equals("white")) {
                x = rand.nextInt(8);
                y = rand.nextInt(8);
                toMove = board.getChecker(x, y);
            }
            System.out.println(x + " " + y + toMove.getColor() + " " + toMove.getX() + " " + toMove.getY());
            if (board.moveChecker(toMove, x - 1, y + 1) ||
                    board.moveChecker(toMove, x + 1, y + 1) ||
                    board.moveChecker(toMove, x - 2, y + 2) ||
                    board.moveChecker(toMove, x + 2, y + 2)) {
                found = true;
            } else {
                toMove = null;
                roundCount++;
            }
            if (roundCount >= 64) {
                Display.setUserInput("Game over! The computer forfeits.");
                return indices;
            }
        }

        indices[0] = x;
        indices[1] = y;
        indices[2] = toMove.getX();
        indices[3] = toMove.getY();
        System.out.println("Indices: " + x + " " + y + " " + indices[2] + " " + indices[3]);
        return indices;
    }

    /**
     * Method that says that computer won
     */
    public void win() {
        super.win();
        Display.setUserInput(Display.getUserInput().getText() + " The computer won!");
    }

}