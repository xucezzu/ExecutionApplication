package com.bubble.execute.model.biz;

import com.bubble.execute.model.biz.biz_interface.IUserBiz;
import com.bubble.execute.model.biz.listener.OnLoginListener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/15
 * 版权所有 © 徐长策
 */

public class UserBiz implements IUserBiz{

    @Override
    public void login(String mail, String password, OnLoginListener loginListener) {
        //开始写登录业务逻辑
    }
}
