package com.tcl.easybill.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import com.tcl.easybill.R;
import com.tcl.easybill.Utils.SnackbarUtils;
import com.tcl.easybill.Utils.ToastUtils;
import com.tcl.easybill.base.SyncEvent;
import com.tcl.easybill.mvp.views.MonthDetailView;
import com.tcl.easybill.mvp.views.UserInfoView;
import com.tcl.easybill.pojo.MonthDetailAccount;
import com.tcl.easybill.pojo.Person;
import com.tcl.easybill.pojo.base;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

public class BudgetActivity extends BaseActivity implements UserInfoView{
    @BindView(R.id.all_content)
    TextView budgetText;
    @BindView(R.id.progressBar)
    ProgressBar surplusProgressBar;
    private String input;
    /*@Subscribe(threadMode = ThreadMode.MAIN)*/
   /* public void Event(SyncEvent event) {
        if (event.getState()==100)
            budgetText.setText(currentUser.getGender());
    }*/
    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //register EventBus
        EventBus.getDefault().register(this);
    }*/

    @Override
    protected  int getLayout(){
        return R.layout.budget;
    }

    protected  void initEventAndData(){

    }

    /**
     * set the surplus
     */
    @OnClick (R.id.edit_surplus)
    public void editSurplus(){
        final EditText enterSurplus = new EditText(this);
        enterSurplus.setHint(R.string.surplus_set);
        enterSurplus.setInputType(TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("每月预算");
        builder.setView(enterSurplus);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*get setting budget*/
                input = enterSurplus.getText().toString();
                budgetText.setText(input);
                currentUser.setBudget(Float.valueOf(input));
                surplusProgressBar.setMax(Integer.valueOf(input));
                surplusProgressBar.setProgress((Integer.valueOf(input))/2);
                Toast.makeText(mContext, "设置成功", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "取消", Toast.LENGTH_LONG).show();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       /* EventBus.getDefault().unregister(this);*/
    }

    @OnClick (R.id.budget_back)
    public void backToParent(){
        finish();
    }


    @Override
    public void loadDataSuccess(Person tData) {
     /*   Log.e("meng00", "loadDataSuccess: "+tData.toString() );
        tData.setBudget(Float.valueOf(input));
        tData.save(new SaveListener() {
            @Override
            public void done(Object o, BmobException e) {
                if (e==null){
                    ToastUtils.show(getApplicationContext(),"修改成功");
                }else {
                    e.printStackTrace();
                }

            }
        });*/
    }

    @Override
    public void loadDataError(Throwable throwable) {
        /*EventBus.getDefault().unregister(this);*/
    }
}
