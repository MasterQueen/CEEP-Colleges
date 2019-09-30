package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.myapplication.tool.Citys;
import com.example.administrator.myapplication.tool.Province;

import java.util.ArrayList;
import java.util.List;

import data_treating.RegisterSchool;

/**
 * Created by Administrator on 7/17/2018.
 */

public class Register extends AppCompatActivity implements  View.OnClickListener,AdapterView.OnItemSelectedListener{

    private Spinner spinner_select_province;
    private Spinner spinner_select_school;
    private Button register;
    private EditText et_password;

    public static String local="河南";
    private String password;
    private String university;
    private List<Citys>data;
    private List<Province>provinces;
    private List<String>city;
    private ArrayAdapter<String> adapter1,adapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        spinner_select_province = findViewById(R.id.select_province);
        spinner_select_school  = findViewById(R.id.select_school);

        getData();
        getPriovince();

        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        spinner_select_province.setAdapter(adapter1);
        spinner_select_province.setOnItemSelectedListener(this);

        register = findViewById(R.id.register_ok);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 获取数据注册
                local = spinner_select_province.getSelectedItem().toString();
                university = spinner_select_school.getSelectedItem().toString();
                et_password = findViewById(R.id.password);
                password = et_password.getText().toString();

                new UserRegisterTask(local, university, password).execute();
            }
        });

    }


    public void setLocal(String local){
        this.local = local;
    }





    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String p_name = city.get(position).trim();
        List<String>citys = getCitys(p_name);

        adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,citys);
        spinner_select_school.setAdapter(adapter2);

    }
    private void getData(){
//      创建集合 把city数据添加到集合
        data=new ArrayList<Citys>();
        provinces=new ArrayList<Province>();
        Citys citys=null;
        Province province=null;
//      北京市区
        citys=new Citys("北京大学");
        data.add(citys);
        citys=new Citys("清华大学");
        data.add(citys);
        citys=new Citys("北京邮电大学");
        data.add(citys);
        citys=new Citys("中国传媒大学");
        data.add(citys);
        citys=new Citys("北京电影学院");
        data.add(citys);
        citys=new Citys("北京理工大学");
        data.add(citys);
        citys=new Citys("中国人民大学");
        data.add(citys);
        citys=new Citys("中国地质大学");
        data.add(citys);
        province=new Province("北京", data);
        provinces.add(province);

//      上海市区
        data=new ArrayList<Citys>();
        citys=new Citys("复旦大学");
        data.add(citys);
        citys=new Citys("上海交通大学");
        data.add(citys);
        citys=new Citys("同济大学");
        data.add(citys);
        citys=new Citys("上海大学");
        data.add(citys);
        citys=new Citys("华东师范大学");
        data.add(citys);
        citys=new Citys("上海电力大学");
        data.add(citys);
        province=new Province("上海", data);
        provinces.add(province);

//      郑州市区
        data=new ArrayList<Citys>();
        citys=new Citys("郑州大学");
        data.add(citys);
        citys=new Citys("河南财经政法大学");
        data.add(citys);
        citys=new Citys("郑州轻工业大学");
        data.add(citys);
        citys=new Citys("河南工业大学");
        data.add(citys);
        citys=new Citys("中原工学院");
        data.add(citys);
        citys=new Citys("华北水利水电大学");
        data.add(citys);
        citys=new Citys("河南中医药大学");
        data.add(citys);
        citys=new Citys("郑州航空航天管理学院");
        data.add(citys);
        province=new Province("郑州", data);
        provinces.add(province);

//      武汉市区
        data=new ArrayList<Citys>();
        citys=new Citys("湖北大学");
        data.add(citys);
        citys=new Citys("武汉大学");
        data.add(citys);
        citys=new Citys("华中科技大学");
        data.add(citys);
        citys=new Citys("武汉轻工大学");
        data.add(citys);
        citys=new Citys("武汉科技大学");
        data.add(citys);
        province=new Province("武汉", data);
        provinces.add(province);

//      新乡市区
        data=new ArrayList<Citys>();
        citys=new Citys("新乡学院");
        data.add(citys);
        citys=new Citys("新乡医学院");
        data.add(citys);
        citys=new Citys("河南师范大学");
        data.add(citys);
        province=new Province("新乡", data);
        provinces.add(province);
    }
    //  通过遍历集合获得市的名字 --备用赋值给Spinner1
    private void getPriovince(){
        city=new ArrayList<String>();
        for (Province province : provinces) {
            String cityname = province.getP_name();
            city.add(cityname);
        }

    }

    @Override
    public void onClick(View view) {

    }
    private List<String> getCitys(String c_name){
        List<String> citylist=new ArrayList<String>();
        for (Province province : provinces) {
            if (c_name.equals(province.getP_name())) {
                List<Citys> citys = province.getCitys();
                for (Citys citys2 : citys) {
                    String cityName = citys2.getCity();
                    citylist.add(cityName);
                }
            }

        }

        return citylist;
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mlocal;
        private final String muniversity;
        private final String mpassword;

        UserRegisterTask(String local, String university, String password) {
            mlocal = local;
            muniversity = university;
            mpassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_register = new RegisterSchool(mlocal, muniversity, mpassword).getIs_register();
                System.out.println(is_register+mlocal+muniversity+mpassword);
                if (is_register.equals("1")){
                    return true;
                }

            } catch (Exception e) {
                Log.i("ok", "有错误！");
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                Toast.makeText(Register.this, "注册成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
                finish();
            } else {

                Toast.makeText(Register.this, "高校已注册或注册失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

}
