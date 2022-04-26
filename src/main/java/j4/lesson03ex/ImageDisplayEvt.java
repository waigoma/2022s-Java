package j4.lesson03ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageDisplayEvt extends JPanel implements ActionListener {
    private static final ImageIcon sakura1Icon = new ImageIcon("img/sakura1.jpg");
    private static final ImageIcon sakura2Icon = new ImageIcon("img/sakura2.jpg");
    private static final ImageIcon sakura3Icon = new ImageIcon("img/sakura3.png");

    private static JLabel mainLabel;
    private static String commandName = "sakura1";

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon icon = sakura1Icon;

        if (mainLabel == null) mainLabel = new JLabel(icon);

        switch (commandName) {
            case "sakura1":
                icon = sakura1Icon;
                break;
            case "sakura2":
                icon = sakura2Icon;
                break;
            case "sakura3":
                icon = sakura3Icon;
                break;
        }

        Image iconScale = icon.getImage().getScaledInstance((int) (sakura1Icon.getIconWidth() * (375f / sakura1Icon.getIconHeight())), 375, Image.SCALE_SMOOTH);
        mainLabel.setIcon(new ImageIcon(iconScale));

        add(mainLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        commandName = e.getActionCommand();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ImageDisplayEvt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(200, 200);
        frame.setSize(640, 600);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        ImageDisplayEvt imageDisplayEvt = new ImageDisplayEvt();
        frame.add(imageDisplayEvt);

        Image sakura1IconScale = sakura1Icon.getImage().getScaledInstance((int) (sakura1Icon.getIconWidth() * (100f / sakura1Icon.getIconHeight())), 100, Image.SCALE_SMOOTH);
        JButton sakura1 = new JButton(new ImageIcon(sakura1IconScale));
        initButton(imageDisplayEvt, sakura1, "sakura1");
        frame.add(sakura1);

        Image sakura2IconScale = sakura2Icon.getImage().getScaledInstance((int) (sakura2Icon.getIconWidth() * (100f / sakura2Icon.getIconHeight())), 100, Image.SCALE_SMOOTH);
        JButton sakura2 = new JButton(new ImageIcon(sakura2IconScale));
        initButton(imageDisplayEvt, sakura2, "sakura2");
        frame.add(sakura2);

        Image sakura3IconScale = sakura3Icon.getImage().getScaledInstance((int) (sakura3Icon.getIconWidth() * (100f / sakura3Icon.getIconHeight())), 100, Image.SCALE_SMOOTH);
        JButton sakura3 = new JButton(new ImageIcon(sakura3IconScale));
        initButton(imageDisplayEvt, sakura3, "sakura3");
        frame.add(sakura3);

        frame.setVisible(true);
    }

    private static void initButton(ActionListener imageDisplayEvt, JButton button, String commandName) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        button.setActionCommand(commandName);
        button.addActionListener(imageDisplayEvt);
    }
}
