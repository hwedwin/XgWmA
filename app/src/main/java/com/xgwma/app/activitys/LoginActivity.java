package com.xgwma.app.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xgwma.app.R;
import com.xgwma.app.bases.Application;
import com.xgwma.app.bases.SimpleBaseActivity;
import com.xgwma.app.presenters.LoginPersenter;
import com.xgwma.app.utils.HttpCallBackUtil;
import com.xgwma.app.views.ILoginView;

/**
 * 登录
 */
public class LoginActivity extends SimpleBaseActivity implements View.OnClickListener,ILoginView,HttpCallBackUtil{
    private EditText et_user_name;
    private EditText et_pwd;

    private LoginPersenter loginPersenter=new LoginPersenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        init();
        monitorEvent();
    }
    private void init() {
        et_user_name=(EditText)findViewById(R.id.et_user_name);
        et_pwd=(EditText)findViewById(R.id.et_user_pwd);
    }

    private void monitorEvent(){
            findViewById(R.id.btn_login).setOnClickListener(this);
            findViewById(R.id.btn_forget_password).setOnClickListener(this);
    }
   //获取用户名
    @Override
    public String getUserName() {
        return et_user_name.getText().toString().trim();
    }
   //获取密码
    @Override
    public String getUserPwd() {
        return et_pwd.getText().toString().trim();
    }
   //提示信息
    @Override
    public void toLoginActivity(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    @Override
    public void showFailedError() {

    }
    @Override
    public void onClick(View v){

        switch(v.getId()){
            case R.id.btn_login:
//                 loginPersenter.getModel().addResponseListener(this);
//                  loginPersenter.login();
                startActivity(new Intent(this,PersonalCenterActivity.class));
                break;
            case R.id.btn_forget_password:

                break;

        }

    }

    @Override
    public void onCallBackData(String url, String content, boolean iscontent) {
            if (iscontent){
                this.toLoginActivity(content);
                startActivity(new Intent(this,PersonalCenterActivity.class));
            }else{
                this.toLoginActivity(content);
            }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //友盟
        MobclickAgent.onPageStart("LoginActivity"); //统计页面
    }

    @Override
    protected void onPause() {
        super.onPause();
        //友盟
        MobclickAgent.onPageEnd("LoginActivity"); //统计页面
    }
}
