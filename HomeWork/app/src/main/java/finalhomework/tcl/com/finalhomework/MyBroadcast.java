package finalhomework.tcl.com.finalhomework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import finalhomework.tcl.com.finalhomework.ui.widget.NotificationTool;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String extras = intent.getStringExtra("msg");
        Log.i("111", extras);
        NotificationTool notificationTool = new NotificationTool(context);
        notificationTool.setTitle("定时提醒");
        notificationTool.setMsg(extras);
        notificationTool.sendNotify();

    }
}
