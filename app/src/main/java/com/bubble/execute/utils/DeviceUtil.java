package com.bubble.execute.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/22
 * 版权所有 © 徐长策
 */

public class DeviceUtil {
    /**
     * 获取AndroidID
     * @param context 全局变量
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
