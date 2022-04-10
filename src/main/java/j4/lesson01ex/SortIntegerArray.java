package j4.lesson01ex;

import javax.swing.*;

public class SortIntegerArray {
    // 入力された配列を昇順にソートする
    private void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        // 入力された文字列を split で分割して保存
        String inputArray = JOptionPane.showInputDialog("整数の配列を入力してください。(1:2:3:4:5)");
        String[] inputArraySplit = inputArray.split(":");

        // 分割された文字分の配列を確保
        int[] nums = new int[inputArraySplit.length];
        int[] numsBk = new int[inputArraySplit.length];

        // 分割された文字列を int に変換して保存
        int i = 0;
        for (String inputNum : inputArraySplit) {
            int num = Integer.parseInt(inputNum);
            nums[i] = num;
            numsBk[i++] = num;
        }

        // ソートする
        SortIntegerArray sortIntegerArray = new SortIntegerArray();
        sortIntegerArray.sort(nums);

        // 最終的に表示する変数に、入力された値を追加
        String outputString = "Original Integers: ";
        outputString += numsBk[0];
        for (int j = 1; j < numsBk.length; j++) {
            outputString += ":" + numsBk[j];
        }

        // ソートされた値を追加
        outputString += "\nSorted Integers: ";
        outputString += nums[0];
        for (int j = 1; j < nums.length; j++) {
            outputString += ":" + nums[j];
        }

        // ダイアログで結果を表示
        JOptionPane.showMessageDialog(null, outputString);
    }
}
