package com.bubble.execute.model.listener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/15
 * 版权所有 © 徐长策
 */
public interface OnMailListener {
    /**
     * 邮箱已经存在
     *
     * @param code 已经存在返回码
     * @param msg  已经存在返回内容
     */
    void mailHasExist(String code, String msg);

    /**
     * 邮箱不存在
     *
     * @param code 不存在返回码
     * @param msg  不存在返回内容
     */
    void mailNotExist(String code, String msg);
}
