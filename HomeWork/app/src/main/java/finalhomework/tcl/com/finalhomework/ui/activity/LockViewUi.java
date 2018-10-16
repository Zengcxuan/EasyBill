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
        setLockView();
    }

    @OnClick ({R.id.switch_lockview, R.id.modify_lockview, R.id.back_lockview})
    protected void onClick(View v){
        if(v.getId() == R.id.back_lockview){
            finish();
        }else {
            if (lockView.getCurrentStatus() == STATUS_NO_PWD) {
                Toast.makeText(this, "当前没有设置手势密码", Toast.LENGTH_SHORT).show();
                lockViewSwh.setChecked(false);
            } else if (isVerify) {
                switch (v.getId()) {
                    case R.id.switch_lockview:
                        // TODO: 18-10-15 密码启用
                        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener(){
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){
                                    lockViewSwh.setChecked(true);
                                    LockViewUtil.setIslock(mContext, true);
                                    Toast.makeText(mContext, "手势密码已启用", Toast.LENGTH_SHORT).show();
                                }else {
                                    lockViewSwh.setChecked(false);
                                    LockViewUtil.setIslock(mContext, false);
                                    Toast.makeText(mContext, "手势密码已取消", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };
                        lockViewSwh.setOnCheckedChangeListener(listener);
                        break;
                    case R.id.modify_lockview:
                        LockViewUtil.clearPwd(mContext);
                        lockView.initStatus();
                        lockViewText.setText("当前无手势密码,请绘制你的手势密码");
                        setLockView();
                        break;
                }
            } else if (!(lockView.getCurrentStatus() == STATUS_NO_PWD) && !isVerify) {
                Toast.makeText(this, "请先验证手势密码", Toast.LENGTH_SHORT).show();
                lockViewSwh.setChecked(false);
            }
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
}
