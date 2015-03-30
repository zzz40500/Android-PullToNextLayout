package com.mingle.pulltonextlayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import java.util.List;

/**
 * Created by zzz40500 on 15/3/21.
 */
public  class PullToNextAdapter {


    List<Fragment> allList = null;


    private FragmentManager fm;


    public PullToNextAdapter(Fragment fragment,List<Fragment> allList) {
        this.allList = allList;
       fm= fragment.getChildFragmentManager();

    }
    public PullToNextAdapter(FragmentManager manager,List<Fragment> allList) {
        this.allList = allList;
        fm=manager;

    }


    public FragmentManager getFm() {
        return fm;
    }

    public  int getCount(){
        return  allList.size();
    }





    public Fragment getItem(int arg0) {
        return allList.get(arg0);
    }





}
