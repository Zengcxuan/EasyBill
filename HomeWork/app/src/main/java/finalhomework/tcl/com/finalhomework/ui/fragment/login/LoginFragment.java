package finalhomework.tcl.com.finalhomework.ui.fragment.login;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.mvp.presenter.UserLoginPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.UserLoginPresenterImpl;
import finalhomework.tcl.com.finalhomework.ui.activity.HomeActivity;
import finalhomework.tcl.com.finalhomework.ui.activity.UesrLoginActivity;

public class LoginFragment extends Fragment{
    private Context mContext;
    private Unbinder mUnBinder;

    private boolean isLogin = true;
    private UserLoginPresenter userLoginPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        this.mContext = getActivity();
//        Button loginBtn = getActivity().findViewById(R.id.login_button);
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), HomeActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
//        initEventAndData();
    }

    @OnClick({R.id.login_button})
    protected void onClick(View v){
        switch (v.getId()){
            case R.id.login_button:
                Toast.makeText(mContext, "正在登陆", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

}
