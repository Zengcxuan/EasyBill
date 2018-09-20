package finalhomework.tcl.com.finalhomework.UI.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import finalhomework.tcl.com.finalhomework.R;

public class mine_Fragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //引用创建好的xml布局
        View view = inflater.inflate(R.layout.fragment_persionalMsg,container,false);
        return view;

    }
}
