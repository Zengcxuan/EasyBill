package finalhomework.tcl.com.finalhomework.UI.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import finalhomework.tcl.com.finalhomework.R;


/**
 * Created by Canxuan.zeng.
 * 带Toolbar的Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    protected String TAG;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        initEventAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
        beforeDestroy();
    }

    public void myToolbar(){
        /**
         * set  toolbar  and show
         * */
        toolbar = getToolbar();
//        toolbar.getMenu().clear();
        navigationView = getViewNavigation();
        mDrawerLayout = getDrawerLayout();

        toolbar.setTitle("");//设置Toolbar标题
//        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        navigationView.setItemIconTintList(null);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.inflateMenu(getItemMenu());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //创建返回键，并实现打开关/闭监听
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                toolbar, R.string.open, R.string.close) {

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
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.menu);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
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


    protected void setToolbar(View v){
        getToolbar().addView(v);
    }
    protected abstract DrawerLayout getDrawerLayout();
    protected abstract Toolbar getToolbar();
    protected abstract NavigationView getViewNavigation();
    protected abstract int getLayoutId();
    protected abstract void initEventAndData();
    protected abstract void beforeDestroy();
    protected abstract int getItemMenu();
    protected abstract void setItemReact();

}
