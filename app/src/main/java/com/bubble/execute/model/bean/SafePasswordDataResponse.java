package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/27
 * 版权所有 © 徐长策
 *
 * 注：虽然每个类中返回数据类型都是一致的，但是不建议共用，一个萝卜一个坑，万一将来某个时候需要在某个类上添加一个字段，
 * 如果是共用的话，会修改很多的地方。既然是和服务器有关联的模块，就应该和服务器方便对应起来，无论View层需要哪些数据
 * 都能够保证每个类只做一件事且是独立的。
 */
public class SafePasswordDataResponse {
    /**
     * 验证是否存在安全密码
     */
    public class IsExistSafePassword{
        /**
         * 用户ID
         */
        String errCode;

        /**
         * 用户的安全密码
         */
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
            return "IsExistSafePasswordResponse: {" + "errCode:" + errCode +  " alertMsg" + alertMsg + "}";
        }
    }

    /**
     * 核对安全密码
     */
    public class CheckSafePassword{
        /**
         * 用户ID
         */
        String errCode;

        /**
         * 用户的安全密码
         */
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
            return "CheckSafePasswordResponse: {" + "errCode: " + errCode +  "alertMsg: " + alertMsg + "}";
        }
    }

    /**
     * 更新安全密码
     */
    public class UpdateSafePassword{
        /**
         * 用户ID
         */
        String errCode;

        /**
         * 用户的安全密码
         */
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
            return "UpdateSafePasswordResponse: {" + "errCode:" + errCode +  "alertMsg" + alertMsg + "}";
        }
    }
}
