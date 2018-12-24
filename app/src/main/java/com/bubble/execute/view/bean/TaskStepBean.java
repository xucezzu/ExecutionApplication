package com.bubble.execute.view.bean;

import java.io.Serializable;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/25
 * 版权所有 © 徐长策
 */
public class TaskStepBean implements Serializable {
    String taskStep;
    String taskContent;
    String taskStatus;

    public String getTaskStep() {
        return taskStep;
    }

    public void setTaskStep(String taskStep) {
        this.taskStep = taskStep;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
