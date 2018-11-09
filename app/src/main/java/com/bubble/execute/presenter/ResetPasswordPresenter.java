package com.bubble.execute.presenter;

import android.content.Context;

import com.bubble.execute.model.biz.PasswordBiz;
import com.bubble.execute.model.impl.IPasswordBiz;
import com.bubble.execute.model.listener.OnPasswordListener;
import com.bubble.execute.presenter.impl.IResetPasswordPresenter;
import com.bubble.execute.utils.SPManager;
import com.bubble.execute.view.impl.IResetPasswordActivity;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/5
 * 版权所有 © 徐长策
 */
public class ResetPasswordPresenter implements IResetPasswordPresenter {
    private Context mContext;
    private IResetPasswordActivity mIResetPasswordActivity;
    private IPasswordBiz mIPasswordBiz;

    public ResetPasswordPresenter(Context context, IResetPasswordActivity passwordActivity) {
        this.mContext = context;
        this.mIResetPasswordActivity = passwordActivity;
        this.mIPasswordBiz = new PasswordBiz(context);
    }

    @Override
    public void checkOldPassword() {
        mIPasswordBiz.checkUserPassword(SPManager.getUserID(), mIResetPasswordActivity.getOldPassword(), new OnPasswordListener.OnCheckPasswordListener() {
            @Override
            public void onCheckSuccess(String code, String msg) {
                mIResetPasswordActivity.intoUpdatePassword();
            }

            @Override
            public void onCheckFailed(String code, String msg) {
                mIResetPasswordActivity.recheckOldPassword();
            }
        });
    }

    @Override
    public void setNewPassword() {
        mIPasswordBiz.updateUserPassword(SPManager.getUserID(), mIResetPasswordActivity.getNewPassword(), mIResetPasswordActivity.getNewAgainPassword(), new OnPasswordListener.OnUpdatePasswordListener() {
            @Override
            public void onUpdateSuccess(String code, String msg, String newPassword) {
                mIResetPasswordActivity.saveNewPassword(newPassword);
                mIResetPasswordActivity.showUpdatePasswordSuccess();
                mIResetPasswordActivity.backLastActivity();
            }

            @Override
            public void onUpdateFailed(String code, String msg) {
                mIResetPasswordActivity.showUpdatePasswordFailed();
            }
        });
    }
}