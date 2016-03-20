package com.xgwma.app.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;
import com.xgwma.app.R;
import com.xgwma.app.bases.SimpleBaseActivity;
import com.xgwma.app.fragments.GrowFragment;
import com.xgwma.app.fragments.MoreFragment;
import com.xgwma.app.fragments.OrderFragment;
import com.xgwma.app.fragments.PersonalCenterFragment;
import com.xgwma.app.fragments.XgwFragment;
import com.xgwma.app.presenters.PresonalCenterPersenter;
import com.xgwma.app.views.IPersonalCenterView;
import java.util.List;
/**
 * 个人中心
 */
public class PersonalCenterActivity extends SimpleBaseActivity implements View.OnClickListener,IPersonalCenterView{
    //微分销系统,我的订单,成长记录,更多
    private LinearLayout ly_xgw;LinearLayout ly_order;LinearLayout ly_grow;LinearLayout ly_more;
    public static PresonalCenterPersenter persenter;
    private XgwFragment xgwFragment;
    private OrderFragment orderFragment;
    private GrowFragment growFragment;
    private MoreFragment moreFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center_activity);
        getSupportFragmentManager().beginTransaction().
                add(R.id.ly_contact, new PersonalCenterFragment(), "center").commit();
        init();
        monitorEvent();
    }

    private void init(){
        ly_xgw=(LinearLayout)findViewById(R.id.ly_xgw);
        ly_order=(LinearLayout)findViewById(R.id.ly_order);
        ly_grow=(LinearLayout)findViewById(R.id.ly_grow);
        ly_more=(LinearLayout)findViewById(R.id.ly_more);
        persenter=new PresonalCenterPersenter(this);
        xgwFragment =new XgwFragment();
        orderFragment=new OrderFragment();
        growFragment=new GrowFragment();
        moreFragment=new  MoreFragment();
    }
   /**
    * 事件监听
    * */
    private void monitorEvent(){
        ly_xgw.setOnClickListener(this);
        ly_order.setOnClickListener(this);
        ly_grow.setOnClickListener(this);
        ly_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        selectorBackground();
      //添加一个FragmentTransaction的实例
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.ly_xgw:
                ly_xgw.setSelected(true);
                popAllFragmentsExceptTheBottomOne();
                    transaction.addToBackStack("xgw");;
                transaction.replace(R.id.ly_contact, xgwFragment, "xgw");
                break;
            case R.id.ly_order:
                ly_order.setSelected(true);
                popAllFragmentsExceptTheBottomOne();
                transaction.addToBackStack("order");
                transaction.replace(R.id.ly_contact, orderFragment, "order");
                break;
            case R.id.ly_grow:
                ly_grow.setSelected(true);
                popAllFragmentsExceptTheBottomOne();
                transaction.addToBackStack("grow");;
                transaction.replace(R.id.ly_contact, growFragment, "grow");
                break;
            case R.id.ly_more:
                ly_more.setSelected(true);
                popAllFragmentsExceptTheBottomOne();
                transaction.addToBackStack("more");
                transaction.replace(R.id.ly_contact, moreFragment, "more");
                break;
        }
    transaction.setCustomAnimations(
            R.anim.fragment_left_enter,
            R.anim.fragment_left_exit,
                R.anim.fragment_pop_left_enter,
                R.anim.fragment_pop_left_exit);
        transaction.commit();


    }
    /**
     * 设置默认背景颜色
     * */
    private void  selectorBackground(){
        ly_xgw.setSelected(false);
        ly_order.setSelected(false);
        ly_grow.setSelected(false);
        ly_more.setSelected(false);
    }
    @Override
    public void showLoading(){

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void showBackround(int id){
        switch (id){
            case 0:
                selectorBackground();
                break;
            case 1:
                ly_xgw.setSelected(true);
                break;
            case 2:
                ly_order.setSelected(true);
                break;
            case 3:
                ly_grow.setSelected(true);
                break;
            case 4:
                ly_more.setSelected(true);
                break;
        }
    }

    @Override
    public void hideBackround(int id) {
        selectorBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //友盟
        MobclickAgent.onPageStart("PersonalCenterActivity"); //统计页面
    }

    @Override
    protected void onPause(){
        super.onPause();
        //友盟
        MobclickAgent.onPageEnd("PersonalCenterActivity"); //统计页面
    }
    /**
     * 从back stack弹出所有的fragment，保留最后三个
     */
    public  void popAllFragmentsExceptTheBottomOne(){
        for (int i = 0, count = getSupportFragmentManager().getBackStackEntryCount()-3; i < count; i++){
            getSupportFragmentManager().popBackStack();
        }
    }
}
