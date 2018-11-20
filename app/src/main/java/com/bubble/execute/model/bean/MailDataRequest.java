package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/15
 * 版权所有 © 徐长策
 */
public class MailDataRequest {

    /**
     * 校验邮箱
     */
    public static class CheckMail {
        String userMail;
        String mailType;

        public String getUserMail() {
            return userMail;
        }

        public void setUserMail(String userMail) {
            this.userMail = userMail;
        }

        public String getMailType() {
            return mailType;
        }

        public void setMailType(String mailType) {
            this.mailType = mailType;
        }

        @Override
        public String toString() {
            return "CheckMailDataBean: {" + "userMail:" + userMail + " mailType:" + userMail + "}";
        }
    }
}
