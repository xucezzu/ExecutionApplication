package com.bubble.execute.presenter.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/10/29
 * 版权所有 © 徐长策
 */
public interface IMainPresenter {

    /**
     * 检查是否通过验证
     */
    void checkIdentifyUser();

    /**
     * 检查是否设置安全密码
     */
    void checkSetSafePassword();
}
