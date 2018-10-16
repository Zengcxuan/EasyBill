package finalhomework.tcl.com.finalhomework.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.pojo.MonthBillForChart;

public class mine_fragmentAdapter extends RecyclerView.Adapter<mine_fragmentAdapter.ViewHolder> {
    private List dataList;
    private LayoutInflater mInflater;
    private Context mContext;

   

    public mine_fragmentAdapter(Context context, List dataList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }

    @Override
    public mine_fragmentAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.button_with_image, parent, false);
        return new mine_fragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mine_fragmentAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView image_left;
        private Button button_show;
        private ImageView image_right;
        private LinearLayout mine_fragment;


        public ViewHolder(View view){
            super(view);

            image_left = (ImageView)view.findViewById(R.id.image_left) ;
            button_show = (Button)view.findViewById(R.id.button_show);
            image_right = (ImageView)view.findViewById(R.id.image_right) ;
            mine_fragment = (LinearLayout)view.findViewById(R.id.mine_fragment);

        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
            }
        }
    }
}
