package finalhomework.tcl.com.finalhomework.UI.activity;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import butterknife.ButterKnife;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);  }

        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_bill:
                                viewPager.setCurrentItem(0, true);
                                break;
                            case R.id.item_chart:
                                viewPager.setCurrentItem(1,true);
                                break;
                            case R.id.item_mine:
                                viewPager.setCurrentItem(2,true);
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
                changeBottomState(position);
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
    }

    @Override
    public void onClick(View v) {

    }


    //切换界面
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(bill_Fragment.newInstance("账单"));
        adapter.addFragment(chart_Fragment.newInstance("图表"));
        adapter.addFragment(mine_Fragment.newInstance("我的"));
        viewPager.setAdapter(adapter);
    }

    public void changeBottomState(int position) {
        menuItem = bottomNavigationView.getMenu().getItem(position);
        switch (position){
            case 0:
                menuItem.setIcon(R.mipmap.bill_parent_color);
                bottomNavigationView.getMenu().getItem(1).setIcon(R.mipmap.chart_parent);
                bottomNavigationView.getMenu().getItem(2).setIcon(R.mipmap.mine_parent);
                break;
            case 1:
                menuItem.setIcon(R.mipmap.chart_parent_color);
                bottomNavigationView.getMenu().getItem(0).setIcon(R.mipmap.bill_parent);
                bottomNavigationView.getMenu().getItem(2).setIcon(R.mipmap.mine_parent);
                break;
            case 2:
                menuItem.setIcon(R.mipmap.mine_parent_color);
                bottomNavigationView.getMenu().getItem(0).setIcon(R.mipmap.bill_parent);
                bottomNavigationView.getMenu().getItem(1).setIcon(R.mipmap.chart_parent);
                break;
        }
    }

}
