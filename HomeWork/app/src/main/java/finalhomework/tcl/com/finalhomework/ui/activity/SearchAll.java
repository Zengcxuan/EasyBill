package finalhomework.tcl.com.finalhomework.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import finalhomework.tcl.com.finalhomework.R;

public class SearchAll extends Activity{
    @BindView (R.id.search_exit)
    Button cancelBtn;
    @BindView(R.id.search_enter)
    EditText searchEdit;
    @BindView(R.id.search_result)
    ListView searchResultList;
    private Unbinder mUnBinder;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_all);
        mUnBinder = ButterKnife.bind(SearchAll.this);
    }


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
