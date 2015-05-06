package com.mingle.example;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mingle.pulltonextlayout.OnItemSelectListener;
import com.mingle.pulltonextlayout.PullToNextAdapter;
import com.mingle.pulltonextlayout.PullToNextLayout;

import java.util.ArrayList;


public class OtherActivity extends ActionBarActivity {

    public PullToNextLayout pullToNextLayout;

    private FragmentPagerAdapter ad;

    private ArrayList<Fragment> list;
    private  String[] names={"于文文","张钧甯","陈乔恩","贾青"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_next_layout_demo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        pullToNextLayout= (PullToNextLayout) findViewById(R.id.pullToNextLayout);


        list=new ArrayList<>();

        list.add(new OtherFragment(0));
        list.add(new OtherFragment(1));
        list.add(new OtherFragment(2));
        list.add(new OtherFragment(3));


        pullToNextLayout.setAdapter(new PullToNextAdapter(getSupportFragmentManager(), list));

        pullToNextLayout.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onSelectItem(int position, View view) {
                setTitle(names[position]);
            }
        });

        setTitle(names[0]);
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


        if (id == android.R.id.home) {


            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
