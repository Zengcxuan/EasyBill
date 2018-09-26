package finalhomework.tcl.com.finalhomework.UI.activity;


import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.meng_MyUtils;
import finalhomework.tcl.com.finalhomework.UI.fragment.bill_Fragment;
import finalhomework.tcl.com.finalhomework.UI.fragment.chart_Fragment;
import finalhomework.tcl.com.finalhomework.UI.fragment.mine_Fragment;

/**
 *     Create by xiangtian.meng@tcl on 2018-8-28
 *
 */

public class meng_all_MainActivity extends AppCompatActivity implements View.OnClickListener {

    private mine_Fragment f3;
    private bill_Fragment f1;
    private chart_Fragment f2;
    private Button foot1,foot2,foot3;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView lvLeftMenu;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private ArrayAdapter arrayAdapter;

    meng_MyUtils meng_util = new meng_MyUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.parent);

        foot1 = (Button)findViewById(R.id.zhangdan);
        foot2 = (Button)findViewById(R.id.tubiao);
        foot3 = (Button)findViewById(R.id.me);

        foot1.setOnClickListener(this);
        foot2.setOnClickListener(this);
        foot3.setOnClickListener(this);

        initFragment1();//show first fragment
        myToolbar();// show toolbar
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
    /*set toolbar and show */
    public void myToolbar(){
        /**
         * set  toolbar  and show
         * */
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu = (NavigationView) findViewById(R.id.navigation_view);
        toolbar.setTitle("Toolbar");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //mAnimationDrawable.stop();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // mAnimationDrawable.start();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
    }

    /*the method of change the main fragment of the app*/
    private void initFragment1(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (f1 == null){
            f1 = new bill_Fragment();
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
