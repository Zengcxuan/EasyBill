package finalhomework.tcl.com.finalhomework.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.ChartUtil;
import finalhomework.tcl.com.finalhomework.Utils.DateUtils;
import finalhomework.tcl.com.finalhomework.Utils.SnackbarUtils;
import finalhomework.tcl.com.finalhomework.base.Constants;
import finalhomework.tcl.com.finalhomework.mvp.presenter.MonthChartPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.MonthDetailPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.MonthChartPresenterImpl;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.MonthDetailPresenterImpl;
import finalhomework.tcl.com.finalhomework.mvp.views.MonthChartView;
import finalhomework.tcl.com.finalhomework.mvp.views.MonthDetailView;
import finalhomework.tcl.com.finalhomework.pojo.MonthBillForChart;
import finalhomework.tcl.com.finalhomework.pojo.MonthDetailAccount;
import finalhomework.tcl.com.finalhomework.pojo.base;
import finalhomework.tcl.com.finalhomework.ui.activity.SearchAll;

import lecho.lib.hellocharts.gesture.ContainerScrollType;

import finalhomework.tcl.com.finalhomework.ui.widget.ImageButtonWithText;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.END;
import static android.view.Gravity.START;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_D;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_M;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_Y;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.calendar;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.getBeginDayOfWeek;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.getEndDayOfWeek;



public class chart_Fragment extends HomeBaseFragment implements /*MonthChartView,*/MonthChartView {

    @BindView(R.id.thisweek)
    Button thisWeekBtn;
    @BindView(R.id.lastweek)
    Button lastWeekBtn;
    @BindView(R.id.view_this)
    View thisView;
    @BindView(R.id.tableRow)
    TableRow tableRow;
    @BindView(R.id.button_week)
    Button weekBtn;
    @BindView(R.id.button_month)
    Button monthBtn;
    @BindView(R.id.button_year)
    Button yearBtn;
    @BindView(R.id.lineChart)
    LineChart chart;//显示线条的自定义View
    private MonthDetailPresenter detailPresenter;
    private boolean TYPE = true;//默认总收入true

    private String TAG = "meng111";
    private MonthBillForChart adapter;
    private String setYear = DateUtils.getCurYear(FORMAT_Y);
    private String setMonth = DateUtils.getCurMonth(FORMAT_M);
    private String setDay = DateUtils.getDay(0, FORMAT_D);
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private List<MonthDetailAccount.DaylistBean> list;
    private List<Line> lines;
    private Line line;
    private boolean hasData = true;
    private List<Entry> values = new ArrayList<>();
    List<PointValue> outcome = new ArrayList<PointValue>();    //每天支出
    List<PointValue> income = new ArrayList<PointValue>();    //每天收入

    /**
     * 图表加入数据
     */
    private void setData(int x, String y) {
        Float data = Float.parseFloat(y);
        Log.e("meng111", "setData: _______________" + data);
        values.add(new Entry(x, data));
        Log.e(TAG, "setData: " + values);
    }

    private void falseData() {
        values.add(new Entry(0, 10));
        values.add(new Entry(1, 20));
        values.add(new Entry(2, 30));
        values.add(new Entry(3, 10));
        values.add(new Entry(4, 40));
        values.add(new Entry(5, 5));
        values.add(new Entry(6, 70));
    }

    /**
     * 按钮事件处理
     */

    @OnClick({R.id.button_week, R.id.button_month, R.id.button_year, R.id.thisweek, R.id.lastweek, R.id.lineChart, R.id.head_chart})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_week:
                BtnStateChange(1);
                lastWeekBtn.setText("上周");
                thisWeekBtn.setText("本周");
                break;
            case R.id.button_month:
                BtnStateChange(2);
                lastWeekBtn.setText("上月");
                thisWeekBtn.setText("本月");
                break;
            case R.id.button_year:
                BtnStateChange(3);
                lastWeekBtn.setText("上年");
                thisWeekBtn.setText("本年");
                break;
            case R.id.thisweek:
                lastWeekBtn.setTextColor(getResources().getColor(R.color.tab_unclicked));
                thisWeekBtn.setTextColor(getResources().getColor(R.color.tab_clicked));
                tableRow.setGravity(END);
                break;
            case R.id.lastweek:
                thisWeekBtn.setTextColor(getResources().getColor(R.color.tab_unclicked));
                lastWeekBtn.setTextColor(getResources().getColor(R.color.tab_clicked));
                tableRow.setGravity(START);
                break;

            case R.id.lineChart:
                falseData();
                ChartUtil.notifyDataSetChanged(chart, values, ChartUtil.weekValue);

            case R.id.head_chart:
                //开启个人信息界面
                Toast.makeText(getActivity(), "你点击了我", Toast.LENGTH_LONG).show();
                break;
            default:
                break;

        }
    }

    /**
     * 加载数据成功
     *
     * @param tData 所有数据
     */
    @Override
    public void loadDataSuccess(MonthBillForChart tData) {
        // list = tData.getDaylist();
        Log.e("meng111", "loadDataSuccess");
        falseData();
       /* String[] come ;
        for (int i=0;i<7;i++){
            if (list.get(i).getList()==null  ){
                setData(i,"0.0");
            }else {
                Log.e(TAG, "loadDataSuccess:!!!!!!!!!!!! "+list.get(i).toString() );
                come= list.get(i).getMoney().split("u");
                Log.e(TAG, "loadDataSuccess: "+come[0] );
                setData(i,come[0]);
            }

        }*/
    }

    /**
     * 年,月,周按钮点击后的颜色变化
     */
    public void BtnStateChange(int type) {
        switch (type) {
            case 1:
                weekBtn.setBackground(getActivity().getDrawable(R.drawable.datepicker_background_clicked));
                monthBtn.setBackground(getActivity().getDrawable(R.drawable.datepicker_background));
                yearBtn.setBackground(getActivity().getDrawable(R.drawable.datepicker_background));
                break;
            case 2:
                weekBtn.setBackground(getActivity().getDrawable(R.drawable.datepicker_background));
                monthBtn.setBackground(getActivity().getDrawable(R.drawable.datepicker_background_clicked));
                yearBtn.setBackground(getActivity().getDrawable(R.drawable.datepicker_background));
                break;
            case 3:
                weekBtn.setBackground(getActivity().getDrawable(R.drawable.datepicker_background));
                monthBtn.setBackground(getActivity().getDrawable(R.drawable.datepicker_background));
                yearBtn.setBackground(getActivity().getDrawable(R.drawable.datepicker_background_clicked));
                break;

        }
    }

    /**
     * 返回Toolbar的菜单项（右边）
     */
    @Override
    protected void improtantData() {

        getday();
        falseData();
        ChartUtil.notifyDataSetChanged(chart, values, ChartUtil.weekValue);
        //ChartUtil.initChart(chart);


    }


    /**
     * 设置菜单项的响应事件,这里是开启查询
     */


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
    }

    /**
     * 重写父类方法,初始化数据
     */
    @Override
    protected void loadData() {
        tableRow.setGravity(END);
        /* presenter=new MonthChartPresenterImpl(this);*/
    }


    /**
     * 返回键名字
     */
    @Override
    protected ImageButton getBackBtn() {
        return getActivity().findViewById(R.id.back_chart);
    }


    @Override
    protected void beforeDestroy() {

    }

    /**
     * 重写父类抽象方法,返回DrawerLayout的ID
     */
    @Override
    protected DrawerLayout getDrawerLayout() {
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerlayout_chart);
        return mDrawerLayout;
    }

    @Override
    protected int getItemMenu() {
        return R.menu.menu_main;
    }

    /**
     * * 设置菜单项的响应事件,这里是开启查询
     */
    @Override
    protected void setItemReact() {
        Intent intent = new Intent(getActivity(), SearchAll.class);
        startActivity(intent);
    }

    /**
     * 重写父类抽象方法,返回Toolbar的ID
     */
    @Override
    protected Toolbar getToolbar() {
        View viewToolbar = getActivity().findViewById(R.id.toolbar_chart);
        return viewToolbar.findViewById(R.id.tl_custom);
    }

    /**
     * 重写父类抽象方法,返回navigationView的ID
     */
    @Override
    protected LinearLayout getLeftWindow() {
        return getActivity().findViewById(R.id.navigationview_chart);
    }


    /*@Override
    public void loadDataSuccess(MonthBillForChart tData) {
        monthChartBean=tData;
        initData();
    }*/

    @Override
    public void loadDataError(Throwable throwable) {
        SnackbarUtils.show(mActivity, throwable.getMessage());
    }

    public void getday() {
       /* detailPresenter = new MonthDetailPresenterImpl(this);
        detailPresenter.getDayCost(Constants.currentUserId, setYear, setMonth*//*,setDay*//*);*/
        Log.e(TAG, "getday: ");
    }


    /**
     * 重写myToolbar,添加Spinner作为下拉框,添加监听事件
     */
    @Override
    public void myToolbar() {
        // TODO: 18-9-29  modify
        super.myToolbar();
        Spinner type = new Spinner(getActivity());
        List<String> dataOfType = new ArrayList<String>();
        dataOfType.add("收入");
        dataOfType.add("支出");
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_style, dataOfType);
        typeAdapter.setDropDownViewResource(R.layout.spinner_drop_style);
        type.setAdapter(typeAdapter);
//        title.setTextColor(getResources().getColor(R.color.white))
        type.setDropDownHorizontalOffset(0);
        type.setDropDownVerticalOffset(60);
        type.setLayoutParams(new ViewGroup.LayoutParams(30,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        type.setPopupBackgroundDrawable(getResources().getDrawable(R.color.center_color));
//        type.setBackground(getResources().getDrawable(R.drawable.shape_color_blue));
        type.setLayoutParams(new Toolbar.LayoutParams(CENTER));
        type.setGravity(END);
        setToolbar(type);
        //下拉框的点击监听
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;//收入
                    case 1:

                        break;//支出
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public static chart_Fragment newInstance(String info) {
        Bundle args = new Bundle();
        chart_Fragment fragment = new chart_Fragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * 返回头像
     */
    @Override
    protected ImageButtonWithText getHead() {
        return getActivity().findViewById(R.id.head_chart);

    }
}
