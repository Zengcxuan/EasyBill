package finalhomework.tcl.com.finalhomework.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.DateUtils;
import finalhomework.tcl.com.finalhomework.Utils.ImageUtils;
import finalhomework.tcl.com.finalhomework.base.MyApplication;
import finalhomework.tcl.com.finalhomework.pojo.MonthBillForChart;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;

import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_HMS_CN;
import static finalhomework.tcl.com.finalhomework.Utils.DateUtils.FORMAT_YMD_CN;

/**
 * ChartFragment
 */
public class MonthChartAdapter extends RecyclerView.Adapter<MonthChartAdapter.ViewHolder>{

   /* private Context mContext;*/
    private List<MonthBillForChart.SortTypeList> dataList;
    private LayoutInflater mInflater;
    private String come;
    private Context mContext;
    private Drawable drawable;
    private String name;
    private boolean isIncome;
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
        this.mContext = context;
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

       drawable = ImageUtils.getDrawable(dataList.get(position).getSortImg());
       name = dataList.get(position).getSortName();
       come =String.valueOf(dataList.get(position).getMoney());
       isIncome = dataList.get(position).getList().get(0).isIncome();
        /*holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(mContext).setTitle("备注")
                        .setPositiveButton("明白", null)
                        .show();
                final Window window = alertDialog.getWindow();
                window.setContentView(R.layout.dialog_chart);
                TextView tv_title = (TextView) window.findViewById(R.id.dialog_bill_tv_title);
                TextView tv_content = (TextView) window.findViewById(R.id.dialog_bill_tv_content);
                ImageView iv_bill = (ImageView) window.findViewById(R.id.dialog_bill_iv);
                TextView tv_btn = (TextView) window.findViewById(R.id.dialog_bill_btn);

                iv_bill.setImageDrawable(drawable);
                if (isIncome)
                    tv_title.setText("本月因" +name+ "收入" +come+ "元");
                else
                    tv_title.setText("本月因" + name+ "消费了" + come+ "元");
                tv_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

            }
        });*/
        holder.title.setText(name);
        holder.image.setImageDrawable(drawable);
        if(isIncome)
            holder.money.setText("+"+come);
        else
            holder.money.setText("-"+come);


        //else
            /*holder.money.setText("-" + mDatas.get(position).getCost());*/
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView image;
        private TextView title;
        private TextView money;
        private RelativeLayout item_layout;

        public ViewHolder(View view){
            super(view);

            image = (ImageView)view.findViewById(R.id.circle_img) ;
            title = (TextView) view.findViewById(R.id.title);
            money = (TextView) view.findViewById(R.id.money);
            item_layout = (RelativeLayout)view.findViewById(R.id.item_layout);

        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
            }
        }
    }

}
