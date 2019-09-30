package com.example.administrator.myapplication;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data_treating.DeleteMessage;


/**
 * Created by Administrator on 7/16/2018.
 */

public class Up_rocard_massage extends AppCompatActivity {

    TextView title;
    TextView title_conter;
    TextView time;
    TextView time_conter;
    TextView office;
    TextView office_conter;
    TextView conter;
    TextView conter_conter;
    Button delect;
    String mtitle;
    String mtime;
    String moffice;
    String mconter;
    String university;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up_rocard_massage);

        android.support.v7.widget.Toolbar toolbar =findViewById(R.id.up_rocard_toolbar);
        toolbar.setTitle("详细内容");
        toolbar.setTitleTextColor(Color.WHITE);

        Intent i = getIntent();
        mtitle = i.getStringExtra("title");
        moffice = i.getStringExtra("room");
        mconter = i.getStringExtra("body");
        mtime = i.getStringExtra("sendtime");

        title=findViewById(R.id.title);
        title_conter=findViewById(R.id.title_conter);
        time = findViewById(R.id.time);
        time_conter = findViewById(R.id.time_conter);
        office = findViewById(R.id.office);
        office_conter=findViewById(R.id.office_conter);
        conter = findViewById(R.id.conter);
        conter_conter = findViewById(R.id.conter_conter);
        delect = findViewById(R.id.delect);


        title_conter.setText(mtitle);
        time_conter.setText(mtime);
        office_conter.setText(moffice);
        conter_conter.setText(mconter);

        delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                university = MainActivity.university;
                new MessageDeleteTask(university, mtitle, mtime, moffice).execute();
            }
        });

    }

    public class MessageDeleteTask extends AsyncTask<Void, Void, Boolean> {

        private final String muniversity;
        private final String mtitle;
        private final String msendtime;
        private final String mroom;

        MessageDeleteTask(String university, String title, String sendtime, String room) {
            muniversity = university;
            mtitle = title;
            msendtime = sendtime;
            mroom = room;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_delete = new DeleteMessage(muniversity, mtitle, msendtime, mroom).getIs_delete();
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
                Toast.makeText(Up_rocard_massage.this, "删除成功！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Up_rocard_massage.this,MainActivity.class);
                intent.putExtra("university", MainActivity.university);
                intent.putExtra("password", MainActivity.password);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Up_rocard_massage.this, "删除失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

}
