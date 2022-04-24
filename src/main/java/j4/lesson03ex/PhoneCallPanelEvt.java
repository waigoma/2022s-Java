package j4.lesson03ex;

import j4.lesson02ex.PhoneCallPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhoneCallPanelEvt extends JFrame implements ActionListener {

    public PhoneCallPanelEvt(String title, int x, int y, int width, int height) {
        setTitle(title);
        setLocation(x, y);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        PhoneCallPanel frame = new PhoneCallPanel("PhoneCallPanelEvt", 200, 200, 300, 380);

        // 表示パネル
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300,80));
        panel.setBackground(Color.GREEN);

        // ボタンパネル
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4, 3, 5, 5));

        Font font = new Font("Times New Roman", Font.BOLD, 24);
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(i + "");
            button.setFont(font);
            panel2.add(button);
        }

        JButton button0 = new JButton("0");
        button0.setFont(font);
        panel2.add(button0);

        ImageIcon callIcon = new ImageIcon("img/phoneCall.png");
        Image callIconScale = callIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton call = new JButton(new ImageIcon(callIconScale));
        panel2.add(call);

        ImageIcon hangupIcon = new ImageIcon("img/phoneHangUp.png");
        Image hangUpIconScale = hangupIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton hangup = new JButton(new ImageIcon(hangUpIconScale));
        panel2.add(hangup);

        // Frame にパネルを追加
        frame.getContentPane().add(panel, "North");
        frame.getContentPane().add(panel2, "South");
        frame.setVisible(true);
    }
}
