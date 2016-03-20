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
import com.xgwma.app.bases.SimpleBaseActivity;
import com.xgwma.app.bases.SimpleBaseFragment;

/**
 * 成长记录
 */
public class GrowFragment extends SimpleBaseFragment {
    private View view;
    @Nullable
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.grow_fragment,container,false);
        PersonalCenterActivity.persenter.selectorHideBackround(0);
        PersonalCenterActivity.persenter.selectorShowBackround(3);
        init();
        return view;
    }

    private void init() {
        Log.i("grow_fragment","3333333333333333333");
        view.findViewById(R.id.tv_grow).setOnClickListener(new View.OnClickListener() {
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
