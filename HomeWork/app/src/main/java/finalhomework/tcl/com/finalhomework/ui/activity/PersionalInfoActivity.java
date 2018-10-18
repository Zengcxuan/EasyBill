package finalhomework.tcl.com.finalhomework.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.pojo.User;

public class PersionalInfoActivity extends BaseActivity {
    @BindView(R.id.name_persional)
    TextView userName;
    @BindView(R.id.gender_persional)
    TextView gender;
    @BindView(R.id.email_persional)
    TextView email;

    private android.support.v7.app.AlertDialog iconDialog;
    private android.support.v7.app.AlertDialog genderDialog;
    private android.support.v7.app.AlertDialog phoneDialog;
    private android.support.v7.app.AlertDialog emailDialog;

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static final int GENDER_MAN = 0;
    protected static final int GENDER_FEMALE = 1;
    private static final int CROP_SMALL_PICTURE = 2;

    @Override
    protected void initEventAndData() {
        userName.setText(currentUser.getUsername());
        //gender.setText(currentUser.getGender());
        email.setText(currentUser.getEmail());
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
