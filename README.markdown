 #效果图先行:#
好像大家挺喜欢这个控件的样子!!


![webView.gif](http://upload-images.jianshu.io/upload_images/166866-c9840dfc44752550.gif)

![ScrollView.gif](http://upload-images.jianshu.io/upload_images/166866-2609a7f9c0d8f740.gif)

![other.gif](http://upload-images.jianshu.io/upload_images/166866-3a9ed8d196dad787.gif)


[github 地址直达](https://github.com/zzz40500/Android-PullToNextLayout)

#usage:#


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

1.0 内容:
>1. 支持 ScrollView和其他布局的下拉切换到.
* 支持预加载,加载当前也的前一个和后一个 Fragment.

1.0.1更新内容;
 >1. 增加了支持WebView 控件的支持.
* 增加 example 中两个实例.
* 美化了 example 例子

1.2更新内容;
 >1.     PullToNextAdapter.notifyDataSetChanged()->   通知Fragment集合有变
* pullToNextLayout.setCurrentItem(2);-> 设置当前页
*  pullToNextLayout.deleteCurrentItem();-> 删除当前页,有动画
*  增加了 app:underLayoutColor="@color/bg" 增加了底色属性,一定要加.


 

#end#
