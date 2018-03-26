package com.bubble.execute.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.presenter.LoginPresenter;
import com.bubble.execute.presenter.impl.ILoginPresenter;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.view.impl.ILoginActivityView;
import com.bubble.execute.widget.CancelEditText;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @Author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/5
 * 版权所有 © 徐长策
 */

@SuppressLint("Registered")
public class LoginActivity extends BaseActivity implements ILoginActivityView{
    private ImageView mImageBack;
    private TextView mTextRegister, mTextLogin, mTextForgetPassword, mTextLoginLoading;
    private CancelEditText mEditMail, mEditPassword, mEditPasswordNext;
    /**
     * 标记是否在登录页面，true && false
     */
    private boolean isPageInLogin = true;

    /**
     * 0 && 1分别代表从闪屏页和密码页面跳转
     */
    private int isFromSplashActivity = 0;
    private int isFromPasswordActivity = 1;
    private int typeFromActivity;

    private ILoginPresenter mILoginPresenter;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initActivity() {
        // 获取从上一个页面传递的值
        Intent intent = getIntent();
        typeFromActivity = intent.getIntExtra(ConstantUtil.LOGIN_ACTIVITY_TYPE, 0);
        mILoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void initView() {
        mImageBack = getViewById(R.id.image_back);
        mTextLogin = getViewById(R.id.button_login);
        mTextRegister = getViewById(R.id.button_register);
        mTextForgetPassword = getViewById(R.id.text_forget_password);
        mTextLoginLoading = getViewById(R.id.text_loading);
        mEditMail = getViewById(R.id.edit_text_user);
        mEditPassword = getViewById(R.id.edit_text_password);
        mEditPasswordNext = getViewById(R.id.edit_text_again_password);
        mTextForgetPassword = getViewById(R.id.text_forget_password);
        setViewForLogin(isPageInLogin);
    }

    /**
     * 通过开关来控制页面的显示
     *
     * @param isClickLoginButton
     */
    public void setViewForLogin(boolean isClickLoginButton) {
        if (isClickLoginButton) {
            // 登录页面
            mTextForgetPassword.setVisibility(View.VISIBLE);
            mEditPasswordNext.setVisibility(View.GONE);
            mTextRegister.setTextColor(getColor(R.color.colorLoginButtonTextNot));
            mTextLogin.setTextColor(getColor(R.color.colorLoginButtonText));
            mEditPassword.setHint(R.string.login_password_hint);
        } else {
            // 注册页面
            mTextForgetPassword.setVisibility(View.GONE);
            mEditPasswordNext.setVisibility(View.VISIBLE);
            mTextRegister.setTextColor(getColor(R.color.colorLoginButtonText));
            mTextLogin.setTextColor(getColor(R.color.colorLoginButtonTextNot));
            mEditPassword.setHint(R.string.register_password_hint);
            mEditPasswordNext.setHint(R.string.register_password_hint_again);
        }
    }

    @Override
    public void initData() {
        RxView.clicks(mTextLogin).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (isPageInLogin) {
                            // 点击之前就在登录界面，这时点击就是提交登录数据
                            LogUtil.d("点击之前就在登录界面，此次点击需要上传登录数据......");
                        } else {
                            // 之前在注册界面，这时需要变换界面显示
                            isPageInLogin = true;
                            setViewForLogin(isPageInLogin);
                            LogUtil.d("点击之前在注册界面，此次点击切换到登录界面......");
                        }

                    }
                });

        RxView.clicks(mTextRegister).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (!isPageInLogin) {
                            // 点击之前就在注册界面，这时点击就是提交注册数据
                            LogUtil.d("点击之前就在注册界面，此次点击需要上传注册数据......");
                        } else {
                            // 之前在登录界面，这时需要变换界面显示
                            isPageInLogin = false;
                            setViewForLogin(false);
                            LogUtil.d("点击之前在登录界面，此次点击切换到注册界面......");
                        }

                    }
                });
    }

    @Override
    public String getMail() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getTwoPassword() {
        return null;
    }

    @Override
    public void toForgetPasswordActivity() {

    }

    @Override
    public void clearAllEditText() {

    }

    @Override
    public void clearAllPasswordEditText() {

    }

    @Override
    public void toPasswordActivity() {

    }

    @Override
    public void toMainActivity() {

    }

    @Override
    public void toResetPasswordActivity() {

    }

    @Override
    public void showLoadingData() {

    }

    @Override
    public void hideLoadingData() {

    }
}
