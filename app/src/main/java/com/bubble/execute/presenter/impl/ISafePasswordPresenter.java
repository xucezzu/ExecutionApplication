package com.bubble.execute.presenter.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public interface ISafePasswordPresenter {

    /**
     * 判断是否存在安全密码
     */
    void isExistSafePassword();

    /**
     * 判断安全密码是否输入正确
     */
    void checkSafePassword();

    /**
     * 更新安全密码
     */
    void updateSafePassword();
}
