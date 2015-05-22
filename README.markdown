 #效果图先行:#



![webView.gif](http://upload-images.jianshu.io/upload_images/166866-c9840dfc44752550.gif)

![ScrollView.gif](http://upload-images.jianshu.io/upload_images/166866-2609a7f9c0d8f740.gif)

![other.gif](http://upload-images.jianshu.io/upload_images/166866-3a9ed8d196dad787.gif)


[github 地址直达](https://github.com/zzz40500/Android-PullToNextLayout)
##更新至1.2.1##
#usage:#

android studio 引用:
~~~
  compile 'com.mingle.zzz40500:pulltonextlayout:1.2.1'
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
        list=new ArrayList<Fragment>();
       
        list.add(new ScrollViewFragment(0));
        list.add(new ScrollViewFragment(1));
        list.add(new ScrollViewFragment(2));
        list.add(new ScrollViewFragment(3));
        list.add(new ScrollViewFragment(4));
 
//设置适配器
        pullToNextLayout.setAdapter(new PullToNextAdapter(getSupportFragmentManager(), list));
//设置切换监听
       pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onSelectItem(int position, View view) {


                setTitle(position+1+".0 谷歌仍是毕业生心目中的最佳雇主");
            }
        });
        setTitle(1+".0 谷歌仍是毕业生心目中的最佳雇主");
~~~

主要方法:
`setAdapter(PullToNextAdapter pullToNextAdapter)` 主要设置数据源适配器.
`setAdapter(PullToNextAdapter pullToNextAdapter,int  currentIndex)` 设置数据源适配器.并且指定第一个显示的位置.
`setOnItemSelectListener(OnItemSelectListener onItemSelectListener)` 设置选中回调.



DemoFragment 中的主要布局:
~~~
<?xml version="1.0" encoding="utf-8"?>
<ScrollView
        xmlns:android="http://schemas.android.com/apk/res/android"

    android:background="#ffffff"
    android:id="@+id/scrollView"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent">
        <LinearLayout
            android:id="@+id/mainLL"
            android:layout_width="fill_parent"
            android:orientation="vertical"
            android:layout_height="fill_parent">

    <TextView
        android:id="@+id/titleTV"
        android:layout_width="fill_parent"
        android:padding="4dp"
        android:layout_marginBottom="12dp"
        android:layout_marginTop="12dp"
        android:textSize="20sp"
        android:gravity="center"
        android:layout_height="wrap_content" />
    <TextView
        android:id="@+id/textView"
        android:layout_width="fill_parent"
        android:textSize="16sp"
        android:textColor="#444444"
        android:gravity="center"
        android:padding="4dp"
        android:layout_height="wrap_content" />
            <View
                android:layout_width="fill_parent"
                android:layout_marginTop="30dp"
                android:paddingLeft="4dp"
                android:paddingRight="4dp"
                android:layout_height="1px"/>
        </LinearLayout>

    </ScrollView>

~~~


DemoFragment  以一个 ScrollView 为根控件.也可以使用其他布局为跟控件.


1.2.1 内容:
>1. 控件支持ScrollView ,WebView 组件控件内滚动;
* 支持gradle 方式加入;
* 支持预加载,逻辑类似ViewPager,即预先加载当前页的前一个和下一个;



PullToNextAdapter 方法:


>1. `notifyDataSetChanged()` :通知数据源有变,会更新所有的Fragments ,当前界面会重新走一遍生命周期.
* `addItem(Fragment f)`: 添加一个Fragment 到数据源的末端,当前界面不会重新走一遍生命周期.
* `addAllItem(List<Fragment > list)`:添加Fragment列表到数据源的末端,当前界面不会重新走一遍生命周期

Q.怎么自定义下拉和上拉的文字提示:
~~~
A.
res->values->strings.xml
添加6个,app会优先读取你项目中的资源文件.
    <string name="ptn_no_more_data">没有更多信息～</string>
    <string name="ptn_release_to_next">放手查看下一个～</string>
    <string name="ptn_pull_up_to_next">上拉查看下一个～</string>
    <string name="ptn_the_first">已经是第一个了～</string>
    <string name="ptn_release_to_previous">放手查看前一个～</string>
    <string name="ptn_pull_down_to_previous">下拉查看前一个～</string>
修改成你自己喜欢的文字
~~~







 

#end#
