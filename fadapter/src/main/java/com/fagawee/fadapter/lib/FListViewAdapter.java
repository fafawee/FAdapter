package com.fagawee.fadapter.lib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mr.Tian on 2020/1/6.
 */

public abstract class FListViewAdapter<T> extends BaseAdapter{



    private List<T> mDataList=new ArrayList<>();
    private LayoutInflater layoutInflater;
    private int mLayoutRes=Constant.Error_Layout;
    private int tagKey=335511320+1;
    private HashMap<Integer,Integer> tagKeyMap=new HashMap<>();

    public FListViewAdapter(List<T> mDataList, int layoutRes) {
        this.mDataList = mDataList;
        this.mLayoutRes=layoutRes;


    }

    public FListViewAdapter(int layoutRes) {
        this.mLayoutRes=layoutRes;
    }

    public FListViewAdapter() {
    }

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





    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FViewHolder holder=null;

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
        if(convertView==null)
        {

            if(layoutInflater==null)
            {
                layoutInflater=LayoutInflater.from(parent.getContext());
            }
            CreateHolder createHolder = new CreateHolder(convertView, layRes, subContentView).invoke();
            convertView = createHolder.getConvertView();
            holder = createHolder.getHolder();

        }
        else
        {

            holder=(FViewHolder)convertView.getTag(getTagKey(layRes));


        }
        if(holder==null)
        {

            CreateHolder createHolder = new CreateHolder(convertView, layRes, subContentView).invoke();
            convertView = createHolder.getConvertView();
            holder = createHolder.getHolder();
        }


        holder.setPosition(position);

        onConvert(t,holder,parent);

        return convertView;
    }

    public  int getLayoutRes()
    {
        return Constant.Error_Layout;
    }
    public   int getMulLayoutRes(T t)
    {
        return Constant.Error_Layout;
    }
    public View getContentView(T t,int position)
    {
        return null;
    }
    public abstract void onConvert(T data,FViewHolder viewHolder,ViewGroup parent);



    public int getTagKey(int layoutRes)
    {
        if(!tagKeyMap.containsKey(layoutRes))
        {
            tagKey++;
            tagKeyMap.put(layoutRes,tagKey);
        }


        return tagKeyMap.get(layoutRes);
    }

    private class CreateHolder {
        private View convertView;
        private int layRes;
        private View subContentView;
        private FViewHolder holder;

        public CreateHolder(View convertView, int layRes, View subContentView) {
            this.convertView = convertView;
            this.layRes = layRes;
            this.subContentView = subContentView;
        }

        public View getConvertView() {
            return convertView;
        }

        public FViewHolder getHolder() {
            return holder;
        }

        public CreateHolder invoke() {
            holder=new FViewHolder();
            if(layRes!=Constant.Error_Layout)
            {
                View view=layoutInflater.inflate(layRes,null,false);

                if(view!=null)
                {
                    convertView=view;

                    holder.setRoot(convertView);
                    convertView.setTag(getTagKey(layRes),holder);
                }
            }
            else if(subContentView!=null)
            {
                //holder=new FViewHolder();
                convertView=subContentView;
               // holder.setRoot(convertView);
               // Log.i("tian","setTag2:"+getTagKey(layRes));
                //convertView.setTag(getTagKey(layRes),holder);
            }
            return this;
        }
    }
}
