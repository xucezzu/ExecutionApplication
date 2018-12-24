package com.bubble.execute.model.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/12/21
 * 版权所有 © 徐长策
 */
public class HomeDataResponse {

    /**
     * 首页“今日寄语”返回数据
     */
    public class HomeDataMottoResponse {
        String errCode;
        String alertMsg;
        MottoData mMottoData;

        public class MottoData {
            String mottoDate;
            String mottoContent;

            public void setMottoDate(String mottoDate) {
                this.mottoDate = mottoDate;
            }

            public String getMottoDate() {
                return mottoDate;
            }

            public void setMottoContent(String mottoContent) {
                this.mottoContent = mottoContent;
            }

            public String getMottoContent() {
                return mottoContent;
            }
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        public String getErrCode() {
            return errCode;
        }

        public void setAlertMsg(String alertMsg) {
            this.alertMsg = alertMsg;
        }

        public String getAlertMsg() {
            return alertMsg;
        }

        public void setMottoData(MottoData mottoData) {
            mMottoData = mottoData;
        }

        public MottoData getMottoData() {
            return mMottoData;
        }
    }

    /**
     * 首页任务展示
     */
    public class HomeDataTaskResponse {
        String errCode;
        String alertMsg;
        HomeTaskData mHomeTaskData;

        public class HomeTaskData {
            String taskContent;
            String taskStartTime;
            String taskEndTime;
            String taskStepContent;

            public void setTaskContent(String taskContent) {
                this.taskContent = taskContent;
            }

            public String getTaskContent() {
                return taskContent;
            }

            public void setTaskStartTime(String taskStartTime) {
                this.taskStartTime = taskStartTime;
            }

            public String getTaskStartTime() {
                return taskStartTime;
            }

            public void setTaskEndTime(String taskEndTime) {
                this.taskEndTime = taskEndTime;
            }

            public String getTaskEndTime() {
                return taskEndTime;
            }

            public void setTaskStepContent(String taskStepContent) {
                this.taskStepContent = taskStepContent;
            }

            public String getTaskStepContent() {
                return taskStepContent;
            }
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        public String getErrCode() {
            return errCode;
        }

        public void setAlertMsg(String alertMsg) {
            this.alertMsg = alertMsg;
        }

        public String getAlertMsg() {
            return alertMsg;
        }

        public void setHomeTaskData(HomeTaskData homeTaskData) {
            mHomeTaskData = homeTaskData;
        }

        public HomeTaskData getHomeTaskData() {
            return mHomeTaskData;
        }
    }
}
