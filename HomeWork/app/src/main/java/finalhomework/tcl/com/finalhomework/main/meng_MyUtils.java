package finalhomework.tcl.com.finalhomework.main;

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
}
