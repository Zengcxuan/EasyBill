package finalhomework.tcl.com.finalhomework.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.liuwan.customdatepicker.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.meng_MyUtils;
import finalhomework.tcl.com.finalhomework.activity.AddBill;

public class bill_fragment extends Fragment implements View.OnClickListener {
    private CustomDatePicker customDatePicker1, customDatePicker2;
    private EditText currentDate, currentYear;
    private ImageButton addBillBtn;
    meng_MyUtils meng_util = new meng_MyUtils();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //引用创建好的xml布局
        View view = inflater.inflate(R.layout.fragment_bill,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentDate=(EditText)getActivity().findViewById(R.id.bill_time_month);
        currentYear=(EditText)getActivity().findViewById(R.id.bill_time_year);
        addBillBtn = getActivity().findViewById(R.id.bill_add);
        currentDate.setOnClickListener(this);
        addBillBtn.setOnClickListener(this);
        initDatePicker();
    }
    @Override
    public void onClick(View v) {
         if(v == currentDate){
            setTime();
        }else if(v == addBillBtn){
             addBill();
         }
    }

    /*id = R.id.bill_time setTime on the editext*/
    public void setTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        customDatePicker1.show(/*currentDate.getText().toString()*/now);
        Log.e("mengtime","settime");
    }
    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        String month1 = meng_util.getMonth(now);// ini
        //Log.e("mengtime", "initDatePicker month: " + month1);
        String year = meng_util.getYear(now);
        currentDate.setText(Html.fromHtml("<big>"+month1+"</big>"+"<small>"+"月"+"</small>"));
        currentYear.setText(year);
        customDatePicker1 = new CustomDatePicker(this.getActivity(), new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                meng_MyUtils meng_util = new meng_MyUtils();
                String month = meng_util.getMonth(time);
                String year = meng_util.getYear(time);
                currentDate.setText(Html.fromHtml("<big>"+month+"</big>"+"<small>"+"月"+"</small>"));
                //String date1= getResources().getString(R.string.date_font, month);//String.format(,month);
                //currentDate.setText(date1);
                currentYear.setText(year.split(" ")[0]);
            }
        }, "2017-01-01 00:00", "2019-12-31 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(true); // 不允许循环滚动

    }

    private void addBill() {
        Intent intent = new Intent(getActivity(), AddBill.class);
        startActivity(intent);
    }

}
