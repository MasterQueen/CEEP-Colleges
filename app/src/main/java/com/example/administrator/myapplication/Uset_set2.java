package com.example.administrator.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data_treating.DeleteSchool;

/**
 * Created by Administrator on 7/17/2018.
 */

public class Uset_set2 extends AppCompatActivity implements View.OnClickListener{

    private TextView school_name;
    private Button logout;
    private Button change_password;
    private String university;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_2);

        school_name = findViewById(R.id.school_name);
        logout = findViewById(R.id.logout);
        change_password = findViewById(R.id.change_password);

        school_name.setText(MainActivity.university);

        //获取登陆高校名称，并赋值给school_name






        //按钮监听
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new  AlertDialog.Builder(Uset_set2.this)
                             .setTitle("注销用户")
                             .setMessage("注销将删除与用户有关的所有内容，是否继续？")
                             .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i) {
                                     //选择确定注销，，删除所有信息并返回登陆界面
                                     university = MainActivity.university;
                                     new UserDeleteTask(university).execute();

                                 }
                             })
                            .setNegativeButton("否", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               //取消注销，返回


                                }
                           })
                           .show();

            }
        });

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Uset_set2.this,User_change_password.class);
                startActivity(intent);



            }
        });

    }

    @Override
    public void onClick(View view) {

    }

    public class UserDeleteTask extends AsyncTask<Void, Void, Boolean> {

        private final String muniversity;

        UserDeleteTask(String university) {
            muniversity = university;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_delete = new DeleteSchool(muniversity).getIs_delete();
                if (is_delete.equals("1")){
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
                Toast.makeText(Uset_set2.this, "注销成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Uset_set2.this,Login.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Uset_set2.this, "注销失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

}
