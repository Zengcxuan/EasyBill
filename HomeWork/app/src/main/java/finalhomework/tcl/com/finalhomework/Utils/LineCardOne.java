package finalhomework.tcl.com.finalhomework.Utils;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.db.chart.animation.Animation;
import com.db.chart.model.LineSet;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.tooltip.Tooltip;
import com.db.chart.util.Tools;
import com.db.chart.view.LineChartView;

import java.util.Date;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.ui.widget.CardController;



import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.getEndDayOfLastWeek;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.getEndDayOfWeek;


public class LineCardOne extends CardController {


    private final LineChartView mChart;


    private final Context mContext;
    public  int weekValue = 1;
    private  String[] mLabels =new String[7];

    private final float[][] mValues = {{3.5f, 4.7f, 4.3f, 8f, 6.5f, 9.9f, 7f, 8.3f, 7.0f},
            {4.5f, 2.5f, 2.5f, 9f, 4.5f, 9.5f, 5f, 8.3f, 1.8f}};

    private Tooltip mTip;
    private Date date;
    private Runnable mBaseAction;
    private boolean thisweek;
    private float[] floats;

    public LineCardOne(CardView card, Context context, float[] floats,boolean week) {

        super(card);
        this.floats=floats;
        this.thisweek=week;
        mContext = context;
        mChart = (LineChartView) card.findViewById(R.id.chart);
    }


    @Override
    public void show(Runnable action) {

        super.show(action);

        // Tooltip
        mTip = new Tooltip(mContext, R.layout.linechart_three_tooltip, R.id.value);

        ((TextView) mTip.findViewById(R.id.value)).setTypeface(
                Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Semibold.ttf"));

        mTip.setVerticalAlignment(Tooltip.Alignment.BOTTOM_TOP);
        mTip.setDimensions((int) Tools.fromDpToPx(58), (int) Tools.fromDpToPx(25));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

            mTip.setEnterAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 1),
                    PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f),
                    PropertyValuesHolder.ofFloat(View.SCALE_X, 1f)).setDuration(200);

            mTip.setExitAnimation(PropertyValuesHolder.ofFloat(View.ALPHA, 0),
                    PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f),
                    PropertyValuesHolder.ofFloat(View.SCALE_X, 0f)).setDuration(200);

            mTip.setPivotX(Tools.fromDpToPx(65) / 2);
            mTip.setPivotY(Tools.fromDpToPx(25));
        }

        // 本周
            if (thisweek) {
                date = getEndDayOfWeek();
            } else {
                date = getEndDayOfLastWeek();
            }
            int j = 0;
            for (int i = 0; i < 7; i++) {
                j = 6 - i;
                mLabels[i] = DateUtils.getDay(date.toString(), -j);
            }

        // Data
        LineSet dataset = new LineSet(mLabels, floats);
        dataset.setColor(Color.parseColor("#758cbb"))
                .setFill(Color.parseColor("#2d374c"))
                .setDotsColor(Color.parseColor("#758cbb"))
                .setThickness(4)
                .setDashed(new float[]{10f, 10f})
                .beginAt(5);
        mChart.addData(dataset);

        dataset = new LineSet(mLabels, floats);
        dataset.setColor(Color.parseColor("#b3b5bb"))
                .setFill(Color.parseColor("#2d374c"))
                .setDotsColor(Color.parseColor("#ffc755"))
                .setThickness(4)
                .endAt(6);
        mChart.addData(dataset);

        mBaseAction = action;
        Runnable chartAction = new Runnable() {
            @Override
            public void run() {

                mBaseAction.run();
                mTip.prepare(mChart.getEntriesArea(0).get(3),floats[3]);
                mChart.showTooltip(mTip, true);
            }
        };

        mChart/*.setAxisBorderValues(0, 20)*/
                .setYLabels(AxisRenderer.LabelPosition.NONE)
                .setYAxis(false)
                .setXAxis(false)
                .setTooltips(mTip)
                .show(new Animation().setInterpolator(new BounceInterpolator())
                        .fromAlpha(0)
                        .withEndAction(chartAction));
    }


    @Override
    public void update() {

        super.update();

        mChart.dismissAllTooltips();
        if (firstStage) {
            mChart.updateValues(0, floats);
            mChart.updateValues(1, floats);
        } else {
            mChart.updateValues(0, floats);
            mChart.updateValues(1, floats);
        }
        mChart.getChartAnimation().withEndAction(mBaseAction);
        mChart.notifyDataUpdate();
    }


    @Override
    public void dismiss(Runnable action) {

        super.dismiss(action);

        mChart.dismissAllTooltips();
        mChart.dismiss(new Animation().setInterpolator(new BounceInterpolator()).withEndAction(action));
    }

}