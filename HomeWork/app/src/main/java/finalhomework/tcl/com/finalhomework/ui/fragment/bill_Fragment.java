package finalhomework.tcl.com.finalhomework.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuwan.customdatepicker.widget.CustomDatePicker;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import finalhomework.tcl.com.finalhomework.R;

import finalhomework.tcl.com.finalhomework.Utils.DateUtils;
import finalhomework.tcl.com.finalhomework.Utils.SnackbarUtils;
import finalhomework.tcl.com.finalhomework.Utils.stickyheader.StickyHeaderGridLayoutManager;
import finalhomework.tcl.com.finalhomework.base.BmobRepository;
import finalhomework.tcl.com.finalhomework.base.SyncEvent;
import finalhomework.tcl.com.finalhomework.mvp.presenter.MonthDetailPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.MonthDetailPresenterImpl;
import finalhomework.tcl.com.finalhomework.mvp.views.MonthDetailView;
import finalhomework.tcl.com.finalhomework.pojo.MonthDetailAccount;
import finalhomework.tcl.com.finalhomework.pojo.Person;
import finalhomework.tcl.com.finalhomework.pojo.ShareBill;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import finalhomework.tcl.com.finalhomework.pojo.User;
import finalhomework.tcl.com.finalhomework.pojo.base;
import finalhomework.tcl.com.finalhomework.ui.activity.PersionalInfoActivity;
import finalhomework.tcl.com.finalhomework.ui.activity.BillAddActivity;
import finalhomework.tcl.com.finalhomework.ui.activity.BillEditActivity;
import finalhomework.tcl.com.finalhomework.ui.activity.SearchAll;

import finalhomework.tcl.com.finalhomework.Utils.meng_MyUtils;

import finalhomework.tcl.com.finalhomework.ui.adapter.MonthDetailAdapter;
import finalhomework.tcl.com.finalhomework.ui.widget.ImageButtonWithText;

import static android.view.Gravity.CENTER;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_M;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_Y;

public class bill_Fragment extends HomeBaseFragment implements MonthDetailView {
    private CustomDatePicker customDatePicker1;
    private String setYear = DateUtils.getCurYear(FORMAT_Y);
    private String setMonth = DateUtils.getCurMonth(FORMAT_M);

    private MonthDetailPresenter presenter;

    int part, index;
    private static final int SPAN_SIZE = 1;
    private static final int RESULTCODE =0;
    private StickyHeaderGridLayoutManager mLayoutManager;
    private MonthDetailAdapter adapter;
    private List<MonthDetailAccount.DaylistBean> list;

    meng_MyUtils meng_util = new meng_MyUtils();
    private Unbinder unbinder;

    @BindView(R.id.money_in)
    TextView tOutcome;
    @BindView(R.id.money_out)
    TextView tIncome;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.bill_time_month)
    EditText currentDate;
    @BindView(R.id.bill_time_year)
    EditText currentYear;
//    @BindView(R.id.bill_add)
//    ImageButton bill_add;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(SyncEvent event) {
        if (event.getState()==100)
            //getBills(Constants.currentUserId, setYear, setMonth);
        getBills( User.getCurrentUser().getObjectId(), setYear, setMonth);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bill;
    }
    @Override
    protected void improtantData() {
        left();
        //注册 EventBus
        EventBus.getDefault().register(this);

        flash();

        presenter=new MonthDetailPresenterImpl(this);

        //请求当月数据
        //getBills(Constants.currentUserId, setYear, setMonth);
        getBills( User.getCurrentUser().getObjectId(), setYear, setMonth);
        initDatePicker();
        initFab();
    }
    @Override
    protected void loadData() {
    }
    /**
     * 监听list侧拉
     */
    public void left(){
        ShareBill shareBill = new ShareBill();
        Log.e("meng111", "left: " );
        mLayoutManager = new StickyHeaderGridLayoutManager(SPAN_SIZE);
        mLayoutManager.setHeaderBottomOverlapMargin(5);

        rvList.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public boolean animateRemove(RecyclerView.ViewHolder holder) {
                dispatchRemoveFinished(holder);
                return false;
            }
        });
        rvList.setLayoutManager(mLayoutManager);
        adapter = new MonthDetailAdapter(mContext, list);
        rvList.setAdapter(adapter);

        adapter.setOnStickyHeaderClickListener(new MonthDetailAdapter.OnStickyHeaderClickListener() {
            @Override
            public void OnDeleteClick(TotalBill item, int section, int offset) {
                item.setVersion(-1);
                //将删除的账单版本号设置为负，而非直接删除
                //便于同步删除服务器数据
                presenter.updateBill(item);
               /* BmobRepository.getInstance().deleteBills(shareBill.getObjectId());*/
                part = section;
                index = offset;
            }

            @Override
            public void OnEditClick(TotalBill item, int section, int offset) {
                Intent intent = new Intent(mContext, BillEditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id", item.getId());
                bundle.putString("rid", item.getRid());
                bundle.putString("sortName", item.getSortName());
                bundle.putString("payName", item.getPayName());
                bundle.putString("content", item.getContent());
                bundle.putDouble("cost", item.getCost());
                bundle.putLong("date", item.getCrdate());
                bundle.putBoolean("income", item.isIncome());
                bundle.putInt("version", item.getVersion());
                intent.putExtra("bundle", bundle);
                startActivityForResult(intent, 0);
            }
        });
    }
    /**
     * 借口回调
     * @param tData
     */
    @Override
    public void loadDataSuccess(MonthDetailAccount tData) {
        tOutcome.setText(tData.getT_outcome());
        tIncome.setText(tData.getT_income());
        list = tData.getDaylist();
        adapter.setmDatas(list);
        adapter.notifyAllSectionsDataSetChanged();//需调用此方法刷新
    }

    @Override
    public void loadDataSuccess(base tData) {
        adapter.remove(part, index);
    }


    @Override
    public void loadDataError(Throwable throwable) {
        SnackbarUtils.show(mActivity,throwable.getMessage());
    }

    /**
     * 下拉刷新和加载数据
     */
    public void flash(){
        swipe.setColorSchemeColors(getResources().getColor(R.color.text_red), getResources().getColor(R.color.text_red));
        //设置向下拉多少出现刷新
        swipe.setDistanceToTriggerSync(200);
        //设置刷新出现的位置
        swipe.setProgressViewEndTarget(false, 200);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
                //getBills(Constants.currentUserId, setYear, setMonth);
                getBills( User.getCurrentUser().getObjectId(), setYear, setMonth);
               // getBills( User.getCurrentUser().getObjectId(), setYear, setMonth);
            }
        });

    }
    /**
     * 获取账单数据
     *
     * @param userid
     * @param year
     * @param month
     */
    private void getBills(String userid, String year, String month) {
        //请求数据前清空数据
        adapter.clear();
        tOutcome.setText("0.00");
        tIncome.setText("0.00");
        //请求某年某月数据
        presenter.getMonthDetailBills(userid, setYear, setMonth);
    }
    /**
     * 时间选择
     */
    /*id = R.id.bill_time setTime on the editext*/
    @OnClick({R.id.bill_time_year,R.id.bill_time_month})
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
                currentYear.setText(year.split(" ")[0]);
                setYear = time.substring(0,4);
                setMonth = time.substring(5,7);
                //getBills(Constants.currentUserId, setYear, setMonth);
                getBills( User.getCurrentUser().getObjectId(), setYear, setMonth);
            }
        }, "2017-01-01 00:00", "2019-12-31 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(true); // 不允许循环滚动

    }

    /**
     * 添加悬浮按钮
     */
    private void initFab(){
//        ImageView image = new ImageView(mContext);
//        image.setImageDrawable(mContext.getDrawable(R.drawable.add_bill3));
        FloatingActionButton floatingActionButton
                = new FloatingActionButton.Builder(mActivity).build();
        floatingActionButton.setBackground(mContext.getDrawable(R.drawable.add_bill3));
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(mActivity);
        ImageView itemIcon = new ImageView(mContext);
//        itemIcon.setImageDrawable(mContext.getDrawable(R.drawable.add));
//        itemIcon.setBackground(mContext.getDrawable(R.drawable.add_bill3));
        SubActionButton addbill = itemBuilder.build();
        addbill.setBackground(mContext.getDrawable(R.drawable.add));
//        ImageView itemIcon2 = new ImageView(mContext);
//        itemIcon2.setImageDrawable(mContext.getDrawable(R.drawable.add_bill3));
//        SubActionButton refreshbill = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton refreshbill = itemBuilder.build();
        refreshbill.setBackground(mContext.getDrawable(R.drawable.add));

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(mActivity)
                .addSubActionView(addbill)
                .addSubActionView(refreshbill)
                .attachTo(floatingActionButton)
                .build();

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(180, 180);
        params.setMargins(0, 0, 0, 100);
        floatingActionButton.setPosition(4, params);

        addbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启账单添加
                Intent intent = new Intent(getActivity(), BillAddActivity.class);
                startActivityForResult(intent,RESULTCODE);
            }
        });

        refreshbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 18-10-18 数据更新
            }
        });

    }
    /**
     * Activity返回
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            //getBills(Constants.currentUserId, setYear, setMonth);
        getBills( User.getCurrentUser().getObjectId(), setYear, setMonth);


    }
    /**
     * 布局加载
     */
    public static bill_Fragment newInstance(String info) {
        Bundle args = new Bundle();
        bill_Fragment fragment = new bill_Fragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;

    }
    @Override
    protected void beforeDestroy() {
        EventBus.getDefault().unregister(this);
    }

    /**
     * 原悬浮按钮
     */
//    @OnClick(R.id.bill_add)
//    public void addBill() {
//        Intent intent = new Intent(getActivity(), BillAddActivity.class);
//        startActivityForResult(intent,RESULTCODE);
//        /*Person person = Person.getCurrentUser(Person.class);
//        BmobRepository.getInstance().syncBill(person.getObjectId());*/
//    }

    /**
     * 开启个人信息界面
     * */
//    @OnClick(R.id.head_bill)
//    public void showHead(){
//        Intent intent = new Intent(mContext, PersionalInfoActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void myToolbar(){
        /**
         * set  toolbar  and show
         * */
       super.myToolbar();
        TextView title = new TextView(getActivity());
        title.setText("账单");
        title.setTextSize(18);
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
    protected DrawerLayout getDrawerLayout(){ return getActivity().findViewById(R.id.drawerlayout_bill); }

    @Override
    protected  Toolbar getToolbar(){
        View viewToolbar = getActivity().findViewById(R.id.toolbar_bill);
        return viewToolbar.findViewById(R.id.tl_custom);
    }

    @Override
    protected LinearLayout getLeftWindow(){ return getActivity().findViewById(R.id.navigationview_bill); }

    /**
     * 返回头像
     * */
    @Override
    protected ImageButtonWithText getHead(){
        return getActivity().findViewById(R.id.head_bill);
    }

    /**
     * 返回键名字
     * */
    @Override
    protected ImageButton getBackBtn(){
        return getActivity().findViewById(R.id.back_bill);
    }

}
