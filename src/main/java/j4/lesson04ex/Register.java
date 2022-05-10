package j4.lesson04ex;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Register extends JFrame implements KeyListener {
    private static final Font consoleFont = new Font("Century", Font.PLAIN, 10);
    private static final Font inputFont = new Font("Century", Font.PLAIN, 24);
    private final JTextField nameField;
    private final JPasswordField pwField, confirmField;
    private final JTextArea displayArea;

    public Register (String title) {
        setTitle(title);
        setPreferredSize(new Dimension(600, 200));
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // Name 入力用テキストパネル
        nameField = new JTextField(10);
        nameField.setFont(inputFont);
        nameField.addKeyListener(this);

        // PW 入力用テキストエリア
        pwField = new JPasswordField(10);
        pwField.setFont(inputFont);
        pwField.addKeyListener(this);

        // confirm 入力用テキストエリア
        confirmField = new JPasswordField(10);
        confirmField.setFont(inputFont);
        confirmField.addKeyListener(this);

        // Input 配置用パネル
        JPanel inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(300, 100));

        GridBagLayout layout = new GridBagLayout();
        inputPanel.setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        int[] i = { 0 };

        // ラベル
        Arrays.asList(
                new JLabel("Name:"),
                new JLabel("PW:"),
                new JLabel("Confirm:")
        ).forEach(label -> {
            gbc.gridx = 0;
            gbc.gridy = i[0];
            i[0]++;
            layout.setConstraints(label, gbc);
            inputPanel.add(label);
        });

        i[0] = 0;

        // テキストフィールド
        Arrays.asList(
                nameField,
                pwField,
                confirmField
        ).forEach(field -> {
            gbc.gridx = 1;
            gbc.gridy = i[0];
            i[0]++;
            layout.setConstraints(field, gbc);
            inputPanel.add(field);
        });

        // Display 用テキストエリア
        displayArea = new JTextArea(10, 25);
        displayArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        displayArea.setFont(consoleFont);
        displayArea.setEditable(false);
        displayArea.setPreferredSize(new Dimension(200, 100));

        // Display 配置用パネル
        JPanel displayPanel = new JPanel();
        displayPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        displayPanel.add(displayArea);

        // 配置
        add(inputPanel);
        add(displayPanel);

        addKeyListener(this);
        pack();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        showMessage();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        showMessage();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        showMessage();
    }

    // 数字だけかどうか
    private boolean isNumeric(String str) {
        Pattern p = Pattern.compile("[0-9]*");
        return p.matcher(str).matches();
    }

    // 範囲内の文字列の長さかどうか
    private boolean isInRange(String str) {
        return 4 < str.length() && str.length() < 16;
    }

    // 範囲内の文字列の長さかどうか
    private boolean isInRange(char[] str) {
        return 4 < str.length && str.length < 16;
    }

    // 文字列と文字列が同じかどうか
    private boolean isSame(String str1, String str2) {
        return str1.equals(str2);
    }

    private void showMessage() {
        boolean isError = false;
        displayArea.setText("");

        // 名前欄のエラー検出
        if (isNumeric(nameField.getText())) {
            displayArea.append("User Name can't be numeric.\n");
            isError = true;
        }

        // 文字列の長さのエラー検出
        if (!isInRange(nameField.getText()) || !isInRange(pwField.getPassword())) {
            displayArea.append("User Name or Password over length or too abort!\n");
            isError = true;
        }

        // パスワード入力のエラー検出
        if (!isSame(String.valueOf(pwField.getPassword()), String.valueOf(confirmField.getPassword()))) {
            displayArea.append("Password don't match!\n");
            isError = true;
        }

        // エラーがなければ OK
        if (!isError) {
            displayArea.append("Check Pass!\n");
        }
    }

    public static void main(String[] args){
        JFrame fm = new Register("Register");

        fm.setVisible(true);
    }
}
