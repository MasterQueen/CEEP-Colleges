package com.example.administrator.myapplication;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * 发送历史界面Recycleview滑动删除适配
 * Created by Administrator on 6/8/2018.
 */

public class RecycleItemTouchHelper extends ItemTouchHelper.Callback {

    private static final  String TAG = "RecycleItemRhuchHelper";
    private final ItemTouchHelperCallback helperCallback;

    public RecycleItemTouchHelper( ItemTouchHelperCallback helperCallback1) {

        this.helperCallback = helperCallback1;
    }

    /**
     * 设置长按操作
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * 设置滑动操作
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 设置允许的滑动方向
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.e(TAG,"getMovementFlags");
        return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT);
    }

    /**
     * 切换Item回调
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.e(TAG,"onMove");
        helperCallback.onMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.e(TAG,"onSwiped");

        helperCallback.onItemDelete(viewHolder.getAdapterPosition());

    }


    /**
     * Item 选中后回调
     * @param viewHolder
     * @param actionState
     */
    public void onSelectedChange(RecyclerView.ViewHolder viewHolder,int actionState){
        super.onSelectedChanged(viewHolder,actionState);
    }




    public interface  ItemTouchHelperCallback{
        void  onItemDelete(int postion);
        void onMove(int fromPostion,int toPosition);
    }
}
