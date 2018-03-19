package com.bubble.execute.utils;

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

    public static SharedPreferences getSharedPreferences() {
        return ExApplication.getInstance()
                .getSharedPreferences(ExApplication.getInstance().getPackageName(), Context.MODE_PRIVATE);
    }

    /**
     * 写入用户账户数据
     * @param USER_MAIL
     */
    public static void setUserMail(String USER_MAIL) {
        getSharedPreferences().edit().putString(ConstantUtil.UserData.USER_MAIL, USER_MAIL).apply();
    }

    /**
     * 返回存储的用户账户数据
     * @return
     */
    public static String getUserMail() {
        return getSharedPreferences().getString(ConstantUtil.UserData.USER_MAIL, "");
    }

    /**
     * 写入用户密码数据
     * @param USER_PASSWORD
     */
    public static void setUserPassword(String USER_PASSWORD) {
        getSharedPreferences().edit().putString(ConstantUtil.UserData.USER_PASSWORD, USER_PASSWORD).apply();
    }

    /**
     * 返回存储的用户账户数据
     * @return
     */
    public static String getUserPassword() {
        return getSharedPreferences().getString(ConstantUtil.UserData.USER_PASSWORD, "");
    }
}
