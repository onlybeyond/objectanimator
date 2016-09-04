package com.onlybeyond.objectanimator.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: only beyond [FR]
 * Date: 2015/9/12
 * Email: wenzhi_bai@163.com
 */
public class SelfRecycleViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> viewMap = new SparseArray<View>();


    private View itemView;
    public SelfRecycleViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;

    }
    public View getView(int id){
        View   ret=null;
        View view = viewMap.get(id);
        if(view==null){
            View viewById = itemView.findViewById(id);
            viewMap.put(id,viewById);
           ret=viewById;
        }else {
            ret=view;
        }
        return ret;
    }
}
