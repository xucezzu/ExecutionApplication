package com.bubble.execute.model.listener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/3/28
 * 版权所有 © 徐长策
 */
public interface OnSafePasswordListener {

    interface OnIsExistListener {
        /**
         * 请求成功
         *
         * @param msg
         * @param code
         */
        void onIsExistSuccess(String code, String msg);

        /**
         * 请求失败
         *
         * @param msg
         * @param code
         */
        void onIsExistFailed(String code, String msg);
    }

    interface OnCheckListener {
        /**
         * 请求成功
         *
         * @param msg
         * @param code
         */
        void onCheckSuccess(String code, String msg);

        /**
         * 请求失败
         *
         * @param msg
         * @param code
         */
        void onCheckFailed(String code, String msg);
    }

    interface OnUpdateListener {
        /**
         * 请求成功
         *
         * @param msg
         * @param code
         */
        void onUpdateSuccess(String code, String msg);

        /**
         * 请求失败
         *
         * @param msg
         * @param code
         */
        void onUpdateFailed(String code, String msg);
    }
}
