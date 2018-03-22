package com.bubble.execute.view.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 * <p>
 *     UI只负责展示界面效果的，故每个方法只能体现页面逻辑的变化
 * </p>
 */

public interface ILoginActivityView {
    /**
     * 设置注册页面可见
     */
    void onSetRegisterPageVisible();

    /**
     * 设置登陆界面可见
     */
    void onSetLoginPageVisible();

    /**
     * 设置登陆结果
     */
    void onLoginResult();

    /**
     * 设置注册结果
     */
    void onRegisterResult();


}
