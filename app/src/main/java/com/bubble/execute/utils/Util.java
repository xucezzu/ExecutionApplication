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
     *
     * @param context Context
     * @param rid     资源的ID
     * @return 具体的文字
     */
    public static String getResourceString(Context context, int rid) {
        return context.getResources().getString(rid);
    }

    /**
     * 把日期中的中文星期转换成应为
     *
     * @param textCnDate 传入的含有中文星期的字符串
     * @return
     */
    public static String getDateFromCnToEn(String textCnDate) {
        String textEnDate = "";
        // eg. 2018/12/16 周日 16:57 => 2018/12/16 Sun. 16:57
        StringBuilder stringBuilder = new StringBuilder(textCnDate);
        String cnText = textCnDate.substring(11, 13);
        switch (cnText) {
            case "周一":
                stringBuilder.replace(11, 13, "Mon.");
                break;
            case "周二":
                stringBuilder.replace(11, 13, "Tues.");
                break;
            case "周三":
                stringBuilder.replace(11, 13, "Wed.");
                break;
            case "周四":
                stringBuilder.replace(11, 13, "Thur.");
                break;
            case "周五":
                stringBuilder.replace(11, 13, "Fri.");
                break;
            case "周六":
                stringBuilder.replace(11, 13, "Sat.");
                break;
            case "周日":
                stringBuilder.replace(11, 13, "Sun.");
                break;
            default:
                break;
        }
        textEnDate = stringBuilder.toString();
        return textEnDate;
    }
}
