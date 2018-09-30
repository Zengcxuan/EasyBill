package finalhomework.tcl.com.finalhomework.UI.activity;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.UI.fragment.login.LoginFragment;
import finalhomework.tcl.com.finalhomework.UI.fragment.login.RegisterFragment;

public class LoginActivity extends FragmentActivity implements View.OnClickListener{
    private Button leftBtn;
    private Button rightBtn;
    private Unbinder mUnBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);  }
        setContentView(R.layout.activity_login);
        mUnBinder = ButterKnife.bind(this);


        leftBtn = findViewById(R.id.button_left);
        rightBtn = findViewById(R.id.button_right);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        openLoginFragment();
    }

//    @Override
//    public void onClick(View v){
//        switch (v){
//            case leftBtn:
//                 break;
//            case rightBtn:
//        }
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_left:
                if(!leftBtn.getText().toString().equals("登陆")) {
                    leftBtn.setText("登陆");
                    rightBtn.setText("注册");
                    openLoginFragment();
                }
                break;
            case R.id.button_right:
                if(!rightBtn.getText().toString().equals("注册")) {
                    leftBtn.setText("注册");
                    rightBtn.setText("登陆");
                    openLoginFragment();
                }
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
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
