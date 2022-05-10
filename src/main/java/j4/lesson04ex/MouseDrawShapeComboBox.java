package j4.lesson04ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class MouseDrawShapeComboBox  extends JFrame implements MouseListener, ItemListener {
    private final DrawPanel drawPanel;
    private final JComboBox<String> shapeBox;
    private final JComboBox<String> colorBox;
    private String btString;
    private Color color;
    private int x, y;
    private final boolean isInitialized;

    public static void main(String[] args) {
        JFrame fm = new MouseDrawShapeComboBox();
        fm.setVisible(true);
    }

    public MouseDrawShapeComboBox() {
        setTitle("MouseDrawShapeComboBox");
        setSize(650, 500);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btString = "Line";

        // マウスイベントのリスナー登録
        addMouseListener(this);
        setLayout(new BorderLayout());

        // comboBoxの作成
        String[] shape = {"Line", "Circle", "Rect"};
        shapeBox = new JComboBox(shape);
        shapeBox.addItemListener(this);

        String[] color = {"Blue", "Red", "Green", "Yellow", "Black"};
        colorBox = new JComboBox(color);
        colorBox.addItemListener(this);

        // パネルの作成
        drawPanel = new DrawPanel();

        JPanel cbPanel = new JPanel();
        cbPanel.setBackground(Color.WHITE);
        cbPanel.setLayout(new FlowLayout());
        cbPanel.setPreferredSize(new Dimension(100,70));
        cbPanel.add(shapeBox);
        cbPanel.add(colorBox);

        add(drawPanel, "Center");
        add(cbPanel, "South");

        JLabel lb = new JLabel("Click mouse to draw", SwingConstants.CENTER);
        lb.setFont(new Font("Century", Font.PLAIN, 24));
        isInitialized = true;
        itemStateChanged(null);
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e){
        x = e.getX() - 8;
        y = e.getY() - 30;
    }
    public void mouseReleased(MouseEvent e) {
        drawPanel.draw(btString, color, x, y, e.getX() - 8, e.getY() - 30);
    }
    // クリックされた時
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (!isInitialized) return;
        btString = (String) shapeBox.getSelectedItem();
        String colorString = (String) colorBox.getSelectedItem();
        switch (Objects.requireNonNull(colorString)) {
            case "Red":
                color = Color.RED;
                break;
            case "Blue":
                color = Color.BLUE;
                break;
            case "Green":
                color = Color.GREEN;
                break;
            case "Yellow":
                color = Color.YELLOW;
                break;
            case "Black":
                color = Color.BLACK;
                break;
        }

    }
}

class DrawPanel extends JPanel {
    String eventSource = "";
    Color color;
    int x=250, y=250, x2=100, y2=100;
    public DrawPanel() {
        setBackground(Color.white);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.setColor(color);
        g.drawString("Click mouse to draw a " + eventSource, 80, 30);

        switch (eventSource) {
            case "Line":
                g.drawLine(x, y, x2, y2);
                break;
            case "Circle":
                int radius = (int) Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));
                g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
                break;
            case "Rect":
                int width;
                int height;
                if (x2 > x) {
                    width = x2 - x;
                } else {
                    width = x - x2;
                    this.x = x2;
                }

                if (y2 > y) {
                    height = y2 - y;
                } else {
                    height = y - y2;
                    this.y = y2;
                }
                g.drawRect(x, y, width, height);
                break;
            default:
                g.drawString("Click an icon to draw or fill", 100, 350);
                break;
        }
    }

    public void draw(String st, Color c, int x, int y, int x2, int y2) {
        eventSource = st;
        color = c;

        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;

        repaint();
    }
}