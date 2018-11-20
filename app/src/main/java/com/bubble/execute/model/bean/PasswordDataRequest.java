package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/7
 * 版权所有 © 徐长策
 */
public class PasswordDataRequest {

    /**
     * 校验老密码是否正确
     */
    public static class CheckPassword {
        String userId;
        String userPassword;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        @Override
        public String toString() {
            return "CheckPasswordDataBean: {" + "userID:" + userId + " userPassword:" + userPassword + "}";
        }
    }

    /**
     * 更新密码
     */
    public static class UpdatePassword {
        String userId;
        String userNewPassword;
        String userNewAgainPassword;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserNewPassword() {
            return userNewPassword;
        }

        public void setUserNewPassword(String userNewPassword) {
            this.userNewPassword = userNewPassword;
        }

        public String getUserNewAgainPassword() {
            return userNewAgainPassword;
        }

        public void setUserNewAgainPassword(String userNewAgainPassword) {
            this.userNewAgainPassword = userNewAgainPassword;
        }

        @Override
        public String toString() {
            return "UpdatePasswordDataBean: {" + "userID: " + userId + " userNewPassword: " + userNewPassword + " userNewAgainPassword: " + userNewAgainPassword + "}";
        }
    }

    /**
     * 重置密码
     */
    public static class ResetPassword {
        String userMail;
        String userNewPassword;
        String userNewAgainPassword;

        public String getUserMail() {
            return userMail;
        }

        public void setUserMail(String userMail) {
            this.userMail = userMail;
        }

        public String getUserNewPassword() {
            return userNewPassword;
        }

        public void setUserNewPassword(String userNewPassword) {
            this.userNewPassword = userNewPassword;
        }

        public String getUserNewAgainPassword() {
            return userNewAgainPassword;
        }

        public void setUserNewAgainPassword(String userNewAgainPassword) {
            this.userNewAgainPassword = userNewAgainPassword;
        }

        @Override
        public String toString() {
            return "ResetPasswordDataBean: {" + "userMail: " + userMail + " userNewPassword: " + userNewPassword + " userNewAgainPassword: " + userNewAgainPassword + "}";
        }
    }
}
