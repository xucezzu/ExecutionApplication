package com.bubble.execute.view.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/14
 * 版权所有 © 徐长策
 */
public interface IForgetPasswordActivity {
    /**
     * 展示邮箱输入
     */
    void showMailEditText();

    /**
     * 获取邮箱
     *
     * @return mail
     */
    String getMail();

    /**
     * 验证邮箱
     */
    void checkMail();

    /**
     * 展示验证码输入
     */
    void showIdentifyCodeEditText();

    /**
     * 重新输入邮箱
     */
    void reInputMail();

    /**
     * 获取验证码
     */
    String getIdentifyCode();

    /**
     * 验证码核对
     */
    void checkIdentifyCode();

    /**
     * 展示密码输入
     */
    void showPassword();

    /**
     * 重新输入验证码
     */
    void reInputIdentifyCode();

    /**
     * 获取新密码
     */
    String getNewPassword();

    /**
     * 再次获取新密码
     */
    String getNewAgainPassword();

    /**
     * 上传更新的密码
     */
    void resetPassword();

    /**
     * 重置密码成功
     */
    void resetPasswordSuccess();

    /**
     * 重置密码失败
     */
    void resetPasswordFailed();

    /**
     * 显示提示信息
     *
     * @param msg
     */
    void showMsg(String msg);
}
