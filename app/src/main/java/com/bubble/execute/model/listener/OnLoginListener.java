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
     *
     * @param msg 成功信息
     */
    void onLoginSuccess(String msg);

    /**
     * 登录失败
     *
     * @param msg 失败信息
     */
    void onLoginFailed(String msg);
}
