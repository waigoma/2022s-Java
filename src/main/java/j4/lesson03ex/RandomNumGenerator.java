package j4.lesson03ex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RandomNumGenerator extends JPanel implements ActionListener{
    public static void main(String[] args){
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 500, 500);
        frame.setTitle("RandomNumDisplayex");
        frame.setLayout(new BorderLayout());

        RandomNumGenerator gpanel = new RandomNumGenerator();

        // ボタン用のパネル定義からのボタン2つ作成
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(100,40));
        p2.setBackground(Color.GREEN);
        p2.setLayout(new FlowLayout());

        Font f = new Font("Arial", Font.BOLD, 20);

        JButton bt1 = new JButton("Uniform Distribution");
        bt1.addActionListener(gpanel);
        bt1.setFont(f);

        JButton bt2 = new JButton("Normal Distribution");
        bt2.addActionListener(gpanel);
        bt2.setFont(f);

        p2.add(bt1);
        p2.add(bt2);

        // Frame にパネルを追加
        frame.getContentPane().add(gpanel, "Center");
        frame.getContentPane().add(p2, "South");
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.setFont(new Font("Century", Font.PLAIN, 24));
        g.drawString("Random Number Generator", 100, 30);
        g.drawRect(90, 50, 300, 300);
        g.setFont(new Font("Century", Font.PLAIN, 18));
        g.drawString("Display 1000 random numbers in the 300x300 rectangle", 100, 400);
    }

    public void actionPerformed(ActionEvent e){
        repaint();
    }
}
