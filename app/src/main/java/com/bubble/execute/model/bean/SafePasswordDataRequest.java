package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 */
public class SafePasswordDataRequest {
    /**
     * 验证是否存在安全密码
     */
    public static class IsExistSafePassword{
        /**
         * 用户ID
         */
        String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "IsExistSafePasswordDataBean: {" + "userID:" + userId +  "}";
        }
    }

    /**
     * 核对安全密码
     */
    public static class CheckSafePassword{
        /**
         * 用户ID
         */
        String userId;

        /**
         * 用户的安全密码
         */
        String userSafePassword;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserSafePassword() {
            return userSafePassword;
        }

        public void setUserSafePassword(String userSafePassword) {
            this.userSafePassword = userSafePassword;
        }

        @Override
        public String toString() {
            return "CheckSafePasswordDataBean: {" + "userID:" + userId +  "userSafePassword" + userSafePassword + "}";
        }
    }

    /**
     * 更新安全密码
     */
    public static class UpdateSafePassword{
        /**
         * 用户ID
         */
        String userId;

        /**
         * 用户的安全密码
         */
        String userSafePassword;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserSafePassword() {
            return userSafePassword;
        }

        public void setUserSafePassword(String userSafePassword) {
            this.userSafePassword = userSafePassword;
        }

        @Override
        public String toString() {
            return "UpdateSafePasswordDataBean: {" + "userID:" + userId +  "userSafePassword" + userSafePassword + "}";
        }
    }

}
