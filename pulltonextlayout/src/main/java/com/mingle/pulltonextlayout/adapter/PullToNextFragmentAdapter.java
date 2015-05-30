package com.mingle.pulltonextlayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;


import com.mingle.pulltonextlayout.BaseAdapter;
import com.mingle.pulltonextlayout.PullToNextEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzz40500 on 15/3/21.
 */

/**
 * 缺陷:对于2.3的系统使用 addItem() addAllItem() 方法会错乱.4.0以上没有这个问题.
 * 对于重复的视图没有做到复用.不支持 notifyDataSetChanged方法.
 * 推荐使用 {@link PullToNextModelAdapter}
 *
 */
@Deprecated()
public class PullToNextFragmentAdapter extends BaseAdapter<Fragment> {


    private FragmentManager fm;

    public PullToNextFragmentAdapter(Fragment fragment, List<Fragment> allList) {
        super(allList);
        if(null  == allList){
            list=new ArrayList<>();
        }
        fm = fragment.getChildFragmentManager();
    }

    public PullToNextFragmentAdapter(FragmentManager manager, List<Fragment> allList) {
        super(allList);
        if(null  == allList){
            this.list=new ArrayList<>();
        }
        fm = manager;
    }

    @Override
    protected void cleanAll() {
        List<Fragment> list = this.fm.getFragments();
        if (list == null || list.size() == 0) {
            return;
        }
        FragmentTransaction transaction = this.fm.beginTransaction();
        for (int j = 0; j < list.size(); j++) {
            Fragment fragment = list.get(j);
            if (fragment != null) {
                transaction.remove(list.get(j));
            }
        }
        transaction.commitAllowingStateLoss();
        if (null != this.fm) {
            this.fm.executePendingTransactions();
        }
    }

    public void setOnItemVisibility(int position, boolean visibility){


        if(getItem(position).getUserVisibleHint() != visibility) {
            getItem(position).setUserVisibleHint(visibility);
            getItem(position).setMenuVisibility(visibility);
        }

    }


    @Override
    public View getContentView(int position) {

        Fragment f = fm.findFragmentByTag("position" + position);


        if (f != null) {
            return  f.getView();
        }else{
            return  null;
        }
    }

    @Override
    protected void attachContentView(PullToNextEntity pullToNextView) {

        FragmentTransaction transaction = fm.beginTransaction();
        Fragment f
                = fm.findFragmentByTag("position" + pullToNextView.getPosition());

        if (f != null) {
            transaction.attach(f);
        } else {
            transaction.add(pullToNextView.getContentId(),
                    this.getItem(pullToNextView.getPosition())
                    , "position" + pullToNextView.getPosition());
        }

        transaction.commitAllowingStateLoss();
        this.fm.executePendingTransactions();

    }

    @Override
    protected void detachContentView(PullToNextEntity pullToNextEntity){
        Fragment f
                = this.fm.findFragmentByTag("position" + pullToNextEntity.getPosition());

        if (f != null) {
            this.fm.beginTransaction().detach(
                    f).commit();

        }
        if (null != this.fm) {

            this.fm.executePendingTransactions();
        }
    }
}
