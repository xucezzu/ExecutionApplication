package com.bubble.execute.model.listener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public interface OnRegisterListener {
    /**
     * 登录成功
     *
     * @param msg 成功信息
     */
    void onRegisterSuccess(String msg);

    /**
     * 登录失败
     *
     * @param msg 失败信息
     */
    void onRegisterFailed(String msg);
}
