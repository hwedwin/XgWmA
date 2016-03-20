package com.xgwma.app.bases;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

/**
 * 基础activty类
 */
public class SimpleBaseActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //友盟日志加密
        AnalyticsConfig.enableEncrypt(true);
        //不显示友盟提示信息
        UmengUpdateAgent.setUpdateCheckConfig(false);
    }
    @Override
    protected void onResume(){
        super.onResume();
        //友盟统计
        MobclickAgent.onResume(this);
        initUmengUpdate();
    }

    /**
     * 友盟自动更新
     */
    private void initUmengUpdate() {
        /** 静默更新 */
        UmengUpdateAgent.silentUpdate(this);

        /** 自动更新，提醒用户下载 */
        // UmengUpdateAgent.update(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        //友盟统计
        MobclickAgent.onPause(this);
    }
   //保存数据的对象
   protected  MyPreference getMyPreference(String spName) {
        return new MyPreference(spName);
    }
}
