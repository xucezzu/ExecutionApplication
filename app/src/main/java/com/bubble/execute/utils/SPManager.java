package com.bubble.execute.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.bubble.execute.ExApplication;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public class SPManager {
    private static SharedPreferences.Editor editor = null;

    private static SharedPreferences getSharedPreferences() {
        return ExApplication.getInstance().getSharedPreferences(ExApplication.getInstance().getPackageName(), Context.MODE_PRIVATE);
    }

    /**
     * 写入用户账户数据
     *
     * @param userMail
     */
    public static void setUserMail(String userMail) {
        getSharedPreferences().edit().putString(ConstantUtil.UserData.USER_MAIL, userMail).apply();
    }

    /**
     * 返回存储的用户账户数据
     *
     * @return
     */
    public static String getUserMail() {
        return getSharedPreferences().getString(ConstantUtil.UserData.USER_MAIL, "");
    }

    /**
     * 写入用户密码数据
     *
     * @param userPassword
     */
    public static void setUserPassword(String userPassword) {
        getSharedPreferences().edit().putString(ConstantUtil.UserData.USER_PASSWORD, userPassword).apply();
    }

    /**
     * 返回存储的用户账户数据
     *
     * @return
     */
    public static String getUserPassword() {
        return getSharedPreferences().getString(ConstantUtil.UserData.USER_PASSWORD, "");
    }

    /**
     * 写入用户个人ID
     *
     * @param userId
     */
    public static void setUserID(String userId) {
        getSharedPreferences().edit().putString(ConstantUtil.UserData.USER_ID, userId).apply();
    }

    /**
     * 返回存储的用户账户数据
     *
     * @return
     */
    public static String getUserID() {
        return getSharedPreferences().getString(ConstantUtil.UserData.USER_ID, "");
    }

    /**
     * 清空所有数据
     */
    @SuppressLint("CommitPrefEdits")
    public static void removeAllData() {
        editor = getSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }
}
