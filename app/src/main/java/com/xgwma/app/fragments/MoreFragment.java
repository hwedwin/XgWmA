package com.xgwma.app.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.fb.FeedbackAgent;
import com.xgwma.app.R;
import com.xgwma.app.activitys.CustomActivity;
import com.xgwma.app.activitys.PersonalCenterActivity;
import com.xgwma.app.bases.SimpleBaseFragment;
import com.xgwma.app.presenters.MorePersenter;
import com.xgwma.app.task.FileCalculateAsyncTask;
import com.xgwma.app.task.FileDeleteAsyncTask;
import com.xgwma.app.task.OnResponseListener;
import com.xgwma.app.utils.CacheManager;
import com.xgwma.app.utils.FileUtils;
import com.xgwma.app.utils.ToastUtil;
import com.xgwma.app.views.BaseDialog;
import com.xgwma.app.views.CustomToolBar;
import com.xgwma.app.views.LoadingDialog;
import com.xgwma.app.views.SelectionDialog;

import java.io.File;

/**
 * 更多
 */
public class MoreFragment extends SimpleBaseFragment implements View.OnClickListener{
    private View view;
    private CustomToolBar customToolBar;
    private RelativeLayout rl_empty_cache;         //清空缓存
    private RelativeLayout rl_check_updates;  //版本更新
    private RelativeLayout rl_invite_friends; //邀请朋友
    private RelativeLayout rl_feedback;      //意见反馈
    private RelativeLayout rl_message_notification; //消息通知
    private RelativeLayout rl_join_us; //加入我们
    private RelativeLayout rl_message_setting;//消息设置
    private TextView tv_cath;
    private MorePersenter persenter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.more_fragment,container,false);
        PersonalCenterActivity.persenter.selectorHideBackround(0);
        PersonalCenterActivity.persenter.selectorShowBackround(4);
        init();
        monitorEvent();
        return view;
    }
    private void init(){
        Log.i("more_fragment", "4444444444444444444444");
        customToolBar=(CustomToolBar)view.findViewById(R.id.custom_toolbar);
        customToolBar.setTitle("更多");
        customToolBar.setTitleTextColor(getResources().getColor(R.color.sky_blue));
        rl_empty_cache=(RelativeLayout)view.findViewById(R.id.rl_empty_cache);
        rl_check_updates=(RelativeLayout)view.findViewById(R.id.rl_check_updates);
        rl_invite_friends=(RelativeLayout)view.findViewById(R.id.rl_invite_friends);
        rl_feedback=(RelativeLayout)view.findViewById(R.id.rl_feedback);
        rl_message_notification=(RelativeLayout)view.findViewById(R.id.rl_message_notification);
        rl_join_us=(RelativeLayout)view.findViewById(R.id.rl_join_us);
        rl_message_setting=(RelativeLayout)view.findViewById(R.id.rl_message_setting);
        tv_cath=(TextView)view.findViewById(R.id.tv_cath);
        persenter=new MorePersenter(getActivity());
        //更新缓存数据
        persenter.updateData(tv_cath);
    }

    private void monitorEvent() {
        rl_empty_cache.setOnClickListener(this);
        rl_check_updates.setOnClickListener(this);
        rl_invite_friends.setOnClickListener(this);
        rl_feedback.setOnClickListener(this);
        rl_message_notification.setOnClickListener(this);
        rl_join_us.setOnClickListener(this);
        rl_empty_cache.setOnClickListener(this);
        rl_message_setting.setOnClickListener(this);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        PersonalCenterActivity.persenter.selectorHideBackround(0);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                //清除缓存数据
                case R.id.rl_empty_cache:
                    persenter.clearCache(tv_cath);
                    break;
                //检查更新
                case R.id.rl_check_updates:
                    persenter.checkUpgrade();
                    break;

                case R.id.rl_feedback:
                    feedback();
                    break;

                case R.id.rl_invite_friends:

                    break;

                case R.id.rl_join_us:
                    break;

                case R.id.rl_message_notification:

                    break;
                case  R.id.rl_message_setting:

                    break;

            }
    }
    /**
     * 用户反馈
     */
    private void feedback() {
        FeedbackAgent fb = new FeedbackAgent(getActivity());
        fb.sync();
        fb.openFeedbackPush();
        Intent intent = new Intent(getActivity(), CustomActivity.class);
        startActivity(intent);
    }
}
