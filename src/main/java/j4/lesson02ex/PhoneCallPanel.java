package j4.lesson02ex;

import javax.swing.*;
import java.awt.*;

public class PhoneCallPanel extends JFrame {
    public PhoneCallPanel(String title, int x, int y, int width, int height) {
        setTitle(title);
        setLocation(x, y);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        PhoneCallPanel frame = new PhoneCallPanel("PhoneCallPanel", 200, 200, 300, 300);
        frame.setLayout(new GridLayout(4, 3, 5, 5));

        Font font = new Font("Times New Roman", Font.BOLD, 24);
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(i + "");
            button.setFont(font);
            frame.add(button);
        }

        JButton button0 = new JButton("0");
        button0.setFont(font);
        frame.add(button0);

        // src/main/resources/
        ImageIcon callIcon = new ImageIcon("img/phoneCall.png");
        Image callIconScale = callIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton call = new JButton(new ImageIcon(callIconScale));
        frame.add(call);

        // src/main/resources/
        ImageIcon hangupIcon = new ImageIcon("img/phoneHangUp.png");
        Image hangUpIconScale = hangupIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton hangup = new JButton(new ImageIcon(hangUpIconScale));
        frame.add(hangup);

        frame.setVisible(true);
    }
}
