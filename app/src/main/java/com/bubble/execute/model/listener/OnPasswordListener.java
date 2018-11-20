package com.bubble.execute.model.listener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/9
 * 版权所有 © 徐长策
 */
public interface OnPasswordListener {

    interface OnCheckPasswordListener {
        /**
         * 密码校验成功
         *
         * @param msg
         * @param code
         */
        void onCheckSuccess(String code, String msg);

        /**
         * 密码校验失败
         *
         * @param msg
         * @param code
         */
        void onCheckFailed(String code, String msg);
    }

    interface OnUpdatePasswordListener {
        /**
         * 密码更新成功
         *
         * @param code 返回的正确码
         * @param msg  返回的成功信息
         */
        void onUpdateSuccess(String code, String msg, String newPassword);

        /**
         * 密码更新失败
         *
         * @param code
         * @param msg
         */
        void onUpdateFailed(String code, String msg);
    }

    interface OnResetPasswordListener {
        /**
         * 密码重置成功
         *
         * @param code
         * @param msg
         */
        void onResetSuccess(String code, String msg);

        /**
         * 密码重置失败
         *
         * @param code
         * @param msg
         */
        void onResetFailed(String code, String msg);
    }
}
