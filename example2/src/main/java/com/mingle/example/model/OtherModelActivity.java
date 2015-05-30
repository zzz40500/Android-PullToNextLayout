package com.mingle.example.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mingle.example.R;
import com.mingle.pulltonextlayout.OnItemSelectListener;
import com.mingle.pulltonextlayout.adapter.PullToNextFragmentAdapter;
import com.mingle.pulltonextlayout.PullToNextLayout;
import com.mingle.pulltonextlayout.adapter.PullToNextModelAdapter;
import com.mingle.pulltonextlayout.model.PullToNextModel;

import java.util.ArrayList;


public class OtherModelActivity extends ActionBarActivity {

    public PullToNextLayout pullToNextLayout;


    private ArrayList<PullToNextModel> list;
    private  String[] names={"于文文","张钧甯","陈乔恩","贾青"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_next_layout_demo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        pullToNextLayout= (PullToNextLayout) findViewById(R.id.pullToNextLayout);


        list=new ArrayList<>();

        list.add(new OtherViewModel(0));
        list.add(new OtherViewModel(1));
        list.add(new OtherViewModel(2));
        list.add(new OtherViewModel(3));


        pullToNextLayout.setAdapter(new PullToNextModelAdapter(this, list));

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == android.R.id.home) {


            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
