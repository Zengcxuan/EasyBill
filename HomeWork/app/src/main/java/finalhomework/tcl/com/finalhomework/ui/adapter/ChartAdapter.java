package finalhomework.tcl.com.finalhomework.ui.adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.LineCardOne;
import finalhomework.tcl.com.finalhomework.pojo.MonthDetailAccount;


public  class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {

    private final static int NUM_CHARTS = 1;
    private final Context mContext;
    private float[] floats;
    private boolean thisWeek;
    public ChartAdapter(Context context, float[] floats,boolean thisWeek) {
        mContext = context;
        this.thisWeek=thisWeek;
        this.floats=floats;
    }
    public void setmDatas(float[] floats) {
        this.floats = floats;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View v;
        switch (viewType) {
            case 0:
                v = LayoutInflater.from(mContext).inflate(R.layout.chart1, parent, false);
                break;

            default:
                v = LayoutInflater.from(mContext).inflate(R.layout.chart1, parent, false);
                break;
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (position) {
            case 0:
                (new LineCardOne(holder.cardView, mContext,floats,thisWeek)).init();
                break;
            default:
                (new LineCardOne(holder.cardView, mContext,floats,thisWeek)).init();
                break;
        }
    }

    @Override
    public int getItemCount() {

        return NUM_CHARTS;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final CardView cardView;

        ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.chart_card);
        }

         @Override
         public void onClick(View v) {

         }
     }
}