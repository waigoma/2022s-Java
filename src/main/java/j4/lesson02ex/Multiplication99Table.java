package j4.lesson02ex;

import javax.swing.*;
import java.awt.*;

public class Multiplication99Table extends JFrame {
    public Multiplication99Table(String title, int x, int y, int width, int height) {
        setTitle(title);
        setLocation(x, y);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        Multiplication99Table frame = new Multiplication99Table("Multiplication99Table", 200, 200, 400, 400);
        frame.setLayout(new GridLayout(9, 9));

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                JLabel label = new JLabel(i * j + "", SwingConstants.CENTER);
                label.setFont(new Font("Times New Roman", Font.BOLD, 24));
                frame.add(label);
            }
        }

        frame.setVisible(true);
    }
}
