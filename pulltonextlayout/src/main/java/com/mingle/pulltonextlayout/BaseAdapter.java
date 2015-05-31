package com.mingle.pulltonextlayout;

import android.view.View;

import com.mingle.pulltonextlayout.observable.PullToNextDataObserver;
import com.mingle.pulltonextlayout.observable.PullToNextDataSetObservable;

import java.util.List;

/**
 * Created by zzz40500 on 15/5/30.
 */
public abstract  class   BaseAdapter<T> {


    protected  List<T>  list;
    protected PullToNextDataSetObservable mDataSetObservable = new PullToNextDataSetObservable();
    public  BaseAdapter(List<T> list){
        this.list=list;
    }


    protected   abstract void cleanAll();
    /**
     * 通知可见
     * @param position
     * @param visibility
     */
    public abstract void setOnItemVisibility(int position, boolean visibility);
    /**
     * 添加视图都pullToNextView.pullToNextView 中
     * @param pullToNextView
     */
    protected abstract void attachContentView(PullToNextEntity pullToNextView) ;
    /**
     *
     * @param pullToNextEntity
     */
    protected abstract void detachContentView(PullToNextEntity pullToNextEntity) ;
    protected abstract View getContentView(int position) ;

    public  int getCount(){
        return  list.size();
    }

    public T getItem(int arg0) {
        return list.get(arg0);
    }

    protected void deleteItem(int arg0) {
        list.remove(arg0);
    }

    public void addItem(T f){
        this.list.add(f);
        mDataSetObservable.notifyNewData();
    }

    public void addAllItem(List<T> list){
        this.list.addAll(list);
        mDataSetObservable.notifyNewData();
    }


    protected void registerDataSetObserver(PullToNextDataObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    protected void unregisterDataSetObserver(PullToNextDataObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    private void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    private void notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated();
    }

}
