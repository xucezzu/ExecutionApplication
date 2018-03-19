package com.bubble.execute.presenter;

import com.bubble.execute.model.biz.biz_interface.IUserBiz;
import com.bubble.execute.presenter.presenter_interface.ISplashPagePresenter;
import com.bubble.execute.view_interface.ISplashActivityView;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/14
 * 版权所有 © 徐长策
 */

public class SplashPagePresenter implements ISplashPagePresenter{
    private ISplashActivityView mSplashView;
    private IUserBiz mUserBiz;

    public SplashPagePresenter(ISplashActivityView splashView){
        this.mSplashView = splashView;
    }

    @Override
    public void userLogin() {

    }
}
