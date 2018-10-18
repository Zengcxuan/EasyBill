package finalhomework.tcl.com.finalhomework.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.MyBroadcast;
import finalhomework.tcl.com.finalhomework.MyService;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.LockViewUtil;
import finalhomework.tcl.com.finalhomework.Utils.SnackbarUtils;
import finalhomework.tcl.com.finalhomework.mvp.presenter.TotalRecordPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.TotalRecordPresenterImpl;
import finalhomework.tcl.com.finalhomework.mvp.views.TotalRecordView;
import finalhomework.tcl.com.finalhomework.pojo.DataSum;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import finalhomework.tcl.com.finalhomework.pojo.User;
import finalhomework.tcl.com.finalhomework.ui.activity.BudgetActivity;
import finalhomework.tcl.com.finalhomework.ui.activity.LockViewUi;
import finalhomework.tcl.com.finalhomework.ui.widget.EditDialog;
import finalhomework.tcl.com.finalhomework.ui.widget.ImageButtonWithText;
import finalhomework.tcl.com.finalhomework.ui.widget.NotificationTool;
import finalhomework.tcl.com.finalhomework.ui.widget.RoundImageView;

import static android.app.PendingIntent.FLAG_ONE_SHOT;
import static android.content.Context.ALARM_SERVICE;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;


public class mine_Fragment extends HomeBaseFragment implements TotalRecordView {
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
    private TotalRecordPresenter presenter;

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


    /**
     * 载入用户数据
     */
    private void initData(){
        // TODO: 18-10-9 载入头像名字，账单数据
        recordDays.setText("0"); //总天数
        recordDeals.setText("0"); //总笔数
        recordSurplus.setText("0.0"); //结余

        //初始化GridView，添加itemClickListener，下方多行栏目的监听
        final List<Map<String, Object>> dataList;
        SimpleAdapter adapter;
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < typeIcon.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("left", typeIcon[i]);
            map.put("text", buttonContent[i]);
            map.put("right",rightIcon[i]);
            dataList.add(map);
        }
        String[] from={"left","text", "right"};
        int[] to={R.id.image_left, R.id.button_show, R.id.image_right};
        adapter=new SimpleAdapter(mContext, dataList, R.layout.button_with_image, from, to);
        listView.setAdapter(adapter);


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
                        clockSet();
                        break;
                    case 2:
                        // TODO: 18-10-8 预算
                        Intent intent = new Intent(getActivity(), BudgetActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 3:
                        // TODO: 18-10-8 手势密码
                        Intent intent1 = new Intent(getActivity(), LockViewUi.class);
                        getActivity().startActivity(intent1);
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
//        //取消按键音效
//        listView.setSoundEffectsEnabled(false);
    }

    /**
     * 弹出对话框,获取提醒内容,获取后调用clockSetting设置闹钟
     */
    private void clockSet(){
        final EditText editText = new EditText(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("定时提醒");
        builder.setMessage("请输入内容");
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String input = editText.getText().toString();
                Log.v(TAG + ":clockSet", input);
                clockSetting(input);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "取消", Toast.LENGTH_LONG).show();
            }
        });
        builder.create().show();
    }

    /**
     * 闹钟设置, 到点开启Service
     */
    private void clockSetting(final String string){
        Log.v(TAG + ":clockSetting", string);
        Calendar currentTime = Calendar.getInstance();
        new TimePickerDialog(getActivity(), 0, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // TODO: 18-10-10 设置闹钟
//                Intent intent = new Intent(mContext, MyService.class);
                Intent intent = new Intent(mContext, MyBroadcast.class);
                intent.putExtra("msg", string);
//                PendingIntent pi = PendingIntent.getService(mContext, 0, intent,
//                        PendingIntent.FLAG_CANCEL_CURRENT);
                PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, intent,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                //用户选择时间
                c.set(Calendar.HOUR, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                //获取系统的AlarmManager
                Log.i(TAG, "System time" + System.currentTimeMillis());
                Log.i(TAG, "choice time" + c.getTimeInMillis());
                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
//                alarmManager.setWindow(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
//                        100, pi);


//                android 4.4(19)以下使用set, 19以上set时间不准确, 需使用setExact
//                alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(), pi);
            }
        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), false).show();
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
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, "快来记账吧");
        File f = new File(Environment.getExternalStorageDirectory()+"/shared.png");
//        Uri uri = Uri.fromFile(f);
        intent.putExtra(Intent.EXTRA_STREAM, R.mipmap.shared);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "选择分享途径"));
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
        presenter = new TotalRecordPresenterImpl(this);
        presenter.getTotalRecord(User.getCurrentUser().getObjectId());
        Log.e(TAG, "improtantData: "+ User.getCurrentUser().getObjectId());
    }
    @Override
    public void loadDataSuccess(DataSum tData) {
        float money = tData.getTotalIncome()-tData.getTotalOutcome();
        Log.e("meng666", "loadDataSuccess: "+tData );
        Log.e("meng666", "day"+tData.getRecordDay()+"number"+tData.getRecordNumber()+"money"+money );
        recordDays.setText(String.valueOf(tData.getRecordDay())); //总天数
        recordDeals.setText(String.valueOf(tData.getRecordNumber())); //总笔数
        recordSurplus.setText(String.valueOf(money)); //结余

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

    @Override
    public void loadDataError(Throwable throwable) {
        SnackbarUtils.show(mActivity, throwable.getMessage());
        Log.e(TAG, "loadDataError: "+throwable );
    }
}
