package com.osiris.food.utils;

import android.util.Log;

import java.util.Calendar;

public class TimeLockUtil {
    public static boolean checkAppTime(long oldDate) {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        if (month == 12) {
            month = 1;
        } else {
            month = month + 1;
        }
        int date = c.get(Calendar.DATE);
        String mMonth = String.valueOf(month);
        if (month < 10) {
            mMonth = "0" + mMonth;
        }
        String mData = String.valueOf(date);
        if (date < 10) {
            mData = "0" + mData;
        }
        long nowdate = Long.valueOf(String.valueOf(year) + mMonth + mData);
        Log.e("TimeLockUtil", "oldDate is " + oldDate + " nowdate " + nowdate);
        if ((nowdate - oldDate) <= 15) {
            return true;
        }
        return false;
    }
}
