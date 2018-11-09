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
import com.bubble.execute.presenter.ResetPasswordPresenter;
import com.bubble.execute.presenter.impl.IResetPasswordPresenter;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.utils.SPManager;
import com.bubble.execute.utils.Util;
import com.bubble.execute.view.impl.IResetPasswordActivity;
import com.bubble.execute.widget.CancelEditText;
import com.jakewharton.rxbinding2.view.RxView;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/26
 * 版权所有 © 徐长策
 */

@SuppressLint("Registered")
public class ResetPasswordActivity extends BaseActivity implements IResetPasswordActivity {
    private Context mContext = ResetPasswordActivity.this;
    private ImageView mImageBack, mImageRight, mImageTitle;
    private TextView mTextTitle;
    private Button mButtonResetPassword;
    private CancelEditText mCancelEditTextOld, mCancelEditTextNew, mCancelEditTextNewAgain;
    private IResetPasswordPresenter mIResetPasswordPresenter;
    /**
     * 设置一个布尔类型值，标记是否已经验证过旧密码
     */
    private boolean isCheckedOldPassword = false;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_reset_password);
    }

    @Override
    public void initActivity() {
        mIResetPasswordPresenter = new ResetPasswordPresenter(mContext, this);
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
        mTextTitle.setText(Util.getResourceString(mContext, R.string.reset_password_title));
        mCancelEditTextOld = findViewById(R.id.edit_text_password_old);
        mCancelEditTextNew = findViewById(R.id.edit_text_password_new);
        mCancelEditTextNewAgain = findViewById(R.id.edit_text_again_password_new);
        mButtonResetPassword = findViewById(R.id.button_reset);
        setNotCheck();
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
                        if (!isCheckedOldPassword) {
                            // 核对老密码
                            checkOldPassword();
                        } else {
                            // 判断密码格式是否正确
                            if (!ConstantUtil.checkPassword(mContext, getNewPassword())) {
                                return;
                            }
                            // 判断两次输入的密码是否一致
                            if (!getNewPassword().equals(getNewAgainPassword())) {
                                StyleableToast.makeText(mContext, getResources().getString(R.string.register_error_password), R.style.AppDefaultToast).show();
                                return;
                            }
                            // 更新密码
                            updatePassword();
                        }
                    }
                });
    }

    private void setNotCheck() {
        mCancelEditTextOld.setVisibility(View.VISIBLE);
        mCancelEditTextNew.setVisibility(View.GONE);
        mCancelEditTextNewAgain.setVisibility(View.GONE);
        mButtonResetPassword.setText(Util.getResourceString(mContext, R.string.reset_password_next));
    }

    private void setHasChecked() {
        mCancelEditTextOld.setVisibility(View.GONE);
        mCancelEditTextNew.setVisibility(View.VISIBLE);
        mCancelEditTextNewAgain.setVisibility(View.VISIBLE);
        mButtonResetPassword.setText(Util.getResourceString(mContext, R.string.reset_password_commit));
    }

    @Override
    public void backLastActivity() {
        finish();
    }

    @Override
    public String getOldPassword() {
        return mCancelEditTextOld.getText().toString();
    }

    @Override
    public String getNewPassword() {
        return mCancelEditTextNew.getText().toString();
    }

    @Override
    public String getNewAgainPassword() {
        return mCancelEditTextNewAgain.getText().toString();
    }

    @Override
    public void checkOldPassword() {
        mIResetPasswordPresenter.checkOldPassword();
    }

    @Override
    public void recheckOldPassword() {
        // 校验老密码失败，需要清空原密码重新输入
        isCheckedOldPassword = false;
        mCancelEditTextOld.setText("");
    }

    @Override
    public void intoUpdatePassword() {
        // 校验老密码成功，进入更新密码页面
        isCheckedOldPassword = true;
        setHasChecked();
    }

    @Override
    public void updatePassword() {
        mIResetPasswordPresenter.setNewPassword();
    }

    @Override
    public void showUpdatePasswordFailed() {
        mCancelEditTextNew.setText("");
        mCancelEditTextNewAgain.setText("");
        StyleableToast.makeText(mContext, getResources().getString(R.string.reset_password_update_failed), R.style.AppDefaultToast).show();
    }

    @Override
    public void showUpdatePasswordSuccess() {
        StyleableToast.makeText(mContext, getResources().getString(R.string.reset_password_update_success), R.style.AppDefaultToast).show();
    }

    @Override
    public void saveNewPassword(String newPassword) {
        SPManager.setUserPassword(newPassword);
    }
}
