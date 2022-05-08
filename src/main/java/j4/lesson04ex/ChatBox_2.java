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
    private static final Font defaultFont = new Font("Century", Font.PLAIN, 24);

    private final JTextField jtf1;
    private final JTextField jtf2;
    private final JTextArea jta;
    private final ButtonGroup colorGroup = new ButtonGroup();
    private final ButtonGroup sizeGroup = new ButtonGroup();
    private final boolean isInitialized;

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

        // 入力した文字列を表示する
        jta = new JTextArea("Left: A, Right: B", 10, 20);
        jta.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jta.setFont(defaultFont);
        jta.setEditable(false);

        JScrollPane jsp = new JScrollPane(jta);
        jsp.setPreferredSize(new Dimension(300, 400));

        // A さんのテキストエリア
        jtf1 = new JTextField("", 15);
        jtf1.setFont(defaultFont);
        jtf1.addActionListener(this);
        jtf1.setActionCommand("A");

        // B さんのテキストエリア
        jtf2 = new JTextField("", 15);
        jtf2.setFont(defaultFont);
        jtf2.addActionListener(this);
        jtf2.setActionCommand("B");

        // 上部配列用パネル
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());

        chatPanel.add(jsp, BorderLayout.NORTH);
        chatPanel.add(jtf1, BorderLayout.WEST);
        chatPanel.add(jtf2, BorderLayout.EAST);

        // 文字の色変えるパネル
        JPanel colorPanel = new JPanel();

        Arrays.asList(
                "Black",
                "Blue",
                "Red"
        ).forEach(buttonName -> {
            JRadioButton button = new JRadioButton(buttonName);
            button.setActionCommand(buttonName);
            button.addItemListener(this);

            if (buttonName.equals("Black")) button.setSelected(true);

            colorGroup.add(button);
            colorPanel.add(button);
        });

        // 文字の大きさを変えるパネル
        JPanel sizePanel = new JPanel();

        Arrays.asList(
                "Small",
                "Medium",
                "Large"
        ).forEach(buttonName -> {
            JRadioButton button = new JRadioButton(buttonName);
            button.setActionCommand(buttonName);
            button.addItemListener(this);

            if (buttonName.equals("Medium")) button.setSelected(true);

            sizeGroup.add(button);
            sizePanel.add(button);
        });

        // 最下部のボタン配列用パネル
        JPanel bottomPanel = new JPanel();

        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(colorPanel, BorderLayout.WEST);
        bottomPanel.add(sizePanel, BorderLayout.EAST);

        // フレームに追加
        setJMenuBar(menuBar);
        add(chatPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        isInitialized = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "A":
                post(jtf1, "A");
                break;
            case "B":
                post(jtf2, "B");
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
        if (e.getStateChange() != ItemEvent.SELECTED || !isInitialized) return;

        switch (colorGroup.getSelection().getActionCommand()) {
            case "Black":
                jta.setForeground(Color.BLACK);
                break;
            case "Blue":
                jta.setForeground(Color.BLUE);
                break;
            case "Red":
                jta.setForeground(Color.RED);
                break;
        }

        switch (sizeGroup.getSelection().getActionCommand()) {
            case "Small":
                jta.setFont(new Font("Century", Font.PLAIN, 18));
                break;
            case "Medium":
                jta.setFont(new Font("Century", Font.PLAIN, 24));
                break;
            case "Large":
                jta.setFont(new Font("Century", Font.PLAIN, 32));
                break;
        }
    }

    private void post(JTextField jtf, String user) {
        if (jtf.getText().equals("")) return;
        jta.append("\n" + user + ": " + jtf.getText());
        jtf.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new ChatBox_2("ChatBox_2");

        frame.pack();
        frame.setVisible(true);
    }
}
