package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/7
 * 版权所有 © 徐长策
 */
public class PasswordDataResponse {

    /**
     * 校验老密码是否正确
     */
    public static class CheckPassword {
        String errCode;
        String alertMsg;

        public String getErrCode() {
            return errCode;
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        public String getAlertMsg() {
            return alertMsg;
        }

        public void setAlertMsg(String alertMsg) {
            this.alertMsg = alertMsg;
        }

        @Override
        public String toString() {
            return "CheckPasswordResponse: {" + "errCode: " + errCode + " alertMsg: " + alertMsg + "}";
        }
    }

    /**
     * 更新密码
     */
    public static class UpdatePassword {
        String errCode;
        String alertMsg;
        String newPassword;

        public String getErrCode() {
            return errCode;
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        public String getAlertMsg() {
            return alertMsg;
        }

        public void setAlertMsg(String alertMsg) {
            this.alertMsg = alertMsg;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        @Override
        public String toString() {
            return "UpdatePasswordResponse: {" + "errCode: " + errCode + " alertMsg: " + alertMsg + " newPassword: " + newPassword + "}";
        }
    }
}
