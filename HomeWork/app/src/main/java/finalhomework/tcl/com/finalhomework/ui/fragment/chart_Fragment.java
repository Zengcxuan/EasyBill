package finalhomework.tcl.com.finalhomework.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.ui.activity.SearchAll;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.END;
import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.START;


public class chart_Fragment extends HomeBaseFragment implements View.OnClickListener{
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
    @BindView(R.id.hellochart11)
    lecho.lib.hellocharts.view.LineChartView chart;//显示线条的自定义View
    private lecho.lib.hellocharts.model.LineChartData data;  //折线图封装的数据类
    private int numberOfLines = 1;   // number of lines
    private int maxNumberOfLines =1;  //the max number of Lines
    private int numberOfPoints =12;  //number of points

    float[][] randomNumbersTab = new float [maxNumberOfLines][numberOfPoints];

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;   //是否有轴的名字
    private boolean hasLines = true;       //是否有线（点和点连接的线）
    private boolean hasPoints = true;       //是否有点（每个值的点）
    private lecho.lib.hellocharts.model.ValueShape shape = lecho.lib.hellocharts.model.ValueShape.CIRCLE;    //点显示的形式，圆形，正方向，菱形
    private boolean isFilled = false;                //是否是填充
    private boolean hasLabels = false;               //每个点是否有名字
    private boolean isCubic = true;                 //是否是立方的，线条是直线还是弧线
    private boolean hasLabelForSelected = false;       //每个点是否可以选择（点击效果）
    private boolean pointsHaveDifferentColor;           //线条的颜色变换
    private boolean hasGradientToTransparent = false;      //是否有梯度的透明

    public static chart_Fragment newInstance(String info) {
        Bundle args = new Bundle();
        chart_Fragment fragment = new chart_Fragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }



    /**
     * 加载数据
     */
    private void initData() {
        // Generate some random values.
        generateValues();   //设置四条线的值数据
        generateData();    //设置数据
        // Disable viewport recalculations, see toggleCubic() method for more info.
        chart.setViewportCalculationEnabled(false);

        chart.setZoomType(ZoomType.HORIZONTAL);//设置线条可以水平方向收缩，默认是全方位缩放
        resetViewport();   //设置折线图的显示大小
    }

    /**
     * 按钮事件处理
     */
    @OnClick({R.id.button_week, R.id.button_month, R.id.button_year, R.id.thisweek, R.id.lastweek})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_week:
                BtnStateChange(1);
                break;
            case R.id.button_month:
                BtnStateChange(2);
                break;
            case R.id.button_year:
                BtnStateChange(3);
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
        }
    }

    /**
     * 重写myToolbar,添加Spinner作为下拉框,添加监听事件
     */
    @Override
    public void myToolbar(){
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
                switch (position){
                    case 0:
                        break;//收入
                    case 1:
                        Toast.makeText(getActivity(), "111", Toast.LENGTH_LONG).show();
                        break;//支出
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 图表添加监听
     */
    private void initEvent() {
        chart.setOnValueTouchListener((LineChartOnValueSelectListener) new ValueTouchListener());

    }
    /**
     * 图像显示大小
     */
    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 100;
        v.left = 0;
        v.right = numberOfPoints - 1;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    /**
     * 设置四条线条的数据
     */
    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }

    /**
     * 配置数据
     */
    private void generateData() {
        //存放线条对象的集合
        List<Line> lines = new ArrayList<Line>();
        //把数据设置到线条上面去
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setCubic(true);
            //line.setHasGradientToTransparent(hasGradientToTransparent);
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setTextColor(Color.BLACK);//设置x轴字体的颜色
                axisY.setTextColor(Color.BLACK);//设置y轴字体的颜色
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }

    /**
     * 触摸监听类
     */
    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getContext(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {


        }

    }


    /**
     * 年,月,周按钮点击后的颜色变化
     */
    public void BtnStateChange(int type){
        switch(type){
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

    }

    @Override
    protected int getItemMenu(){ return R.menu.menu_main; }

    /**
     * 设置菜单项的响应事件,这里是开启查询
     */
    @Override
    protected void setItemReact(){
        Intent intent = new Intent(getActivity(), SearchAll.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chart;
    }

    /**
     * 重写父类方法,初始化数据
     */
    @Override
    protected void loadData() {
        initData();
        initEvent();
        tableRow.setGravity(END);

    }

    @Override
    protected void beforeDestroy() {

    }

    /**
     * 重写父类抽象方法,返回DrawerLayout的ID
     */
    @Override
    protected DrawerLayout getDrawerLayout(){
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerlayout_chart);
        return mDrawerLayout;
    }

    /**
     * 重写父类抽象方法,返回Toolbar的ID
     */
     @Override
    protected  Toolbar getToolbar(){
        View viewToolbar = getActivity().findViewById(R.id.toolbar_chart);
        Toolbar toolbar = (Toolbar) viewToolbar.findViewById(R.id.tl_custom);
        return toolbar;
    }

    /**
     * 重写父类抽象方法,返回navigationView的ID
     */
    @Override
    protected NavigationView getViewNavigation(){
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.navigationview_chart);
        return navigationView;
    }

}
