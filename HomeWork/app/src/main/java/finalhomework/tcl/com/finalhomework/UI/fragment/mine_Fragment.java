package finalhomework.tcl.com.finalhomework.UI.fragment;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finalhomework.tcl.com.finalhomework.R;


public class mine_Fragment extends Fragment implements View.OnClickListener{
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //引用创建好的xml布局
        View view = inflater.inflate(R.layout.fragment_persionalmsg,container,false);
        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GridView grd = (GridView) getActivity().findViewById(R.id.button_list);
        initData(typeIcon, buttonContent, grd);

        ImageView imageView = (ImageView)getActivity().findViewById(R.id.out_background);
        ClipDrawable drawable = (ClipDrawable)imageView.getBackground();
        drawable.setLevel(8000);

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

}
