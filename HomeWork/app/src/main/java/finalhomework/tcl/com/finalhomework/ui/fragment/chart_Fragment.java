package finalhomework.tcl.com.finalhomework.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

import java.util.Date;
import java.util.List;


import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.R;

import finalhomework.tcl.com.finalhomework.Utils.DateUtils;
import finalhomework.tcl.com.finalhomework.Utils.SnackbarUtils;
import finalhomework.tcl.com.finalhomework.mvp.presenter.MonthChartPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.MonthChartPresenterImpl;
import finalhomework.tcl.com.finalhomework.mvp.views.MonthChartView;
import finalhomework.tcl.com.finalhomework.pojo.MonthBillForChart;
import finalhomework.tcl.com.finalhomework.pojo.MonthDetailAccount;
import finalhomework.tcl.com.finalhomework.ui.activity.PersionalInfoActivity;
import finalhomework.tcl.com.finalhomework.ui.activity.SearchAll;
import finalhomework.tcl.com.finalhomework.ui.adapter.ChartAdapter;
import finalhomework.tcl.com.finalhomework.ui.adapter.MonthChartAdapter;
import finalhomework.tcl.com.finalhomework.ui.widget.ImageButtonWithText;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.END;
import static android.view.Gravity.START;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_M;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_Y;


public class chart_Fragment extends HomeBaseFragment implements /*MonthChartView,*/MonthChartView/*bill_Fragment.DailyDatas*/ {

    @BindView(R.id.thisweek)
    Button thisWeekBtn;
    @BindView(R.id.lastweek)
    Button lastWeekBtn;
    @BindView(R.id.view_this)
    View thisView;
    @BindView(R.id.tableRow)
    TableRow tableRow;
//    @BindView(R.id.button_week)
//    Button weekBtn;
//    @BindView(R.id.button_month)
//    Button monthBtn;
//    @BindView(R.id.button_year)
//    Button yearBtn;
    /*@BindView(R.id.chart)
    LineChart chart;//显示线条的自定义View*/
    @BindView(R.id.rv_list_chart)
    RecyclerView rvList;
    @BindView(R.id.swipe2)
    SwipeRefreshLayout swipe;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private MonthChartAdapter adapter;

    private boolean TYPE = true;//默认总收入true
    private final static int FULL_SPAN = 3;
    private final static int MULTI_SPAN = 2;
    private final static int SINGLE_SPAN = 1;
    private String TAG = "meng111";

    private  GridLayoutManager mLayoutManager;
    private Context context;
    private String setYear = DateUtils.getCurYear(FORMAT_Y);
    private String setMonth = DateUtils.getCurMonth(FORMAT_M);
    private MonthChartPresenter presenter;
    private ChartAdapter chartAdapter;
   // private List<Entry> values = new ArrayList<>();

    List<MonthBillForChart.SortTypeList> listInComeData ;
    List<MonthBillForChart.SortTypeList> listOutComeData;
    List<MonthDetailAccount.DaylistBean> detailList;
    //private int now=4;
    private String crdate;
    private int week;
    private String allMoney;
    private String income;
    private String outcome;
    private static boolean isIncome = true;
    public static boolean isThisWeek = true;
    private Date beginDayOfWeek;
    private Date endDayOfWeek;
    private Date beginDayOfLastWeek;
    private Date endDayOfLastWeek;
    //private float[][] incomeValues = {{0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f}};
    private float[] incomeValues = {0f,0f,0f,0f,0f,0f,0f};
    private float[] outcomeValues = {0f,0f,0f,0f,0f,0f,0f};
    //private float[][] outcomeValues = {{0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f}};
    //private float[] data = {10f,20f,30f,40f,65f,10f,77f};
    /**
     * 按钮事件处理
     */
    @OnClick({R.id.thisweek, R.id.lastweek, R.id.head_chart})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.thisweek:
                lastWeekBtn.setTextColor(getResources().getColor(R.color.tab_unclicked));
                thisWeekBtn.setTextColor(getResources().getColor(R.color.tab_clicked));
                tableRow.setGravity(END);
                isThisWeek =true;
                presenter.getMonthChartBills(currentUser.getObjectId(), setYear, setMonth);
                break;
            case R.id.lastweek:
                thisWeekBtn.setTextColor(getResources().getColor(R.color.tab_unclicked));
                lastWeekBtn.setTextColor(getResources().getColor(R.color.tab_clicked));
                tableRow.setGravity(START);
                isThisWeek =false;
                presenter.getMonthChartBills(currentUser.getObjectId(), setYear, setMonth);
                break;
            case R.id.head_chart:
                //开启个人信息界面
                Intent intent = new Intent(mContext, PersionalInfoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void data(){
        Log.e(TAG, "data: " );
        /*判断是收入还是支出，图表和view显示数据*/
        if (isIncome ){
            if (isThisWeek){//isIncome=true,isThisWeek=true
                WeekData(beginDayOfWeek,endDayOfWeek);
                setChart(incomeValues,isThisWeek);
            }else {//isIncome=false,isThisWeek=true
                WeekData(beginDayOfLastWeek,endDayOfLastWeek);
                setChart(incomeValues,isThisWeek);
            }
            setList(listInComeData);

        }else {
            if (isThisWeek){//isIncome=false,isThisWeek=true
                WeekData(beginDayOfWeek,endDayOfWeek);
                setChart(outcomeValues,isThisWeek);
            }else {//isIncome=false,isThisWeek=false
                WeekData(beginDayOfLastWeek,endDayOfLastWeek);
                setChart(outcomeValues,isThisWeek);
            }
            setList(listOutComeData);
        }
    }
    /**
     * 加载数据成功
     *
     * @param tData 所有数据
     */
    @Override
    public void loadDataSuccess(MonthBillForChart tData) {
        rvList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        context = getActivity().getApplicationContext();
        listInComeData = tData.getInSortlist();
        listOutComeData = tData.getOutSortlist();
        detailList = tData.getDaylist();

        beginDayOfWeek = DateUtils.getBeginDayOfWeek();
        endDayOfWeek = DateUtils.getEndDayOfWeek();
        beginDayOfLastWeek = DateUtils.getBeginDayOfLastWeek();
        endDayOfLastWeek = DateUtils.getEndDayOfLastWeek();
        data();

        }

    /**
     * 本周或者上周的收入和支出
     */
    public void WeekData(Date beginDay,Date endDay){

        for (int i =0;i<detailList.size();i++){
            crdate=detailList.get(i).getTime();
            crdate = crdate+" 12:00:00";//防止特殊时间报错
            if (DateUtils.belongCalendar(DateUtils.str2Date(crdate),beginDay,endDay)){
                try {
                    week =  DateUtils.DayForWeek(crdate);
                    allMoney = detailList.get(i).getMoney();

                    income = allMoney.substring(allMoney.indexOf("入")+2,allMoney.length());
                    incomeValues[week-1] = Float.valueOf(income);

                    outcome = allMoney.substring(3, allMoney.indexOf(" "));
                    outcomeValues[week-1] = Float.valueOf(outcome);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    /**
     * 下拉刷新和加载数据
     */
    public void flash(){
        swipe.setColorSchemeColors(getResources().getColor(R.color.text_red), getResources().getColor(R.color.text_red));
        //设置向下拉多少出现刷新
        swipe.setDistanceToTriggerSync(50);
        //设置刷新出现的位置
        swipe.setProgressViewEndTarget(false, 250);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
                presenter.getMonthChartBills(currentUser.getObjectId(), setYear, setMonth);
            }
        });
    }

    /**
     * 加入图表
     * @param values
     * @param isThisWeek
     */
    public void setChart(float[] values,boolean isThisWeek){
        chartAdapter = new ChartAdapter(getContext(),values,isThisWeek,5);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(chartAdapter);
    }
    /**
     * 加入listview
     */
    public void setList(List<MonthBillForChart.SortTypeList> Data){
        adapter = new MonthChartAdapter(getActivity(),Data);
        rvList.setAdapter(adapter);
    }
    /**
     * 返回Toolbar的菜单项（右边）
     */
    @Override
    protected void importantData() {

        presenter=new MonthChartPresenterImpl(this);
        presenter.getMonthChartBills(currentUser.getObjectId(), setYear, setMonth);
        flash();
        context = getActivity().getApplicationContext();
        mLayoutManager = new GridLayoutManager(getContext(), FULL_SPAN);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                switch (chartAdapter.getItemViewType(position)) {
                    case 0:
                        return FULL_SPAN;
                    default:
                        return SINGLE_SPAN;
                }
            }
        });
        float[] mValues = {0f, 0f, 0f, 0f, 0f, 0f, 0f};

        setChart(mValues,isThisWeek);

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
//        View viewToolbar = getActivity().findViewById(R.id.toolbar_chart);
        return getActivity().findViewById(R.id.toolbar_chart);
    }
    /**
     * 重写父类抽象方法,返回navigationView的ID
     */
    @Override
    protected LinearLayout getLeftWindow() {
        return getActivity().findViewById(R.id.navigationview_chart);
    }

    @Override
    public void loadDataError(Throwable throwable) {
        SnackbarUtils.show(mActivity, throwable.getMessage());
    }
    /**
     * 重写myToolbar,添加Spinner作为下拉框,添加监听事件
     */
    @Override
    public void myToolbar() {
        super.myToolbar();
        Spinner type = new Spinner(getActivity());
        List<String> dataOfType = new ArrayList<String>();
        dataOfType.add("收入");
        dataOfType.add("支出");
//        LinkedList<String> dataOfType=new LinkedList<>(Arrays.asList("收入", "支出"));
//        type.attachDataSource(dataOfType);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_style, dataOfType);
        typeAdapter.setDropDownViewResource(R.layout.spinner_drop_style);
        type.setAdapter(typeAdapter);
//        title.setTextColor(getResources().getColor(R.color.white))
//        type.setDropDownHorizontalOffset(0);
        type.setDropDownVerticalOffset(60);
        type.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        type.setPopupBackgroundDrawable(getResources().getDrawable(R.color.center_color));
//        type.setBackground(getResources().getDrawable(R.drawable.shape_color_blue));
        type.setLayoutParams(new Toolbar.LayoutParams(CENTER));
        type.setBackground(getResources().getDrawable(R.drawable.spinner_background));
        setToolbar(type);
        //下拉框的点击监听
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        isIncome = true;
                        //isThisWeek=true;
                        presenter.getMonthChartBills(currentUser.getObjectId(), setYear, setMonth);

                        break;//收入
                    case 1:
                        isIncome = false;
                        //isThisWeek=true;
                        presenter.getMonthChartBills(currentUser.getObjectId(), setYear, setMonth);
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

    /**
     * 切换fragment重新加载数据
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isCreated) {
            return;
        }
        if (isVisibleToUser) {
            presenter.getMonthChartBills(currentUser.getObjectId(),setYear,setMonth);
        }

    }
}
