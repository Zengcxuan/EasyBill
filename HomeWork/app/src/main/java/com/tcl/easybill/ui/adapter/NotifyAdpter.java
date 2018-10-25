package com.tcl.easybill.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import com.tcl.easybill.R;


public class NotifyAdpter extends RecyclerView.Adapter<NotifyAdpter.MyViewHolder>{
    private Context context;
    private List<String> mdatas;
    private LayoutInflater mInflater;
    private OnNotifyClickListener onNotifyClickListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.notify_item, parent, false);
        return new MyViewHolder(view);
    }

    public NotifyAdpter(Context context, List<String> datas) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mdatas = datas;
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyAdpter.MyViewHolder holder, int position) {
//        holder.tv.setText(mdatas.get(position));
//        int Hour = mdatas.get(position).get(Calendar.HOUR);
//        int Minu = mdatas.get(position).get(Calendar.MINUTE);
//        String show = String.valueOf(Hour) + ":" + String.valueOf(Minu);
//        holder.tv.setText(show);
//        Date date = DateUtils.longToDate(mdatas.get(position));
//        int Hour = date.getHours();
//        int Minu = date.getMinutes();
//        String show = String.valueOf(Hour) + ":" + String.valueOf(Minu)
        Log.i("----",mdatas.get(position));
        holder.tv.setText(mdatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public void addData(int position, String time) {
        //      在list中添加数据，并通知条目加入一条
        mdatas.add(position, time);
        //添加动画
        notifyItemInserted(position);

    }

    //  删除数据
    public void removeData(int position) {
        mdatas.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv;
        public MyViewHolder(View view){
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_time);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            onNotifyClickListener.OnClick(getAdapterPosition());

        }
    }

    public void setOnNotifyClickListener(OnNotifyClickListener listener) {
        if (onNotifyClickListener == null)
            this.onNotifyClickListener = listener;
    }

    /**
     * 自定义分类选择接口
     */
    public interface OnNotifyClickListener {
        void OnClick(int index);
    }
}
