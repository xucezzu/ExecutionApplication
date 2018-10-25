package com.bubble.execute.presenter;

import android.content.Context;

import com.bubble.execute.model.biz.LoginBiz;
import com.bubble.execute.model.biz.RegisterBiz;
import com.bubble.execute.model.impl.ILoginBiz;
import com.bubble.execute.model.impl.IRegisterBiz;
import com.bubble.execute.model.listener.OnLoginListener;
import com.bubble.execute.model.listener.OnRegisterListener;
import com.bubble.execute.presenter.impl.ILoginPresenter;
import com.bubble.execute.view.impl.ILoginActivityView;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public class LoginPresenter implements ILoginPresenter{
    private Context mContext;
    private ILoginActivityView mILoginActivityView;
    private ILoginBiz mILoginBiz;
    private IRegisterBiz mIRegisterBiz;

    public LoginPresenter(Context context, ILoginActivityView loginActivityView){
        this.mContext = context;
        this.mILoginActivityView = loginActivityView;
        this.mILoginBiz = new LoginBiz(mContext);
        this.mIRegisterBiz = new RegisterBiz(mContext);
    }

    @Override
    public void userLogin() {
        mILoginActivityView.showLoginLoading();
        mILoginBiz.login(mILoginActivityView.getMail(), mILoginActivityView.getPassword(), mILoginActivityView.getDeviceID(), mILoginActivityView.getUserLoginType(), new OnLoginListener() {
            @Override
            public void onLoginSuccess(String msg) {
                mILoginActivityView.toMainActivity();
                mILoginActivityView.hideLoginLoadingData();
                mILoginActivityView.showReturnMsg(msg);
            }

            @Override
            public void onLoginFailed(String msg) {
                mILoginActivityView.hideLoginLoadingData();
                mILoginActivityView.showReturnMsg(msg);
            }
        });
    }

    @Override
    public void userRegister() {
        mILoginActivityView.showRegisterLoading();
        mIRegisterBiz.register(mILoginActivityView.getMail(), mILoginActivityView.getPassword(), mILoginActivityView.getDeviceID(), new OnRegisterListener() {
            @Override
            public void onRegisterSuccess(String msg) {
                mILoginActivityView.hideRegisterLoadingData();
                mILoginActivityView.showReturnMsg(msg);
            }

            @Override
            public void onRegisterFailed(String msg) {
                mILoginActivityView.hideRegisterLoadingData();
                mILoginActivityView.showReturnMsg(msg);
            }
        });
    }

    @Override
    public void resetPassword() {
        mILoginActivityView.toResetPasswordActivity();
    }
}
