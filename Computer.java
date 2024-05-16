import java.util.Random;
/**
 * Computer class to play checkers based on randomly generated valid moves. The Computer class extends the Player class.
 * @author: Sophia Zhang
 **/

public class Computer extends Player {
  private Random rand;
  
  /**
   * Constructor that sets Computer class 
   *  
   * @param turn - determines if it is the Computer's turn 
   */
    public Computer(boolean turn){
        super("Computer", turn);
        rand = new Random();
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

        while( c == null || !c.getColor().equals("white") || !board.moveChecker(x, y, c) || board.isEmpty(x, y)){
            x = random.nextInt(8);
            y = random.nextInt(8);
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
        if (toMove != null) {
          int startX = checker.getX();
          int startY = checker.getY();
        }
        int newX = rand.nextInt(8);
        int newY = rand.nextInt(8);

        while(!board.moveChecker(toMove, newX, newY)){
            newX = rand.nextInt(8);
            newY = rand.nextInt(8);
        }
        moveChecker(toMove, newX, newY);
  }
}