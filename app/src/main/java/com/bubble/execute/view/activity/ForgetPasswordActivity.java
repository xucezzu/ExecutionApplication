package com.bubble.execute.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bubble.execute.R;
import com.bubble.execute.presenter.ForgetPasswordPresenter;
import com.bubble.execute.presenter.impl.IForgetPasswordPresenter;
import com.bubble.execute.utils.Util;
import com.bubble.execute.view.impl.IForgetPasswordActivity;
import com.bubble.execute.widget.CancelEditText;
import com.jakewharton.rxbinding2.view.RxView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/9/17
 * 版权所有 © 徐长策
 */
@SuppressLint("Registered")
public class ForgetPasswordActivity extends BaseActivity implements IForgetPasswordActivity {
    private Context mContext = ForgetPasswordActivity.this;
    private ImageView mImageBack, mImageRight, mImageTitle;
    private TextView mTextTitle;
    private Button mButtonResetPassword;
    private CancelEditText mEditTextMail, mEditTextCode, mEditTextNewPassword, mEditTextNewAgainPassword;
    /**
     * 验证邮箱和验证码
     */
    private boolean isCheckedMail = false, isCheckedCode = false;
    private IForgetPasswordPresenter mIForgetPasswordPresenter;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_forget);
    }

    @Override
    public void initActivity() {
        mIForgetPasswordPresenter = new ForgetPasswordPresenter(mContext, this);
    }

    @Override
    public void initView() {
        mImageBack = findViewById(R.id.view_image_left);
        mImageRight = findViewById(R.id.view_image_right);
        mImageTitle = findViewById(R.id.view_image_title);
        mTextTitle = findViewById(R.id.view_text_title);
        mImageRight.setVisibility(View.GONE);
        mImageTitle.setVisibility(View.GONE);
        mTextTitle.setVisibility(View.VISIBLE);
        mTextTitle.setText(Util.getResourceString(mContext, R.string.forget_password_title));
        mEditTextMail = findViewById(R.id.edit_text_mail);
        mEditTextCode = findViewById(R.id.edit_text_code);
        mEditTextNewPassword = findViewById(R.id.edit_text_password_new);
        mEditTextNewAgainPassword = findViewById(R.id.edit_text_again_password_new);
        mButtonResetPassword = findViewById(R.id.button_reset);
        showMailEditText();
    }

    @SuppressLint("CheckResult")
    @Override
    public void initData() {
        RxView.clicks(mImageBack).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        finish();
                    }
                });

        RxView.clicks(mButtonResetPassword).throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void accept(Object o) throws Exception {
                        if (!isCheckedMail && !isCheckedCode) {
                            checkMail();
                        } else if (isCheckedMail && !isCheckedCode) {
                            checkIdentifyCode();
                        } else {
                            resetPassword();
                        }
                    }
                });
    }

    @Override
    public void showMailEditText() {
        mEditTextMail.setVisibility(View.VISIBLE);
        mEditTextCode.setVisibility(View.GONE);
        mEditTextNewPassword.setVisibility(View.GONE);
        mEditTextNewAgainPassword.setVisibility(View.GONE);
        mButtonResetPassword.setText(Util.getResourceString(mContext, R.string.forget_password_next));
    }

    @Override
    public String getMail() {
        return mEditTextMail.getText().toString();
    }

    @Override
    public void checkMail() {
        mIForgetPasswordPresenter.checkMail();
    }

    @Override
    public void showIdentifyCodeEditText() {
        isCheckedMail = true;
        mEditTextMail.setVisibility(View.GONE);
        mEditTextCode.setVisibility(View.VISIBLE);
        mEditTextNewPassword.setVisibility(View.GONE);
        mEditTextNewAgainPassword.setVisibility(View.GONE);
        mButtonResetPassword.setText(Util.getResourceString(mContext, R.string.forget_password_next));
    }

    @Override
    public void reInputMail() {
        isCheckedMail = false;
        mEditTextMail.setText("");
    }

    @Override
    public String getIdentifyCode() {
        return mEditTextCode.getText().toString();
    }

    @Override
    public void checkIdentifyCode() {
        mIForgetPasswordPresenter.checkIdentifyCode();
    }

    @Override
    public void showPassword() {
        isCheckedCode = true;
        mEditTextMail.setVisibility(View.GONE);
        mEditTextCode.setVisibility(View.GONE);
        mEditTextNewPassword.setVisibility(View.VISIBLE);
        mEditTextNewAgainPassword.setVisibility(View.VISIBLE);
        mButtonResetPassword.setText(Util.getResourceString(mContext, R.string.forget_password_update));
    }

    @Override
    public void reInputIdentifyCode() {
        isCheckedCode = false;
        mEditTextCode.setText("");
    }

    @Override
    public String getNewPassword() {
        return mEditTextNewPassword.getText().toString();
    }

    @Override
    public String getNewAgainPassword() {
        return mEditTextNewAgainPassword.getText().toString();
    }

    @Override
    public void resetPassword() {
        mIForgetPasswordPresenter.resetPassword();
    }

    @Override
    public void resetPasswordSuccess() {
        finish();
    }

    @Override
    public void resetPasswordFailed() {
        mEditTextNewPassword.setText("");
        mEditTextNewAgainPassword.setText("");
    }

    @Override
    public void showMsg(String msg) {
        StyleableToast.makeText(mContext, msg, R.style.AppDefaultToast).show();
    }
}
