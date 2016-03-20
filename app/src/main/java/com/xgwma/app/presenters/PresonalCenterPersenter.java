package com.xgwma.app.presenters;

import com.xgwma.app.activitys.PersonalCenterActivity;
import com.xgwma.app.views.IPersonalCenterView;

/**
 * Created by admin on 2016/3/15.
 */
public class PresonalCenterPersenter {
    public  IPersonalCenterView personalCenterView;
    public  PresonalCenterPersenter(IPersonalCenterView personalCenterView){
        this.personalCenterView=personalCenterView;
    }
    /**
     * 注册
     * */
    public void selectorShowBackround(int id){
        personalCenterView.showBackround(id);
    }
    public void selectorHideBackround(int id){
        personalCenterView.hideBackround(id);
    }
}
