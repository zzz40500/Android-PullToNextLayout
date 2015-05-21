package com.mingle.example;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

    private int  currentIndex=0;
    private PullToNextAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_next_layout_demo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        pullToNextLayout= (PullToNextLayout) findViewById(R.id.pullToNextLayout);


        list=new ArrayList<>();

//        for (int i = 0; i < 4; i++) {
//
//            list.add(new ScrollViewFragment(currentIndex++));
//
//        }
        adapter=  new PullToNextAdapter(getSupportFragmentManager(), list);
        pullToNextLayout.setAdapter(adapter);

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
        getMenuInflater().inflate(R.menu.menu_pull_to_next_layout_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id== R.id.action_addALL){

//


            for (int i = 0; i < 5; i++) {

//                list.add(new ScrollViewFragment(currentIndex++));
                adapter.addItem(new ScrollViewFragment(currentIndex++));

            }
//            adapter.notifyDataSetChanged();





//             pullToNextLayout.setAdapter( new PullToNextAdapter(getSupportFragmentManager(), list));

        }else if(id== R.id.action_setSelection){

            pullToNextLayout.setCurrentItem(1);

        }else if(id== R.id.action_delete){
            pullToNextLayout.deleteCurrentItem();
        }

        if (id == android.R.id.home) {


            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
