package com.xgwma.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xgwma.app.R;
import com.xgwma.app.activitys.PersonalCenterActivity;
import com.xgwma.app.bases.SimpleBaseActivity;
import com.xgwma.app.bases.SimpleBaseFragment;

import org.w3c.dom.Text;

/**
 * 个人中心
 */
public class PersonalCenterFragment extends SimpleBaseFragment implements View.OnClickListener{
   private View view;
    private TextView tv_user_name;//姓名
    private TextView tv_user_agent;//代理
    private TextView tv_agent_level; //代理等级
    private TextView tv_is_authentication;//是否认证
    private TextView tv_user_id;  //个人id
    private TextView tv_account_settings; //账号设置
    private TextView tv_total_sales; //销售总额
    private TextView tv_team_number; //团队人数
    private TextView tv_become_agent;              //代理
    private TextView tv_immediately_authentication;//认证

    private TextView tv_recommended_person;//推荐人

    private TextView tv_tream_number;//团队人数
    private TextView tv_achievement_number;//我的业绩

    private RelativeLayout rl_my_team;//我的团队
    private RelativeLayout rl_my_achievement;//我的业绩
    private RelativeLayout rl_apply_withdrawals;//申请提现
    private RelativeLayout rl_apply_reward;//申请奖励
    private RelativeLayout rl_report;//举报
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.personal_center_fragment,container,false);
        PersonalCenterActivity.persenter.selectorHideBackround(0);
        init();
        monitorEvent();
        return view;
    }



    private void init(){
        Log.i("personal_fragment", "000000");
        tv_user_name=(TextView)view.findViewById(R.id.tv_user_name);
        tv_user_agent=(TextView)view.findViewById(R.id.tv_user_agent);
        tv_agent_level=(TextView)view.findViewById(R.id.tv_agent_level);
        tv_is_authentication=(TextView)view.findViewById(R.id.tv_is_authentication);
        tv_user_id=(TextView)view.findViewById(R.id.tv_user_id);
        tv_account_settings=(TextView)view.findViewById(R.id.tv_account_settings);
        tv_team_number=(TextView)view.findViewById(R.id.tv_team_number);
        tv_total_sales=(TextView)view.findViewById(R.id.tv_total_sales);
        tv_become_agent=(TextView)view.findViewById(R.id.tv_become_agent);
        tv_immediately_authentication=(TextView)view.findViewById(R.id.tv_immediately_authentication);
        tv_recommended_person=(TextView)view.findViewById(R.id.tv_recommended_person);
        tv_tream_number=(TextView)view.findViewById(R.id.tv_tream_number);
        tv_achievement_number=(TextView)view.findViewById(R.id.tv_achievement_number);
        rl_my_team=(RelativeLayout)view.findViewById(R.id.rl_my_team);
        rl_my_achievement=(RelativeLayout)view.findViewById(R.id.rl_my_achievement);
        rl_apply_withdrawals=(RelativeLayout)view.findViewById(R.id.rl_apply_withdrawals);
        rl_apply_reward=(RelativeLayout)view.findViewById(R.id.rl_apply_reward);
        rl_report=(RelativeLayout)view.findViewById(R.id.rl_report);
    }
    private void monitorEvent(){
        tv_become_agent.setOnClickListener(this);
        rl_my_team.setOnClickListener(this);
        rl_my_achievement.setOnClickListener(this);
        rl_apply_withdrawals.setOnClickListener(this);
        rl_apply_reward.setOnClickListener(this);
        rl_report.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_become_agent:
                break;
            case R.id.tv_immediately_authentication:
                break;
            case R.id.tv_account_settings:
                break;
            case R.id.rl_my_team:
                break;
            case R.id.rl_my_achievement:
                break;
            case R.id.rl_apply_withdrawals:
                break;
            case R.id.rl_apply_reward:
                break;
            case R.id.rl_report:
                break;

     }
    }
}
