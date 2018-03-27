package com.bubble.execute.utils;

import android.content.Context;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public class Util {
    /**
     * 获取资源文件的文字
     * @param context Context
     * @param rid 资源的ID
     * @return 具体的文字
     */
    public static String getResourceString(Context context, int rid) {
        return context.getResources().getString(rid);
    }
}
