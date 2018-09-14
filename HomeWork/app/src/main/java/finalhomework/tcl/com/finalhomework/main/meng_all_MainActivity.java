package finalhomework.tcl.com.finalhomework.main;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.fragment.bill_fragment;
import finalhomework.tcl.com.finalhomework.fragment.chart_Fragment;
import finalhomework.tcl.com.finalhomework.fragment.mine_Fragment;


/**
 *     Create by xiangtian.meng@tcl on 2018-8-28
 *
 */

public class meng_all_MainActivity extends AppCompatActivity implements View.OnClickListener {

    private mine_Fragment f3;
    private bill_fragment f1;
    private chart_Fragment f2;
    private Button foot1,foot2,foot3;
    private EditText setTime;
    private RelativeLayout selectDate, selectTime;

    meng_MyUtils meng_util = new meng_MyUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.bill_bottom);


        foot1 = (Button)findViewById(R.id.zhangdan);
        foot2 = (Button)findViewById(R.id.tubiao);
        foot3 = (Button)findViewById(R.id.me);

        foot1.setOnClickListener(this);
        foot2.setOnClickListener(this);
        foot3.setOnClickListener(this);

        initFragment1();


    }
    @Override
    public void onClick(View v) {
        if (v == foot1) {
            initFragment1();
        } else if (v == foot2) {
            initFragment2();
        } else if (v == foot3) {
            initFragment3();
        }

    }




    /*the method of change the main fragment of the app*/
    private void initFragment1(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (f1 == null){
            f1 = new bill_fragment();
            transaction.add(R.id.main_frame_layout,f1);
        }
        hideFragment(transaction);
       // mine_Fragment mine_fragment = new mine_Fragment();
        transaction.show(f1);
        transaction.commit();
    }

    public void initFragment2(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f2 == null){
            f2 = new chart_Fragment();
            transaction.add(R.id.main_frame_layout,f2);
        }
        hideFragment(transaction);
        transaction.show(f2);
        transaction.commit();
    }


    private void initFragment3(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f3 == null){
            f3 = new mine_Fragment();
            transaction.add(R.id.main_frame_layout,f3);
        }
        hideFragment(transaction);
        transaction.show(f3);
        transaction.commit();
    }


    private void hideFragment(FragmentTransaction transaction){
        if(f1 != null){
            transaction.hide(f1);
        }
        if(f2 != null){
            transaction.hide(f2);
        }
        if(f3 != null){
            transaction.hide(f3);
        }
    }




}
