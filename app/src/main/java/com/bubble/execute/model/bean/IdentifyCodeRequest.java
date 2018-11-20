package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/20
 * 版权所有 © 徐长策
 */
public class IdentifyCodeRequest {

    /**
     * 核对验证码
     */
    public static class CheckIdentifyCode {
        String userMail;
        String identifyCode;
        String typeFrom;

        public void setUserMail(String userMail) {
            this.userMail = userMail;
        }

        public String getUserMail() {
            return userMail;
        }

        public void setIdentifyCode(String identifyCode) {
            this.identifyCode = identifyCode;
        }

        public String getIdentifyCode() {
            return identifyCode;
        }

        public void setTypeFrom(String typeFrom) {
            this.typeFrom = typeFrom;
        }

        public String getTypeFrom() {
            return typeFrom;
        }

        @Override
        public String toString() {
            return "CheckIdentifyCodeDataBean: {" + "userMail:" + userMail + " identifyCode:" + identifyCode + " typeFrom:" + typeFrom + "}";
        }
    }
}
