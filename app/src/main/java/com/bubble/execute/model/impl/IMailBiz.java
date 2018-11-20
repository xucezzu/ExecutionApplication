package com.bubble.execute.model.impl;

import com.bubble.execute.model.listener.OnMailListener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/15
 * 版权所有 © 徐长策
 */
public interface IMailBiz {

    /**
     * 核对邮箱是否存在
     *
     * @param mail         邮箱
     * @param type         类型
     * @param mailListener 结果监听
     */
    void checkMail(String mail, String type, OnMailListener mailListener);
}
