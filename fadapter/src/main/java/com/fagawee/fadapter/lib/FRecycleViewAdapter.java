package com.fagawee.fadapter.lib;

import android.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Tian on 2020/1/7.
 */

public abstract class FRecycleViewAdapter<T> extends RecyclerView.Adapter<FRecycleViewHolder> {

    private List<T> mDataList=new ArrayList<>();
    private LayoutInflater layoutInflater;
    private int mLayoutRes=Constant.Error_Layout;

    public void setNewData(List<T> mDataList)
    {
        this.mDataList=mDataList;
        notifyDataSetChanged();
    }
    public void addData(List<T> mDataList)
    {
        this.mDataList.addAll(mDataList);
        notifyDataSetChanged();
    }


    public FRecycleViewAdapter() {
    }

    public FRecycleViewAdapter(int mLayoutRes) {
        this.mLayoutRes = mLayoutRes;
    }

    public FRecycleViewAdapter(List<T> mDataList, int mLayoutRes) {
        this.mDataList = mDataList;
        this.mLayoutRes = mLayoutRes;

    }

    @Override
    public FRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(layoutInflater==null)
        {
            layoutInflater=LayoutInflater.from(parent.getContext());
        }
        FRecycleViewHolder fRecycleViewHolder;
        if(viewType>0)
        {
            View view =layoutInflater.inflate(viewType,null,false);
            fRecycleViewHolder=new FRecycleViewHolder(view);
            fRecycleViewHolder.isSelfView=false;
        }
        else
        {
            int position=-viewType;
            T t=mDataList.get(position);
            View subContentView=getContentView(t,position);
            fRecycleViewHolder=new FRecycleViewHolder(subContentView);
            fRecycleViewHolder.isSelfView=true;
        }

        return fRecycleViewHolder;
    }

    @Override
    public int getItemViewType(int position) {

        int layRes=Constant.Error_Layout;
        T t=mDataList.get(position);

        int mullayoutType=getMulLayoutRes(t);
        int defaultLayRes=getLayoutRes();
        View subContentView=getContentView(t,position);


        if(defaultLayRes!=Constant.Error_Layout||mullayoutType!=Constant.Error_Layout||mLayoutRes!=Constant.Error_Layout)
        {


            if(mullayoutType!=Constant.Error_Layout)
            {
                layRes=mullayoutType;

            }
            else if(defaultLayRes!=Constant.Error_Layout){
                layRes=defaultLayRes;

            }

            else if(mLayoutRes!=Constant.Error_Layout)
            {
                layRes=mLayoutRes;
            }

        }
        else if(subContentView!=null)
        {
            layRes=-position;
        }

        return layRes;




    }

    @Override
    public void onBindViewHolder(FRecycleViewHolder holder, int position) {

            T t=mDataList.get(position);

            if(!holder.isSelfView)
            {
                onConvert(t,holder);
            }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }




    int getLayoutRes()
    {
        return Constant.Error_Layout;
    }
    public  int getMulLayoutRes(T t)
    {
        return Constant.Error_Layout;
    }
    public View getContentView(T t, int position)
    {
        return null;
    }
    public abstract void onConvert(T data,FRecycleViewHolder viewHolder);



}
