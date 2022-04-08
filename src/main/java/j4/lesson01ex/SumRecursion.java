package j4.lesson01ex;

import javax.swing.*;

public class SumRecursion {
    public static void main(String[] args) {
        String inputText = JOptionPane.showInputDialog("整数を入力してください。");
        int inputNumber = Integer.parseInt(inputText);

        int total = 0;
        String resultMessage = "";
        boolean isAbbreviate = false;
        boolean isFirst = true;

        if (inputNumber % 2 == 0) {
            int abbrNumber = 4;
            for (int i = 2; ; i += 2) {
                if (i > inputNumber) break;
                if (!isFirst && i <= abbrNumber) resultMessage += " + ";

                total += i;
                if (i <= abbrNumber) resultMessage += i;
                else isAbbreviate = true;
                isFirst = false;
            }
        } else {
            int abbrNumber = 3;
            for (int i = 1; ; i += 2) {
                if (i > inputNumber) break;
                if (!isFirst && i <= abbrNumber) resultMessage += " + ";

                total += i;
                if (i <= abbrNumber) resultMessage += i;
                else isAbbreviate = true;
                isFirst = false;
            }
        }
        if (isAbbreviate) resultMessage += " + ... + " + inputText;
        resultMessage += " = " + total;

        JOptionPane.showMessageDialog(null, resultMessage);
        System.out.println(total);
    }
}
