package finalhomework.tcl.com.finalhomework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import finalhomework.tcl.com.finalhomework.ui.widget.NotificationTool;

public class MyService extends Service{
    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }

    @Override
    public void onCreate(){
        Log.i("----", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        String extras = intent.getStringExtra("msg");
        Log.i("----", extras);
        NotificationTool notificationTool = new NotificationTool(this);
        notificationTool.setTitle("定时提醒");
        notificationTool.setMsg("extras");
        notificationTool.sendNotify();
        for(int i = 0; i < 10; i++){
            if(i == 9){
                stopSelf();
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        Log.i("----", "onDestroy");
        super.onDestroy();
    }
}
