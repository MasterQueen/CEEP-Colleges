package com.example.administrator.myapplication;

/**
 * Created by Administrator on 6/9/2018.
 */

public class Rocard_Datas {

    private String title;
    private  String time;
    private  String postion;

    public  Rocard_Datas(String title,String time,String postion){
        this.title = title;
        this.time = time;
        this.postion  = postion;
    }

    public String getTitle(){

        return title;
    }

    public String getTime() {
        return time;
    }

    public String getPostion() {
        return postion;
    }
}
