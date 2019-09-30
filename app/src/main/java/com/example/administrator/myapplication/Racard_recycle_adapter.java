package com.example.administrator.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * 浏览历史界面Recycleview适配器
 *
 * Created by Administrator on 6/7/2018.
 */

public class Racard_recycle_adapter  extends RecyclerView.Adapter<Racard_recycle_adapter.MyviewHolder> implements RecycleItemTouchHelper.ItemTouchHelperCallback{

    private LayoutInflater mlayout;
    private Context mContext;
    private List<Rocard_Datas> mDatas;
    private MyItemClickListener mItemClickListener;

   public interface OnItemClickListener{

       void onItemClick(View view,int postion);
   }

   private OnItemClickListener mOnItemClickListener;

   public void setmOnItemClickListener(OnItemClickListener listener){
       this.mOnItemClickListener = listener;
   }


    public Racard_recycle_adapter(Context context,List<Rocard_Datas> mDatas){

        mContext=context;
        this.mDatas = mDatas;
        mlayout = LayoutInflater.from(context);

    }




    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview,parent,false);
        MyviewHolder holder = new MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, final int position) {

        Rocard_Datas datas = mDatas.get(position);

        holder.title.setText(datas.getTitle());
        holder.time.setText(datas.getTime());
        holder.positon.setText(datas.getPostion());

        if(mOnItemClickListener!=null){

            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount()  {

        return mDatas.size();
    }

    @Override
    public void onItemDelete(int postion) {

        mDatas.remove(postion);

        notifyItemRemoved(postion);


    }

    @Override
    public void onMove(int fromPostion, int toPosition) {

        Collections.swap(mDatas,fromPostion,toPosition);
        notifyItemMoved(fromPostion,toPosition);

    }

    class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MyItemClickListener mListener;
        TextView title;
        TextView time;
        TextView positon;

        public  MyviewHolder(View view ){

            super(view);

            title = view.findViewById(R.id.announce_title);
            time  = view.findViewById(R.id.announce_time);
            positon = view.findViewById(R.id.announce_postion);

            this.mListener=mItemClickListener;


        }

        public  void onClick(View v){
            if(mListener!=null){
                mListener.onItemClick(v,getPosition());
            }
        }


    }
    public interface  MyItemClickListener{
        void onItemClick(View view , int position);
    }

    public void setmItemClickListener(MyItemClickListener myItemClickListener){
        this.mItemClickListener=myItemClickListener;
    }


}
