package com.xgwma.app.views;

/**
 * 根据需求，view可以对用户姓名,用户性别,用户年龄三个控件进行操作
 */
public interface IUserView {

    int getAge();

    String getUserName();

    String getUserSex();

    void setUserName(String usertName);

    void setUserSex(String userSex);
    //显示加载
    void showLoading();
    //隐藏加载
    void hideLoading();
    //加载失败
    void showFailedError();

}
