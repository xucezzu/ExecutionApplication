package com.bubble.execute.model.impl;

import com.bubble.execute.model.listener.OnPasswordListener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/9
 * 版权所有 © 徐长策
 */
public interface IPasswordBiz {
    /**
     * 用户核对密码接口
     * @param userId 用户ID
     * @param userPassword  用户密码
     * @param checkPasswordListener  核对结果监听
     */
    void checkUserPassword(String userId, String userPassword, OnPasswordListener.OnCheckPasswordListener checkPasswordListener);

    /**
     * 用户更新密码接口
     * @param userId 用户ID
     * @param userNewPassword  用户的新密码
     * @param userNewAgainPassword  用户再次输入的新密码
     * @param updatePasswordListener  更新结果监听
     */
    void updateUserPassword(String userId, String userNewPassword, String userNewAgainPassword, OnPasswordListener.OnUpdatePasswordListener updatePasswordListener);
}
