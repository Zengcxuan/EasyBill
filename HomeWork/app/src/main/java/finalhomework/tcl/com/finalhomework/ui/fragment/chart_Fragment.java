package finalhomework.tcl.com.finalhomework.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


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


public class chart_Fragment extends HomeBaseFragment implements View.OnClickListener{
    private Button weekBtn, monthBtn, yearBtn;
    private lecho.lib.hellocharts.view.LineChartView chart;  //显示线条的自定义View
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


    //this is for linechart
    private void initView() {
    //实例化
    chart = (LineChartView) getActivity().findViewById(R.id.hellochart11);
    }

    private void initData() {
        // Generate some random values.
        generateValues();   //设置四条线的值数据
        generateData();    //设置数据

        // Disable viewport recalculations, see toggleCubic() method for more info.
        chart.setViewportCalculationEnabled(false);

        chart.setZoomType(ZoomType.HORIZONTAL);//设置线条可以水平方向收缩，默认是全方位缩放
        resetViewport();   //设置折线图的显示大小
    }

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
    //this is for
    @Override
    public void myToolbar(){
        // TODO: 18-9-29  modify
        super.myToolbar();
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
        return R.layout.fragment_chart;
    }

    @Override
    protected void loadData() {
        initView();
        initData();
        initEvent();
        weekBtn = (Button) getActivity().findViewById(R.id.button_week);
        weekBtn.setOnClickListener(this);
        monthBtn = (Button) getActivity().findViewById(R.id.button_month);
        monthBtn.setOnClickListener(this);
        yearBtn = (Button) getActivity().findViewById(R.id.button_year);
        yearBtn.setOnClickListener(this);
    }

    @Override
    protected void beforeDestroy() {

    }
    protected DrawerLayout getDrawerLayout(){
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerlayout_chart);
        return mDrawerLayout;
    }
    protected  Toolbar getToolbar(){
        View viewToolbar = getActivity().findViewById(R.id.toolbar_chart);
        Toolbar toolbar = (Toolbar) viewToolbar.findViewById(R.id.tl_custom);
        return toolbar;
    }
    protected NavigationView getViewNavigation(){
        NavigationView navigationView = (NavigationView)getActivity().findViewById(R.id.navigationview_chart);
        return navigationView;
    }

    @OnClick({R.id.button_week, R.id.button_month, R.id.button_year})
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


        }
    }


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
}
