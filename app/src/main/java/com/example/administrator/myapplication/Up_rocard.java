package com.example.administrator.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 浏览发送历史界面
 * Created by Administrator on 6/7/2018.
 */

public class Up_rocard extends Fragment {


    private RecyclerView mRecyclerView;
    private List<Rocard_Datas> mDatas = new ArrayList<>();
    private Racard_recycle_adapter mAdapter;
    private String select_json = MainActivity.select_json;
    private int count = MainActivity.count;
//    private String a[]={"普罗技术（上海）有限公司招聘启事","北京中科锐智电器有限公司招聘启事","职场达人秀，等你来战","广州粤澄环境技术有限公司招聘启事","郑州轻工业大学第三周校园专场招聘会汇总","a"};
//    private String b[]={"2018-04-23","2018-04-22","2018-04-08","2018-04-02","2018-03-21"};

    private String a[] = new String[count];
    private String b[] = new String[count];
    private String c[] = new String[count];
    private String d[] = new String[count];
    private String e[] = new String[count];


    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.up_record,null);

        setView(view);

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.a));
        mRecyclerView.addItemDecoration(divider);
        return view;
    }

    public void setView(View view){

        setmDatas();

        mRecyclerView = view.findViewById(R.id.id_recycleview);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new Racard_recycle_adapter(getActivity(),mDatas);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);



            }
        });

        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new RecycleItemTouchHelper(mAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);

        itemTouchHelper.attachToRecyclerView(mRecyclerView);



        //监听
        mAdapter.setmOnItemClickListener(new Racard_recycle_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),Up_rocard_massage.class);
                intent.putExtra("title",a[position]);
                intent.putExtra("sendtime",b[position]);
                intent.putExtra("body",d[position]);
                intent.putExtra("room",e[position]);
                startActivity(intent);
            }
        });


    }

    public void setmDatas(){

        try {
            JSONArray jsonArray = new JSONArray(select_json);
            for (int i = 0; i < count; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                a[i] = jsonObject.getString("title");
                b[i] = jsonObject.getString("sendtime");
                c[i] = jsonObject.getString("mark");
                d[i] = jsonObject.getString("body");
                e[i] = jsonObject.getString("room");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0;i<count;i++){
            Rocard_Datas A = new Rocard_Datas(a[i],b[i],c[i]);
            mDatas.add(A);
        }


    }

}
