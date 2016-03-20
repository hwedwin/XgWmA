package com.xgwma.app.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xgwma.app.R;
import com.xgwma.app.bases.Application;
import com.xgwma.app.bases.SimpleBaseActivity;
import com.xgwma.app.presenters.RegisterPresenter;
import com.xgwma.app.utils.Code;
import com.xgwma.app.utils.HttpCallBackUtil;
import com.xgwma.app.views.IRegisterView;
import com.xgwma.app.views.ZProgressHUD;

/**
 *注册类
 */
public class RegisterActivity  extends SimpleBaseActivity implements IRegisterView,View.OnClickListener,HttpCallBackUtil{
    private RegisterPresenter registerPresenter=new RegisterPresenter(this);
    private EditText et_realname;EditText et_setpassword;EditText et_confirm_password;EditText et_email;
    EditText et_phone;EditText et_id_card_number;
    //验证码
    private String getCode=null;
    private EditText et_code;
    private ImageView iv_code;
//    private  TextView tv_code_prompt;
    //登录
//    private TextView tv_login;
    private ZProgressHUD progressHUD;
    private CheckBox checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        init();
        monitorEvent();


    }

   //初始化
    private void init(){
        et_realname=(EditText)findViewById(R.id.et_real_name);
        et_code=(EditText)findViewById(R.id.et_code);
        iv_code=(ImageView)findViewById(R.id.iv_code);
        iv_code.setImageBitmap(Code.getInstance().getBitmap());
        getCode=Code.getInstance().getCode(); //获取显示的验证码
//        tv_login=(TextView)findViewById(R.id.tv_login);
        et_setpassword=(EditText)findViewById(R.id.et_set_password);
        et_confirm_password=(EditText)findViewById(R.id.et_confirm_password);
        et_email=(EditText)findViewById(R.id.et_email);
        et_phone=(EditText)findViewById(R.id.et_phone);
        et_id_card_number=(EditText)findViewById(R.id.et_id_card_number);
        et_code=(EditText)findViewById(R.id.et_code);
        registerPresenter.getModel().addResponseListener(this);
        progressHUD = ZProgressHUD.getInstance(this);
        progressHUD.setMessage("加载中");
        checkbox=(CheckBox)findViewById(R.id.checkbox);

    }
    //监听事件
    private void monitorEvent(){

        findViewById(R.id.btn_register_immediately).setOnClickListener(this);

        findViewById(R.id.tv_login).setOnClickListener(this);

        findViewById(R.id.tv_code_prompt).setOnClickListener(this);
    }
    //获取真实姓名
    @Override
    public String getRealName(){
       return et_realname.getText().toString().trim();
    }
   //获取设置密码
    @Override
    public String getSetPassword(){
        return et_setpassword.getText().toString().trim();
    }
  //获取确认密码
    @Override
    public String getConfirmPassword() {
        return et_confirm_password.getText().toString().trim();
    }
  //获取电子邮件
    @Override
    public String getEmail(){
        return et_email.getText().toString().trim();
    }
  //获取手机号
    @Override
    public String getPhone(){
        return et_phone.getText().toString().trim();
    }
   //获取身份证号
    @Override
    public String getIdCardNumber() {
        return et_id_card_number.getText().toString().trim();
    }
    //获取验证码
    @Override
    public String getCode(){
        return et_code.getText().toString().trim();
    }
    //获取验证码
    @Override
    public String getIvCode(){
        return getCode;
    }
    //获取推荐人手机号
    @Override
    public String getRecommendedPersonPhone(){
        return null;
    }
   //获取推荐人
    @Override
    public boolean getCheckBox() {return checkbox.isChecked();}
    //获取推荐人
    @Override
    public String getRecommendedPerson() {
        return null;
    }
   //提示信息
    @Override
    public void toRegisterActivity(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
   //显示加载
    @Override
    public void showLoading() {
        progressHUD.show();
    }
    //隐藏加载
    @Override
    public void hideLoading() {
        progressHUD.dismiss();
    }
    //加载失败
    @Override
    public void showFailedError() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //注册
            case R.id.btn_register_immediately:
                registerPresenter.register();
                break;
            //登录
            case R.id.tv_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            //切换验证码
            case R.id.tv_code_prompt:
                iv_code.setImageBitmap(Code.getInstance().getBitmap());
                getCode=Code.getInstance().getCode();
                break;
        }
    }
   //网络数据返回值
    @Override
    public void onCallBackData(String url, String content, boolean iscontent){
        if(iscontent){
            this.toRegisterActivity(content);
            startActivity(new Intent(this, LoginActivity.class));
        }else{

            this.toRegisterActivity(content);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //友盟
        MobclickAgent.onPageStart("RegisterActivity"); //统计页面
    }

    @Override
    protected void onPause() {
        super.onPause();
        //友盟
        MobclickAgent.onPageEnd("RegisterActivity"); //统计页面
    }
}
