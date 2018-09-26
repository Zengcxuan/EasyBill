package finalhomework.tcl.com.finalhomework.UI.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.UI.adapter.ViewPagerAdapter;
import finalhomework.tcl.com.finalhomework.UI.fragment.bill_Fragment;
import finalhomework.tcl.com.finalhomework.UI.fragment.chart_Fragment;
import finalhomework.tcl.com.finalhomework.UI.fragment.mine_Fragment;


/**
 * Created by bruce on 2016/11/1.
 * HomeActivity 主界面
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ImageButton searchBtn;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        toolbar = (Toolbar) findViewById(R.id.tl_custom);
//        searchBtn = (ImageButton) findViewById(R.id.search_parent);
//        searchBtn.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_bill:
                                viewPager.setCurrentItem(0);
//                                toolbar.setBackground(getDrawable(R.drawable.shape_color_blue));
                                break;
                            case R.id.item_chart:
                                viewPager.setCurrentItem(1);
//                                toolbar.setBackground(getDrawable(R.drawable.shape_color_blue));
                                break;
                            case R.id.item_mine:
                                viewPager.setCurrentItem(2);
//                                toolbar.setBackground(getDrawable(R.drawable.up_background));
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        setupViewPager(viewPager);
//        myToolbar();
    }

    @Override
    public void onClick(View v) {
        if (v == searchBtn) {
            searchAll();
        }

    }

    public void myToolbar(){
        /**
         * set  toolbar  and show
         * */
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        toolbar.setTitle("");//设置Toolbar标题
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
//        mDrawerToggle.setHomeAsUpIndicator(R.drawable.menu);
//        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
    }

    //切换界面
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(bill_Fragment.newInstance("账单"));
        adapter.addFragment(chart_Fragment.newInstance("图表"));
        adapter.addFragment(mine_Fragment.newInstance("我的"));
        viewPager.setAdapter(adapter);
    }

    //全局查找
    private void searchAll(){
//        // TODO: 18-9-26 查找
//        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
//        builder.setTitle("请输入用户名和密码");
//        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
//        View view = LayoutInflater.from(HomeActivity.this).inflate(R.layout.mydialog, null);
//        //    设置我们自己定义的布局文件作为弹出框的Content
//        builder.setView(view);
//
//        final EditText username = (EditText)view.findViewById(R.id.username);
//        final EditText password = (EditText)view.findViewById(R.id.password);
//
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                String a = username.getText().toString().trim();
//                String b = password.getText().toString().trim();
//                //    将输入的用户名和密码打印出来
//                Toast.makeText(HomeActivity.this, "用户名: " + a + ", 密码: " + b, Toast.LENGTH_SHORT).show();
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//
//            }
//        });
//        builder.show();

    }
}
