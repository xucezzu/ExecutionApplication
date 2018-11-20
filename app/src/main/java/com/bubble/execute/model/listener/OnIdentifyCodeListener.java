package com.bubble.execute.model.listener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/20
 * 版权所有 © 徐长策
 */
public interface OnIdentifyCodeListener {

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
}
