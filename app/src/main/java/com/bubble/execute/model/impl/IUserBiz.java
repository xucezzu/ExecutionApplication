package com.bubble.execute.model.impl;

import com.bubble.execute.model.listener.OnLoginListener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/15
 * 版权所有 © 徐长策
 */

public interface IUserBiz {
    /**
     * 用户登录接口
     * @param mail 用户邮箱
     * @param password 用户登录密码
     * @param phoneDeviceId  用户设备ID
     * @param userLoginType 用户登录类型
     * @param loginListener  用户登录结果监听
     */
    void login(String mail, String password, String phoneDeviceId, String userLoginType, OnLoginListener loginListener);
}
