import jdk.dynalink.beans.StaticClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Display implements ActionListener {
    private CheckerBoard cb = new CheckerBoard();
    private JButton[][] buttonList = new JButton[8][8];
    private ArrayList<JButton> checkerButtons = new ArrayList<>();
    private JFrame f = new JFrame();
    private Checker selected;
    private boolean turnOver;

    public Display() {
        f.setLayout(new GridLayout(8, 8));
        String identity;

        for (int i = 0; i < 64; i++) {

            int x = i % 8;
            int y = i / 8;

            Color light_brown = new Color(241, 212, 154);
            Color dark_brown = new Color(135, 99, 41);

            if (cb.isEmpty(x, y)) {
                identity = "tile";
                buttonList[x][y] = new JButton();
                if (i / 8 % 2 == 1) {
                    if (i % 2 == 0) buttonList[x][y].setBackground(dark_brown);
                    else buttonList[x][y].setBackground(light_brown);
                } else {
                    if (i % 2 == 1) buttonList[x][y].setBackground(dark_brown);
                    else buttonList[x][y].setBackground(light_brown);
                }
            } else {
                identity = "checker";
                Checker c = cb.getChecker(x, y);
                Icon blackIcon = new ImageIcon("resources/black_checker.png");
                Icon whiteIcon = new ImageIcon("resources/white_checker.png");

                if (c.getColor().equals("white"))
                    buttonList[x][y] = new JButton(whiteIcon);
                else
                    buttonList[x][y] = new JButton(blackIcon);
                checkerButtons.add(buttonList[x][y]);
            }


            buttonList[x][y].addActionListener(this);
            buttonList[x][y].setOpaque(true);
            buttonList[x][y].setBorderPainted(false);
            buttonList[x][y].setName((char) x + " " + (char) y);
            f.add(buttonList[x][y]);
        }


        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        turnOver = false;
    }

    public void showBoard() {
        f.setVisible(true);
    }

    public void handleMove(int newX, int newY) {
        int oldX = selected.getX();
        int oldY = selected.getY();
        boolean valid = cb.moveChecker(selected, newX, newY);
        // TODO implement this later
        if (valid) {
            JButton checkerButton = buttonList[oldX][oldY];
            JButton newButton = buttonList[newX][newY];

            if ((newY == oldY + 2 || newY == oldY - 2) && (newX == oldX + 2 || newX == oldX - 2)) {
                // capture move
                JButton capturedBtn = null;
                if (newY == oldY + 2) {
                    if (newX == oldX + 2) {
                        capturedBtn = buttonList[oldX + 1][oldY + 1];
                    } else if (newX == oldX - 2) {
                        capturedBtn = buttonList[oldX - 1][oldY + 1];
                    }
                } else if (newY == oldY - 2) {
                    if (newX == oldX + 2) {
                        capturedBtn = buttonList[oldX + 1][oldY - 1];
                    } else if (newX == oldX - 2) {
                        capturedBtn = buttonList[oldX - 1][oldY - 1];
                    }
                }

                capturedBtn.setIcon(null);
                capturedBtn.setBackground(newButton.getBackground());
                checkerButtons.remove(capturedBtn);
            }
            checkerButtons.remove(checkerButton);
            checkerButtons.add(newButton);
            checkerButton.setBackground(newButton.getBackground());
            newButton.setIcon(checkerButton.getIcon());
            checkerButton.setIcon(null);


        }
    }

    public void handleMove(int formerX, int formerY, int newX, int newY) {
        int oldX = formerX;
        int oldY = formerY;
        boolean valid = cb.moveChecker(selected, newX, newY);
        // TODO implement this later
        if (valid) {
            JButton checkerButton = buttonList[oldX][oldY];
            JButton newButton = buttonList[newX][newY];

            if ((newY == oldY + 2 || newY == oldY - 2) && (newX == oldX + 2 || newX == oldX - 2)) {
                // capture move
                JButton capturedBtn = null;
                if (newY == oldY + 2) {
                    if (newX == oldX + 2) {
                        capturedBtn = buttonList[oldX + 1][oldY + 1];
                    } else if (newX == oldX - 2) {
                        capturedBtn = buttonList[oldX - 1][oldY + 1];
                    }
                } else if (newY == oldY - 2) {
                    if (newX == oldX + 2) {
                        capturedBtn = buttonList[oldX + 1][oldY - 1];
                    } else if (newX == oldX - 2) {
                        capturedBtn = buttonList[oldX - 1][oldY - 1];
                    }
                }

                capturedBtn.setIcon(null);
                capturedBtn.setBackground(newButton.getBackground());
                checkerButtons.remove(capturedBtn);
            }
            checkerButtons.remove(checkerButton);
            checkerButtons.add(newButton);
            checkerButton.setBackground(newButton.getBackground());
            newButton.setIcon(checkerButton.getIcon());
            checkerButton.setIcon(null);


        }
    }

    public void actionPerformed(ActionEvent e) {
        
        if (selected != null && !selected.getColor().equals("white")) {
            JButton button = (JButton) e.getSource();
            int x = button.getName().charAt(0);
            int y = button.getName().charAt(2);
            handleMove(x, y);

            selected = null;
            return;
        }

        JButton srcButton = (JButton) e.getSource();
        int srcX = srcButton.getName().charAt(0);
        int srcY = srcButton.getName().charAt(2);

        for (JButton checkerButton : checkerButtons) {
            int cbX = checkerButton.getName().charAt(0);
            int cbY = checkerButton.getName().charAt(2);
            if (cbX == srcX && cbY == srcY) {
                // select a checker
                selected = cb.getChecker(cbX, cbY);
                System.out.println(selected.getColor());
                turnOver = true;
                return;
            }
        }

    }

    /**
    * Helper method for status of player's turn
    * @return boolean - true if it is the computer's turn, false if it is the human's turn
    **/
    public boolean getTurn() {
        return turnOver;
    }

    /**
    * sets the status of the player's turn
    **/
    public void setTurn(boolean turn) {
        turnOver = turn;
    }

}
