package com.mingle.example;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.mingle.pulltonextlayout.OnItemSelectListener;
import com.mingle.pulltonextlayout.adapter.PullToNextFragmentAdapter;
import com.mingle.pulltonextlayout.PullToNextLayout;

import java.util.ArrayList;


public class WebViewActivity extends ActionBarActivity {

    public PullToNextLayout pullToNextLayout;

    private ArrayList<Fragment> list;

    private  String[] titles={"PullToNextLayout","使用 DrawerLayout 实现 Material Design风格的侧滑",
            "分享3个 自定义控件","BouncyEditText 不一样的 EditText"
            ,"会变色的 ViewPager","android Heads-Up风格通知"
            ,"快速集成图片游览器 android","JSon实体类快速生成插件 GsonFormat使用"
    ,"ListView适配器的超省写法"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_next_layout_demo);


        pullToNextLayout= (PullToNextLayout) findViewById(R.id.pullToNextLayout);


        list=new ArrayList<>();

        list.add( WebViewFragment.newInstant(0));
        list.add( WebViewFragment.newInstant(1));
        list.add( WebViewFragment.newInstant(2));
        list.add( WebViewFragment.newInstant(3));
        list.add( WebViewFragment.newInstant(4));
        list.add( WebViewFragment.newInstant(5));
        list.add( WebViewFragment.newInstant(6));
        list.add( WebViewFragment.newInstant(7));
        list.add( WebViewFragment.newInstant(8));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        pullToNextLayout.setAdapter(new PullToNextFragmentAdapter(getSupportFragmentManager(), list));

        pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onSelectItem(int position, View view) {

                WebViewActivity.this.setTitle(titles[position]);


            }
        });
        WebViewActivity.this.setTitle(titles[0]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == android.R.id.home) {


            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
