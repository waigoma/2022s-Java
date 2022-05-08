package j4.lesson04ex;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatBox_1 extends JFrame implements ActionListener {
    private static final Font centuryFont = new Font("Century", Font.PLAIN, 24);

    private final JTextField jtf1;
    private final JTextField jtf2;
    private final JTextArea jta;

    public ChatBox_1(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jtf1 = new JTextField("", 10);
        jtf2 = new JTextField("", 10);

        jtf1.setFont(centuryFont);
        jtf1.addActionListener(this);
        jtf1.setActionCommand("A");

        jtf2.setFont(centuryFont);
        jtf2.addActionListener(this);
        jtf2.setActionCommand("B");

        jta = new JTextArea("Left: A, Right: B", 10, 15);
        jta.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jta.setFont(centuryFont);
        jta.setEditable(false);

        JScrollPane jsp = new JScrollPane(jta);

        add(jsp, "North");
        add(jtf1, "West");
        add(jtf2, "East");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("A")) {
            if (jtf1.getText().equals("")) return;
            jta.append("\nA: " + jtf1.getText());
            jtf1.setText("");
        } else if (e.getActionCommand().equals("B")) {
            if (jtf2.getText().equals("")) return;
            jta.append("\nB: " + jtf2.getText());
            jtf2.setText("");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new ChatBox_1("ChatBox_1");

        frame.pack();
        frame.setVisible(true);
    }
}