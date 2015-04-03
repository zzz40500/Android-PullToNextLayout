package com.mingle.example;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mingle.pulltonextlayout.OnItemSelectListener;
import com.mingle.pulltonextlayout.PullToNextAdapter;
import com.mingle.pulltonextlayout.PullToNextLayout;

import java.util.ArrayList;


public class ScrollViewActivity extends ActionBarActivity {

    public  PullToNextLayout pullToNextLayout;

    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_next_layout_demo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        pullToNextLayout= (PullToNextLayout) findViewById(R.id.pullToNextLayout);


        list=new ArrayList<>();

        list.add(new ScrollViewFragment(0));
        list.add(new ScrollViewFragment(1));
        list.add(new ScrollViewFragment(2));
        list.add(new ScrollViewFragment(3));
        list.add(new ScrollViewFragment(4));
        list.add(new ScrollViewFragment(5));
        list.add(new ScrollViewFragment(6));
        list.add(new ScrollViewFragment(7));


        pullToNextLayout.setAdapter(new PullToNextAdapter(getSupportFragmentManager(), list));

        pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onSelectItem(int position, View view) {


                setTitle(position+1+".0 谷歌仍是毕业生心目中的最佳雇主");
            }
        });
        setTitle(1+".0 谷歌仍是毕业生心目中的最佳雇主");
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
        if (id == android.R.id.home) {


            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
