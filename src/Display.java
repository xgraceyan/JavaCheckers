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
    private JPanel p = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel all = new JPanel();
    private JLabel welcome;
    private JTextField prompt;
    private JTextField prompt2;
    private static JTextField userInput;
    private Icon kingBlack;
    private Icon kingWhite;
    private JLabel humanScore;
    private JLabel compScore;
    private Checker selected;
    private Computer comp;
    private String name;
    private Human h;
    private boolean turnOver;
    private boolean nameEntered;
    private boolean started;
    private boolean reset;

    /**
     * Constructor for Display class
     * Initializes the JFrame and JButtons 2D array (responsible for our checkerboard)
     **/
    public Display() {
        String identity;
        nameEntered = false;
        started = false;
        reset = false;
        kingBlack = new ImageIcon("resources/black_king1.png");
        kingWhite = new ImageIcon("resources/white_king3.png");
        all.setLayout(new BoxLayout(all, BoxLayout.X_AXIS));
        p.setLayout(new GridLayout(8, 8));
        p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));

        prompt = new JTextField("Enter player name below.");
        prompt2 = new JTextField("For game to function, do not enter \"computer\".");
        userInput = new JTextField(15);
        prompt.setBounds(50, 50, 200, 100);
        prompt.setEditable(false);
        prompt2.setEditable(false);
        humanScore = new JLabel();
        humanScore.setVisible(false);
        compScore = new JLabel();
        compScore.setVisible(false);

        JPanel space = new JPanel();
        space.setBackground(new Color(238, 238, 238));
        space.setPreferredSize(new Dimension(400, 600));

        welcome = new JLabel("Welcome to Checkers!");
        welcome.setFont(new Font("calibri", Font.BOLD, 20));
        userInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = userInput.getText();
                if (input.length() < 3 && !nameEntered) {
                    prompt2.setText("Please make sure your player name is at least three characters long.");
                } else if (input.equalsIgnoreCase("computer") && !nameEntered) {
                    prompt2.setText("Error. Please do not enter in 'computer' as your name.");
                } else if (!nameEntered) {
                    name = input;
                    prompt.setText("Hello " + input + "! Please enter your favorite number.");
                    userInput.setText("");
                    prompt2.setVisible(false);
                    nameEntered = true;
                } else {
                    int favNum;
                    try {
                        favNum = Integer.parseInt(input);
                        String username = "HumanPlayer_" + name.substring(0, 3) + favNum;
                        prompt2.setVisible(true);
                        prompt.setText("Your username is: " + username);
                        prompt2.setText("Start the game by selecting a checker piece to move.");
                        userInput.setText("Next, click the spot you want the checker to move to.");
                        h = new Human(username, true);
                    } catch (Exception ex) {
                        userInput.setText("");
                        prompt.setText("Please enter a valid number.");
                    }
                }
            }
        });

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
            buttonList[x][y].setPreferredSize(new Dimension(100, 100));
            p.add(buttonList[x][y]);
        }

        turnOver = false;
        comp = new Computer(false);
        f.setSize(1200, 800);
        p.setSize(800, 800);
        p2.setSize(400, 800);
        p2.add(welcome);
        p2.add(prompt);
        p2.add(prompt2);
        p2.add(userInput);
        p2.add(humanScore);
        p2.add(compScore);
        p2.add(space);
        all.add(p);
        all.add(p2);
        f.add(all);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method that returns the boolean getTurn
     *
     * @return boolean getTurn - true if it is computer's turn, false otherwise
     */
    public boolean getTurn() {
        return turnOver;
    }

    /**
     * Method that returns the JTextField userInput
     *
     * @return userInput JTextField
     */
    public static JTextField getUserInput() {
        return userInput;
    }

    /**
     * Method that handles the human player's checker move on the display
     *
     * @return boolean - true if move has been handled successfully, false otherwise
     **/
    public boolean handleMove(Checker c, int oldX, int oldY, int newX, int newY) {
        boolean valid = true;
        if (c != null) {
            valid = cb.moveChecker(c, newX, newY);
        }
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
     * Method that makes the JFrame visible
     **/
    public void showBoard() {
        f.setVisible(true);
    }

    /**
     * Method that changes the icon of the Checker
     *
     * @param c - Checker that is a King checker and must be updated
     */
    public void changeIcon(Checker c) {
        String color = c.getColor();
        int x = c.getX();
        int y = c.getY();
        if (color.equals("white")) {
            buttonList[x][y].setIcon(kingWhite);
        } else if (color.equals("black")) {
            buttonList[x][y].setIcon(kingBlack);
        }
    }

    /**
     * Method that changes the text of the userInput JTextField
     *
     * @param str - String that the userInput will be changed to
     */
    public static void setUserInput(String str) {
        userInput.setText(str);
    }

    /**
     * Method that checks to see if the checkerboard contains any checkers that are new kings; updates their icon accordingly
     */
    public void checkForKing() {
        for (Checker checker : cb.getCheckerList()) {
            if (checker.isKing()) {
                changeIcon(checker);
            }
        }
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
            if (!started) {
                prompt2.setText("*Game ends when one player reaches a score of 12 or computer forfeits.");
                userInput.setText("");
            }
            JButton button = (JButton) e.getSource();
            int x = button.getName().charAt(0);
            int y = button.getName().charAt(2);
            if (handleMove(selected, selected.getX(), selected.getY(), x, y)) {
                userInput.setText("");
                selected = null;
                int[] indices = comp.makeRandomMove(cb);
                handleMoveComp(indices[0], indices[1], indices[2], indices[3]);
                humanScore.setText("Your score is: " + cb.getBlackScore());
                humanScore.setVisible(true);
                compScore.setText("Computer's score is: " + cb.getWhiteScore());
                compScore.setVisible(true);
                checkForKing();
                if (cb.getWhiteScore() == 12) {
                    comp.win();
                } else if (cb.getBlackScore() == 12) {
                    h.win();
                }
            } else {
                userInput.setText("Sorry, that move wasn't valid. Try again!");
                JButton srcButton = (JButton) e.getSource();
                int srcX = srcButton.getName().charAt(0);
                int srcY = srcButton.getName().charAt(2);

                for (JButton checkerButton : checkerButtons) {
                    int cbX = checkerButton.getName().charAt(0);
                    int cbY = checkerButton.getName().charAt(2);
                    if (cbX == srcX && cbY == srcY) {
                        // select a checker
                        selected = cb.getChecker(cbX, cbY);
                        userInput.setText("");
                        return;
                    }
                }
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
