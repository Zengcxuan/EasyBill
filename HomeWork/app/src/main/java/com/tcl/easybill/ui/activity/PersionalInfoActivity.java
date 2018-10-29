package com.tcl.easybill.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import com.tcl.easybill.R;
import com.tcl.easybill.Utils.DateUtils;
import com.tcl.easybill.Utils.SnackbarUtils;
import com.tcl.easybill.Utils.StringUtils;
import com.tcl.easybill.mvp.presenter.UserInfoPresenter;
import com.tcl.easybill.mvp.presenter.impl.UserInfoPresenterImp;
import com.tcl.easybill.mvp.views.UserInfoView;
import com.tcl.easybill.pojo.Person;
import com.tcl.easybill.ui.widget.RoundImageView;
import com.tcl.easybill.ui.widget.TextWithImg;

public class PersionalInfoActivity extends BaseActivity implements UserInfoView {
    @BindView(R.id.name_text)
    TextWithImg userName;
    @BindView(R.id.gender_text)
    TextWithImg gender;
    @BindView(R.id.email_text)
    TextWithImg email;
    @BindView(R.id.share_text)
    TextWithImg shareAccount;
    @BindView(R.id.persional_head)
    RoundImageView perisonalHead;

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
    private File file;
    private Uri imageUri;

    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA, Manifest.permission.MEDIA_CONTENT_CONTROL};




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
               changeHead();
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

    /**
     * 修改头像
     */
    private void changeHead(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            for(int i = 0; i < 3; i++){
                if(ContextCompat.checkSelfPermission(this, permissions[i]) !=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, permissions,111);
                }
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("选择模式");
        builder.setPositiveButton("相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        }).setNegativeButton("拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "拍照", Toast.LENGTH_LONG).show();
                imageUri = createImageUri(PersionalInfoActivity.this);
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//如果不设置EXTRA_OUTPUT getData()  获取的是bitmap数据  是压缩后的
                startActivityForResult(intent, 2);

            }
        });
        builder.create().show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            delteImageUri(mContext, imageUri);
            return;
        }else {
            switch (requestCode) {
                // 如果是直接从相册获取
                case 1:
                    try {
                        imageUri = data.getData();
                        Log.e("TAG", imageUri.toString());
                        perisonalHead.setImageURI(imageUri);
                        Bitmap photo = data.getParcelableExtra("data");
                        saveBitmap(photo);
//                        perisonalHead.setImageBitmap(photo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                // 如果是调用相机拍照时
                case 2:
                    imageUri = data.getData();
                    Log.e("TAG", imageUri.toString());
                    perisonalHead.setImageURI(imageUri);
                    Bitmap photo = data.getParcelableExtra("data");
//                    perisonalHead.setImageBitmap(photo);
//                    saveBitmap(photo);
                    break;
                default:
                    break;

            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    private static Uri createImageUri(Context context) {
        String name = "takePhoto" + System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, name);
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpeg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        return uri;
    }


    public static void delteImageUri(Context context, Uri uri) {
        context.getContentResolver().delete(uri, null, null);

    }

    @Override
    protected void onPause() {
        presenter = new UserInfoPresenterImp(this);
        userName.setText(currentUser.getUsername());
        gender.setText(getGender(currentUser.getGender()));
        email.setText(currentUser.getEmail());
        getShareNumber();
        super.onPause();
    }

    @Override
    public void loadDataSuccess(Person tData) {

    }

    @Override
    public void loadDataError(Throwable throwable) {

    }

    public void saveBitmap(Bitmap bitmap) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),"mine_head");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "mine_head" + ".png";
        file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "/sdcard/namecard/")));
    }
}