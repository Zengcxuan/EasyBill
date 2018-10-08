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
import finalhomework.tcl.com.finalhomework.Utils.ProgressUtils;
import finalhomework.tcl.com.finalhomework.Utils.SnackbarUtils;
import finalhomework.tcl.com.finalhomework.mvp.presenter.UserLoginPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.UserLoginPresenterImpl;
import finalhomework.tcl.com.finalhomework.mvp.views.UserLoginView;
import finalhomework.tcl.com.finalhomework.pojo.User;
import finalhomework.tcl.com.finalhomework.ui.fragment.login.LoginFragment;
import finalhomework.tcl.com.finalhomework.ui.fragment.login.RegisterFragment;

public class UesrLoginActivity extends BaseActivity implements UserLoginView{
    @BindView(R.id.button_left)
    Button leftBtn;
    @BindView(R.id.button_right)
    Button rightBtn;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.username)
    EditText userName;
    @BindView(R.id.password)
    EditText passWord;

    private UserLoginPresenter userLoginPresenter;
    private boolean isLogin = true;
    @Override
    protected int getLayout() {
        return R.layout.login;
    }

    @Override
    protected void initEventAndData() {

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
            case R.id.button_left:
                leftBtn.setText("登陆");
                rightBtn.setText("注册");
                loginButton.setText("登陆");
                break;
            case R.id.button_right:
                leftBtn.setText("注册");
                rightBtn.setText("登陆");
                loginButton.setText("注册");
                isLogin =!isLogin;
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
        String username = userName.getText().toString();
        String password = passWord.getText().toString();
        if (username.length() == 0 || password.length() == 0) {
            SnackbarUtils.show(mContext, "用户名或密码不能为空");
            return;
        }

        ProgressUtils.show(this, "正在登陆...");

        userLoginPresenter.login(username, password);

    }
    public void register(){
        String username = userName.getText().toString();
        String password = passWord.getText().toString();
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
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
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
    /*    private void openLoginFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        LoginFragment fragment = new LoginFragment();
        transaction.add(R.id.fragment_change, fragment);
        transaction.commit();
    }*/

    /*private void openRigsterFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        RegisterFragment fragment = new RegisterFragment();
        transaction.add(R.id.fragment_change, fragment);
        transaction.commit();
    }*/
}
