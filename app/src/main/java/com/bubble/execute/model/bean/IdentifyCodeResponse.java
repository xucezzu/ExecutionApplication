package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/20
 * 版权所有 © 徐长策
 */
public class IdentifyCodeResponse {

    /**
     * 校验老密码是否正确
     */
    public static class CheckIdentifyCode {
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
            return "CheckIdentifyCodeResponse: {" + "errCode: " + errCode + " alertMsg: " + alertMsg + "}";
        }
    }
}
