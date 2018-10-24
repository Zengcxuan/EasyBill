package finalhomework.tcl.com.finalhomework.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LockViewUtil {
    //手势密码
    private static final String SP_NAME = "LOCKVIEW";
    private static final String SP_KEY = "PASSWORD";
    //是否上锁
    private static final String ISLOCK = "status";
    //是否第一次使用，用于引导页
    private static final String ISFIRST = "times";
    //时间记录，用于定时提醒
    private static final String TIME_H = "hour";
    private static final String TIME_M = "minus";
    private static final String IS_SET = "set";
    public static void savePwd(Context mContext , List<Integer> password){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(SP_KEY, listToString(password)).commit();
    }

    public static String getPwd(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(SP_KEY, "");
    }

    public static void clearPwd(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().remove(SP_KEY).commit();
    }

    public static String listToString(List<Integer> lists){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < lists.size(); i++){
            sb.append(lists.get(i));
        }
        return sb.toString();
    }

    public static List<Integer> stringToList(String string){
        List<Integer> lists = new ArrayList<>();
        for(int i = 0; i < string.length(); i++){
            lists.add(Integer.parseInt(string.charAt(i) + ""));
        }
        return lists;
    }

    public static void setIslock(Context mContext, Boolean islock){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(ISLOCK, islock).commit();
    }

    public static boolean getIslock(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(ISLOCK, false);
    }

    public static boolean getIsfirst(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(ISFIRST, true);
    }

    public static void setIsfirst(Context mContext, Boolean isfirst){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(ISFIRST, isfirst).commit();
    }

    public static boolean getIsSet(Context mContext) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(IS_SET, true);
    }

    public static void setIsSet (Context mContext, Boolean isSet){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(IS_SET, isSet).commit();
    }

    public static void saveCalender(Context mContext, int hour, int minus){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(TIME_H, hour).commit();
        sp.edit().putInt(TIME_M, minus).commit();
    }

    public static ArrayList getCalender(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        ArrayList<Integer> time = new ArrayList<>();
        time.add(sp.getInt(TIME_H, 0));
        time.add(sp.getInt(TIME_M, 0));
       return  time;
    }
}

