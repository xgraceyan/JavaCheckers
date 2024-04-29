import java.util.ArrayList;

/**
 * CheckerBoard class to store & manage all the checkers in a checkerboard
 * Author: Grace Yan
 **/
public class CheckerBoard {
    private ArrayList<Checker> checkers = new ArrayList<>();
    private int whiteScore = 0;
    private int blackScore = 0;

    /**
     * Constructor for the Checkers class.
     * Initializes pieces and sets the board up for a new game
     **/
    public CheckerBoard() {
        int count = 0; // total number of checkers added
        // add black (player-side) checkers
        for (int i = 0; i <= 23; i += 2) {
            int x = i % 8;
            int y = i / 8;
            if (y % 2 == 1) x++;
            checkers.add(count, new Checker("black", x, y));
            count++;
        }

        // add white checkers
        for (int j = 40; j < 64; j += 2) {
            int x = j % 8;
            int y = j / 8;
            if (y % 2 == 1) x++;
            checkers.add(count, new Checker("white", x, y));
            count++;
        }
    }

    public ArrayList<Checker> getCheckerList() {
        return checkers;
    }

    /**
     * Get a checker object in the board based on its coordinates.
     *
     * @param x - x position of checker
     * @param y - y position of checker
     * @return Checker - the checker object at x,y
     **/
    public Checker getChecker(int x, int y) {
        for (Checker checker : checkers) {
            if (checker.getX() == x && checker.getY() == y) return checker;
        }
        return null;
    }

    /**
     * Check if a checker occupies a space on the board
     *
     * @param x - x position to check
     * @param y - y position to check
     * @return boolean - if the space is empty or not
     **/
    public boolean isEmpty(int x, int y) {
        for (Checker checker : checkers) {
            if (checker.getX() == x && checker.getY() == y) return false;
        }
        return true;
    }

    /**
     * Move a checker on the board to another spot
     *
     * @param c    - Checker object to move
     * @param newX - x position to move to
     * @param newY - y position to move to
     * @return boolean - whether the move was valid or not
     **/
    public boolean moveChecker(Checker c, int newX, int newY) {
        int x = c.getX();
        int y = c.getY();

        if (!isEmpty(newX, newY)) return false;
        if (newX > 7 || newY > 7) return false;

        if (!c.isKing()) {
            // regular move
            if ((newX == x + 1 || newX == x - 1) && newY == y + 1) {
                c.move(newX, newY);
                if (newY == 0 || newY == 7) c.setKing(true); // make end piece king
                return true;
            }
            // jump move
            if (newY == y + 2) {
                Checker existing = null;
                if (newX == x + 2) {
                    existing = getChecker(newX - 1, newY - 1);
                } else if (newX == x - 2) {
                    existing = getChecker(newX + 1, newY - 1);
                }
                if (existing != null) {
                    if (existing.getColor().equals("white")) blackScore++;
                    else whiteScore++;
                    checkers.remove(existing);
                    c.move(newX, newY);
                    if (newY == 0 || newY == 7) c.setKing(true); // make end piece king
                    return true;
                }
            }

        } else {
            // Checker is king - can move forwards & backwards
            // regular move
            if ((newX == x + 1 || newX == x - 1) && (newY == y + 1 || newY == y - 1)) {
                c.move(newX, newY);
                return true;
            }
            // jump move
            if (newY == y + 2) {
                Checker existing = null;
                if (newX == x + 2) {
                    existing = getChecker(newX - 1, newY - 1);
                } else if (newX == x - 2) {
                    existing = getChecker(newX + 1, newY - 1);
                }
                if (existing != null) {
                    if (existing.getColor().equals("white")) blackScore++;
                    else whiteScore++;
                    checkers.remove(existing);
                    c.move(newX, newY);
                    if (newY == 0 || newY == 7) c.setKing(true); // make end piece king
                    return true;
                }
            } else if (newY == y - 2) {
                Checker existing = null;
                if (newX == x + 2) {
                    existing = getChecker(newX - 1, newY + 1);
                } else if (newX == x - 2) {
                    existing = getChecker(newX + 1, newY + 1);
                }
                if (existing != null) {
                    if (existing.getColor().equals("white")) blackScore++;
                    else whiteScore++;
                    checkers.remove(existing);
                    c.move(newX, newY);
                    if (newY == 0 || newY == 7) c.setKing(true); // make end piece king
                    return true;
                }
            }
        }

        return false;
    }
}
