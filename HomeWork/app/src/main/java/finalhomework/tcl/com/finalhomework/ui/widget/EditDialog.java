package finalhomework.tcl.com.finalhomework.ui.widget;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


import finalhomework.tcl.com.finalhomework.R;

public class EditDialog extends Dialog {
    private Activity mContext;
    private String tip, titleName;
    private String input = "默认提醒内容";
    public  EditDialog(Activity context){
        super(context, R.style.ActionSheetDialogStyle);
        this.mContext = context;
    }

    @Override
    public void show() {
        super.show();
        final EditText editText = new EditText(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("定时提醒");
        builder.setMessage("请输入内容");
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                input = editText.getText().toString();
                Log.v("----", input);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, "取消", Toast.LENGTH_LONG).show();
            }
        });
        builder.create().show();
    }


    public String getInput(){
        return input;
    }
}
