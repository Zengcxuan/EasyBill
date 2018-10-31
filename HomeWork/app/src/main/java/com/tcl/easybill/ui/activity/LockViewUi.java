package com.tcl.easybill.ui.activity;


import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;

import com.syd.oden.gesturelock.view.GestureLockViewGroup;
import com.tcl.easybill.R;
import com.tcl.easybill.Utils.LockViewUtil;


public class LockViewUi extends BaseActivity{
    @BindView(R.id.switch_lockview)
    Switch lockViewSwh;
    @BindView(R.id.tv_prompt_lock_on)
    TextView tv_state;
    @BindView(R.id.gesturelock)
    GestureLockViewGroup mGestureLockViewGroup;
    private Boolean isVerify = false;
    @Override
    protected int getLayout() {
        return R.layout.activity_lockview;
    }

    @Override
    protected void initEventAndData() {
        if(LockViewUtil.getIslock(mContext)){
            lockViewSwh.setChecked(true);
        }

    }

    @OnClick ({R.id.switch_lockview,  R.id.back_persional})
    protected void onClick(View v){
        switch (v.getId()) {
            //login out this activity
            case R.id.back_persional:
                finish();
                break;
            //switch button
//            case R.id.switch_lockview:
//                swhHandle();
//                break;
        }
    }

}
