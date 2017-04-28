package com.lee.x.view;

/**
 * Created by android on 2017/4/28.
 */
public class CalenderDay {

    private int day;
    private int week;
    private boolean today;
    private boolean selecte;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        today = today;
    }

    public boolean isSelecte() {
        return selecte;
    }

    public void setSelecte(boolean selecte) {
        this.selecte = selecte;
    }
}
