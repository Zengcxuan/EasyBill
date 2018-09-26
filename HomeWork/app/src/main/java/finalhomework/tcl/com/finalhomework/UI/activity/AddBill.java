package finalhomework.tcl.com.finalhomework.UI.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.UI.widget.Keyboard;

public class AddBill extends AppCompatActivity implements View.OnClickListener {
    private Button exitBtn;
    private Keyboard dialog;
    private String[] dataSource =new String[]{"支出","收入"};
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
    private String name1[]={
            "加号1","加号2","加号3","加号4"};

    private String name2[]={
            "加号1","加号2","加号3","加号4",
            "加号1","加号2","加号3","加号4",
            "加号1","加号2","加号3","加号4",
            "加号1","加号2","加号3","加号4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_out);
        GridView grid1 = (GridView) findViewById(R.id.grid01);
        GridView grid2 = (GridView) findViewById(R.id.grid02);
        Spinner changeType = (Spinner) findViewById(R.id.changeType);

        exitBtn = (Button) findViewById(R.id.bill_out_exit);
        initData(typeIcon1, name1, grid1);
        initData(typeIcon2, name2, grid2);


        exitBtn.setOnClickListener(this);
        changeType.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, dataSource));

    }

    @Override
    public void onClick(View v) {
      if(v == exitBtn){
          finish();
      }
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
        int[] to={R.id.img,R.id.text};
        adapter=new SimpleAdapter(this, dataList, R.layout.cell, from, to);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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



}
