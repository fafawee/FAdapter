# FAdapter
 
 这是一个同时适配ListView还有RecycleView 的Adapter的库
 
## 如何使用
```
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
```
```
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

```
1. onConvert 必须重写
2. getLayoutRes 传入layoutres，可以不重写 在构造方法中传入
3. getMulLayoutRes 多类型的item 布局时候需要重写
4. getContentView 通过代码实例化View的方式 则需要重写
5. FListViewAdapter和FRecycleViewAdapter的api方式一致，FListViewAdapter针对ListView，FRecycleViewAdapter针对RecycleView
## 依赖方式
```
implementation 'com.github.fafawee:FAdapter:1.0.3'
 
```
需要jitpack仓库的支持
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```
## 联系方式
1. qq:597536434
2. github:https://github.com/fafawee/

