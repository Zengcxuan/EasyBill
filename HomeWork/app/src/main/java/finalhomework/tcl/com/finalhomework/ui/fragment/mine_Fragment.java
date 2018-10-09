package finalhomework.tcl.com.finalhomework.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import finalhomework.tcl.com.finalhomework.R;

import static android.view.Gravity.CENTER;


public class mine_Fragment extends HomeBaseFragment implements View.OnClickListener{
    @BindView(R.id.record_days)  //记录总天数
    TextView recordDays;
    @BindView(R.id.record_deals) //记录总笔数
    TextView recordDeals;
    @BindView(R.id.surplus)     //结余
    TextView recordSurplus;
    protected Activity mContext;
    private int[] typeIcon = new int[]{
            R.mipmap.voice, R.mipmap.notify, R.mipmap.yusuan, R.mipmap.cyper, R.mipmap.outport,
            R.mipmap.count, R.mipmap.help
    };
    private String[] buttonContent = new String[]{
       "声音开关", "定时提醒", "每月预算", "手势密码", "导出账单", "评分", "帮助"
    };

    public static mine_Fragment newInstance(String info) {
        Bundle args = new Bundle();
        mine_Fragment fragment = new mine_Fragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onClick(View v) {

    }


    void initData(int[] typeIcon, String[] name, GridView grid) {
        //初始化账单数据
        recordDays.setText("55"); //总天数
        recordDeals.setText("11"); //总笔数
        recordSurplus.setText("55"); //结余

        //初始化GridView，添加itemClickListener
        final List<Map<String, Object>> dataList;
        SimpleAdapter adapter;
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <typeIcon.length; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("img", typeIcon[i]);
            map.put("text",name[i]);
            dataList.add(map);
        }
        String[] from={"img","text"};
        int[] to={R.id.image_left,R.id.button_show};
        adapter=new SimpleAdapter(getActivity(), dataList, R.layout.button_with_image, from, to);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
                switch ((int) id) {
                    case 1:
                        // TODO: 18-10-8  声音开关
                        Toast.makeText(getActivity(),"声音开关", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        // TODO: 18-10-8 定时提醒
                        break;
                    case 3:
                        // TODO: 18-10-8 预算
                        break;
                    case 4:
                        // TODO: 18-10-8 手势密码
                        break;
                    case 5:
                        // TODO: 18-10-8 导出账单
                        break;
                    case 6:
                        // TODO: 18-10-8 评分
                        break;
                    case 7:
                        // TODO: 18-10-8 帮助
                        break;
                }

            }
        });
    }
    @Override
    public void myToolbar(){
    // TODO: 18-9-29 modify
        super.myToolbar();

    }

    @Override
    protected void improtantData() {

    }

    @Override
    protected DrawerLayout getDrawerLayout(){
        DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawerlayout_mine);
        return mDrawerLayout;
    }

    @Override
    protected  Toolbar getToolbar(){ return getActivity().findViewById(R.id.tl_mine); }

    @Override
    protected  NavigationView getViewNavigation(){ return getActivity().findViewById(R.id.navigationview_mine);}

    @Override
    protected  int getLayoutId(){ return R.layout.fragment_persionalmsg; }

    @Override
    protected  void loadData(){
        GridView grd = (GridView) getActivity().findViewById(R.id.button_list);
        initData(typeIcon, buttonContent, grd);
    }

    @Override
    protected  void beforeDestroy(){}

    @Override
    protected  int getItemMenu(){return R.menu.menu_main;}

    @Override
    protected  void setItemReact(){}

}
