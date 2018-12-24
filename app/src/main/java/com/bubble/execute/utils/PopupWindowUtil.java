package com.bubble.execute.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/29
 * 版权所有 © 徐长策
 */
public class PopupWindowUtil {
    /**
     * 用volatile关键字修饰一个共享变量，保证修改的值立刻被更新
     */
    private static volatile PopupWindowUtil sPopupWindowUtil = null;

    private PopupWindowUtil() {

    }

    /**
     * 双重检测
     *
     * @return
     */
    public static PopupWindowUtil getInstance() {
        if (sPopupWindowUtil == null) {
            synchronized (DialogUtil.class) {
                if (sPopupWindowUtil == null) {
                    sPopupWindowUtil = new PopupWindowUtil();
                }
            }
        }
        return sPopupWindowUtil;
    }

    /**
     * 展示带星期的日期选择框
     * @param context
     */
    public void showDateForWeekPopupWindow(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
    }
}
