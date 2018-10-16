package finalhomework.tcl.com.finalhomework.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.MyService;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.ui.activity.BudgetActivity;
import finalhomework.tcl.com.finalhomework.ui.widget.EditDialog;
import finalhomework.tcl.com.finalhomework.ui.widget.ImageButtonWithText;
import finalhomework.tcl.com.finalhomework.ui.widget.NotificationTool;
import finalhomework.tcl.com.finalhomework.ui.widget.RoundImageView;
import static android.content.Context.ALARM_SERVICE;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;


public class mine_Fragment extends HomeBaseFragment {
    @BindView(R.id.record_days)  //记录总天数
    TextView recordDays;
    @BindView(R.id.record_deals) //记录总笔数
    TextView recordDeals;
    @BindView(R.id.surplus)     //结余
    TextView recordSurplus;
    @BindView(R.id.button_list)
    GridView listView;
    @BindView(R.id.headimage)
    RoundImageView headImage; //加载头像的圆形ImageView
    @BindView(R.id.name)
    TextView userName; //用户名字
    private Boolean isOpen = true;
    private int[] typeIcon = new int[]{
            R.mipmap.voice, R.mipmap.notify, R.mipmap.yusuan, R.mipmap.cyper, R.mipmap.outport,
            R.mipmap.count, R.mipmap.help
    };
    private String[] buttonContent = new String[]{
       "声音开关", "定时提醒", "每月预算", "手势密码", "导出账单", "评分", "帮助"
    };

    private int[] rightIcon = new int[]{
            R.drawable.voice_switch, R.drawable.arrow, R.drawable.arrow, R.drawable.arrow, R.drawable.arrow,
            R.drawable.arrow, R.drawable.arrow
    };

    public static mine_Fragment newInstance(String info) {
        Bundle args = new Bundle();
        mine_Fragment fragment = new mine_Fragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick ({R.id.headimage, R.id.head_mine})
    protected void onClick(View view){
        switch (view.getId()) {
            case R.id.headimage:
                // TODO: 18-10-9  开启个人信息界面
                Toast.makeText(getActivity(), "你点击了我", Toast.LENGTH_LONG).show();
                break;
        }
    }

    /**
     * 载入数据
     */
    @Override
    protected  void loadData(){
    }

    /**闹钟设置*/
    private void clockSetting(final String string){
        Calendar currentTime = Calendar.getInstance();
        new TimePickerDialog(getActivity(), 0, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // TODO: 18-10-10 设置闹钟
                Intent intent = new Intent(getActivity(), MyService.class);
                intent.putExtra("msg", string);

                PendingIntent pi = PendingIntent.getService(getActivity(), 0, intent, 0);
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                //用户选择时间
                c.set(Calendar.HOUR, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                //获取系统的AlarmManager
                Log.i(TAG, "System time" + System.currentTimeMillis());
                Log.i(TAG, "choice time" + c.getTimeInMillis());
                AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(
                        ALARM_SERVICE);
//                alarmManager.set(AlarmManager.RTC_WAKEUP,
//                        c.getTimeInMillis(), pi);
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);


            }
        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), false).show();
    }

    /**
     * 载入用户数据
     */
    private void initData(){
        // TODO: 18-10-9 载入头像名字，账单数据
        recordDays.setText("55"); //总天数
        recordDeals.setText("11"); //总笔数
        recordSurplus.setText("55"); //结余

        //初始化GridView，添加itemClickListener，下方多行栏目的监听
        final List<Map<String, Object>> dataList;
        SimpleAdapter adapter;





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
//                Toast.makeText(getActivity(),"pick"+position, Toast.LENGTH_LONG).show();
                switch (position) {
                    case 0:
                        // TODO: 18-10-8  声音开关
                        if(isOpen) {
                            Toast.makeText(getActivity(), "声音关闭", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getActivity(), "声音开启", Toast.LENGTH_LONG).show();
                        }
                        isOpen = !isOpen;
                        break;
                    case 1:
                        // TODO: 18-10-8 定时提醒
                        /**
                        * 逻辑不对，应该先输入内容
                        */
                        EditDialog clockContent = new EditDialog(getActivity());
                        clockContent.show();
                        while (clockContent.getIsSet()) {
                            String notifyContent = clockContent.getInput();
                            clockSetting(notifyContent);
                            clockContent.setIsSet();
                        }
                        break;
                    case 2:
                        // TODO: 18-10-8 预算
                        Intent intent = new Intent(getActivity(), BudgetActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 3:
                        // TODO: 18-10-8 手势密码
                        break;
                    case 4:
                        // TODO: 18-10-8 导出账单
                        break;
                    case 5:
                        // TODO: 18-10-8 评分
                        break;
                    case 6:
                        // TODO: 18-10-8 帮助
                        break;
                }

            }
        });
    }
    /**
     * 设置菜单图标
     */
    @Override
    protected  int getItemMenu(){return R.menu.menu_share;}

    /**
     * 设置菜单项的响应事件,这里应该是分享
     */
    @Override
    protected  void setItemReact(){
        // TODO: 18-10-9 分享
    }

    @Override
    public void myToolbar(){
        // TODO: 18-9-29 modify
        super.myToolbar();

    }

    @Override
    protected  void beforeDestroy(){}

    @Override
    protected void improtantData() {
        initData();
    }


    /**
     * 返回键名字
     * */
    @Override
    protected ImageButton getBackBtn(){
        return getActivity().findViewById(R.id.back_mine);
    }

    @Override
    protected DrawerLayout getDrawerLayout(){ return getActivity().findViewById(R.id.drawerlayout_mine); }

    @Override
    protected  Toolbar getToolbar(){ return getActivity().findViewById(R.id.tl_mine); }

    @Override
    protected LinearLayout getLeftWindow(){ return getActivity().findViewById(R.id.navigationview_mine);}

    @Override
    protected  int getLayoutId(){ return R.layout.fragment_persionalmsg; }

    /**
     * 返回头像
     * */
    @Override
    protected ImageButtonWithText getHead(){
        return getActivity().findViewById(R.id.head_mine);
    }

}
