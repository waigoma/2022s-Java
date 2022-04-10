package j4.lesson01ex;

import javax.swing.*;

public class SumRecursion {
    public static void main(String[] args) {
        // 10 以上の整数を入力するダイアログを表示させ、string を int に変換し、変数に保存
        String inputText = JOptionPane.showInputDialog("10 以上の整数を入力してください。");
        int inputNumber = Integer.parseInt(inputText);

        // 入力された値が 10 異常かどうかを判定
        if (inputNumber <= 9) {
            JOptionPane.showMessageDialog(null, "10 以上の整数ではないので終了します。");
            return;
        }

        // 足し合わせた結果用と、足した数を表示する用
        int total = 0;
        String resultMessage = "";

        // 偶数か奇数かで分岐
        // 足し合わせた数を表示するために、最初の2つの数字を格納
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

        // 足し合わせた数の最後の数字を格納
        resultMessage += "... + " + inputText;
        resultMessage += " = " + total;

        // 足し合わせた結果を表示
        JOptionPane.showMessageDialog(null, resultMessage);
    }
}
