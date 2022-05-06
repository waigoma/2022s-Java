package j4.lesson04ex;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

public class ChatBox_2 extends JFrame implements ActionListener, ItemListener {
    private static final Font menuFont = new Font("Century", Font.BOLD, 14);
    private static final Font centuryFont = new Font("Century", Font.PLAIN, 24);

    private final JTextField jtf1;
    private final JTextField jtf2;
    private final JTextArea jta;

    public ChatBox_2(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // メニューバー
        JMenuBar menuBar = new JMenuBar();

        JMenu bgMenu = new JMenu("Background Color");
        bgMenu.setFont(menuFont);

        Arrays.asList(
            new JMenuItem("White"),
            new JMenuItem("Yellow"),
            new JMenuItem("Gray")
        ).forEach(item -> {
            item.addActionListener(this);
            bgMenu.add(item);
        });

        menuBar.add(bgMenu);

        // A さんのテキストエリア
        jtf1 = new JTextField("", 10);
        jtf1.setFont(centuryFont);
        jtf1.addActionListener(this);
        jtf1.setActionCommand("A");

        // B さんのテキストエリア
        jtf2 = new JTextField("", 10);
        jtf2.setFont(centuryFont);
        jtf2.addActionListener(this);
        jtf2.setActionCommand("B");

        // 入力した文字列を表示する
        jta = new JTextArea("Left: A, Right: B", 10, 15);
        jta.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jta.setFont(centuryFont);
        jta.setEditable(false);

        JScrollPane jsp = new JScrollPane(jta);

        // 文字の色変えるパネル
        JPanel colorPanel = new JPanel();
        ButtonGroup colorGroup = new ButtonGroup();

        Arrays.asList(
                new JRadioButton("Black", true),
                new JRadioButton("Blue"),
                new JRadioButton("Red")
        ).forEach(button -> {
            button.addItemListener(this);
            colorGroup.add(button);
            colorPanel.add(button);
        });

        // 文字の大きさを変えるパネル
        JPanel sizePanel = new JPanel();
        ButtonGroup sizeGroup = new ButtonGroup();

        Arrays.asList(
                new JRadioButton("Small", true),
                new JRadioButton("Medium"),
                new JRadioButton("Large")
        ).forEach(button -> {
            button.addItemListener(this);
            sizeGroup.add(button);
            sizePanel.add(button);
        });

        // 最下部のボタン配列用パネル
        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());
        panel.add(colorPanel, BorderLayout.WEST);
        panel.add(sizePanel, BorderLayout.EAST);

        // フレームに追加
        setJMenuBar(menuBar);
        add(jsp, BorderLayout.NORTH);
        add(jtf1, BorderLayout.WEST);
        add(jtf2, BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "A":
                post(jtf1);
                break;
            case "B":
                post(jtf2);
                break;
            case "White":
                jta.setBackground(Color.WHITE);
                break;
            case "Yellow":
                jta.setBackground(Color.YELLOW);
                break;
            case "Gray":
                jta.setBackground(Color.GRAY);
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) return;

        switch (e.getItem().toString()) {
            case "Black":
                jta.setForeground(Color.BLACK);
                break;
            case "Blue":
                jta.setForeground(Color.BLUE);
                break;
            case "Red":
                jta.setForeground(Color.RED);
                break;
            case "Small":
                jta.setFont(new Font("Century", Font.PLAIN, 18));
                break;
            case "Medium":
                jta.setFont(new Font("Century", Font.PLAIN, 24));
                break;
            case "Large":
                jta.setFont(new Font("Century", Font.PLAIN, 32));
                break;
            default:
                System.out.println(e.getItemSelectable());
        }
    }

    private void post(JTextField jtf) {
        if (jtf.getText().equals("")) return;
        jta.append("\nA: " + jtf.getText());
        jtf.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new ChatBox_2("ChatBox_2");

        frame.pack();
        frame.setVisible(true);
    }
}
