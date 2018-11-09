package com.bubble.execute.presenter.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/5
 * 版权所有 © 徐长策
 */
public interface IResetPasswordPresenter {
    /**
     * 核对老密码是否正确
     */
    void checkOldPassword();

    /**
     * 重置新密码
     */
    void setNewPassword();
}
