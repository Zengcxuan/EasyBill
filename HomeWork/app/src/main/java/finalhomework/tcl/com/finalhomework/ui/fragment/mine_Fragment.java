package finalhomework.tcl.com.finalhomework.ui.fragment;

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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finalhomework.tcl.com.finalhomework.R;

import static android.view.Gravity.CENTER;


public class mine_Fragment extends HomeBaseFragment implements View.OnClickListener{
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
        //图标
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
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

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
