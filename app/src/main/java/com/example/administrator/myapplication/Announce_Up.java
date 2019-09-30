package com.example.administrator.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;

import data_treating.Add;

/**
 *  发布通知界面
 * Created by Administrator on 6/3/2018.
 */

public class Announce_Up extends Fragment implements View.OnClickListener{

    private EditText iEditText_office;
    private EditText iEditText_title;
    private EditText iEditText_time;
    private EditText iEditText_conter;
    private Button up;
    private String title="title";
    private String time="time";
    private String conter="conter";
    private String office="office";
    private String university;
    private String mark = "通知";
    private View view;
    private TextView bar;
    private String sendtime;
    private String theDate;
    private String theDateTime;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fargement_1,null);

        setView(view);
        onClick(view);

        return view;
    }

    /*
    初始化视图结构
    系统获取和设置发布日期
     */
    public void setView(View view){

        iEditText_conter=view.findViewById(R.id.announce_conter);
        iEditText_conter.setSelection(0);

        iEditText_office=view.findViewById(R.id.announce_office_deitText);

        iEditText_title=view.findViewById(R.id.announce_title_editText);

        iEditText_time=view.findViewById(R.id.announce_time_deitText);

        up=view.findViewById(R.id.announce_up);

        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);

        final int month = calendar.get(Calendar.MONTH);

        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        sendtime = String.format("%d-%02d-%02d",year,month+1,day);

        iEditText_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        theDateTime = theDate + " " + String.format("%02d:%02d",hourOfDay,minute);
                        iEditText_time.setText(theDateTime);
                    }
                },0,0,true).show();
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        theDate = String.format("%d-%02d-%02d",year,month+1,dayOfMonth);
//                        iEditText_time.setText(theDate);
                    }
                },year,month,day).show();
            }
        });

        up.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id  = view.getId();
        view=this.getView();
        switch (id) {
            case R.id.announce_up:
                iEditText_title = view.findViewById(R.id.announce_title_editText);
                iEditText_time = view.findViewById(R.id.announce_time_deitText);
                iEditText_conter = view.findViewById(R.id.announce_conter);
                iEditText_office = view.findViewById(R.id.announce_office_deitText);
                title = iEditText_title.getText().toString();
                time = iEditText_time.getText().toString();
                conter = iEditText_conter.getText().toString();
                office = iEditText_office.getText().toString();
                university = MainActivity.university;
                if(title.equals("")&&conter.equals("")&&office.equals("")){
                    Toast.makeText(getContext(), "请输入所有信息！",Toast.LENGTH_SHORT).show();
                }
                else{
                    new AddThingsTask(title, time, conter, office, university, mark, sendtime).execute();
                }

                 break;
            default:
                break;
        }
    }

    public class AddThingsTask extends AsyncTask<Void, Void, Boolean> {

        private final String mtitle;
        private final String mtime;
        private final String mconter;
        private final String moffice;
        private final String muniversity;
        private final String mmark;
        private final String msendtime;

        AddThingsTask(String title, String time , String conter, String office, String university, String mark, String sendtime) {
            mtitle = title;
            mtime = time;
            mconter = conter;
            moffice = office;
            muniversity = university;
            mmark = mark;
            msendtime = sendtime;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                String is_add = new Add(mtitle, mtime, mconter, moffice, muniversity, mmark, msendtime).getIs_add();
                if (is_add.equals("1")){
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
                Toast.makeText(getContext(), "发布成功！",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(School_news.this,User_school.class);
//                startActivity(intent);
//                finish();
                // 提交成功后清空文本框
                iEditText_title = view.findViewById(R.id.announce_title_editText);
                iEditText_conter = view.findViewById(R.id.announce_conter);
                iEditText_office = view.findViewById(R.id.announce_office_deitText);
                iEditText_time = view.findViewById(R.id.announce_time_deitText);
                iEditText_title.setText("");
                iEditText_conter.setText("");
                iEditText_office.setText("");
                iEditText_time.setText("");
            } else {
                Toast.makeText(getContext(), "发布失败！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
