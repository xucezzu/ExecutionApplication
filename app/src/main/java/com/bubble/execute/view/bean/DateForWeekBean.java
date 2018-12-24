package com.bubble.execute.view.bean;

import java.io.Serializable;

/**
 * @author 徐长策
 * E-Mail: xuce_zzu@163.com
 * Date：2018/11/29
 * 版权所有 © 徐长策
 */
public class DateForWeekBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 一年12个月
     */
    private static final int oneYearOnMonthCount = 12;
    public int dateYear;
    public int dateMonth;
    public int dateDay;
    public int dateWeek;

    public DateForWeekBean(int year, int month, int day) {
        if (month > oneYearOnMonthCount) {
            month = 1;
            year++;
        } else if (month < 1) {
            month = oneYearOnMonthCount;
            year--;
        }
        this.dateYear = year;
        this.dateMonth = month;
        this.dateDay = day;
    }
}
