package finalhomework.tcl.com.finalhomework.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.R;

public class PersionalInfoActivity extends BaseActivity {
    @BindView(R.id.name_persional)
    TextView userName;
    @BindView(R.id.gender_persional)
    TextView gender;
    @BindView(R.id.email_persional)
    TextView email;
    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_persionalinf;
    }


    @OnClick ({R.id.name_persional, R.id.head_persional, R.id.gender_persional, R.id.email_persional,
    R.id.logout, R.id.back_persional})
    protected void onClick(View view) {
        switch (view.getId()) {
           case R.id.name_persional:
               break;
           case R.id.head_persional:
               break;
           case R.id.gender_persional:
               break;
           case R.id.email_persional:
               break;
           case R.id.logout:
               logout();
               break;
           case R.id.back_persional:
               finish();
               break;

       }
    }

    /**
     * 退出登陆并返回到登陆界面
     */
    private void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("退出登陆并返回到登陆界面？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: 18-10-18 这里注销

                Intent intent  = new Intent(mContext,UesrLoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}
