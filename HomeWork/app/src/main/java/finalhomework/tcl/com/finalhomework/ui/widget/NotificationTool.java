package finalhomework.tcl.com.finalhomework.ui.widget;

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

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.ui.activity.HomeActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationTool {
    private Context mContext;
    private String title, msg;
    public  NotificationTool(Context context){
        this.mContext = context;
    }

    public void sendNotify(){
        Log.i("----", "send" );
        final int NOTIFICATION_ID = 0x123;
        NotificationManager nm = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(mContext, HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(mContext, 1, intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /**
             * 安卓O之后新增要求,需要添加一个Channel
             */
            NotificationChannel mChannel = new NotificationChannel("18801267106", "canxuan",NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setDescription("for EasyBill");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});
            nm.createNotificationChannel(mChannel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                    .setSmallIcon(R.mipmap.sort_kid)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setDefaults(NotificationCompat.DEFAULT_SOUND)
                    .setOngoing(true)
                    .setChannelId("18801267106");
            nm.notify(1, builder.build());
        }else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
//                .setAutoCancel(true)
                    .setTicker("有新消息")
                    .setSmallIcon(R.mipmap.sort_kid)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setAutoCancel(true);
//                .setDefaults(android.app.Notification.DEFAULT_SOUND)
//                .setWhen(System.currentTimeMillis());
//                .setContentIntent(pi);
            nm.notify(1, builder.build());
        }
    }

    public void setTitle(String string){
        this.title = string;
    }

    public void setMsg(String string){
        this.msg = string;
    }
}
