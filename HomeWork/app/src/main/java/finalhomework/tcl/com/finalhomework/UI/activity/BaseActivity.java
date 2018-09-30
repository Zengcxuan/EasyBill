package finalhomework.tcl.com.finalhomework.UI.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutInflater().inflate(getLayout(), null, true));
    }

    protected abstract int getLayout();
}
