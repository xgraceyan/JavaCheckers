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

    public Display() {
        f.setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 64; i++) {

            int x = i % 8;
            int y = i / 8;

            Color light_brown = new Color(241, 212, 154);
            Color dark_brown = new Color(135, 99, 41);

            if (cb.isEmpty(x, y)) {
                buttonList[x][y] = new JButton();
                if (i / 8 % 2 == 1) {
                    if (i % 2 == 0) buttonList[x][y].setBackground(dark_brown);
                    else buttonList[x][y].setBackground(light_brown);
                } else {
                    if (i % 2 == 1) buttonList[x][y].setBackground(dark_brown);
                    else buttonList[x][y].setBackground(light_brown);
                }
            } else {
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
    }

    public void showBoard() {
        f.setVisible(true);
    }

    public void handleMove(int newX, int newY) {
        // TODO implement this later
        JButton checkerButton = buttonList[selected.getX()][selected.getY()];
        JButton newButton = buttonList[newX][newY];
        System.out.println(checkerButton.getIcon());
        checkerButton.setBackground(newButton.getBackground());
        newButton.setIcon(checkerButton.getIcon());
        checkerButton.setIcon(null);
        buttonList[selected.getX()][selected.getY()] = newButton;
        buttonList[newX][newY] = checkerButton;
        cb.moveChecker(selected, newX, newY);
        selected = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (selected != null) {
            JButton button = (JButton) e.getSource();
            int x = button.getName().charAt(0);
            int y = button.getName().charAt(2);
            handleMove(x, y);
            return;
        }

        for (JButton checkerButton : checkerButtons) {
            if (checkerButton == e.getSource()) {
                // select a checker
                int x = checkerButton.getName().charAt(0);
                int y = checkerButton.getName().charAt(2);
                selected = cb.getChecker(x, y);
                System.out.println(selected.getColor());
                return;
            }
        }
    }
}
