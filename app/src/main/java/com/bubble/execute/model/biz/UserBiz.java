package com.bubble.execute.model.biz;

import com.bubble.execute.model.bean.LoginDataRequest;
import com.bubble.execute.model.impl.IUserBiz;
import com.bubble.execute.model.listener.OnLoginListener;
import com.google.gson.Gson;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/15
 * 版权所有 © 徐长策
 */

public class UserBiz implements IUserBiz{

    @Override
    public void login(String mail, String password, String phoneDeviceId, String userLoginType, OnLoginListener loginListener) {

    }
}
