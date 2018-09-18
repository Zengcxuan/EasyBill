package finalhomework.tcl.com.finalhomework.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.liuwan.customdatepicker.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import finalhomework.tcl.com.finalhomework.R;

public class AddBill extends AppCompatActivity implements View.OnClickListener {
    private GridView grid1, grid2;
    private Button exitBtn;
    private String[] datasource=new String[]{"支出","收入"};
    private Spinner changeType;
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
        exitBtn = findViewById(R.id.bill_out_exit);
        grid1 = findViewById(R.id.grid01);
        grid2 = findViewById(R.id.grid02);
        initData(typeIcon1, name1, grid1);
        initData(typeIcon2, name2, grid2);

        exitBtn.setOnClickListener(this);
        changeType = findViewById(R.id.changeType);
        changeType.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datasource));

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
                AlertDialog.Builder builder= new AlertDialog.Builder(AddBill.this);
                builder.setTitle("提示").setMessage(dataList.get(arg2).get("text").toString()).create().show();
            }
        });
    }


}
