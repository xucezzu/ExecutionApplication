package com.bubble.execute;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public class ExApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static ExApplication mApplication;

    public static ExApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mApplication = this;
    }
}
