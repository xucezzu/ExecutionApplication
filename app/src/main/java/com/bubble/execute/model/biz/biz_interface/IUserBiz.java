package com.bubble.execute.model.biz.biz_interface;

import com.bubble.execute.model.biz.listener.OnLoginListener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/15
 * 版权所有 © 徐长策
 */

public interface IUserBiz {
    /**
     * 登录功能业务
     * @param mail 用户邮箱
     * @param password 用户密码
     * @param loginListener  登录结果监听
     */
    void login(String mail, String password, OnLoginListener loginListener);
}
