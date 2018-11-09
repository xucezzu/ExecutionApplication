package com.bubble.execute.view.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/9/28
 * 版权所有 © 徐长策
 */
public interface IResetPasswordActivity {
    /**
     * 返回上一个页面
     */
    void backLastActivity();

    /**
     * 获取老密码
     */
    String getOldPassword();

    /**
     * 获取新密码
     */
    String getNewPassword();

    /**
     * 再一次获取新密码
     */
    String getNewAgainPassword();

    /**
     * 校验老密码
     */
    void checkOldPassword();

    /**
     * 校验老密码失败，重新校验老密码
     */
    void recheckOldPassword();

    /**
     * 校验老密码成功，跳转到更新密码这一步
     */
    void intoUpdatePassword();

    /**
     * 更新密码
     */
    void updatePassword();

    /**
     * 展示密码更新失败提示
     */
    void showUpdatePasswordFailed();

    /**
     * 展示密码更新成功提示
     */
    void showUpdatePasswordSuccess();

    /**
     * 存储新的密码
     */
    void saveNewPassword(String newPassword);
}
