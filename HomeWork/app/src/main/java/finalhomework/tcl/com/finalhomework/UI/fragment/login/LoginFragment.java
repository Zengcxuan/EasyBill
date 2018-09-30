package finalhomework.tcl.com.finalhomework.UI.fragment.login;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.UI.activity.HomeActivity;

public class LoginFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Button loginBtn = getActivity().findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.login_button})
    protected void onClick(View v){
        switch (v.getId()){
            case R.id.login_button:
                Intent intent = new Intent();
                intent.setClass(getActivity(), HomeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
