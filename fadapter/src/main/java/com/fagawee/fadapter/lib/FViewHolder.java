package com.fagawee.fadapter.lib;

import android.annotation.IdRes;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.Tian on 2020/1/6.
 */

public class FViewHolder {




    private View root;



    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public View getRoot() {
        return root;
    }

    public void setRoot(View root) {
        this.root = root;
    }

    public <T> T getView(@IdRes int idres)
    {
        if(root==null)
            throw new RuntimeException("layoutType 和 Layout 不匹配");
        View view=root.findViewById(idres);
        return (T)view;
    }

}
