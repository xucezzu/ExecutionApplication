package com.bubble.execute.model.listener;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/9/17
 * 版权所有 © 徐长策
 */
public interface OnUploadTargetDayListener {
    /**
     * 数据上传成功
     * @param msg
     */
    void onUploadSuccess(String msg);

    /**
     * 数据上传失败
     * @param msg
     */
    void onUploadFailed(String msg);
}
