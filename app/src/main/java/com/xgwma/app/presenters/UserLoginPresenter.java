package com.xgwma.app.presenters;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xgwma.app.utils.HttpUtil;
import com.xgwma.app.views.IUserView;

import cz.msebera.android.httpclient.Header;

/**
 * Created by yangjie on 16/3/9.
 */
public class UserLoginPresenter{

    private IUserView userView;

    public  UserLoginPresenter(IUserView userView){
        this.userView=userView;
    }


    public void login(){
        userView.showLoading();
        RequestParams params=new RequestParams();
        params.add("",userView.getUserName());
        params.add("",userView.getUserSex());
        HttpUtil.post("", params, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                userView.hideLoading();
                String result = new String(responseBody);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
