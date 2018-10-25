package com.bubble.execute.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.presenter.SplashPagePresenter;
import com.bubble.execute.presenter.impl.ISplashPagePresenter;
import com.bubble.execute.thread.DefaultExecutorSupplier;
import com.bubble.execute.thread.task.SpeechTask;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.DeviceUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.utils.SpManager;
import com.bubble.execute.view.impl.ISplashActivityView;
import com.bubble.execute.widget.ExecCircleProgressBar;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * @author 徐长策
 * Date: 2018/02/31
 * E-mail: xuce_zzu@163.com
 * 版权所有 © 徐长策
 */

@SuppressLint("Registered")
public class SplashPageActivity extends BaseActivity implements ISplashActivityView {
    private TextView mTextLoading;
    private ExecCircleProgressBar mCircleProgressBar;
    private ISplashPagePresenter mISplashPagePresenter;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initActivity() {
        initPermission();
        countDownTimer.start();
    }

    @Override
    public void initView() {
        mTextLoading = getViewById(R.id.text_loading);
        mTextLoading.setVisibility(View.VISIBLE);
        mCircleProgressBar = getViewById(R.id.circle_bar);
        mCircleProgressBar.setFirstColor(Color.parseColor("#bdbdbd"));
        mCircleProgressBar.setSecondColor(Color.parseColor("#000000"));
        mCircleProgressBar.setProgress(100, true);
        // 如果持久化的用户登录数据不完整，则直接隐藏“数据加载...”字段
        if (TextUtils.isEmpty(SpManager.getUserMail()) || TextUtils.isEmpty(SpManager.getUserPassword())) {
            hideLoadingData();
        }
    }

    @Override
    public void initData() {
        mISplashPagePresenter = new SplashPagePresenter(SplashPageActivity.this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    @Override
    public String getMail() {
        return SpManager.getUserMail();
    }

    @Override
    public String getPassword() {
        return SpManager.getUserPassword();
    }

    @Override
    public void showLoadingData() {
        mTextLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public String getDeviceID() {
        return DeviceUtil.getAndroidID(this);
    }

    @Override
    public String getUserLoginType() {
        return ConstantUtil.USER_LOGIN_TYPE_UN_UPDATE_DEVICE_ID;
    }

    @Override
    public void hideLoadingData() {
        mTextLoading.setVisibility(View.GONE);
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent(SplashPageActivity.this, LoginActivity.class);
        intent.putExtra(ConstantUtil.LOGIN_ACTIVITY_TYPE, 0);
        startActivity(intent);
        finish();
    }

    @Override
    public void toPasswordActivity() {
        Intent intent = new Intent(SplashPageActivity.this, PasswordActivity.class);
        intent.putExtra(ConstantUtil.PASSWORD_ACTIVITY_TYPE, 0);
        startActivity(intent);
        finish();
    }

    @Override
    public void showReturnMsg(String msg) {
        // 展示登录的结果，但是为了照顾逻辑，有时此处不易显示Toast
        StyleableToast.makeText(SplashPageActivity.this, msg, R.style.AppDefaultToast).show();
        // 语音（测试）
        Future future = DefaultExecutorSupplier.getInstance()
                .forLightWeightBackgroundTasks()
                .submit(new SpeechTask(SplashPageActivity.this, msg));
    }

    /**
     * 利用倒计时控件实现
     * 1，onTick: 在时间结束之前，返回还剩余多长时间结束，如 String.valueOf((int) (millisUntilFinished / 1000))
     * 2，onFinish: 时间结束后，要完成的动作
     */
    private CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            // 如果需要，该值可以显示在广告页面上
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            mCircleProgressBar.setCenterText(value);
        }

        @Override
        public void onFinish() {
            // 完成倒计时后，需要根据条件跳转
            if (!TextUtils.isEmpty(SpManager.getUserMail()) && !TextUtils.isEmpty(SpManager.getUserPassword())) {
                mISplashPagePresenter.userLogin();
            } else {
                hideLoadingData();
                toLoginActivity();
            }
        }
    };

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
            }
        }

        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }
    }
}