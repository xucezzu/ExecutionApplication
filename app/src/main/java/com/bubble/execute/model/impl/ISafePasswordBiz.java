package com.bubble.execute.model.impl;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public interface ISafePasswordBiz {
    /**
     * 是否存在安全密码
     * @param userId  用户ID
     */
    void isExistSafePassword(String userId);

    /**
     * 核对安全密码是否正确
     * @param userId  用户ID
     * @param userSafePassword  用户安全密码
     */
    void checkSafePassword(String userId, String userSafePassword);

    /**
     * 更新安全密码
     * @param userId  用户ID
     * @param userSafePassword   新安全密码
     */
    void updateSafePassword(String userId, String userSafePassword);
}
