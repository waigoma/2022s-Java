package j4.lesson09ex;

import javax.swing.*;
import java.awt.*;

public class TrebleCountThreadSwing {
    public static void main(String[] args) {
        CountUpJFrame ctt1 = new CountUpJFrame("Count-up Thread");
        CountDownJFrame ctt2 = new CountDownJFrame("Count-down Thread");
        CountMultiplyJFrame ctt3 = new CountMultiplyJFrame("Count-multiply Thread");
        ctt1.start();
        ctt2.start();
        ctt3.start();
    }
}

class ThreadJFrame extends JFrame {
    JTextArea ta;
    ThreadJFrame(String s) {
        super(s);
        ta = new JTextArea(50, 50);
        ta.setFont(new Font("Century", Font.PLAIN, 20));
        add(ta);
        setSize(350,410);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void newline(String line) {
        ta.append(line);
    }
}

class CountUpJFrame extends Thread {
    ThreadJFrame fm;

    public CountUpJFrame(String threadName) {
        super(threadName);
        fm = new ThreadJFrame(threadName);
        fm.setLocation(600, 200);
    }

    public void run() {
        fm.newline(getName()+ " is starting\n\n");
        for (int i = 0; i < 10; i++) {
            try {
                sleep((int)(Math.random()* 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fm.newline(getName()+"  i = " + i + "\n");
        }
        fm.newline("\n"+getName()+ " is finished\n");
    }
}

class CountDownJFrame extends Thread {
    ThreadJFrame fm;

    public CountDownJFrame(String threadName) {
        super(threadName);
        fm = new ThreadJFrame(threadName);
        fm.setLocation(1000, 200);
    }

    public void run() {
        fm.newline(getName()+ " is starting\n\n");
        for (int j = 9; j >= 0; j--) {
            try {
                sleep((int)(Math.random()* 1000));
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            fm.newline(getName()+"  j = " + j + "\n");
        }
        fm.newline("\n"+getName()+ " is finished\n");
    }
}

class CountMultiplyJFrame extends Thread {
    ThreadJFrame fm;

    public CountMultiplyJFrame(String threadName) {
        super(threadName);
        fm = new ThreadJFrame(threadName);
        fm.setLocation(1400, 200);
    }

    public void run() {
        fm.newline(getName()+ " is starting\n\n");
        for (int j = 0; j <= 9; j++) {
            try {
                sleep((int)(Math.random()* 1000));
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            fm.newline(getName()+"  j = " + j * j + "\n");
        }
        fm.newline("\n"+getName()+ " is finished\n");
    }
}