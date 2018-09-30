package finalhomework.tcl.com.finalhomework.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.ui.widget.Keyboard;
import finalhomework.tcl.com.finalhomework.Utils.DateUtils;
import finalhomework.tcl.com.finalhomework.mvp.presenter.BillPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.BillPresenterImpl;
import finalhomework.tcl.com.finalhomework.mvp.views.BillView;
import finalhomework.tcl.com.finalhomework.pojo.TotalBill;
import finalhomework.tcl.com.finalhomework.pojo.base;

public class AddBill {

/*    @BindView(R.id.tb_note_income)
    TextView incomeTv;    //收入按钮
    @BindView(R.id.tb_note_outcome)
    TextView outcomeTv;   //支出按钮
    @BindView(R.id.item_tb_type_tv)
    TextView sortTv;     //显示选择的分类
    @BindView(R.id.tb_note_money)
    TextView moneyTv;     //金额
    @BindView(R.id.tb_note_date)
    TextView dateTv;      //时间选择
    @BindView(R.id.tb_note_cash)
    TextView cashTv;      //支出方式选择
    @BindView(R.id.tb_note_remark)
    ImageView remarkIv;   //
    @BindView(R.id.viewpager_item)
    ViewPager viewpagerItem;
    @BindView(R.id.layout_icon)
    LinearLayout layoutIcon;

    protected BillPresenter presenter;

    protected Boolean isOutcome;
    *//*计算器输入部分*//*
    protected Boolean isDecimal;
    protected String num = "0";
    protected String decimal = ".00";
    protected final int MAX_NUM = 9999999;
    protected final int DECIMAL_NUM = 2;
    protected int count = 0 ;
    *//**//*



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void loadDataSuccess(base tData) {

    }

    @Override
    public void loadDataError(Throwable throwable) {

    }*/
}
/*
    BillPresenter presenter;

    private Button exitBtn;
    private Button addtest;
    private Keyboard dialog;
    private String[] dataSource = new String[]{"支出", "收入"};
    private int[] typeIcon1 = new int[]{
            R.drawable.add, R.drawable.add, R.drawable.add, R.drawable.add
    };

    private int[] typeIcon2 = new int[]{
            R.drawable.add, R.drawable.add, R.drawable.add, R.drawable.add,
            R.drawable.add, R.drawable.add, R.drawable.add, R.drawable.add,
            R.drawable.add, R.drawable.add, R.drawable.add, R.drawable.add,
            R.drawable.add, R.drawable.add, R.drawable.add, R.drawable.add
    };
    //图标下的文字
    private String name1[] = {
            "加号1", "加号2", "加号3", "加号4"};

    private String name2[] = {
            "加号1", "加号2", "加号3", "加号4",
            "加号1", "加号2", "加号3", "加号4",
            "加号1", "加号2", "加号3", "加号4",
            "加号1", "加号2", "加号3", "加号4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_out);
<<<<<<< Updated upstream
        GridView grid1 = (GridView) findViewById(R.id.grid01);
        GridView grid2 = (GridView) findViewById(R.id.grid02);
        Spinner changeType = (Spinner) findViewById(R.id.changeType);
=======
        GridView grid1 = findViewById(R.id.grid01);
        GridView grid2 = findViewById(R.id.grid02);
        Spinner changeType = findViewById(R.id.changeType);
        addtest = (Button)findViewById(R.id.addtest);
        addtest.setOnClickListener(this);

        presenter=new BillPresenterImpl(this);
>>>>>>> Stashed changes

        exitBtn = (Button) findViewById(R.id.bill_out_exit);
        initData(typeIcon1, name1, grid1);
        initData(typeIcon2, name2, grid2);


//        exitBtn.setOnClickListener(this);
//        changeType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSource));

    }

    public void addTest() {

        TotalBill totalBill = new TotalBill(
                null,
                null,
                Float.valueOf("0.00"),
                "123",
                "666",
                "123",
                "123",
                "picturename",
                "picture",
                DateUtils.getMillis("2018-09-27 15:21:17"),
                true,
                0);
        presenter.add(totalBill);
        Log.e("meng111","已添加");
    }



    @Override
    public void onClick(View v) {
      if(v == exitBtn){
          finish();
      }else if (v == addtest){
          addTest();
      }
    }

    void initData(int[] typeIcon, String[] name, GridView grid) {
        //图标
        final List<Map<String, Object>> dataList;
        SimpleAdapter adapter;
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < typeIcon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", typeIcon[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
        String[] from = {"img", "text"};
        int[] to = {R.id.img, R.id.text};
        adapter = new SimpleAdapter(this, dataList, R.layout.cell, from, to);
        grid.setAdapter(adapter);
    }

    @Override
    public void loadDataSuccess(base tData) {

    }

    @Override
    public void loadDataError(Throwable throwable) {

    }
*/
/*        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                dialog = new Keyboard(AddBill.this);
                dialog.show();
//                AlertDialog.Builder builder= new AlertDialog.Builder(AddBill.this);
//                builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
            }
        });
    }



}*//*

    }*/
