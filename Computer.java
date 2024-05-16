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
       * Selects a random white checker piece from the checkerboard.
       *
       * @param board The checkerboard.
       * @return The selected checker, or null if no valid checker is found.
      */
      private Checker selectRandomChecker(CheckerBoard board) {
          int x = rand.nextInt(8);
          int y = rand.nextInt(8);
          Checker c = null;

          while( c == null || !c.getColor().equals("white") || !board.moveChecker(c, x, y) || board.isEmpty(x, y)){
              x = rand.nextInt(8);
              y = rand.nextInt(8);
              c = board.getChecker(x, y);
      }
          return c; 
  }

    /**
     * Method for the Computer to make a move 
     *  
     * @param board -- CheckerBoard 
     */
      public void makeRandomMove(CheckerBoard board){

          Checker toMove = selectRandomChecker(board);
          while (toMove == null) {
            toMove = selectRandomChecker(board);
          }
          int startX = toMove.getX();
          int startY = toMove.getY();
          int newX = rand.nextInt(8);
          int newY = rand.nextInt(8);

          while(!board.moveChecker(toMove, newX, newY)){
              newX = rand.nextInt(8);
              newY = rand.nextInt(8);
          }
          d.handleMove(toMove.getX(), toMove.getY(), newX, newY);
    }

  public boolean isValidMove(CheckerBoard board, int startX, int startY, int endX, int endY) {

    // Check if the start and end positions are within the bounds of the board
    if ((startX - 1 != endX || startX + 1 != endX) && startY - 1 != endY) {
        if ((startX + 2 == endX && board.getChecker(startX + 1, startY + 1).getColor().equals("black")) || (startX - 2 == endX && board.getChecker(startX - 1, startY + 1).getColor().equals("black")) && startY + 2 == endY) {
          return true;
        }
        else return false;
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
  public void playGame(CheckerBoard c, Display d) {
      if (d.getTurn()) {
          System.out.println("It is the computer's turn. Wait for the computer to make a move before playing.");
          makeRandomMove(c);
      }
  }
  
}
