package com.bubble.execute.model.impl;

import com.bubble.execute.model.listener.OnIdentifyCodeListener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/20
 * 版权所有 © 徐长策
 */
public interface IIdentifyCodeBiz {

    /**
     * 核对验证码是否存在
     *
     * @param mail          邮箱
     * @param code          验证码
     * @param type          验证码类型
     * @param checkListener 监听
     */
    void checkIdentify(String mail, String code, String type, OnIdentifyCodeListener.OnCheckListener checkListener);
}
