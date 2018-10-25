package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/22
 * 版权所有 © 徐长策
 */

public class LoginDataResponse {
    private String errCode;
    private String alertMsg;
    private ReturnData returnData;

    public class ReturnData {
        private String userId;
        private String userMail;
        private String userPassword;
        private String userName;
        private String userTokenId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserMail() {
            return userMail;
        }

        public void setUserMail(String userMail) {
            this.userMail = userMail;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserTokenId() {
            return userTokenId;
        }

        public void setUserTokenId(String userTokenId) {
            this.userTokenId = userTokenId;
        }
    }

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

    public ReturnData getReturnData() {
        return returnData;
    }

    public void setReturnData(ReturnData returnData) {
        this.returnData = returnData;
    }

    @Override
    public String toString() {
        String returnMessage = "{ " + "errCode: " + getErrCode() + " alertMsg: " + getAlertMsg();
        String returnDataForUser = "";
        if (returnData != null) {
            returnDataForUser = " returnData: {" +
                    " userId: " + returnData.getUserId() +
                    " userMail: " + returnData.getUserMail() +
                    " userPassword " + returnData.getUserPassword() +
                    " userName " + returnData.getUserName() +
                    " userTokenId " + returnData.getUserTokenId() + " }";
        }
        return returnMessage + returnDataForUser + " }";
    }
}
