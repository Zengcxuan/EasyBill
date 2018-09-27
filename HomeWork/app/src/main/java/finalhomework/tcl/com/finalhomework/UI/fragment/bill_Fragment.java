package finalhomework.tcl.com.finalhomework.UI.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liuwan.customdatepicker.widget.CustomDatePicker;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.UI.activity.SearchAll;
import finalhomework.tcl.com.finalhomework.Utils.meng_MyUtils;
import finalhomework.tcl.com.finalhomework.UI.activity.AddBill;

import static android.view.Gravity.CENTER;

public class bill_Fragment extends Fragment implements View.OnClickListener {
    private CustomDatePicker customDatePicker1, customDatePicker2;
    private EditText currentDate, currentYear;
    private ImageButton addBillBtn;
    private ImageView nullBillBtn;
    private ImageButton searchBtn;
    meng_MyUtils meng_util = new meng_MyUtils();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //引用创建好的xml布局
        View view = inflater.inflate(R.layout.fragment_bill,container,false);
        return view;
    }
    public static bill_Fragment newInstance(String info) {
        Bundle args = new Bundle();
        bill_Fragment fragment = new bill_Fragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        currentDate=(EditText)getActivity().findViewById(R.id.bill_time_month);
        currentYear=(EditText)getActivity().findViewById(R.id.bill_time_year);
        addBillBtn = (ImageButton) getActivity().findViewById(R.id.bill_add);
        nullBillBtn = (ImageView) getActivity().findViewById(R.id.bill_null);
        nullBillBtn.setOnClickListener(this);
        currentDate.setOnClickListener(this);
        addBillBtn.setOnClickListener(this);
        myToolbar();
        initDatePicker();
        setHasOptionsMenu(true);
    }
    @Override
    public void onClick(View v) {
         if(v == currentDate){
            setTime();
        }else if(v == addBillBtn || v == nullBillBtn){
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


    public void myToolbar(){
        /**
         * set  toolbar  and show
         * */
        View viewToolbar = getActivity().findViewById(R.id.include_toolbar);

        Toolbar toolbar = (Toolbar) viewToolbar.findViewById(R.id.tl_custom);
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.navigation_view);
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.dl_left);

        TextView title = new TextView(getActivity());
        title.setText("账单");
        title.setTextSize(22);
        title.setTextColor(getResources().getColor(R.color.white));
        title.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        title.setLayoutParams(new Toolbar.LayoutParams(CENTER));
        toolbar.addView(title);
        title.setGravity(CENTER);
        navigationView.setItemIconTintList(null);

        toolbar.setTitle("");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //创建返回键，并实现打开关/闭监听
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                toolbar, R.string.open, R.string.close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //mAnimationDrawable.stop();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // mAnimationDrawable.start();

            }
        };
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.menu);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_edit:
                Intent intent = new Intent(getActivity(), SearchAll.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
        return true;
    }


}
