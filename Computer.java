import java.util.Random;
/**
 * Computer class to play checkers based on randomly generated valid moves. The Computer class extends the Player class.
 * @author: Sophia Zhang
 */

public class Computer extends Player {
  private Random rand;
  private final int BOARD_SIZE;
  private Display d;
  
  /**
   * Constructor that sets Computer class 
   *  
   * @param turn - determines if it is the Computer's turn 
   */
  public Computer(boolean turn){
    super("Computer", turn);
    rand = new Random();
    BOARD_SIZE = 8;
    d = new Display();
  }

  /**
   * Method for the Computer to make a move 
   *  
   * @param board -- CheckerBoard 
   */
  public void makeMove(CheckerBoard board){
    Checker toMove = board.getChecker(rand.nextInt(BOARD_SIZE), rand.nextInt(BOARD_SIZE));
    while (board.isEmpty(toMove.getX(), toMove.getY())) {
      toMove = board.getChecker(rand.nextInt(BOARD_SIZE), rand.nextInt(BOARD_SIZE));
    }
        int startX = toMove.getX();
        int startY = toMove.getY();
        int endX = rand.nextInt(BOARD_SIZE);
        int endY = rand.nextInt(BOARD_SIZE);
        while (!board.isEmpty(toMove.getX(), toMove.getY())) {
          endX = rand.nextInt(BOARD_SIZE);
          endY = rand.nextInt(BOARD_SIZE);
        }
        if (!isValidMove(board, startX, startY, endX, endY)) {
          return makeMove(board);
        }
        d.handleMove(startX, startY, endX, endY);
  }
    
public boolean isValidMove(CheckerBoard board, int startX, int startY, int endX, int endY) {
    
    // Check if the start and end positions are within the bounds of the board
    if ((startX - 1 != endX || startX + 1 != endX) && startY - 1 != endY) {
        if ((startX + 2 == endX && board.getChecker(startX + 1, startY + 1).getColor().equals("black")) || (startX - 2 == endX && board.getChecker(startX - 1, startY + 1).getColor().equals("black")) && startY + 2 == endY) {
          return true;
        }
        else return false;
    }
    return true;
  }

    // Retrieve the checker at the start position
    Checker startChecker = board.getChecker(startX, startY);

    // Check if the start position contains a checker and if it belongs to the computer player
    if (startChecker == null || super.isHuman()) {
        return false; 
    }

    // Check if the end position is empty
    if (!board.isEmpty(endX, endY)) {
        return false; 
    }

    //idk guys any other checks I need to add here?
    return true;
  }
  
  */
  
}
