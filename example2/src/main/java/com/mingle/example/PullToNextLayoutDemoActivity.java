package com.mingle.example;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mingle.pulltonextlayout.OnItemSelectListener;
import com.mingle.pulltonextlayout.PullToNextAdapter;
import com.mingle.pulltonextlayout.PullToNextLayout;

import java.util.ArrayList;
import java.util.List;


public class PullToNextLayoutDemoActivity extends ActionBarActivity {

    public  PullToNextLayout pullToNextLayout;

    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_next_layout_demo);


        pullToNextLayout= (PullToNextLayout) findViewById(R.id.pullToNextLayout);


        list=new ArrayList<>();

        list.add(new  DemoFragment(0));
        list.add(new  DemoFragment(1));
        list.add(new  DemoFragment(2));
        list.add(new  DemoFragment(3));
        list.add(new  DemoFragment(4));
        list.add(new  DemoFragment(5));
        list.add(new  DemoFragment(6));
        list.add(new  DemoFragment(7));


        pullToNextLayout.setAdapter(new PullToNextAdapter(getSupportFragmentManager(), list));

        pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onSelectItem(int position, View view) {


                Log.e("PullToNextLayoutDemoActivity","选中第 "+position+" 个 Item");
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_pull_to_next_layout_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            pullToNextLayout.setCurrentItem(4);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
