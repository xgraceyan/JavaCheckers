/**
 * class to code Checker objects for CheckerBoard class
 * Author: Leka Ekambaram
 **/
public class Checker {
    private boolean isKing;
    private String color;
    private int x;
    private int y;

    /**
     * Constructor for the Checker class.
     *
     * @param color - checker color
     * @param xVar  - x-axis position of checker
     * @param yVar  - y-axis position of checker
     **/
    public Checker(String color, int xVar, int yVar) {
        isKing = false;
        this.color = color;
        x = xVar;
        y = yVar;
    }

    /**
     * Getter for the x-axis position of the checker.
     *
     * @return int - x-axis position of checker
     **/
    public int getX() {
        return x;
    }

    /**
     * Getter for the y-axis position of the checker.
     *
     * @return int - y-axis position of checker
     **/
    public int getY() {
        return y;
    }

    /**
     * Getter for the king state of the checker.
     *
     * @return boolean - if checker is king or not
     **/
    public boolean isKing() {
        return isKing;
    }

    /**
     * Getter for the color of the checker.
     *
     * @return String - color of checker black/white
     **/
    public String getColor() {
        return color;
    }

    /**
     * method that moves checker if coordinates given constitutes a valid move. otherwise, it prints an error message.
     *
     * @param newX - x-axis position of destination
     * @param newY - y-axis position of destination
     **/
    public void move(int newX, int newY) {
        if (x == 0 && newX == x + 1 && newY == y + 1) { //checks if checker is on the left border of checkerboard
            x = newX;
            y = newY;
            System.out.println("Moved to " + newX + ", " + newY);
        } else if (x == 7 && newX == x - 1 && newY == y + 1) { //checks if checker is on the right border of checkerboard
            x = newX;
            y = newY;
            System.out.println("Moved to " + newX + ", " + newY);
        } else if ((newX == x - 1 || newX == x + 1) && newY == y + 1) { //checks if move is valid for any other checker location
            x = newX;
            y = newY;
            System.out.println("Moved to " + newX + ", " + newY);
        } else {
            System.out.println("Invalid move. Try again with a different set of coordinates.");
        }
    }

    /**
     * setter that makes checker a king by setting isKing boolean as true
     **/
    public void setKing(boolean isKing) {
        this.isKing = isKing;
    }
}