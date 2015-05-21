package com.mingle.pulltonextlayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import com.mingle.pulltonextlayout.observable.PullToNextDataObserver;
import com.mingle.pulltonextlayout.observable.PullToNextDataSetObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzz40500 on 15/3/21.
 */
public class PullToNextAdapter {


    List<Fragment> allList = null;
    private FragmentManager fm;

    public PullToNextAdapter(Fragment fragment, List<Fragment> allList) {
        if(null  == allList){
            this.allList=new ArrayList<>();
        }else {
            this.allList = allList;
        }
        fm = fragment.getChildFragmentManager();
    }

    public PullToNextAdapter(FragmentManager manager, List<Fragment> allList) {



        if(null  == allList){
            this.allList=new ArrayList<>();
        }else {
            this.allList = allList;
        }
        fm = manager;

    }


    public FragmentManager getFm() {
        return fm;
    }

    public int getCount() {
        return allList.size();
    }


    public Fragment getItem(int arg0) {
        return allList.get(arg0);
    }


    protected void remove(int arg0) {
        allList.remove(arg0);
    }


    private PullToNextDataSetObservable mDataSetObservable = new PullToNextDataSetObservable();


    public void registerDataSetObserver(PullToNextDataObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(PullToNextDataObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated();
    }


    public void addItem(Fragment f){
        this.allList.add(f);
        mDataSetObservable.notifyNewData();

    }

    public void addAllItem(List<Fragment> list){

        this.allList.addAll(list);
        mDataSetObservable.notifyNewData();

    }
}
