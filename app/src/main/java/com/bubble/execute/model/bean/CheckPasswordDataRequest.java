package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/26
 * 版权所有 © 徐长策
 */

public class CheckPasswordDataRequest {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "CheckPasswordDataBean: {" + "userId:" + userId + "}";
    }
}