package j4.lesson04ex;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatBox_3 extends JFrame implements KeyListener, ItemListener {
    private final Font centuryPlain = new Font("Century", Font.PLAIN, 24);
    private final Font centuryItalic = new Font("Century", Font.ITALIC, 24);
    private final Font centuryBold = new Font("Century", Font.BOLD, 24);
    private final Font centuryBoldItalic = new Font("Century", Font.BOLD | Font.ITALIC, 24);
    private final JTextArea jta, display;
    private final JCheckBox boldBox, italicBox;

    public ChatBox_3 (String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 入力用テキストエリア
        jta = new JTextArea(10, 20);
        jta.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jta.setFont(centuryPlain);
        jta.addKeyListener(this);
        jta.setPreferredSize(new Dimension(200, 300));
        jta.setLineWrap(true);

        // Display 用テキストエリア
        display = new JTextArea(10, 20);
        display.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        display.setFont(centuryPlain);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(200, 300));
        display.setLineWrap(true);

        // CheckBox
        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.Y_AXIS));

        italicBox = new JCheckBox("Italic");
        italicBox.setFont(centuryItalic);
        italicBox.addItemListener(this);

        boldBox = new JCheckBox("Bold");
        boldBox.setFont(centuryBold);
        boldBox.addItemListener(this);

        checkPanel.add(italicBox, BorderLayout.NORTH);
        checkPanel.add(boldBox, BorderLayout.NORTH);

        // 配置
        add(jta, BorderLayout.WEST);
        add(display, BorderLayout.CENTER);
        add(checkPanel, BorderLayout.EAST);

        addKeyListener(this);
        pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        display.setText(jta.getText());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        display.setText(jta.getText());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        display.setText(jta.getText());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (boldBox.isSelected() && italicBox.isSelected())
            display.setFont(centuryBoldItalic);
        else if (boldBox.isSelected())
            display.setFont(centuryBold);
        else if (italicBox.isSelected())
            display.setFont(centuryItalic);
        else
            display.setFont(centuryPlain);
    }

    public static void main(String[] args){
        JFrame fm = new ChatBox_3("ChatBox_3");
        fm.setVisible(true);
    }
}
