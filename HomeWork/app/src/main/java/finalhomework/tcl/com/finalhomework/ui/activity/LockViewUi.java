package finalhomework.tcl.com.finalhomework.ui.activity;


import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.R;
import finalhomework.tcl.com.finalhomework.Utils.LockViewUtil;
import finalhomework.tcl.com.finalhomework.ui.widget.LockView;

import static finalhomework.tcl.com.finalhomework.ui.widget.LockView.STATUS_NO_PWD;

public class LockViewUi extends BaseActivity{
    @BindView(R.id.lockview)
    LockView lockView;
    @BindView(R.id.lockview_text)
    TextView lockViewText;
    @BindView(R.id.switch_lockview)
    Switch lockViewSwh;
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
        if(lockView.getCurrentStatus() == STATUS_NO_PWD){
            lockViewText.setText("当前无手势密码,请绘制你的手势密码");
        }else{
            lockViewText.setText("你已经设置了手势密码,请输入你的手势密码");
        }
        swhHandle();
        setLockView();
    }

    @OnClick ({R.id.switch_lockview, R.id.modify_lockview, R.id.back_persional})
    protected void onClick(View v){
        switch (v.getId()) {
            //退出当前Activity
            case R.id.back_persional:
                finish();
                break;
            //开关按钮
//            case R.id.switch_lockview:
//                swhHandle();
//                break;
            //修改手势密码,点击后直接清除当前存储密码
            case R.id.modify_lockview:
                modifyHandle();
                break;
        }
    }
    /**
     * 根据当前状态做出反应
     */
    private void setLockView(){
        lockView.setOnLockListener(new LockView.OnLockListener() {
            @Override
            public void onTypeInOnce(String input) {
                lockViewText.setText("再次输入密码");
            }

            @Override
            public void onTypeInTwice(String input, boolean isSuccess) {
                lockViewText.setText("成功保存密码");
            }

            @Override
            public void onUnLock(String input, boolean isSuccess) {
                if(isSuccess){
                    lockViewText.setText("验证成功");
                    isVerify = true;
                }else{
                    lockViewText.setText("密码错误");
                }
            }

            @Override
            public void onError() {
                lockViewText.setText("密码长度不够");
            }
        });
    }

    /**
     * Switch
     * 点击开启会获取手势密码的当前状态,无密码点击无效；有密码先看是否已验证,没验证无效,验证了则启用手势密
     * 码
     * 点击关闭直接停用手势密码（不清除记录）
     */
    private void swhHandle(){
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(isVerify){
                        if (lockView.getCurrentStatus() == STATUS_NO_PWD) {
                        Toast.makeText(mContext, "当前没有设置手势密码", Toast.LENGTH_SHORT).show();
                        lockViewSwh.setChecked(false);
                        }else {
                        lockViewSwh.setChecked(true);
                        LockViewUtil.setIslock(mContext, true);
                        Toast.makeText(mContext, "手势密码已启用", Toast.LENGTH_SHORT).show();
                       }
                    }else {
                        lockViewSwh.setChecked(false);
                        Toast.makeText(mContext, "请先验证密码", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    lockViewSwh.setChecked(false);
                    LockViewUtil.setIslock(mContext, false);
                    Toast.makeText(mContext, "手势密码已取消", Toast.LENGTH_SHORT).show();
                }
            }
        };
        lockViewSwh.setOnCheckedChangeListener(listener);
    }

    /**
     * 修改密码,已验证则点击清除当前密码,没验证点击无效
     */
    private void modifyHandle(){
        if(lockView.getCurrentStatus() == STATUS_NO_PWD){
            Toast.makeText(mContext, "你没有设置密码", Toast.LENGTH_SHORT).show();
        }else {
            if(isVerify) {
                LockViewUtil.clearPwd(mContext);
                lockView.initStatus();
                lockViewText.setText("当前无手势密码,请绘制你的手势密码");
                setLockView();
            }else {
                Toast.makeText(mContext, "请先验证密码", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
