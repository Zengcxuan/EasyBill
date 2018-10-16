package finalhomework.tcl.com.finalhomework.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class LockViewUtil {

    private static final String SP_NAME = "LOCKVIEW";
    private static final String SP_KEY = "PASSWORD";
    private static final String ISLOCK = "status";

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

}

