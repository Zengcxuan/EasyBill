package finalhomework.tcl.com.finalhomework.UI.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import finalhomework.tcl.com.finalhomework.R;

public class SearchAll extends Activity{
    @BindView (R.id.search_exit)
    Button cancelBtn;
    private TextView searchContent;
    private Unbinder mUnBinder;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_all);
        searchContent = (TextView) findViewById(R.id.search_content);
//        cancelBtn = (Button) findViewById(R.id.search_exit);
        mUnBinder = ButterKnife.bind(SearchAll.this);
//        cancelBtn.setOnClickListener(this);
    }

    /*@Override
    public void onClick(View v){
        if (v == cancelBtn){
            finish();
        }
    }*/


    @OnClick ({R.id.search_exit, R.id.search_content})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_exit:
                Log.d("------","exit");
                finish();
                break;
            case R.id.search_content:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
