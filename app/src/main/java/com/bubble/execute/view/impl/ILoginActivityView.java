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
     * 获取用户邮箱
     * @return 邮箱
     */
    String getMail();

    /**
     * 获取密码
     * @return 密码
     */
    String getPassword();

    /**
     * 注册时获取再次输入的密码
     * @return 再次输入的密码
     */
    String getTwoPassword();

    /**
     * 跳转到忘记密码界面
     */
    void toForgetPasswordActivity();

    /**
     * 邮箱输入错误 || 注册失败， 全部清空输入框
     */
    void clearAllEditText();

    /**
     * 密码输入错误，全部清除密码输入框
     */
    void clearAllPasswordEditText();

    /**
     * 注册成功||登录成功但未设置安全密码，需要跳转到密码设置界面
     */
    void toPasswordActivity();

    /**
     * 登录成功，需要跳转到主界面
     */
    void toMainActivity();

    /**
     * 忘记密码，跳转到密码重置页面
     */
    void toResetPasswordActivity();

    /**
     * 显示“正在登录...”
     */
    void showLoadingData();

    /**
     * 隐藏“正在登录...”
     */
    void hideLoadingData();
}
