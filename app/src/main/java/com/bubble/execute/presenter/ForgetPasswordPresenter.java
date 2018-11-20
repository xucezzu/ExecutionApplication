package com.bubble.execute.presenter;

import android.content.Context;

import com.bubble.execute.model.biz.IdentifyCodeBiz;
import com.bubble.execute.model.biz.MailBiz;
import com.bubble.execute.model.biz.PasswordBiz;
import com.bubble.execute.model.impl.IIdentifyCodeBiz;
import com.bubble.execute.model.impl.IMailBiz;
import com.bubble.execute.model.impl.IPasswordBiz;
import com.bubble.execute.model.listener.OnIdentifyCodeListener;
import com.bubble.execute.model.listener.OnMailListener;
import com.bubble.execute.model.listener.OnPasswordListener;
import com.bubble.execute.presenter.impl.IForgetPasswordPresenter;
import com.bubble.execute.utils.ConstantUtil;
import com.bubble.execute.view.impl.IForgetPasswordActivity;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/14
 * 版权所有 © 徐长策
 */
public class ForgetPasswordPresenter implements IForgetPasswordPresenter {
    private Context mContext;
    private IForgetPasswordActivity mIForgetPasswordActivity;
    private IMailBiz mIMailBiz;
    private IIdentifyCodeBiz mIIdentifyCodeBiz;
    private IPasswordBiz mIPasswordBiz;

    public ForgetPasswordPresenter(Context context, IForgetPasswordActivity forgetPasswordActivity) {
        this.mContext = context;
        this.mIForgetPasswordActivity = forgetPasswordActivity;
        this.mIMailBiz = new MailBiz(mContext);
        this.mIIdentifyCodeBiz = new IdentifyCodeBiz(mContext);
        this.mIPasswordBiz = new PasswordBiz(mContext);
    }

    @Override
    public void checkMail() {
        mIMailBiz.checkMail(mIForgetPasswordActivity.getMail(), ConstantUtil.CheckMailType.TYPE_FROM_FORGET_PASSWORD, new OnMailListener() {
            @Override
            public void mailHasExist(String code, String msg) {
                mIForgetPasswordActivity.showIdentifyCodeEditText();
            }

            @Override
            public void mailNotExist(String code, String msg) {
                mIForgetPasswordActivity.reInputMail();
                mIForgetPasswordActivity.showMsg(msg);
            }
        });
    }

    @Override
    public void checkIdentifyCode() {
        mIIdentifyCodeBiz.checkIdentify(mIForgetPasswordActivity.getMail(), mIForgetPasswordActivity.getIdentifyCode(), ConstantUtil.IdentifyCodeType.TYPE_FROM_FORGET_PASSWORD, new OnIdentifyCodeListener.OnCheckListener() {
            @Override
            public void onCheckSuccess(String code, String msg) {
                mIForgetPasswordActivity.showPassword();
            }

            @Override
            public void onCheckFailed(String code, String msg) {
                mIForgetPasswordActivity.reInputIdentifyCode();
                mIForgetPasswordActivity.showMsg(msg);
            }
        });
    }

    @Override
    public void resetPassword() {
        mIPasswordBiz.resetUserPassword(mIForgetPasswordActivity.getMail(), mIForgetPasswordActivity.getNewPassword(), mIForgetPasswordActivity.getNewAgainPassword(), new OnPasswordListener.OnResetPasswordListener() {
            @Override
            public void onResetSuccess(String code, String msg) {
                mIForgetPasswordActivity.resetPasswordSuccess();
                mIForgetPasswordActivity.showMsg(msg);
            }

            @Override
            public void onResetFailed(String code, String msg) {
                mIForgetPasswordActivity.resetPasswordFailed();
                mIForgetPasswordActivity.showMsg(msg);
            }
        });
    }
}
