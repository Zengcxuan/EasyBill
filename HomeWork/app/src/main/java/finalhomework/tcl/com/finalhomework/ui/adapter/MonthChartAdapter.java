package finalhomework.tcl.com.finalhomework.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.ImageUtils;
import finalhomework.tcl.com.finalhomework.base.MyApplication;
import finalhomework.tcl.com.finalhomework.pojo.MonthBillForChart;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;

/**
 * ChartFragment
 */
public class MonthChartAdapter extends RecyclerView.Adapter<MonthChartAdapter.ViewHolder>{

   /* private Context mContext;*/
    private List<MonthBillForChart.SortTypeList> dataList;
    private LayoutInflater mInflater;
    private float come;
   /* private String mDatas;*/
    //测试

 /*   private String sortName;
    private Drawable sortImage;*/

/*    public void setSortImage(Drawable sortImage) {
        this.sortImage = sortImage;
    }
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }*/

/*    public void setmDatas(String mDatas) {
        this.mDatas = mDatas;
    }*/

    public MonthChartAdapter(Context context, List<MonthBillForChart.SortTypeList>  dataList){
        /*this.mContext = context;*/
        this.mInflater = LayoutInflater.from(context);
        this. dataList = dataList;
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.chartadapter, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //test
       /* holder.title.setText("123");
        holder.money.setText("666");
        holder.image.setImageDrawable(MyApplication.application.getDrawable(R.mipmap.sort_fruit));*/

        holder.title.setText(dataList.get(position).getSortName());
        holder.image.setImageDrawable(ImageUtils.getDrawable(dataList.get(position).getSortImg()));
        come =dataList.get(position).getMoney();
        if(dataList.get(position).getList().get(0).isIncome())
            holder.money.setText("+"+String.valueOf(come));
        else
            holder.money.setText("-"+String.valueOf(come));


        //else
            /*holder.money.setText("-" + mDatas.get(position).getCost());*/
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView image;
        private TextView title;
        private TextView money;

        public ViewHolder(View view){
            super(view);

            image = (ImageView)view.findViewById(R.id.circle_img) ;
            title = (TextView) view.findViewById(R.id.title);
            money = (TextView) view.findViewById(R.id.money);

        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
            }
        }
    }

}
