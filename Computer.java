import java.util.Random;
/**
 * Computer class to play checkers based on randomly generated valid moves. The Computer class extends the Player class.
 * @author: Sophia Zhang
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
  public Computer(boolean turn){
    super("Computer", turn);
    rand = new Random();
    BOARD_SIZE = 8;
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
       Checker c = board.getChecker(x, y);

       while(c == null || board.isEmpty(x, y) || !c.getColor().equals("white")){
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
      public int[] makeRandomMove(CheckerBoard board){
    	  System.out.println("test");
    	  int x = rand.nextInt(8);
          int y = rand.nextInt(8);
          Checker toMove = board.getChecker(x, y);
          boolean found = false;
          
          while (!found) {
        	  while(toMove == null || board.isEmpty(x, y) || !toMove.getColor().equals("white")){
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
        	  }
          }
    	  int[] indices = new int[4];

          indices[0] = x;
          indices[1] = y;
          indices[2] = toMove.getX();
          indices[3] = toMove.getY();
          System.out.println("Indices: " + x + " " + y + " " + indices[2] + " " + indices[3]);
          return indices;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
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

}
