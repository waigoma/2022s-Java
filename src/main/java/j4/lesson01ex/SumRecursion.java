package j4.lesson01ex;

import javax.swing.*;

public class SumRecursion {
    public static void main(String[] args) {
        String inputText = JOptionPane.showInputDialog("10 以上の整数を入力してください。");
        int inputNumber = Integer.parseInt(inputText);

        int total = 0;
        String resultMessage = "";

        if (inputNumber <= 9) {
            JOptionPane.showMessageDialog(null, "10 以上の整数ではないので終了します。");
            return;
        }

        if (inputNumber % 2 == 0) {
            for (int i = 2; i <= inputNumber; i += 2) {
                total += i;
                if (i <= 4) resultMessage += i + " + ";
            }
        } else {
            for (int i = 1; i <= inputNumber; i += 2) {
                total += i;
                if (i <= 3) resultMessage += i + " + ";
            }
        }
        resultMessage += "... + " + inputText;
        resultMessage += " = " + total;

        JOptionPane.showMessageDialog(null, resultMessage);
        System.out.println(total);
    }
}
