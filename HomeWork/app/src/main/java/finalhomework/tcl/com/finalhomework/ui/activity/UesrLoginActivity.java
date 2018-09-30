package finalhomework.tcl.com.finalhomework.ui.activity;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.ui.fragment.login.LoginFragment;
import finalhomework.tcl.com.finalhomework.ui.fragment.login.RegisterFragment;

public class UesrLoginActivity extends BaseActivity{
    @BindView(R.id.button_left)
    Button leftBtn;
    @BindView(R.id.button_right)
    Button rightBtn;


//    @Override
//    public void onClick(View v){
//        switch (v){
//            case leftBtn:
//                 break;
//            case rightBtn:
//        }
//    }

    /**
     * 监听点击事件
     *
     * @param view
     */
    @OnClick({R.id.button_left, R.id.button_right})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_left:
                leftBtn.setText("登陆");
                rightBtn.setText("注册");
                openLoginFragment();

                break;
            case R.id.button_right:
                leftBtn.setText("注册");
                rightBtn.setText("登陆");
                openLoginFragment();
                break;
        }
    }

    private void openLoginFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        LoginFragment fragment = new LoginFragment();
        transaction.add(R.id.fragment_change, fragment);
        transaction.commit();
    }

    private void openRigsterFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        RegisterFragment fragment = new RegisterFragment();
        transaction.add(R.id.fragment_change, fragment);
        transaction.commit();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
//        leftBtn = findViewById(R.id.button_left);
//        rightBtn = findViewById(R.id.button_right);
//        leftBtn.setOnClickListener(this);
//        rightBtn.setOnClickListener(this);
        openLoginFragment();

    }

}
