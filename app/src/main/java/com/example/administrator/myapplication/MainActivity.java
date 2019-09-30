package com.example.administrator.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;

import com.special.ResideMenu.ResideMenuItem;

import org.json.JSONArray;

import data_treating.SelectBySchool;

public class MainActivity extends FragmentActivity implements OnClickListener {

    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;
    private TextView main_bar;
    private int a=0;
    public static String university;
    public static String password;
    public static String select_json;
    public static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 接收LoginActivity传输来的登录用户ID
        Intent i = getIntent();
        university = i.getStringExtra("university");
        password = i.getStringExtra("password");

        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new Announce_Up());

    }

    private void setBar(int a){
        main_bar=findViewById(R.id.main_bar);
        main_bar.setTextColor(Color.WHITE);
        if (a==0){
            main_bar.setText("通知/创业");
        }
        if(a==1){
            main_bar.setText("推送/就业");
        }
        if(a==2){
            main_bar.setText("发布记录");
        }
        if(a==3){
            main_bar.setText("个人设置");
        }


    }
    private void setUpMenu(){

        resideMenu = new ResideMenu(this);

        resideMenu.setBackground(R.drawable.backc);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        resideMenu.setScaleValue(0.5f);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        itemHome = new ResideMenuItem(this,R.drawable.icon_home,"通知/创业");
        itemProfile = new ResideMenuItem(this,R.drawable.icon_profile,"推送/就业");
        itemCalendar = new ResideMenuItem(this,R.drawable.icon_calendar,"发布记录");
        itemSettings = new ResideMenuItem(this,R.drawable.icon_settings,"个人设置");

        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar,ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings,ResideMenu.DIRECTION_LEFT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);

            }
        });
//        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
//
//            }
//        });


    }

    public  boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new Announce_Up());
            setBar(0);
        }else if (view == itemProfile){
            changeFragment(new Massage_Up());
            setBar(1);
        }else if (view == itemCalendar){
            new UpRocardTask(university).execute();

        }else if(view == itemSettings){
            changeFragment(new User_set1());
            setBar(3);
        }

           resideMenu.closeMenu();

    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {

        }

        @Override
        public void closeMenu() {

        }
    };
    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
    public ResideMenu getResideMenu(){
        return resideMenu;
    }

    public class UpRocardTask extends AsyncTask<Void, Void, Boolean> {

        private final String muniversity;

        UpRocardTask(String university) {
            muniversity = university;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                select_json = new SelectBySchool(muniversity).getSelect_json();
                JSONArray jsonArray = new JSONArray(select_json);
                count = jsonArray.length();
                if (count > 0){
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
                Toast.makeText(MainActivity.this, "查询记录成功！",Toast.LENGTH_SHORT).show();
                changeFragment(new Up_rocard());
                setBar(2);
            } else {
                Toast.makeText(MainActivity.this, "暂无推送记录！",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

    /**
     * 使得按下返回就会跟按下Home键操作一致
     * 启动界面在第一次启动会显示出来
     * 按下返回键回到桌面再次进入就不会显示了
     * 除非程序被杀死或退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            Intent i= new Intent(Intent.ACTION_MAIN);  //主启动，不期望接收数据

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);       //新的activity栈中开启，或者已经存在就调到栈前

            i.addCategory(Intent.CATEGORY_HOME);            //添加种类，为设备首次启动显示的页面

            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }
}

