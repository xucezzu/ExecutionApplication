package com.bubble.execute.thread.task;

import com.bubble.execute.thread.Priority;
import com.bubble.execute.thread.PriorityRunnable;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/12/2
 * 版权所有 © 徐长策
 */
public class ActionDataTask extends PriorityRunnable {
    private String fileName;
    private String actionData;

    public ActionDataTask(String fileName, String actionData) {
        super(Priority.IMMEDIATE);
        this.actionData = actionData;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        super.run();
    }
}
