package com.bubble.execute.presenter;

import com.bubble.execute.model.biz.LoginBiz;
import com.bubble.execute.model.impl.ILoginBiz;
import com.bubble.execute.presenter.impl.ILoginPresenter;
import com.bubble.execute.view.impl.ILoginActivityView;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public class LoginPresenter implements ILoginPresenter{
    private ILoginActivityView mILoginActivityView;
    private ILoginBiz mILoginBiz;

    public LoginPresenter(ILoginActivityView loginActivityView){
        this.mILoginActivityView = loginActivityView;
        this.mILoginBiz = new LoginBiz();
    }

    @Override
    public void userLogin() {

    }

    @Override
    public void userRegister() {

    }
}
