package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.myapplication.MainActivity;
/**
 * Created by Administrator on 7/17/2018.
 */

public class User_set1 extends Fragment implements View.OnClickListener{

    private EditText shcool_name_edit;
    private EditText password_edit;
    private Button ok;
    private Button change;
    private String school_name;
    private String password;
    private int e=0;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.set_1, null);



        ok = view.findViewById(R.id.set_ok);
        change = view.findViewById(R.id.set_change);
        shcool_name_edit = view.findViewById(R.id.set_school_edit);
        shcool_name_edit.setText(MainActivity.university);
        password_edit = view.findViewById(R.id.set_password_edit);


        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(shcool_name_edit.getText().toString().equals(MainActivity.university)){

                    if (password_edit.getText().toString().equals(MainActivity.password)){

                        Intent intent = new Intent(getContext(),Uset_set2.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(getContext(),"密码输入有误",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(),"账号输入有误",Toast.LENGTH_SHORT).show();
                }

            }
        });

        change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Login.class);
                startActivity(intent);
            }
        });
        return view;
    }




    @Override
    public void onClick(View view) {

    }
}
