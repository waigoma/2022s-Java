package j4.lesson03ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MouseDrawShapeColor extends JFrame implements MouseListener, ActionListener {
    private static final ImageIcon lineIcon = new ImageIcon("img/Line.png");
    private static final ImageIcon squareIcon = new ImageIcon("img/Square.png");
    private static final ImageIcon rectIcon = new ImageIcon("img/Rect.png");
    private static final ImageIcon redIcon = new ImageIcon("img/Red.png");
    private static final ImageIcon blueIcon = new ImageIcon("img/Blue.png");
    private static final ImageIcon greenIcon = new ImageIcon("img/Green.png");
    private static final ImageIcon yellowIcon = new ImageIcon("img/Yellow.png");
    private static final ImageIcon blackIcon = new ImageIcon("img/Black.png");

    private final DrawPanel drawPanel;
    private String btString;
    private Color color;
    private int x, y;

    public static void main(String[] args) {
        JFrame fm = new MouseDrawShapeColor();
        fm.setVisible(true);
    }

    public MouseDrawShapeColor() {
        setTitle("MouseDrawShapeColor");
        setSize(800, 600);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btString = "Line";

        // マウスイベントのリスナー登録
        addMouseListener(this);
        setLayout(new BorderLayout());

        drawPanel = new DrawPanel();
        add(drawPanel, "Center");

        LinkedHashMap<String, JButton> btMap = new LinkedHashMap<>();

        JPanel btPanel = new JPanel();
        btPanel.setBackground(Color.WHITE);
        btPanel.setLayout(new FlowLayout());
        btPanel.setPreferredSize(new Dimension(100,70));

        Image lineScale = lineIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btMap.put("Line", new JButton(new ImageIcon(lineScale)));

        Image circleScale = squareIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btMap.put("Circle", new JButton(new ImageIcon(circleScale)));

        Image rectScale = rectIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btMap.put("Rect", new JButton(new ImageIcon(rectScale)));

        Image redScale = redIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btMap.put("Red", new JButton(new ImageIcon(redScale)));

        Image blueScale = blueIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btMap.put("Blue", new JButton(new ImageIcon(blueScale)));

        Image greenScale = greenIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btMap.put("Green", new JButton(new ImageIcon(greenScale)));

        Image yellowScale = yellowIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btMap.put("Yellow", new JButton(new ImageIcon(yellowScale)));

        Image blackScale = blackIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        btMap.put("Black", new JButton(new ImageIcon(blackScale)));

        btMap.forEach((str, bt) -> {
            initButton(this, bt, str);
            btPanel.add(bt);
        });

        add(btPanel, "South");

        JLabel lb = new JLabel("Please move your mouse", SwingConstants.CENTER);
        lb.setFont(new Font("Century", Font.PLAIN, 24));
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

    public void actionPerformed(ActionEvent e){
        switch (e.getActionCommand()) {
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
            default:
                btString = e.getActionCommand();
                break;
        }
    }

    private static void initButton(ActionListener imageDisplayEvt, JButton button, String commandName) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        button.setActionCommand(commandName);
        button.addActionListener(imageDisplayEvt);
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
