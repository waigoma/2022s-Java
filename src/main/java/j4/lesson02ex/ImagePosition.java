package j4.lesson02ex;

import javax.swing.*;
import java.awt.*;

public class ImagePosition extends JFrame {
    public ImagePosition(String title, int x, int y, int width, int height) {
        setTitle(title);
        setLocation(x, y);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        ImagePosition frame = new ImagePosition("ImagePosition", 200, 200, 600, 600);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        ImageIcon sakura1Icon = new ImageIcon("img/sakura1.jpg");
        JLabel sakura1 = new JLabel(sakura1Icon);
        frame.add(sakura1);

        Image sakura1IconScale = sakura1Icon.getImage().getScaledInstance((int) (sakura1Icon.getIconWidth() * (100f / sakura1Icon.getIconHeight())), 100, Image.SCALE_SMOOTH);
        JLabel sakura1n = new JLabel(new ImageIcon(sakura1IconScale));
        frame.add(sakura1n);

        ImageIcon sakura2Icon = new ImageIcon("img/sakura2.jpg");
        Image sakura2IconScale = sakura2Icon.getImage().getScaledInstance((int) (sakura2Icon.getIconWidth() * (100f / sakura2Icon.getIconHeight())), 100, Image.SCALE_SMOOTH);
        JLabel sakura2 = new JLabel(new ImageIcon(sakura2IconScale));
        frame.add(sakura2);

        ImageIcon sakura3Icon = new ImageIcon("img/sakura3.png");
        Image sakura3IconScale = sakura3Icon.getImage().getScaledInstance((int) (sakura3Icon.getIconWidth() * (100f / sakura3Icon.getIconHeight())), 100, Image.SCALE_SMOOTH);
        JLabel sakura3 = new JLabel(new ImageIcon(sakura3IconScale));
        frame.add(sakura3);

        frame.setVisible(true);
    }
}
