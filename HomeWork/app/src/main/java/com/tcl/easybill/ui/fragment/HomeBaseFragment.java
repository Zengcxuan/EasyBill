package com.tcl.easybill.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tcl.easybill.R;
import com.tcl.easybill.Utils.LockViewUtil;
import com.tcl.easybill.pojo.Person;
import com.tcl.easybill.ui.activity.AccountActivity;
import com.tcl.easybill.ui.activity.PersionalInfoActivity;
import com.tcl.easybill.ui.widget.ImageButtonWithText;


/**
 * Created by Canxuan.zeng.
 * 带Toolbar的Fragment基类
 */

public abstract class HomeBaseFragment extends Fragment {
    protected String TAG;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected Toolbar toolbar;
    protected Person currentUser;
    LinearLayout navigationView;
    DrawerLayout mDrawerLayout;
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;
    protected boolean isCreated = false;

    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        // 设置 TAG
        TAG = this.getClass().getSimpleName();
        //当前用户
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = true;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        mUnBinder = ButterKnife.bind(this, mView);
        return mView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        currentUser= Person.getCurrentUser(Person.class);
        setHasOptionsMenu(true);
        importantData();
        myToolbar();
        lazyLoad();
    }

//    @OnClick ({R.id.head})
//    protected void onClick(View v){
//        Toast.makeText(getActivity(), "你点击了我", Toast.LENGTH_LONG).show();
//
//    }

    public void myToolbar(){
        /**
         * 这里获取屏幕的宽和高并赋予给侧滑栏，使其全屏显示
         * */
        /*DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);*/
        DisplayMetrics metric=getActivity().getResources().getDisplayMetrics();
        /*int windowsWight = metric.widthPixels;
        int windowsHeight = metric.heightPixels;*/
//        View leftMenu = getLeftWindow();
//        ViewGroup.LayoutParams leftParams = leftMenu.getLayoutParams();
//        leftParams.height =metric.heightPixels;
//        leftParams.width = metric.widthPixels;
//        leftMenu.setLayoutParams(leftParams);
        /* set  toolbar  and show */
        toolbar = getToolbar();
//        toolbar.getMenu().clear();
//        navigationView = getLeftWindow();
//        mDrawerLayout = getDrawerLayout();
//        navigationView.setFitsSystemWindows(true);
//        navigationView.findViewById(R.id.imageView_my)
//                .setBackground(mContext.getDrawable(R.drawable.persional));
//        navigationView.findViewById(R.id.imageView_share)
//                .setBackground(mContext.getDrawable(R.drawable.shared));
        toolbar.setTitle("");//设置Toolbar标题
//        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
//        navigationView.setItemIconTintList(null);
        toolbar.inflateMenu(getItemMenu());
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        //创建返回键，并实现打开关/闭监听

//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止滑动
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
//                toolbar, R.string.open, R.string.close) {
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                //mAnimationDrawable.stop();
//                drawerView.setClickable(true);
//                mDrawerLayout.bringChildToFront(getLeftWindow());
//                ImageButton backBtn = getBackBtn();
//                backBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mDrawerLayout.closeDrawers();
//                    }
//                });
//
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                // mAnimationDrawable.start();
//            }
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView, slideOffset);
//            }
//
//
//
//        };
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDrawerLayout.openDrawer(Gravity.LEFT);
                Intent accountIntent = new Intent(mContext, AccountActivity.class);
                startActivity(accountIntent);
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        Log.d(TAG,"onCreateOptionsMenu");
        inflater.inflate(getItemMenu(), menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.d(TAG,"onOptionsItemSelected");

        switch (item.getItemId())
        {
            case R.id.action_edit:
                setItemReact();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,
        // 并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
//        if (isViewCreated && isUIVisible) {
//            loadData();
//            //数据加载完毕,恢复标记,防止重复加载
//            isViewCreated = false;
//            isUIVisible = false;
//            ImageButton backBtn = getBackBtn();
//            backBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mDrawerLayout.closeDrawers();
//                }
//            });
//
//            ImageButtonWithText headView = getHead();
//            headView.setText("我的");
//            headView.setImageView(R.mipmap.sort_kid);
//
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
        beforeDestroy();
    }


    protected abstract void importantData();
    protected abstract void loadData();
    protected void setToolbar(View v){
        getToolbar().addView(v);
    }
//    protected abstract DrawerLayout getDrawerLayout();
    protected abstract Toolbar getToolbar();
//    protected abstract LinearLayout getLeftWindow();
    protected abstract int getLayoutId();
    protected abstract void beforeDestroy();
    protected abstract int getItemMenu();
    protected abstract void setItemReact();
//    protected abstract ImageButton getBackBtn();
//    protected abstract ImageButtonWithText getHead();

}
