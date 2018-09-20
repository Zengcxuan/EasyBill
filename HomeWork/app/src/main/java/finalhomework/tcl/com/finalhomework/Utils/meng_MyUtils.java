package finalhomework.tcl.com.finalhomework.Utils;

public class meng_MyUtils {
    public String getMonth(String month){
        if (month.substring(5,6).equals("0")){
            month = month.substring(6,7);
        }else{
            month = month.substring(5,7);
        }
        return month;
    }
    public String getYear(String year){
        return year.substring(0,4);
    }
    public String getDay(String day){
        if (day.substring(7,8).equals("0")){
            day = day.substring(8,9);
        }else {
            day = day.substring(7,9);
        }
        return day;
    }
}
