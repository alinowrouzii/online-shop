package model;

import java.util.Calendar;
;

public class Date {
    private Calendar calendar;
    public Date(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
    }
    public void setDay(int day){
        calendar.set(Calendar.DATE,day) ;
    }
    public void setMonth(int month){
        calendar.set(Calendar.MONTH,month);
    }
    public void setYear(int year){
        calendar.set(Calendar.YEAR, year);
    }
    public java.util.Date getDate(){
        return calendar.getTime() ;
    }
}
