package com.example.administrator.myapplication.tool;

import java.util.List;

/**
 * Created by Administrator on 3/19/2018.
 */

public class Province {

    private String p_name;
    private List<Citys>citys;

    public Province(String p_name , List<Citys> citys){
        this.p_name = p_name;
        this.citys = citys;
    }

    public  String getP_name(){
        return  p_name;
    }


    public List<Citys> getCitys() {
        return citys;
    }
}
