package com.bubble.execute.presenter.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public interface ILoginPresenter {
    /**
     * 用户登陆
     * @param userMail 用户账户
     * @param userPassword  用户密码
     */
    void doLogin(String userMail, String userPassword);
}
