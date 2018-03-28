package com.bubble.execute.model.impl;

import com.bubble.execute.model.listener.OnSafePasswordListener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public interface ISafePasswordBiz {
    /**
     * 是否存在安全密码
     *
     * @param userId          用户 ID
     * @param isExistListener 监听
     */
    void isExistSafePassword(String userId, OnSafePasswordListener.OnIsExistListener isExistListener);

    /**
     * 核对安全密码是否正确
     *
     * @param userId           用户ID
     * @param userSafePassword 用户安全密码
     * @param checkListener    监听
     */
    void checkSafePassword(String userId, String userSafePassword, OnSafePasswordListener.OnCheckListener checkListener);

    /**
     * 更新安全密码
     *
     * @param userId           用户ID
     * @param userSafePassword 新安全密码
     * @param updateListener   监听
     */
    void updateSafePassword(String userId, String userSafePassword, OnSafePasswordListener.OnUpdateListener updateListener);
}
