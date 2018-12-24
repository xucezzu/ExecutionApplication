package com.bubble.execute.view.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.ScreenListener;

/**
 * @author 徐长策
 * Date: 2018/02/28
 * E-mail: xuce_zzu@163.com
 * 版权所有 © 徐长策
 */

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {
    private ScreenListener mScreenListener;
    protected boolean root;
    private String nameClass = "";

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                setContentView();
                initActivity();
                initView();
                initData();
                return false;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(this.getClass().getSimpleName() + ": " + "onRestart()");
        // 监听屏幕有无锁屏
        mScreenListener = new ScreenListener(this);
        nameClass = this.getClass().getSimpleName();
        if(!ConstantUtil.PASSWORD_ACTIVITY.equals(nameClass)){
            mScreenListener.begin(new ScreenListener.ScreenStateListener() {
                @Override
                public void onScreenOn() {
                    LogUtil.d("【" + nameClass + "】屏幕打开了~");
                }

                @Override
                public void onScreenOff() {
                    LogUtil.d("【" + nameClass + "】屏幕关闭了~");
                }

                @Override
                public void onUserPresent() {
                    LogUtil.d("【" + nameClass + "】屏幕解锁了~");
                    toPasswordActivity();
                }
            });
        }
    }

    public void toPasswordActivity() {
        Intent intent = new Intent(this, SafePasswordActivity.class);
        intent.putExtra(ConstantUtil.PASSWORD_ACTIVITY_TYPE, 3);
        startActivity(intent);
    }

    /**
     * 设置布局文件
     * 就是setContentView(R.layout.XXX)
     */
    public abstract void setContentView();

    /**
     * 初始化Activity
     * 主要功能是写View初始化之前的代码
     */
    public abstract void initActivity();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 查找组件
     *
     * @param viewId：View的ID
     * @return View
     */
    @SuppressWarnings("unchecked")
    protected <view extends View> view getViewById(int viewId) {
        return (view) findViewById(viewId);
    }

    public boolean isRoot() {
        return root;
    }
}