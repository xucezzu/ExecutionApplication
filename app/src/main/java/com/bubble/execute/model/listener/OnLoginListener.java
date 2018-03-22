package com.bubble.execute.model.listener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/15
 * 版权所有 © 徐长策
 */

public interface OnLoginListener {
    /**
     * 登录成功
     */
    void onLoginSuccess();

    /**
     * 登录失败
     */
    void onLoginFailed();
}
