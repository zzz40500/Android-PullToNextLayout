
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android--PullToNextLayout-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1893)
---


![webView.gif](http://upload-images.jianshu.io/upload_images/166866-c9840dfc44752550.gif)

![ScrollView.gif](http://upload-images.jianshu.io/upload_images/166866-2609a7f9c0d8f740.gif)

![other.gif](http://upload-images.jianshu.io/upload_images/166866-3a9ed8d196dad787.gif)



[github](https://github.com/zzz40500/Android-PullToNextLayout)
##更新至1.3.1##
#usage:#

android studio 引用:
~~~
  compile 'com.mingle.zzz40500:pulltonextlayout:1.3.1'
~~~


activity 的布局
~~~
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <com.mingle.pulltonextlayout.PullToNextLayout
        android:id="@+id/pullToNextLayout"
        app:underLayoutColor="@color/bg"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"/>

</RelativeLayout>


~~~

简单说明 :  布局中只有一个控件`PullToNextLayout`, 控件中不包含其他组件

java 代码中引用使用:
~~~
 pullToNextLayout= (PullToNextLayout) findViewById(R.id.pullToNextLayout);


        list=new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            list.add(new ScrollViewModel(currentIndex++));
        }

        adapter=  new PullToNextModelAdapter(this,list);
        pullToNextLayout.setAdapter(adapter);

        pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onSelectItem(int position, View view) {
                setTitle(position+1+".0 谷歌仍是毕业生心目中的最佳雇主");
            }
        });
~~~






ScrollViewModel  
主要方法如下:
~~~
@Override
    public void onCreate(Context context) {
        super.onCreate(context);
        Log.e(TAG,"onCreate"+"   "+index);
    }

    /**
     * 返回视图
     * @return
     */
    @Override
    public int getLayoutViewId() {
        return R.layout.fragment_scrollview;
    }
    /**
     * 绑定数据源
     * @param v
     */
    @Override
    public void onBindView(int position, View v, PullToNextView pullToNextView) {

        PromptEntity p=new PromptEntity();

        TextView titleTV= (TextView) v.findViewById(R.id.titleTV);
        TextView contentTv= (TextView) v.findViewById(R.id.textView);
        scrollView = (ScrollView) v.findViewById(R.id.scrollView);
        titleTV.setText(index+1+".0"+title);
        contentTv.setText(content);
        Log.e(TAG, "onBindView"+"   "+index);
    }




    /**
     * 在onBindView 调用后调用.
     * @param view
     */
    @Override
    public void onResumeView(int position, View view, PullToNextView pullToNextView) {
        super.onResumeView(position, view, pullToNextView);

        if(scrollView!=null){
            scrollView.pageScroll(ScrollView.FOCUS_UP);
        }
        Log.e(TAG, "onResumeView"+"   "+index);
    }


    /**
     * 解绑数据
     * @param view
     */

    @Override
    public void onUnBindView(int position, View view, PullToNextView pullToNextView) {
        super.onUnBindView(position,view,pullToNextView);
        Log.e(TAG, "onUnBindView"+"   "+index);
    }
    @Override
    public void onPauseView(int position, View view, PullToNextView pullToNextView) {
        super.onPauseView(position,view,pullToNextView);
        Log.e(TAG, "onPauseView"+"   "+index);
    }
    @Override
    public void setUserVisibleHint(boolean userVisibleHint) {
        super.setUserVisibleHint(userVisibleHint);

        if(userVisibleHint){
        }
        Log.e(TAG, "userVisibleHint" + userVisibleHint+"   " + index);
    }
~~~

1.3.1 更新
>1. 修改了onBindView方法暴露出PullToNextView 和 position 字段
* 支持动态修改提示提示内容. 

1.3.0 更新
>1. 重构了部分代码.Deprecated 了PullToNextFragmentAdapter,加入了PullToNextModelAdapter,新的适配器支持了相同视图的复用.
* 向下支持 sdk9 ;
* 加入了PullToNextModel 类,视图配置类


1.2.1 内容:
>1. 控件支持ScrollView ,WebView 组件控件内滚动;
* 支持gradle 方式加入;
* 支持预加载,逻辑类似ViewPager,即预先加载当前页的前一个和下一个;



Q.怎么自定义下拉和上拉的文字提示:
~~~
A.
静态(资源文件):
res->values->strings.xml
添加6个,app会优先读取你项目中的资源文件.
    <string name="ptn_no_more_data">没有更多信息～</string>
    <string name="ptn_release_to_next">放手查看下一个～</string>
    <string name="ptn_pull_up_to_next">上拉查看下一个～</string>
    <string name="ptn_the_first">已经是第一个了～</string>
    <string name="ptn_release_to_previous">放手查看前一个～</string>
    <string name="ptn_pull_down_to_previous">下拉查看前一个～</string>
修改成你自己喜欢的文字
动态(java 代码)
   pullToNextView.setPromptEntity();
~~~








 

#end#
