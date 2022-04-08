package j4.lesson01ex;

import javax.swing.*;
import java.text.DateFormat;

public class WhatDay {
    public static void main(String[] args) {
        String inputYear = JOptionPane.showInputDialog("曜日を求めたい日付を入力。何年？");
        String inputMonth = JOptionPane.showInputDialog(inputYear + " 年何月？");
        String inputDay = JOptionPane.showInputDialog(inputYear + " 年" + inputMonth + " 月何日？");

        int year = Integer.parseInt(inputYear);
        int month = Integer.parseInt(inputMonth);
        int day = Integer.parseInt(inputDay);

        if (checkDate(year, month, day)) {
            int dayOfWeek = dayOfWeek(year, month, day);
            String dayOfWeekString = dayOfWeekString(dayOfWeek);
            JOptionPane.showMessageDialog(null, year + " 年 " + month + " 月 " + day + " 日: " + dayOfWeekString + "曜日");
            return;
        }

        JOptionPane.showMessageDialog(null, year + " 年 " + month + " 月 " + day + " 日: Error in date");
    }

    private static boolean checkDate(int year, int month, int day) {
        String strDate = String.format("%04d/%02d/%02d", year, month, day);
        if (strDate.length() != 10) return false;

        DateFormat format = DateFormat.getDateInstance();
        format.setLenient(false);
        try {
            format.parse(strDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 曜日を計算する
    private static int dayOfWeek(int year, int month, int day) {
        if (month == 1 || month == 2) {
            year--;
            month += 12;
        }

        return (year + year / 4 - year / 100 + year / 400 + (13 * month + 8) / 5 + day) % 7;
    }

    private static String dayOfWeekString(int dayOfWeek) {
        String[] days = {"日", "月", "火", "水", "木", "金", "土"};
        return days[dayOfWeek];
    }
}
