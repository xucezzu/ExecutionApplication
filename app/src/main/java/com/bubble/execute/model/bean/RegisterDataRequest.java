package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/13
 * 版权所有 © 徐长策
 */

public class RegisterDataRequest {
    private String mail;
    private String password;
    private String phoneDeviceId;

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneDeviceId(String phoneDeviceId) {
        this.phoneDeviceId = phoneDeviceId;
    }

    public String getPhoneDeviceId() {
        return phoneDeviceId;
    }
}
