package com.bubble.execute.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/4/4
 * 版权所有 © 徐长策
 */
public class ScreenListener {
    private Context mContext;
    private ScreenBroadcastReceiver mScreenBroadcastReceiver;
    private ScreenStateListener mScreenStateListener;

    public ScreenListener(Context context) {
        mContext = context;
        mScreenBroadcastReceiver = new ScreenBroadcastReceiver();
    }

    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private String action = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            assert action != null;
            switch (action) {
                case Intent.ACTION_SCREEN_ON:
                    mScreenStateListener.onScreenOn();
                    break;

                case Intent.ACTION_SCREEN_OFF:
                    mScreenStateListener.onScreenOff();
                    break;

                case Intent.ACTION_USER_PRESENT:
                    mScreenStateListener.onUserPresent();
                    break;

                default:
                    break;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public void begin(ScreenStateListener listener) {
        mScreenStateListener = listener;
        registerListener();
        getScreenState(listener);
    }

    /**
     * 获取screen状态
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    private void getScreenState(ScreenStateListener listener) {
        PowerManager manager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        assert manager != null;
        if (manager.isInteractive()) {
            if (listener != null) {
                listener.onScreenOn();
            }
        } else {
            if (listener != null) {
                listener.onScreenOff();
            }
        }
    }

    /**
     * 停止screen状态监听
     */
    public void unregisterListener() {
        mContext.unregisterReceiver(mScreenBroadcastReceiver);
    }

    /**
     * 启动screen状态广播接收器
     */
    private void registerListener() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        mContext.registerReceiver(mScreenBroadcastReceiver, filter);
    }

    /**
     * 返回给调用者屏幕信息
     */
    public interface ScreenStateListener {
        /**
         * 屏幕开启
         */
        void onScreenOn();

        /**
         * 屏幕关闭
         */
        void onScreenOff();

        /**
         * 屏幕解锁
         */
        void onUserPresent();
    }
}
