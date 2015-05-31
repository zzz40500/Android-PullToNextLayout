package com.mingle.pulltonextlayout.model;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.BaseAdapter;

import com.mingle.pulltonextlayout.PullToNextView;

/**
 *
 * Created by zzz40500 on 15/5/30.
 */
public abstract class PullToNextModel {


    protected Context mContext;
    protected View view;
    private boolean userVisibleHint;


    private  boolean create;
    /**
     * @param context
     */
    public void onCreate(Context context) {
        this.mContext = context;

    }
    /**
     * 返回布局的 ID
     * @return
     */
    public abstract
    @LayoutRes
    int getLayoutViewId();


    /**
     * 绑定数据
     *
     * @param view
     */
    public abstract void onBindView( int position,View view, PullToNextView pullToNextView) ;

    /**
     * 可见状态
     *
     * @param view
     */
    public void onResumeView(int position,View view, PullToNextView pullToNextView) {

    }

    /**
     * 不可见状态
     *
     * @param view
     */
    public void onPauseView(int position,View view, PullToNextView pullToNextView) {

    }

    /**
     * 解绑数据
     *
     * @param view
     */

    public void onUnBindView(int position,View view, PullToNextView pullToNextView) {

    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean isUserVisibleHint() {
        return userVisibleHint;
    }

    public void setUserVisibleHint(boolean userVisibleHint) {
        this.userVisibleHint = userVisibleHint;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }
}
