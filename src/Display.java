import jdk.dynalink.beans.StaticClass;

import javax.swing.*;
import java.awt.*;

public class Display {
    public static void main(String[] args) {
        CheckerBoard cb = new CheckerBoard();

        JFrame f = new JFrame();
        f.setLayout(new GridLayout(8, 8));
        JButton[] buttonList = new JButton[64];


        for (int i = 0; i < 64; i++) {
            buttonList[i] = new JButton();

            int x = i % 8;
            int y = i / 8;


            if (i / 8 % 2 == 1) {
                if (i % 2 == 0) buttonList[i].setBackground(Color.white);
                else buttonList[i].setBackground(Color.BLACK);
            } else {
                if (i % 2 == 1) buttonList[i].setBackground(Color.white);
                else buttonList[i].setBackground(Color.BLACK);
            }
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            for (Checker c : cb.getCheckerList()) {
                if (c.getX() == x && c.getY() == y) {
                    if (c.getColor().equals("white"))
                        buttonList[i].setBackground(Color.BLUE);
                    else
                        buttonList[i].setBackground(Color.RED);
                }
            }

            buttonList[i].setOpaque(true);
            buttonList[i].setBorderPainted(false);
            f.add(buttonList[i]);
        }


        f.setSize(700, 700);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
