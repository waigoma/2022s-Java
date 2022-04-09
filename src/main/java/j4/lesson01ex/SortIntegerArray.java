package j4.lesson01ex;

import javax.swing.*;

public class SortIntegerArray {
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
        String inputArray = JOptionPane.showInputDialog("整数の配列を入力してください。(1:2:3:4:5)");
        String[] inputArraySplit = inputArray.split(":");

        int[] nums = new int[inputArraySplit.length];
        int[] numsBk = new int[inputArraySplit.length];

        int i = 0;
        for (String inputNum : inputArraySplit) {
            int num = Integer.parseInt(inputNum);
            nums[i] = num;
            numsBk[i++] = num;
        }

        SortIntegerArray sortIntegerArray = new SortIntegerArray();
        sortIntegerArray.sort(nums);

        String outputString = "Original Integers: ";
        outputString += numsBk[0];
        for (int j = 1; j < numsBk.length; j++) {
            outputString += ":" + numsBk[j];
        }

        outputString += "\nSorted Integers: ";
        outputString += nums[0];
        for (int j = 1; j < nums.length; j++) {
            outputString += ":" + nums[j];
        }

        JOptionPane.showMessageDialog(null, outputString);
    }
}
