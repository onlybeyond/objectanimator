package com.onlybeyond.objectanimator.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.onlybeyond.objectanimator.R;
import com.onlybeyond.objectanimator.ui.utils.UiUtils;

import java.util.List;

/**
 * Created by only on 16/8/20.
 * Email: onlybeyond99@gmail.com
 */
public class ImageAdapter extends RecyclerView.Adapter<SelfRecycleViewHolder> {
    private Context mContext;
    private List<Integer> mResourceList;
    private SelfItemClickListener mSelfItemClickListener;

    public interface SelfItemClickListener {
        void selfItemClick(int position);
    }

    public ImageAdapter(Context context, List<Integer> intList, SelfItemClickListener selfItemClickListener) {
        this.mContext = context;
        this.mResourceList = intList;
        this.mSelfItemClickListener = selfItemClickListener;
    }


    @Override
    public SelfRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        return new SelfRecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SelfRecycleViewHolder holder, final int position) {
        ImageView imageView = (ImageView) holder.getView(R.id.iv_img);
        int resource = mResourceList.get(position);
        imageView.setImageResource(resource);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelfItemClickListener.selfItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResourceList.size();
    }
}
