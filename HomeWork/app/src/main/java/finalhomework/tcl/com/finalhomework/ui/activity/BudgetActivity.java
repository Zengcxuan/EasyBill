package finalhomework.tcl.com.finalhomework.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import finalhomework.tcl.com.finalhomework.R;

import static android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

public class BudgetActivity extends BaseActivity{
    @BindView(R.id.all_content)
    TextView budgetText;
    @BindView(R.id.progressBar)
    ProgressBar surplusProgressBar;
    private String input;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected  int getLayout(){
        return R.layout.budget;
    }

    protected  void initEventAndData(){

    }

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
                //获得设置的预算
                input = enterSurplus.getText().toString();
                budgetText.setText(input);
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
    @OnClick (R.id.budget_back)
    public void backToParent(){
        finish();
    }
}
