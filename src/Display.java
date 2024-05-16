import jdk.dynalink.beans.StaticClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
* Display class to manage the actual GUI for checkers 
* Author: Grace Yan 
**/
public class Display implements ActionListener {
    private CheckerBoard cb = new CheckerBoard();
    private JButton[][] buttonList = new JButton[8][8];
    private ArrayList<JButton> checkerButtons = new ArrayList<>();
    private JFrame f = new JFrame();
    private Checker selected;
    private boolean turnOver;
    private Computer comp; 

    /**
     * Constructor for Display class 
     * Initializes the JFrame and JButtons 2D array (responsible for our checkerboard) 
     **/
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

        turnOver = false;
        comp = new Computer(false);
        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method that makes the JFrame visible 
     **/
    public void showBoard() {
        f.setVisible(true);
    }

    /**
     * Method that handles the human player's checker move on the display 
     **/
    public boolean handleMove(Checker c, int oldX, int oldY, int newX, int newY) {
        boolean valid = true;
        if (c != null) {
        	valid = cb.moveChecker(c, newX, newY);
        }
       // System.out.println(newX + " " + newY);
        // TODO implement this later
        if (valid) {
        	//System.out.println(newX + " " + newY);
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
        return valid;
    }

    /**
     * Overloaded method that handles the computer's checker move on the display 
     **/
    public void handleMoveComp(int formerX, int formerY, int newX, int newY) {
         handleMove(null, formerX, formerY, newX, newY);
    }

    /**
     * Performs actions when checker is selected by player
     */
    public void actionPerformed(ActionEvent e) {

        if (selected != null && !selected.getColor().equals("white")) {
            JButton button = (JButton) e.getSource();
            int x = button.getName().charAt(0);
            int y = button.getName().charAt(2);
            if (handleMove(selected, selected.getX(), selected.getY(), x, y)) {
            	selected = null;
            	int[] indices = comp.makeRandomMove(cb);
            	handleMoveComp(indices[0], indices[1], indices[2], indices[3]);
            }
            
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
                return;
            }
        }
        
        
    }

}
