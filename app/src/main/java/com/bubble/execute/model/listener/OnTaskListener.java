package com.bubble.execute.model.listener;

/**
 * @Author：徐长策 E-Mail: xuce_zzu@163.com
 * Date：2018/12/22
 * 版权所有 © 徐长策
 */
public interface OnTaskListener {
    interface OnTaskMottoListener {

        /**
         * 获取今日寄语 成功
         *
         * @param errCode
         * @param alertMsg
         * @param returnData
         */
        void onSuccess(String errCode, String alertMsg, String returnData);

        /**
         * 获取今日寄语 失败
         *
         * @param errCode
         * @param alertMsg
         */
        void onFailed(String errCode, String alertMsg);
    }

    interface OnTaskHomeListener{
        /**
         * 获取首页任务数据 成功
         *
         * @param errCode
         * @param alertMsg
         * @param returnData
         */
        void onSuccess(String errCode, String alertMsg, String returnData);

        /**
         * 获取首页任务数据 失败
         *
         * @param errCode
         * @param alertMsg
         */
        void onFailed(String errCode, String alertMsg);
    }
}
