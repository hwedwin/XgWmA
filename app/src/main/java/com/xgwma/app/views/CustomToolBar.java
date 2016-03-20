package com.xgwma.app.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xgwma.app.R;

/**
 * 自定义标题栏
 */
public class CustomToolBar extends Toolbar {
    private LayoutInflater mInflater;
    private View mView;
    private TextView tv_title,titles;
    private ImageView return_back,add;
    private LinearLayout ll_mysearch;
    public  CustomToolBar(Context context){
        this(context, null);
    }
    public  CustomToolBar(Context context,AttributeSet attrs){
        this(context, attrs, 0);
    }
    public  CustomToolBar(Context context,AttributeSet attrs,int defStyleAttr){
        super(context, attrs, defStyleAttr);
        initVIew();
    }
    private void initVIew(){
        if(mView==null){
            mInflater=LayoutInflater.from(getContext());
            mView=mInflater.inflate(R.layout.custom_toolbar,null);
            titles=(TextView)mView.findViewById(R.id.title);
            ViewGroup.LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView, lp);
        }
    }
    public  void setRightButtonIcon(Drawable icon){
        if(add!=null){
            add.setImageDrawable(icon);
            add.setVisibility(VISIBLE);
        }
    }
    /**
     * 设置字体大小
     * */
    public  void setTitlesSize(float size){
        if (titles!=null){
            titles.setTextSize(size);
        }
    }
    //右边监听
    public void setRightButtonOnClickListener(OnClickListener li){
        add.setOnClickListener(li);
    }
    //左边监听
    public void setLeftButtonOnClickListener(OnClickListener li){
        return_back.setOnClickListener(li);
    }
    @Override
    public void setTitle(CharSequence title){
        super.setTitle(title);
        initVIew();
        if(titles!=null){
            titles.setText(title);
            showTitleView();
        }
    }

    @Override
    public void setTitle(int resId) {
        setTitle(getContext().getText(resId));
    }

    /**
     * 设置标题颜色
     * */
    @Override
    public void setTitleTextColor(int color) {
        super.setTitleTextColor(color);
        initVIew();
        if (titles != null) {
            titles.setTextColor(color);
        }
    }

    public  void showTitleView(){
        if(titles!=null){
            titles.setVisibility(VISIBLE);
        }
    }
    public  void hideTitleView(){
        if(titles!=null){
            titles.setVisibility(GONE);
        }
    }
    public  void showSearchView(){
        if(ll_mysearch!=null){
            ll_mysearch.setVisibility(VISIBLE);
        }
    }
    public  void hideSearchView(){
        if(ll_mysearch!=null){
            ll_mysearch.setVisibility(VISIBLE);
        }
    }
}
