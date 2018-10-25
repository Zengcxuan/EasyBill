package com.tcl.easybill.ui.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.tcl.easybill.R;
import com.tcl.easybill.Utils.LockViewUtil;

public class WelcomeActivity extends BaseActivity {
    @BindView(R.id.welcome_viewpager)
    ViewPager viewPager;
    private List<View> tabViews;
    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
        tabViews = new ArrayList<>();
        initData();
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return tabViews.size();//
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(tabViews.get(position));
                //滑到最后一个页面，显示button
                if(position == tabViews.size()-1)
                {
                    Button button= (Button) tabViews.get(position).findViewById(R.id.button);
                    AnimationSet aset=new AnimationSet(true);
                    AlphaAnimation aa=new AlphaAnimation(0,1);
                    aa.setDuration(2000);
                    aset.addAnimation(aa);
                    button.setVisibility(View.VISIBLE);
                    button.startAnimation(aset);
//                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LockViewUtil.setIsfirst(WelcomeActivity.this, false);
                            Intent intent  = new Intent(WelcomeActivity.this,SplashActivity.class);
                            startActivityForResult(intent,0);
                            finish();
                        }
                    });
                }
                return tabViews.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(tabViews.get(position));
            }
        });
    }

    private void initData() {
        //引入一个布局，三个共用，设置不同的背景图片
        LayoutInflater tabs = LayoutInflater.from(WelcomeActivity.this);
        View tab1 = tabs.inflate(R.layout.tab_01, null);
        tab1.setBackgroundResource(R.mipmap.persional);

        View tab2 = tabs.inflate(R.layout.tab_01, null);
        tab2.setBackgroundResource(R.mipmap.shared);
        //添加到列表里
        tabViews.add(tab1);
        tabViews.add(tab2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LockViewUtil.setIsfirst(this, false);
    }
}
