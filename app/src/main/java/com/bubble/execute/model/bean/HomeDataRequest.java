package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/12/21
 * 版权所有 © 徐长策
 */
public class HomeDataRequest {
    public static class HomeDataMottoRequest {
        String mottoDate;

        public void setMottoDate(String mottoDate) {
            this.mottoDate = mottoDate;
        }

        public String getMottoDate() {
            return mottoDate;
        }
    }

    public static class HomeDataTaskRequest {
        String userId;
        String deviceId;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceId() {
            return deviceId;
        }
    }
}
