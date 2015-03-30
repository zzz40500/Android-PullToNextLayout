 #效果图先行:#


![pulltoNext.gif](http://upload-images.jianshu.io/upload_images/166866-59550ea4582a4a22.gif)


[github 地址直达](https://github.com/zzz40500/Android-PullToNextLayout)

#usage:#
activity 的布局
~~~
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">


    <com.mingle.pulltonextlayout.PullToNextLayout
        android:id="@+id/pullToNextLayout"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"/>

</RelativeLayout>

~~~

简单说明 :  布局中只有一个控件`PullToNextLayout`, 控件中不包含其他组件

java 代码中引用使用:
~~~
 pullToNextLayout= (PullToNextLayout) findViewById(R.id.pullToNextLayout);
        list=new ArrayList<Fragment>();
        list.add(new  DemoFragment(0));
        list.add(new  DemoFragment(1));
        list.add(new  DemoFragment(2));
        list.add(new  DemoFragment(3));
        list.add(new  DemoFragment(4));
        list.add(new  DemoFragment(5));
        list.add(new  DemoFragment(6));
        list.add(new  DemoFragment(7));
//设置适配器
        pullToNextLayout.setAdapter(new PullToNextAdapter(getSupportFragmentManager(), list));

        pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onSelectItem(int position, View view) {


                Log.e("PullToNextLayoutDemoActivity","选中第 "+position+" 个 Item");
            }
        });
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


#end#
