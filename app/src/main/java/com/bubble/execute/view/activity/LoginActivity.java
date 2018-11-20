package com.bubble.execute.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.presenter.LoginPresenter;
import com.bubble.execute.presenter.impl.ILoginPresenter;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.DeviceUtil;
import com.bubble.execute.utils.LogUtil;
import com.bubble.execute.view.impl.ILoginActivity;
import com.bubble.execute.widget.CancelEditText;
import com.jakewharton.rxbinding2.view.RxView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @Author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/5
 * 版权所有 © 徐长策
 */

@SuppressLint("Registered")
public class LoginActivity extends BaseActivity implements ILoginActivity {
    private Context mContext = LoginActivity.this;
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
        mILoginPresenter = new LoginPresenter(mContext, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        setViewForLogin(isPageInLogin);
        mImageBack.setVisibility(View.GONE);
        mTextLoginLoading.setVisibility(View.GONE);
    }

    /**
     * 通过开关来控制页面的显示
     *
     * @param isClickLoginButton
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
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

    @SuppressLint("CheckResult")
    @Override
    public void initData() {
        RxView.clicks(mTextLogin).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        if (isPageInLogin) {
                            // 点击之前就在登录界面，这时点击就是提交登录数据
                            LogUtil.d("点击之前就在登录界面，此次点击需要上传登录数据......");
                            mILoginPresenter.userLogin();
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
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        if (!isPageInLogin) {
                            // 点击之前就在注册界面，这时点击就是提交注册数据
                            LogUtil.d("点击之前就在注册界面，此次点击需要上传注册数据......");
                            if (!TextUtils.isEmpty(getMail()) && !TextUtils.isEmpty(getPassword()) && !TextUtils.isEmpty(getTwoPassword())) {
                                LogUtil.d("注册数据全部不为空。。。");
                                // 先判断邮箱是否格式正确
                                if (!ConstantUtil.isEmail(getMail())) {
                                    StyleableToast.makeText(LoginActivity.this, getResources().getString(R.string.login_mail_error), R.style.AppDefaultToast).show();
                                    return;
                                }
                                // 再次判断密码格式是否正确
                                if (!ConstantUtil.checkPassword(LoginActivity.this, getPassword())) {
                                    return;
                                }
                                // 再次判断两次输入的密码是否一致
                                if (!getPassword().equals(getTwoPassword())) {
                                    StyleableToast.makeText(LoginActivity.this, getResources().getString(R.string.register_error_password), R.style.AppDefaultToast).show();
                                    return;
                                }
                                mILoginPresenter.userRegister();
                            } else {
                                LogUtil.d("注册数据存在空数据。。。");
                                StyleableToast.makeText(LoginActivity.this, getResources().getString(R.string.register_error_empty), R.style.AppDefaultToast).show();
                            }
                        } else {
                            // 之前在登录界面，这时需要变换界面显示
                            isPageInLogin = false;
                            setViewForLogin(false);
                            LogUtil.d("点击之前在登录界面，此次点击切换到注册界面......");
                        }
                    }
                });

        RxView.clicks(mTextForgetPassword).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        mILoginPresenter.forgetPassword();
                    }
                });
    }

    @Override
    public String getMail() {
        return mEditMail.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEditPassword.getText().toString();
    }

    @Override
    public String getTwoPassword() {
        return mEditPasswordNext.getText().toString();
    }

    @Override
    public String getDeviceID() {
        return DeviceUtil.getAndroidID(this);
    }

    @Override
    public String getUserLoginType() {
        return ConstantUtil.USER_LOGIN_TYPE_UPDATE_DEVICE_ID;
    }

    @Override
    public void clearAllEditText() {

    }

    @Override
    public void clearAllPasswordEditText() {

    }

    @Override
    public void toPasswordActivity() {
        Intent intent = new Intent(LoginActivity.this, SafePasswordActivity.class);
        intent.putExtra(ConstantUtil.PASSWORD_ACTIVITY_TYPE, 1);
        startActivity(intent);
        finish();
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void toForgetPasswordActivity() {
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void toIdentifyUserActivity() {
        Intent intent = new Intent(LoginActivity.this, IdentifyUserActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoginLoading() {
        mTextLoginLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginLoadingData() {
        mTextLoginLoading.setVisibility(View.GONE);
    }

    @Override
    public void showRegisterLoading() {
        mTextLoginLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRegisterLoadingData() {
        mTextLoginLoading.setVisibility(View.GONE);
    }

    @Override
    public void showReturnMsg(String msg) {
        // 展示登录的结果，但是为了照顾逻辑，有时此处不易显示Toast
        StyleableToast.makeText(LoginActivity.this, msg, R.style.AppDefaultToast).show();
    }
}
