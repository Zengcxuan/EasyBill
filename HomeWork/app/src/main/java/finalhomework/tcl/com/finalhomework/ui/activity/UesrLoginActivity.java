package finalhomework.tcl.com.finalhomework.ui.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.LockViewUtil;
import finalhomework.tcl.com.finalhomework.Utils.ProgressUtils;
import finalhomework.tcl.com.finalhomework.Utils.SnackbarUtils;
import finalhomework.tcl.com.finalhomework.mvp.presenter.UserLoginPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.UserLoginPresenterImpl;
import finalhomework.tcl.com.finalhomework.mvp.views.UserLoginView;
import finalhomework.tcl.com.finalhomework.pojo.User;
import finalhomework.tcl.com.finalhomework.ui.fragment.login.LoginFragment;
import finalhomework.tcl.com.finalhomework.ui.fragment.login.RegisterFragment;
import finalhomework.tcl.com.finalhomework.ui.widget.LockView;

import static finalhomework.tcl.com.finalhomework.Utils.UiUtils.getContext;

public class UesrLoginActivity extends BaseActivity implements UserLoginView{
    @BindView(R.id.button_left)
    Button leftBtn;
    @BindView(R.id.button_right)
    Button rightBtn;
    @BindView(R.id.login_button)
    Button loginButton;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;
    private UserLoginPresenter userLoginPresenter;
    private boolean isLogin = true;
    private static boolean isLock = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        openLoginFragment();
        userLoginPresenter = new UserLoginPresenterImpl(this);

    }
    /**
     * 监听点击事件
     *
     * @param view
     */
    @OnClick({R.id.button_left, R.id.button_right,R.id.login_button})
    protected void onClick(View view) {
        switch (view.getId()) {
//            case R.id.button_left:
//                if (!isLogin) {
//                    openLoginFragment();
//                    leftBtn.setText("登陆");
//                    rightBtn.setText("注册");
//                    loginButton.setText("登陆");
//                    isLogin =!isLogin;
//                }
//                break;
            case R.id.button_right:
                if (isLogin) {
                    openRigsterFragment();
                    leftBtn.setText("注册");
                    rightBtn.setText("登陆");
                    loginButton.setText("注册");
                    isLogin =!isLogin;
                }else {
                    openLoginFragment();
                    leftBtn.setText("登陆");
                    rightBtn.setText("注册");
                    loginButton.setText("登陆");
                    isLogin =!isLogin;
                }
                break;
            case R.id.login_button:
                if(isLogin){
                    login();
                }else {
                    register();
                }
                break;
        }
    }

    public void login(){
        String username = loginFragment.getUserName();
        String password = loginFragment.getPassWord();
        if (username.length() == 0 || password.length() == 0) {
            SnackbarUtils.show(mContext, "用户名或密码不能为空");
            //return;
        }

        ProgressUtils.show(this, "正在登陆...");
        userLoginPresenter.login(username,password);
       // userLoginPresenter.login("1", "1");

    }
    public void register(){
        String username = registerFragment.getUserName();
        String password = registerFragment.getPassWord();
        if (username.length() == 0 || password.length() == 0) {
            SnackbarUtils.show(mContext, "用户名或密码不能为空");
            return;
        }
        userLoginPresenter.signup(username,password,"123@qq.com");
    }

    @Override
    public void loadDataSuccess(User tData) {
        ProgressUtils.dismiss();
        if (isLogin) {
            Log.i(TAG, "is" + LockViewUtil.getIslock(mContext));
            if(LockViewUtil.getIslock(mContext)){
                startActivity(new Intent(getApplicationContext(), UnlockUI.class));
                finish();
            }else {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
            setResult(RESULT_OK, new Intent());
            finish();

        }else {
            SnackbarUtils.show(mContext, "注册成功");
        }
    }

    @Override
    public void loadDataError(Throwable throwable) {
        ProgressUtils.dismiss();
        SnackbarUtils.show(mContext, throwable.getMessage());
    }

    /**
     * 切换至登陆界面
     */
    private void openLoginFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        loginFragment = new LoginFragment();
        transaction.replace(R.id.fragment_change, loginFragment);
        transaction.commit();
    }

    /**
     * 切换至注册界面
     */
    private void openRigsterFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        registerFragment = new RegisterFragment();
        transaction.replace(R.id.fragment_change, registerFragment);
        transaction.commit();
    }

    public static void setLock(boolean lock) {
        isLock = lock;
    }

    public boolean isLock() {
        return isLock;
    }
}
