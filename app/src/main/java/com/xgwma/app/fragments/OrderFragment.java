package com.xgwma.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xgwma.app.R;
import com.xgwma.app.activitys.PersonalCenterActivity;
import com.xgwma.app.bases.SimpleBaseFragment;

/**
 * 我的订单
 */
public class OrderFragment  extends SimpleBaseFragment {
    private View view;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.order_fragment,container,false);
        PersonalCenterActivity.persenter.selectorHideBackround(0);
        PersonalCenterActivity.persenter.selectorShowBackround(2);
        init();
        return view;
    }

    private void init() {
        Log.i("order_fragment", "222222222222222222");
        view.findViewById(R.id.tv_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        PersonalCenterActivity.persenter.selectorHideBackround(0);
    }
}
