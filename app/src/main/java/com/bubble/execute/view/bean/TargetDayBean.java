package com.bubble.execute.view.bean;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/9/5
 * 版权所有 © 徐长策
 */
public class TargetDayBean {
    /**
     * 是否完成
     */
    boolean isChecked;
    /**
     * 每日必做的内容
     */
    String targetDayContent;

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setTargetDayContent(String targetDayContent) {
        this.targetDayContent = targetDayContent;
    }

    public String getTargetDayContent() {
        return targetDayContent;
    }
}
