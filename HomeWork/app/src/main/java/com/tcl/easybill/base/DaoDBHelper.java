package com.tcl.easybill.base;

import android.database.sqlite.SQLiteDatabase;

import com.tcl.easybill.greendao.DaoMaster;
import com.tcl.easybill.greendao.DaoSession;

public class DaoDBHelper {
    private static final String DB_NAME = "CoShareBill_DB";

    private static volatile DaoDBHelper sInstance;
    private SQLiteDatabase mDb;
    private DaoMaster mDaoMaster;
    private DaoSession mSession;


    private  DaoDBHelper(){

        //封装数据库的创建、更新、删除
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(MyApplication.getContext(),DB_NAME,null);
        //获取数据库
        mDb = openHelper.getWritableDatabase();
        //封装数据库中表的创建、更新、删除
        mDaoMaster = new DaoMaster(mDb);  //合起来就是对数据库的操作
        //对表操作的对象。
        mSession = mDaoMaster.newSession(); //可以认为是对数据的操作
    }


    public static DaoDBHelper getInstance(){
        if (sInstance == null){
            synchronized (DaoDBHelper.class){
                if (sInstance == null){
                    sInstance = new DaoDBHelper();
                }
            }
        }
        return sInstance;
    }

    public DaoSession getSession(){
        return mSession;
    }

    public SQLiteDatabase getDatabase(){
        return mDb;
    }

    public DaoSession getNewSession(){
        return mDaoMaster.newSession();
    }
}
