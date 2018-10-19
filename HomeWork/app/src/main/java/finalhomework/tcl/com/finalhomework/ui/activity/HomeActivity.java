package finalhomework.tcl.com.finalhomework.ui.activity;




import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.List;

import butterknife.BindView;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.SharedPUtils;
import finalhomework.tcl.com.finalhomework.base.Constants;
import finalhomework.tcl.com.finalhomework.base.LocalRepository;
import finalhomework.tcl.com.finalhomework.pojo.AllSortBill;
import finalhomework.tcl.com.finalhomework.pojo.SortBill;
import finalhomework.tcl.com.finalhomework.ui.MyViewPager;
import finalhomework.tcl.com.finalhomework.ui.adapter.ViewPagerAdapter;
import finalhomework.tcl.com.finalhomework.ui.fragment.bill_Fragment;
import finalhomework.tcl.com.finalhomework.ui.fragment.chart_Fragment;
import finalhomework.tcl.com.finalhomework.ui.fragment.mine_Fragment;

import static android.view.Gravity.CENTER;


public class HomeActivity extends BaseActivity  {

   /* private MyViewPager viewPager;*/
    private MenuItem menuItem;
    /*private BottomNavigationView bottomNavigationView;*/
    @BindView(R.id.viewpager)
    MyViewPager viewPager ;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initEventAndData() {
        //FAB
        initFab();
        //第一次进入将默认账单分类添加到数据库
        if(SharedPUtils.isFirstStart(mContext)){
            Log.i(TAG,"第一次进入将默认账单分类添加到数据库");
            AllSortBill note= new Gson().fromJson(Constants.BILL_NOTE, AllSortBill.class);
            List<SortBill> sorts=note.getOutSortList();
            sorts.addAll(note.getInSortList());
            LocalRepository.getInstance().saveBsorts(sorts);
            LocalRepository.getInstance().saveBPays(note.getPayinfo());
        }
       /* viewPager = findViewById(R.id.viewpager);*/
       //viewPager = new MyViewPager(getApplicationContext());
        viewPager.setOffscreenPageLimit(5);
       /* bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);*/
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

    }

    /**
     * 添加悬浮按钮
     */
    private void initFab(){
//        ImageView image = new ImageView(mContext);
//        image.setImageDrawable(mContext.getDrawable(R.drawable.add_bill3));
        FloatingActionButton floatingActionButton
                = new FloatingActionButton.Builder(this).build();
        floatingActionButton.setBackground(mContext.getDrawable(R.drawable.add_bill3));
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView itemIcon = new ImageView(mContext);
//        itemIcon.setImageDrawable(mContext.getDrawable(R.drawable.add));
//        itemIcon.setBackground(mContext.getDrawable(R.drawable.add_bill3));
        SubActionButton addbill = itemBuilder.build();
        addbill.setBackground(mContext.getDrawable(R.drawable.add));
//        ImageView itemIcon2 = new ImageView(mContext);
//        itemIcon2.setImageDrawable(mContext.getDrawable(R.drawable.add_bill3));
//        SubActionButton refreshbill = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton refreshbill = itemBuilder.build();
        refreshbill.setBackground(mContext.getDrawable(R.drawable.add));

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(addbill)
                .addSubActionView(refreshbill)
                .attachTo(floatingActionButton)
                .build();

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(180, 180);
        params.setMargins(0, 0, 0, 100);
        floatingActionButton.setPosition(4, params);

        addbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启账单添加
                Intent intent = new Intent(HomeActivity.this, BillAddActivity.class);
                startActivity(intent);
            }
        });

        refreshbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 18-10-18 数据更新
            }
        });

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
