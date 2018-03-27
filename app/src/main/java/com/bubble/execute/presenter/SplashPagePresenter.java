package com.bubble.execute.presenter;

import android.content.Context;

import com.bubble.execute.model.biz.LoginBiz;
import com.bubble.execute.model.impl.ILoginBiz;
import com.bubble.execute.model.listener.OnLoginListener;
import com.bubble.execute.presenter.impl.ISplashPagePresenter;
import com.bubble.execute.view.impl.ISplashActivityView;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/14
 * 版权所有 © 徐长策
 */

public class SplashPagePresenter implements ISplashPagePresenter{
    private Context mContext;
    private ISplashActivityView mSplashView;
    private ILoginBiz mILoginBiz;

    public SplashPagePresenter(Context context, ISplashActivityView splashView){
        this.mContext = context;
        this.mSplashView = splashView;
        this.mILoginBiz = new LoginBiz(mContext);
    }

    @Override
    public void userLogin() {
        mSplashView.showLoadingData();
        mILoginBiz.login(mSplashView.getMail(), mSplashView.getPassword(), mSplashView.getDeviceID(), mSplashView.getUserLoginType(), new OnLoginListener() {
            @Override
            public void onLoginSuccess(String msg) {
                mSplashView.toPasswordActivity();
                mSplashView.hideLoadingData();
                mSplashView.showReturnMsg(msg);
            }

            @Override
            public void onLoginFailed(String msg) {
                mSplashView.toLoginActivity();
                mSplashView.hideLoadingData();
                mSplashView.showReturnMsg(msg);
            }
        });
    }
}
