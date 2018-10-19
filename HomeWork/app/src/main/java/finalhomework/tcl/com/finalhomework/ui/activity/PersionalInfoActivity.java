package finalhomework.tcl.com.finalhomework.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.b.V;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.DateUtils;
import finalhomework.tcl.com.finalhomework.Utils.ProgressUtils;
import finalhomework.tcl.com.finalhomework.Utils.SnackbarUtils;
import finalhomework.tcl.com.finalhomework.Utils.StringUtils;
import finalhomework.tcl.com.finalhomework.mvp.model.impl.UserInfoModelImp;
import finalhomework.tcl.com.finalhomework.mvp.presenter.UserInfoPresenter;
import finalhomework.tcl.com.finalhomework.mvp.presenter.impl.UserInfoPresenterImp;
import finalhomework.tcl.com.finalhomework.mvp.views.UserInfoView;
import finalhomework.tcl.com.finalhomework.pojo.Person;
import finalhomework.tcl.com.finalhomework.pojo.User;
import finalhomework.tcl.com.finalhomework.ui.widget.TextWithImg;

public class PersionalInfoActivity extends BaseActivity implements UserInfoView {
    @BindView(R.id.name_text)
    TextWithImg userName;
    @BindView(R.id.gender_text)
    TextWithImg gender;
    @BindView(R.id.email_text)
    TextWithImg email;
    @BindView(R.id.share_text)
    TextWithImg shareAccount;

    private UserInfoPresenter presenter;

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
        presenter = new UserInfoPresenterImp(this);
        userName.setText(currentUser.getUsername());
        gender.setText(getGender(currentUser.getGender()));
        email.setText(currentUser.getEmail());
        getShareNumber();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_persional;
    }


    @OnClick ({R.id.name_persional, R.id.head_persional, R.id.gender_persional, R.id.email_persional,
    R.id.logout, R.id.back_persional, R.id.share_account})
    protected void onClick(View view) {
        switch (view.getId()) {
           case R.id.name_persional:
               changUserName();
               break;
           case R.id.head_persional:
               break;
           case R.id.gender_persional:
               changeGender();
               break;
           case R.id.email_persional:
                changeEmail();
               break;
           case R.id.logout:
               logout();
               break;
           case R.id.back_persional:
               finish();
               break;
            case R.id.share_account:

               break;

       }
    }
    /**
     * 更改Email
     */
    private void changeEmail(){
        final EditText emailEditText = new EditText(PersionalInfoActivity.this);
        emailEditText.setText(currentUser.getEmail());
        //将光标移至文字末尾
        emailEditText.setSelection(currentUser.getEmail().length());
        if (emailDialog == null) {
            emailDialog = new android.support.v7.app.AlertDialog.Builder(this)
                    .setTitle("邮箱")
                    .setView(emailEditText)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String input = emailEditText.getText().toString();
                            if (input.equals("")) {
                                Toast.makeText(getApplicationContext(), "内容不能为空！" + input,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                if (StringUtils.checkEmail(input)) {
                                    currentUser.setEmail(input);
                                    email.setText(input);
                                    doUpdate();
                                } else {
                                    Toast.makeText(PersionalInfoActivity.this,
                                            "请输入正确的邮箱格式", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create();
        }
        if (!emailDialog.isShowing()) {
            emailDialog.show();
        }
    }
    /**
     * 根据注册时间随即生成分享码
     */
    public void getShareNumber(){
        if (currentUser.getShareid() == 0){
            String shareNumber ;
            int updateNUmber =0;
            int right = (int)(Math.random()*90+10);
            String time =  currentUser.getCreatedAt();
            Date date = DateUtils.str2Date(time);
            Long l = date.getTime();
            int left =  Math.abs(l.intValue()) ;
            shareNumber = left + String.valueOf(right);
            updateNUmber = l.intValue()+right;
            shareAccount.setText(shareNumber);
            currentUser.setShareid(updateNUmber);
            doUpdate();
        }else {
            shareAccount.setText(String.valueOf(Math.abs(currentUser.getShareid())));
        }

    }
    /**
     * 更改username
     */
    private void changUserName(){
        SnackbarUtils.show(mContext, "社会人行不改名，坐不改姓！！！！");
    }
    private void changeGender(){
        if (genderDialog == null) {
            genderDialog = new android.support.v7.app.AlertDialog.Builder(this).setItems(new String[]{"男", "女"},
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case GENDER_MAN: // 男性
                                    /*if(currentUser.getGender()== 0 ){
                                        currentUser.setGender(1);
                                    }*/
                                    if(currentUser.getGender() == 0){
                                        currentUser.setGender(1);
                                        doUpdate();
                                    }
                                    break;
                                case GENDER_FEMALE: // 女性
                                    /*if(currentUser.getGender()== 0 ){
                                        currentUser.setGender(2);
                                    }*/
                                    if(currentUser.getGender() ==1){
                                        currentUser.setGender(0);
                                        doUpdate();
                                    }
                                    break;
                            }
                            gender.setText(getGender(currentUser.getGender()));
                            SnackbarUtils.show(mContext, "更改成功，同时恭喜手术成功");
                        }
                    }).create();
        }
        if (!genderDialog.isShowing()) {
            genderDialog.show();
        }
    }
    /**
     * 更新同步
     */
    public void doUpdate() {
        if (currentUser == null)
            return;
       // ProgressUtils.show(PersionalInfoActivity.this, "正在修改...");
        presenter.update(currentUser);

    }
    /**
     * 修改性别
     */
    public String getGender(int i){
        String gender ;
        if (i==1){
            gender = "男";
        }else {
            gender = "女";
        }
        return gender;
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

    @Override
    public void loadDataSuccess(Person tData) {

    }

    @Override
    public void loadDataError(Throwable throwable) {

    }
}
