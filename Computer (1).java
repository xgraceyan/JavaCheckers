import java.util.Random;
/**
 * Computer class to play checkers based on randomly generated valid moves. The Computer class extends the Player class.
 * @author: Sophia Zhang
 **/

public class Computer extends Player {
  private Random rand;
  private final int BOARD_SIZE;
  
  /**
   * Constructor that sets Computer class 
   *  
   * @param turn - determines if it is the Computer's turn 
   */
  public Computer(boolean turn){
    super("Computer", turn);
    rand = new Random();
    BOARD_SIZE = 8;
  }

  /**
   * Method for the Computer to make a move 
   *  
   * @param board -- CheckerBoard 
   */
  public void makeMove(CheckerBoard board){
    //I saw startChecker in Yansy's Human class but didn't find it elsewhere... just double checking does the method exist/work? Do I need to code like a selectRandomChecker method? 
    Checker toMove = startChecker(board);

    if (toMove != null) {
        int startX = checker.getX();
        int startY = checker.getY();
        int endX = rand.nextInt(BOARD_SIZE);
        int endY = rand.nextInt(BOARD_SIZE);
    }
    //Next I need to add a while loop in above to continue regenerating random values of endX/endY until a valid combination is generated
    //Also need to of couse assign these values to the checker objects to move it and make the previous position null.
  }

  /**
   * Method for the Computer to determine if a move is   
   * valid before making it.
   *  
   * @param board -- CheckerBoard 
   */
  private boolean isValidMove(CheckerBoard board, int startX, int startY, int endX, int endY) {
    
    // Check if the start and end positions are within the bounds of the board
    if (startX < 0 || startY < 0 || startX >= BOARD_SIZE || startY >= BOARD_SIZE ||
        endX < 0 || endY < 0 || endX >= BOARD_SIZE || endY >= BOARD_SIZE) {
        return false; 
    }

    // Retrieve the checker at the start position
    Checker startChecker = board.getChecker(startX, startY);

    // Check if the start position contains a checker and if it belongs to the computer player
    if (startChecker == null || startChecker.isHuman()) {
        return false; 
    }

    // Check if the end position is empty
    if (!board.isEmpty(endX, endY)) {
        return false; 
    }

    //idk guys any other checks I need to add here?
    return true;
  }
  
  
  
}
