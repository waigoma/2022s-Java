package j4.lesson02ex;

import javax.swing.*;
import java.awt.*;

public class FrameFive extends JFrame {
    public FrameFive(String title, int x, int y, int width, int height, Color color) {
        setTitle(title);
        setLocation(x, y);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(color);

        setVisible(true);
    }

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        int width = screenWidth / 3;
        int height = screenHeight / 3;

        new FrameFive("Frame1", 0, 0, width, height, Color.YELLOW);
        new FrameFive("Frame2", width * 2, 0, width, height, Color.GREEN);

        new FrameFive("Frame3", width, height, width, height, Color.RED);

        new FrameFive("Frame4", 0, height * 2, width, height, Color.BLUE);
        new FrameFive("Frame5", width * 2, height * 2, width, height, Color.BLACK);
    }
}
