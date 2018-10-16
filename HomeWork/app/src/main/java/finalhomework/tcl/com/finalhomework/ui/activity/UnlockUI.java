package finalhomework.tcl.com.finalhomework.ui.activity;

import android.content.Intent;
import android.widget.Toast;

import butterknife.BindView;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.ui.widget.LockView;


public class UnlockUI extends BaseActivity {
    @BindView(R.id.unlock_lockview)
    LockView lockView;
    @Override
    protected int getLayout() {
        return R.layout.activity_unlock_ui;
    }

    @Override
    protected void initEventAndData() {
        lockView.setOnLockListener(new LockView.OnLockListener() {
            @Override
            public void onTypeInOnce(String input) {

            }

            @Override
            public void onTypeInTwice(String input, boolean isSuccess) {

            }

            @Override
            public void onUnLock(String input, boolean isSuccess) {
               if(isSuccess){
                   startActivity(new Intent(getApplicationContext(), HomeActivity.class));
               }else {
                   Toast.makeText(mContext, "错误", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onError() {
                Toast.makeText(mContext, "密码长度不够", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
