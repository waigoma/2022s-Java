package j4.lesson03ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PhoneCallPanelEvt extends JPanel implements ActionListener {
    private static final Font font = new Font("Times New Roman", Font.BOLD, 24);

    private final ArrayList<String> phoneNumbers = new ArrayList<>();
    private boolean isCalling = false;
    private boolean isHangup = false;
    private JLabel label;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setFont(font);

        StringBuilder str = new StringBuilder();
        String labelStr;

        for (String phoneNumber : phoneNumbers) str.append(phoneNumber);

        if (str.length() == 0) str.append("enter number");

        if (isCalling) labelStr = "Calling " + str;
        else if (isHangup) labelStr = "HangUp";
        else labelStr = str.toString();

        if (label == null) label = new JLabel(labelStr, SwingConstants.CENTER);
        else label.setText(labelStr);

        add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "0":
                phoneNumbers.add("0");
                isHangup = false;
                isCalling = false;
                break;
            case "1":
                phoneNumbers.add("1");
                isHangup = false;
                isCalling = false;
                break;
            case "2":
                phoneNumbers.add("2");
                isHangup = false;
                isCalling = false;
                break;
            case "3":
                phoneNumbers.add("3");
                isHangup = false;
                isCalling = false;
                break;
            case "4":
                phoneNumbers.add("4");
                isHangup = false;
                isCalling = false;
                break;
            case "5":
                phoneNumbers.add("5");
                isHangup = false;
                isCalling = false;
                break;
            case "6":
                phoneNumbers.add("6");
                isHangup = false;
                isCalling = false;
                break;
            case "7":
                phoneNumbers.add("7");
                isHangup = false;
                isCalling = false;
                break;
            case "8":
                phoneNumbers.add("8");
                isHangup = false;
                isCalling = false;
                break;
            case "9":
                phoneNumbers.add("9");
                isHangup = false;
                isCalling = false;
                break;
            case "call":
                isCalling = true;
                isHangup = false;
                break;
            case "hangup":
                isHangup = true;
                isCalling = false;
                phoneNumbers.clear();
                break;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("PhoneCallPanelEvt");
        frame.setLocation(200, 200);
        frame.setSize(300, 380);

        // 表示パネル
        PhoneCallPanelEvt displayPanel = new PhoneCallPanelEvt();

        // ボタンパネル
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4, 3, 5, 5));

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(i + "");
            button.setFont(font);
            button.addActionListener(displayPanel);
            panel2.add(button);
        }

        JButton button0 = new JButton("0");
        button0.setFont(font);
        button0.addActionListener(displayPanel);
        panel2.add(button0);

        ImageIcon callIcon = new ImageIcon("img/phoneCall.png");
        Image callIconScale = callIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton call = new JButton(new ImageIcon(callIconScale));
        call.setActionCommand("call");
        call.addActionListener(displayPanel);
        panel2.add(call);

        ImageIcon hangupIcon = new ImageIcon("img/phoneHangUp.png");
        Image hangUpIconScale = hangupIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton hangup = new JButton(new ImageIcon(hangUpIconScale));
        hangup.setActionCommand("hangup");
        hangup.addActionListener(displayPanel);
        panel2.add(hangup);

        // Frame にパネルを追加
        frame.getContentPane().add(displayPanel, "North");
        frame.getContentPane().add(panel2, "South");
        frame.setVisible(true);
    }
}
