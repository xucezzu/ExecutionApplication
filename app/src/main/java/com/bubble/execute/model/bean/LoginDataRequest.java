package com.bubble.execute.model.bean;

/**
 * @Author：徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/21
 * 版权所有 © 徐长策
 */

public class LoginDataRequest {
    private String mail;
    private String password;
    private String phoneDeviceId;
    private String userLoginType;

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

    public void setUserLoginType(String userLoginType) {
        this.userLoginType = userLoginType;
    }

    public String getUserLoginType() {
        return userLoginType;
    }

    @Override
    public String toString() {
        return "LoginDataBean: {" +
                "mail:" + mail + " Password:" + password +
                " PhoneDeviceID:" + phoneDeviceId + " UserLoginType:" + userLoginType + "}";
    }
}
