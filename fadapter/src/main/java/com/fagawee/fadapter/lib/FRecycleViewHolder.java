package com.fagawee.fadapter.lib;

import android.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Mr.Tian on 2020/1/7.
 */

public class FRecycleViewHolder extends RecyclerView.ViewHolder {
    public boolean isSelfView=false;
    public FRecycleViewHolder(View itemView) {
        super(itemView);
    }



    public <T> T getView(@IdRes int idres)
    {
        if(itemView==null)
            throw new RuntimeException("layoutType 和 Layout 不匹配");
        View view=itemView.findViewById(idres);
        return (T)view;
    }


}
