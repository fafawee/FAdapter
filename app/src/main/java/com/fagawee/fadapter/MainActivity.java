package com.fagawee.fadapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.fagawee.fadapter.lib.FListViewAdapter;
import com.fagawee.fadapter.lib.FRecycleViewAdapter;
import com.fagawee.fadapter.lib.FRecycleViewHolder;
import com.fagawee.fadapter.lib.FViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listView;
    private RecyclerView recycle;
    private FListViewAdapter<FModel> adapter;
    private FRecycleViewAdapter<FModel> recycleViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        recycle=findViewById(R.id.recycle);

        adapter=new FListViewAdapter<FModel>() {
            @Override
            public void onConvert(FModel model, FViewHolder viewHolder, ViewGroup parent) {


                  TextView title=viewHolder.getView(R.id.title);
                 TextView content=viewHolder.getView(R.id.content);

                 title.setText(model.name);
                 content.setText(model.age+"  "+viewHolder.getPosition());

                 if(model.type==0)
                 {

                 }
                 else {
                 TextView content2=viewHolder.getView(R.id.content);
                 content2.setText(model.age+"..."+viewHolder.getPosition());
                 }

            }



            @Override
            public int getLayoutRes() {
                return R.layout.list_item;
            }

            @Override
            public int getMulLayoutRes(FModel fModel) {

                if(fModel.type==0)
                {
                    return R.layout.list_item;
                }
                else {
                    return R.layout.list_item2;
                }

            }


        };

        listView.setAdapter(adapter);


        recycleViewAdapter=new FRecycleViewAdapter<FModel>(R.layout.list_item) {
            @Override
            public void onConvert(FModel data, FRecycleViewHolder viewHolder) {


                TextView title=viewHolder.getView(R.id.title);
                TextView content=viewHolder.getView(R.id.content);

                title.setText(data.name);
                content.setText(data.age+"  "+viewHolder.getLayoutPosition());

                if(data.type==0)
                {

                }
                else {
                    //TextView content2=viewHolder.getView(R.id.content);
                   // content2.setText(data.age+"..."+viewHolder.getLayoutPosition());
                }

            }

            /*
            @Override
            public View getContentView(FModel fModel, int position) {
                TextView textView=new TextView(MainActivity.this);
                textView.setText("position"+position);
                return textView;

            }
            */
            /*
            @Override
            public int getLayoutRes() {
                return R.layout.list_item;
            }

            @Override
            public int getMulLayoutRes(FModel fModel) {

                if(fModel.type==0)
                {
                    return R.layout.list_item;
                }
                else {
                    return R.layout.list_item2;
                }

            }
            */

        };
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recycle.setLayoutManager(linearLayoutManager);
        recycle.setAdapter(recycleViewAdapter);

        List<FModel> modelList=new ArrayList<>();
        for (int i = 0; i < 55; i++) {
            FModel fModel=new FModel();
            fModel.age=12;
            fModel.name="name"+i;
            if(i%2==0)
            {
                fModel.type=0;
            }
            else
            {
                fModel.type=1;
            }
            modelList.add(fModel);
        }
        adapter.setNewData(modelList);
        recycleViewAdapter.setNewData(modelList);

    }
}
