package com.bubble.execute.presenter.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/14
 * 版权所有 © 徐长策
 */
public interface IForgetPasswordPresenter {
    /**
     * 核对邮箱
     */
    void checkMail();

    /**
     * 核对验证码
     */
    void checkIdentifyCode();

    /**
     * 重置密码
     */
    void resetPassword();
}
