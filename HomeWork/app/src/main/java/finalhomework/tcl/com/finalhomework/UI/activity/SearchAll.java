package finalhomework.tcl.com.finalhomework.UI.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import finalhomework.tcl.com.finalhomework.R;

public class SearchAll extends Activity implements View.OnClickListener{
    private TextView searchContent;
    private Button cancelBtn;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_all);
        searchContent = (TextView) findViewById(R.id.search_content);
        cancelBtn = (Button) findViewById(R.id.search_exit);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == cancelBtn){
            finish();
        }
    }
}
