package com.osiris.food.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.osiris.food.base.App;


public class SharePrefUtils {
    private final static String SP_NAME = "food";
    private static String tag = SharePrefUtils.class.getSimpleName();
    private static SharedPreferences sp;

    /**
     * 保存布尔值
     * @param key
     * @param value
     */
    public static void saveBoolean(String key, boolean value) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 保存字符串
     * @param key
     * @param value
     */
    public static void saveString(String key, String value) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().putString(key, value).commit();

    }

    public static void clear(Context context) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().clear().commit();
    }

    /**
     * 保存long型
     * @param key
     * @param value
     */
    public static void saveLong(String key, long value) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().putLong(key, value).commit();
    }

    /**
     * 保存int型
     * @param key
     * @param value
     */
    public static void saveInt(String key, int value) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 保存float型
     * @param key
     * @param value
     */
    public static void saveFloat(String key, float value) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        sp.edit().putFloat(key, value).commit();
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    /**
     * 获取字符值
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(String key, String defValue) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        return sp.getString(key, defValue);
    }


    /**
     * 获取int值
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(String key, int defValue) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        return sp.getInt(key, defValue);
    }

    /**
     * 获取int值
     * @param key
     */
    public static int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * 获取long值
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(String key, long defValue) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        return sp.getLong(key, defValue);
    }

    /**
     * 获取float值
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(String key, float defValue) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        return sp.getFloat(key, defValue);
    }

    /**
     * 获取布尔值
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue) {
        if (sp == null)
            sp = App.getInstance().getSharedPreferences(SP_NAME, 0);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 获取布尔值
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }
}
