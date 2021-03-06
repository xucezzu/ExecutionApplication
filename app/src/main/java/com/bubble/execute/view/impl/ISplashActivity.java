package com.bubble.execute.view.impl;

/**
 * @Author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public interface ISplashActivity {
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
     * 获取设备ID
     * @return 设备ID
     */
    String getDeviceID();

    /**
     * 获取用户登录类型
     * @return 用户登录类型
     */
    String getUserLoginType();

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

    /**
     * 弹出Toast的信息
     * @param msg 展示的信息
     */
    void showReturnMsg(String msg);
}
