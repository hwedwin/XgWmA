package com.xgwma.app.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.xgwma.app.R;
import com.xgwma.app.bases.MyPreference;
import com.xgwma.app.bases.SimpleBaseActivity;

public class MainActivity extends SimpleBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMyPreference("").getuserName();
    }

}
