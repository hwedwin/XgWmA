package com.xgwma.app.beans;

/**
 * 用户数据
 */
public class UserBean{

    private  String userName;

    private  String userSex;

    private  int age;

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
