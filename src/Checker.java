/**
 * Class to code Checker objects for CheckerBoard class
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
     * setter that makes checker a king by setting isKing boolean as true
     **/
    public void setKing(boolean isKing) {
        this.isKing = isKing;
    }

    /**
     * Moves the checker to newX, newY
     *
     * @param newX - x coordinate of new location
     * @param newY - y coordinate of new location
     **/
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}
