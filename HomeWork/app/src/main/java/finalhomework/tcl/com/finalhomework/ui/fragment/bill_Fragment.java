package finalhomework.tcl.com.finalhomework.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liuwan.customdatepicker.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Unbinder;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.ui.activity.BillAddActivity;
import finalhomework.tcl.com.finalhomework.ui.activity.SearchAll;

import finalhomework.tcl.com.finalhomework.Utils.meng_MyUtils;
import finalhomework.tcl.com.finalhomework.ui.activity.AddBill;
import finalhomework.tcl.com.finalhomework.base.Constants;
import finalhomework.tcl.com.finalhomework.base.LocalRepository;
import finalhomework.tcl.com.finalhomework.pojo.AllSortBill;
import finalhomework.tcl.com.finalhomework.pojo.SortBill;

import static android.view.Gravity.CENTER;

public class bill_Fragment extends HomeBaseFragment implements View.OnClickListener {
    private CustomDatePicker customDatePicker1, customDatePicker2;
    private EditText currentDate, currentYear;
    private ImageButton addBillBtn;
    private ImageView nullBillBtn;
    private ImageButton searchBtn;
    meng_MyUtils meng_util = new meng_MyUtils();
    private Unbinder unbinder;

    public static bill_Fragment newInstance(String info) {
        Bundle args = new Bundle();
        bill_Fragment fragment = new bill_Fragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onClick(View v) {
         if(v == currentDate){
            setTime();
        }else if(v == addBillBtn || v == nullBillBtn){
             addBill();
         }
    }

    /**
     * this is a test for add note
     */


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
        Intent intent = new Intent(getActivity(), BillAddActivity.class);
        startActivity(intent);
    }

    @Override
    public void myToolbar(){
        /**
         * set  toolbar  and show
         * */
        super.myToolbar();
        TextView title = new TextView(getActivity());
        title.setText("账单");
        title.setTextSize(22);
        title.setTextColor(getResources().getColor(R.color.white));
        title.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        title.setLayoutParams(new Toolbar.LayoutParams(CENTER));
        title.setGravity(CENTER);
        setToolbar(title);

    }


    @Override
    protected int getItemMenu(){ return R.menu.menu_main; }

    @Override
    protected void setItemReact(){
        Intent intent = new Intent(getActivity(), SearchAll.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bill;
    }

    @Override
    protected void loadData() {
        currentDate=(EditText)getActivity().findViewById(R.id.bill_time_month);
        currentYear=(EditText)getActivity().findViewById(R.id.bill_time_year);
        addBillBtn = (ImageButton) getActivity().findViewById(R.id.bill_add);
        nullBillBtn = (ImageView) getActivity().findViewById(R.id.bill_null);
        nullBillBtn.setOnClickListener(this);
        currentDate.setOnClickListener(this);
        addBillBtn.setOnClickListener(this);
        initDatePicker();
    }

    @Override
    protected void beforeDestroy() {

    }

    @Override
    protected DrawerLayout getDrawerLayout(){
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerlayout_bill);
        return mDrawerLayout;
    }

    @Override
    protected  Toolbar getToolbar(){
        View viewToolbar = getActivity().findViewById(R.id.toolbar_bill);
        Toolbar toolbar = (Toolbar) viewToolbar.findViewById(R.id.tl_custom);
        return toolbar;
    }

    @Override
    protected NavigationView getViewNavigation(){
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.navigationview_bill);
        return navigationView;
    }
}
