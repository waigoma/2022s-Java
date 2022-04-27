package j4.lesson03ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseDrawShape extends JFrame implements MouseListener, ActionListener {

    private final DrawSquare dSquare;
    private String btString;
    private int x, y;

    public static void main(String[] args) {
        JFrame fm = new MouseDrawShape();
        fm.setVisible(true);
    }

    public MouseDrawShape() {
        setTitle("MouseDrawShape");
        setSize(500, 500);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btString = "Fill";

        // マウスイベントのリスナー登録
        addMouseListener(this);
        setLayout(new BorderLayout());

        dSquare = new DrawSquare();
        add(dSquare, "Center");

        JPanel btPanel = new JPanel();
        btPanel.setBackground(Color.WHITE);
        btPanel.setLayout(new FlowLayout());
        btPanel.setPreferredSize(new Dimension(100,70));

        ImageIcon icon1 = new ImageIcon("img/squareFill.png");
        Image imgScale1 = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton bt1 = new JButton("Fill", new ImageIcon(imgScale1));

        ImageIcon icon2 = new ImageIcon("img/squareNoFill.png");
        Image imgScale2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton bt2 = new JButton("NoFill", new ImageIcon(imgScale2));

        bt1.setOpaque(false);
        bt1.setContentAreaFilled(false);
        bt1.setBorderPainted(false);

        bt2.setOpaque(false);
        bt2.setContentAreaFilled(false);
        bt2.setBorderPainted(false);

        btPanel.add(bt1);
        btPanel.add(bt2);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

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
        dSquare.drawSquare(btString, x, y, e.getX() - 8, e.getY() - 30);
    }
    // クリックされた時
    public void mouseClicked(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e){
        btString = e.getActionCommand();
    }
}

class DrawSquare extends JPanel {
    String eventSource = "";
    int x=250, y=250, width=100, height=100;
    public DrawSquare() {
        setBackground(Color.white);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Click mouse to draw a square", 80, 30);
        g.setColor(Color.BLUE);
        if(eventSource.equalsIgnoreCase("Fill"))
            g.fillRect(x, y, width, height);
        else if (eventSource.equalsIgnoreCase("NoFill"))
            g.drawRect(x, y, width, height);
        else g.drawString("Click an icon to draw or fill", 100, 350);
    }

    public void drawSquare(String st, int x, int y, int x2, int y2) {
        eventSource = st;

        if (x2 > x) {
            width = x2 - x;
            this.x = x;
        } else {
            width = x - x2;
            this.x = x2;
        }

        if (y2 > y) {
            height = y2 - y;
            this.y = y;
        } else {
            height = y - y2;
            this.y = y2;
        }

        repaint();
    }
}
