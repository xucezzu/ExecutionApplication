package com.bubble.execute.view_interface;

/**
 * @Author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public interface ISplashActivityView {
    /**
     * 获取用户的账号
     * @return 用户的邮箱
     */
    String getMail();

    /**
     * 获取用户的密码
     * @return 用户的密码
     */
    String getPassword();

    /**
     * 显示用户数据验证中
     */
    void showLoadingData();

    /**
     * 用户数据验证完毕
     */
    void hideLoadingData();

    /**
     * 验证失败跳转到登录界面
     */
    void toLoginActivity();

    /**
     * 验证成功跳转到安全密码输入界面
     */
    void toPasswordActivity();
}
