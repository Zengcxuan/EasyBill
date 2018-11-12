package com.tcl.easybill.ui.widget;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.tcl.easybill.R;
import com.tcl.easybill.ui.activity.HomeActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationTool {
    private Context mContext;
    private String title, msg;
    public static final  String CHANNEL_ID          = "Notify";
    private static final String CHANNEL_NAME        = "Default Channel";
    private static final String CHANNEL_DESCRIPTION = "定时提醒";
    public  NotificationTool(Context context){
        this.mContext = context;

    }

    public void sendNotify(){
        Log.i("----", "send" );
        final int NOTIFICATION_ID = 1;
        NotificationManager nm = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(mContext, HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(mContext, 1, intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /*
             * 安卓O之后新增要求,需要添加一个Channel
             */
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "定时提醒",NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setDescription(CHANNEL_DESCRIPTION);
            mChannel.setName(CHANNEL_NAME);
            if (nm != null) {
                nm.createNotificationChannel(mChannel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.start)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setColor(mContext.getResources().getColor(R.color.startColor))
                    .setDefaults(NotificationCompat.DEFAULT_SOUND)
                    .setOngoing(true)
                    .setAutoCancel(true)
                    .setContentIntent(pi);
            nm.notify(NOTIFICATION_ID, builder.build());
        }else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
//                .setAutoCancel(true)
                    .setTicker("有新消息")
                    .setSmallIcon(R.mipmap.start)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setAutoCancel(true);
//                .setDefaults(android.app.Notification.DEFAULT_SOUND)
//                .setWhen(System.currentTimeMillis());
//                .setContentIntent(pi);
            nm.notify(NOTIFICATION_ID, builder.build());
        }

    }

    public void setTitle(String string){
        this.title = string;
    }

    public void setMsg(String string){
        this.msg = string;
    }
}
