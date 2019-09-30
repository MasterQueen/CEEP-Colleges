//package com.example.administrator.myapplication;
//
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import data_treating.Add;
//
//public class School_news extends AppCompatActivity {
//
//
//    private Button sendSchoolNews;
//    private EditText announce_title;
//    private EditText announce_time;
//    private EditText announce_conter;
//    private EditText announce_office;
//    private String title;
//    private String time;
//    private String conter;
//    private String office;
//    private String university;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fargement_1);
//        initData();
//        initView();
//    }
//
//    private void initData() {
//
//        sendSchoolNews = (Button)findViewById(R.id.announce_up);
//    }
//
//    private void initView() {
//        sendSchoolNews.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View view) {
//                announce_title = (EditText)findViewById(R.id.announce_title_editText);
//                announce_time = (EditText)findViewById(R.id.announce_time_deitText);
//                announce_conter = (EditText)findViewById(R.id.announce_conter);
//                announce_office = (EditText)findViewById(R.id.announce_office_deitText);
//                title = announce_title.getText().toString();
//                time = announce_time.getText().toString();
//                conter = announce_conter.getText().toString();
//                office = announce_office.getText().toString();
//                university = MainActivity.university;
//                new AddThingsTask(title, time, conter, office, university).execute();
//            }
//        });
//    }
//
//    public class AddThingsTask extends AsyncTask<Void, Void, Boolean> {
//
//        private final String mtitle;
//        private final String mtime;
//        private final String mconter;
//        private final String moffice;
//        private final String muniversity;
//
//        AddThingsTask(String title, String time , String conter, String office, String university) {
//            mtitle = title;
//            mtime = time;
//            mconter = conter;
//            moffice = office;
//            muniversity = university;
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//
//            try {
//                String is_add = new Add(mtitle, mtime, mconter, moffice, muniversity).getIs_add();
//                if (is_add.equals("1")){
//                    return true;
//                }
//
//            } catch (Exception e) {
//                Log.i("ok", "有错误！");
//            }
//
//            return false;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//
//            if (success) {
//                Toast.makeText(School_news.this, "发布成功！",Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(School_news.this,User_school.class);
////                startActivity(intent);
////                finish();
//            } else {
//                Toast.makeText(School_news.this, "发布失败！",Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//
//        }
//    }
//}
