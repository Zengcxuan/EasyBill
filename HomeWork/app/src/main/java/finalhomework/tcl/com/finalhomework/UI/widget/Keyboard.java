package finalhomework.tcl.com.finalhomework.UI.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.liuwan.customdatepicker.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import finalhomework.tcl.com.finalhomework.R;

public class Keyboard extends Dialog implements View.OnClickListener{

    private TextView currentDate;
    private CustomDatePicker customDatePicker;
    private TextView[] key = new TextView[15];

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
        this.getWindow();
        //设置Dialog从窗体底部弹出
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().getAttributes().y = 0;//设置Dialog距离底部的距离
    }

    public Keyboard (Context context) {
        super(context, R.style.ActionSheetDialogStyle);
        setOwnerActivity((Activity) context);
        setContentView(R.layout.keyboard);
        currentDate = findViewById(R.id.tb_calc_day);
        currentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
        initDatePicker((Activity)context);


    }

    private void setTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        customDatePicker.show(/*currentDate.getText().toString()*/now);
        Log.e("mengtime","settime");
    }

    private void initDatePicker(Activity activity) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        final String now = sdf.format(new Date());
        final String day = now.substring(0,10);
        customDatePicker = new CustomDatePicker(activity, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                String choice;
                if(day.equals(time.substring(0,10))){
                    choice = "今天";
                }else {
                    choice = time.substring(0,10);
                }
                currentDate.setText(choice);
            }
        }, "2017-01-01 00:00", "2019-12-31 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(false); // 不显示时和分
        customDatePicker.setIsLoop(true); // 不允许循环滚动

    }


    @Override
    public void onClick(View v){

    }
}
