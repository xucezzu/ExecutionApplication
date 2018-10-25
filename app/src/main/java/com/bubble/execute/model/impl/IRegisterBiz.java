package com.bubble.execute.model.impl;

import com.bubble.execute.model.listener.OnRegisterListener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/15
 * 版权所有 © 徐长策
 */

public interface IRegisterBiz {
    /**
     * 用户注册接口
     * @param mail 用户邮箱
     * @param password 用户登录密码
     * @param phoneDeviceId  用户设备ID
     * @param registerListener  用户登录结果监听
     */
    void register(String mail, String password, String phoneDeviceId, OnRegisterListener registerListener);
}
