package j4.lesson02ex;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ImageDisplay extends JFrame {
    public ImageDisplay(String title, int x, int y, int width, int height) {
        setTitle(title);
        setLocation(x, y);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        ImagePosition frame = new ImagePosition("ImageDisplay", 200, 200, 400, 400);
        frame.setLayout(new BorderLayout());

        ArrayList<ImageIcon> images = new ArrayList<>(Arrays.asList(
                new ImageIcon("img/Red.png"),
                new ImageIcon("img/Green.png"),
                new ImageIcon("img/Blue.png"),
                new ImageIcon("img/Yellow.png"),
                new ImageIcon("img/Black.png")
        ));

        String inputStr = JOptionPane.showInputDialog("Center Color: r, g, b, y or bk?");
        int inputValue = 0;

        switch (inputStr) {
            case "r":
                inputValue = 0;
                break;
            case "g":
                inputValue = 1;
                break;
            case "b":
                inputValue = 2;
                break;
            case "y":
                inputValue = 3;
                break;
            case "bk":
                inputValue = 4;
                break;
            default:
                JOptionPane.showMessageDialog(null, inputStr + " is wrong input!\nPlease enter r, g, b, y or bk.\nRe-execute the program and input again.");
                System.exit(0);
        }

        ArrayList<ImageIcon> newImages = new ArrayList<>();

        for (int i = inputValue; i < images.size(); i++) {
            newImages.add(images.get(i));
        }

        for (int i = 0; i < inputValue; i++) {
            newImages.add(images.get(i));
        }

        frame.add(new JLabel(newImages.get(0)), BorderLayout.CENTER);
        frame.add(new JLabel(newImages.get(1)), BorderLayout.EAST);
        frame.add(new JLabel(newImages.get(2)), BorderLayout.SOUTH);
        frame.add(new JLabel(newImages.get(3)), BorderLayout.WEST);
        frame.add(new JLabel(newImages.get(4)), BorderLayout.NORTH);

        frame.setVisible(true);
    }
}
