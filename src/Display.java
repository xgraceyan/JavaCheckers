import jdk.dynalink.beans.StaticClass;

import javax.swing.*;
import java.awt.*;

public class Display {
    private CheckerBoard cb = new CheckerBoard();
    JFrame f = new JFrame();

    public Display() {
        f.setLayout(new GridLayout(8, 8));
        JButton[] buttonList = new JButton[64];


        for (int i = 0; i < 64; i++) {

            int x = i % 8;
            int y = i / 8;

            Color light_brown = new Color(241, 212, 154);
            Color dark_brown = new Color(135, 99, 41);

            if (cb.isEmpty(x, y)) {
                buttonList[i] = new JButton();
                if (i / 8 % 2 == 1) {
                    if (i % 2 == 0) buttonList[i].setBackground(dark_brown);
                    else buttonList[i].setBackground(light_brown);
                } else {
                    if (i % 2 == 1) buttonList[i].setBackground(dark_brown);
                    else buttonList[i].setBackground(light_brown);
                }
            } else {
                Checker c = cb.getChecker(x, y);
                Icon blackIcon = new ImageIcon("resources/black_checker.png");
                Icon whiteIcon = new ImageIcon("resources/white_checker.png");
                System.out.println(blackIcon.getIconHeight());
                if (c.getColor().equals("white"))
                    buttonList[i] = new JButton(whiteIcon);
                else
                    buttonList[i] = new JButton(blackIcon);
            }


            buttonList[i].setOpaque(true);
            buttonList[i].setBorderPainted(false);
            f.add(buttonList[i]);
        }


        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showBoard() {
        f.setVisible(true);
    }

    public void handleMove(Checker c, int newX, int newY) {
        // TODO implement this later
    }
}
