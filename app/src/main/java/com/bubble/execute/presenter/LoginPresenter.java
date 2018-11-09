package com.bubble.execute.presenter;

import android.content.Context;

import com.bubble.execute.model.biz.LoginBiz;
import com.bubble.execute.model.biz.RegisterBiz;
import com.bubble.execute.model.impl.ILoginBiz;
import com.bubble.execute.model.impl.IRegisterBiz;
import com.bubble.execute.model.listener.OnLoginListener;
import com.bubble.execute.model.listener.OnRegisterListener;
import com.bubble.execute.presenter.impl.ILoginPresenter;
import com.bubble.execute.view.impl.ILoginActivity;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public class LoginPresenter implements ILoginPresenter{
    private Context mContext;
    private ILoginActivity mILoginActivity;
    private ILoginBiz mILoginBiz;
    private IRegisterBiz mIRegisterBiz;

    public LoginPresenter(Context context, ILoginActivity loginActivityView){
        this.mContext = context;
        this.mILoginActivity = loginActivityView;
        this.mILoginBiz = new LoginBiz(mContext);
        this.mIRegisterBiz = new RegisterBiz(mContext);
    }

    @Override
    public void userLogin() {
        mILoginActivity.showLoginLoading();
        mILoginBiz.login(mILoginActivity.getMail(), mILoginActivity.getPassword(), mILoginActivity.getDeviceID(), mILoginActivity.getUserLoginType(), new OnLoginListener() {
            @Override
            public void onLoginSuccess(String msg) {
                mILoginActivity.toMainActivity();
                mILoginActivity.hideLoginLoadingData();
                mILoginActivity.showReturnMsg(msg);
            }

            @Override
            public void onLoginFailed(String msg) {
                mILoginActivity.hideLoginLoadingData();
                mILoginActivity.showReturnMsg(msg);
            }
        });
    }

    @Override
    public void userRegister() {
        mILoginActivity.showRegisterLoading();
        mIRegisterBiz.register(mILoginActivity.getMail(), mILoginActivity.getPassword(), mILoginActivity.getDeviceID(), new OnRegisterListener() {
            @Override
            public void onRegisterSuccess(String msg) {
                mILoginActivity.hideRegisterLoadingData();
                mILoginActivity.showReturnMsg(msg);
                mILoginActivity.toMainActivity();
            }

            @Override
            public void onRegisterFailed(String msg) {
                mILoginActivity.hideRegisterLoadingData();
                mILoginActivity.showReturnMsg(msg);
                mILoginActivity.toIdentifyUserActivity();
            }
        });
    }

    @Override
    public void resetPassword() {
        mILoginActivity.toResetPasswordActivity();
    }
}
