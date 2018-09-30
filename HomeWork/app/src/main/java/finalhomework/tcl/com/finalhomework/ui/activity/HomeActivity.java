package finalhomework.tcl.com.finalhomework.ui.activity;



import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.SharedPUtils;
import finalhomework.tcl.com.finalhomework.base.Constants;
import finalhomework.tcl.com.finalhomework.base.LocalRepository;
import finalhomework.tcl.com.finalhomework.pojo.AllSortBill;
import finalhomework.tcl.com.finalhomework.pojo.SortBill;
import finalhomework.tcl.com.finalhomework.ui.adapter.ViewPagerAdapter;
import finalhomework.tcl.com.finalhomework.ui.fragment.bill_Fragment;
import finalhomework.tcl.com.finalhomework.ui.fragment.chart_Fragment;
import finalhomework.tcl.com.finalhomework.ui.fragment.mine_Fragment;



public class HomeActivity extends BaseActivity  {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initEventAndData() {

        //设置全屏
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        if(SharedPUtils.isFirstStart(mContext)){
            Log.i(TAG,"第一次进入将默认账单分类添加到数据库");
            AllSortBill note= new Gson().fromJson(Constants.BILL_NOTE, AllSortBill.class);
            List<SortBill> sorts=note.getOutSortList();
            sorts.addAll(note.getInSortList());
            LocalRepository.getInstance().saveBsorts(sorts);
            LocalRepository.getInstance().saveBPays(note.getPayinfo());
        }


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_bill:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_chart:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_mine:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });
/*        menuItem = bottomNavigationView.getMenu().getItem(0);
        menuItem.setChecked(true);*/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
          /*      if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(true);
                }*/
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
                //changeBottomState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

        setupViewPager(viewPager);
    }



    //切换界面
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

       /* adapter.addFragment(bill_Fragment.newInstance("账单"));
        adapter.addFragment(chart_Fragment.newInstance("图表"));
        adapter.addFragment(mine_Fragment.newInstance("我的"));*/
       adapter.addFragment(new bill_Fragment());
       adapter.addFragment(new chart_Fragment());
       adapter.addFragment(new mine_Fragment());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }

    /*public void changeBottomState(int position) {
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
    }*/

}
