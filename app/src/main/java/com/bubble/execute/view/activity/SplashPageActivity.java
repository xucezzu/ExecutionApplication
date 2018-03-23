package com.bubble.execute.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.presenter.SplashPagePresenter;
import com.bubble.execute.presenter.impl.ISplashPagePresenter;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.DeviceUtil;
import com.bubble.execute.utils.SPManager;
import com.bubble.execute.view.impl.ISplashActivityView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

/**
 * @author 徐长策
 * Date: 2018/02/31
 * E-mail: xuce_zzu@163.com
 * 版权所有 © 徐长策
 */

@SuppressLint("Registered")
public class SplashPageActivity extends BaseActivity implements ISplashActivityView{
    private TextView mTextLoading;
    private ISplashPagePresenter mISplashPagePresenter;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initActivity() {
        countDownTimer.start();
    }

    @Override
    public void initView() {
        mTextLoading = getViewById(R.id.text_loading);
        mTextLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        mISplashPagePresenter = new SplashPagePresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    @Override
    public String getMail() {
        return SPManager.getUserMail();
    }

    @Override
    public String getPassword() {
        return SPManager.getUserPassword();
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
        return ConstantUtil.USER_LOGIN_TYPE_UNUPDATA_DEVICEID;
    }

    @Override
    public void hideLoadingData() {
        mTextLoading.setVisibility(View.GONE);
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent(SplashPageActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void toPasswordActivity() {
        Intent intent = new Intent(SplashPageActivity.this, PasswordActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showReturnMsg(String msg) {
        StyleableToast.makeText(SplashPageActivity.this, msg, R.style.AppDefaultToast).show();
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
        }

        @Override
        public void onFinish() {
            // 完成倒计时后，需要根据条件跳转
            mISplashPagePresenter.userLogin();
        }
    };
}
