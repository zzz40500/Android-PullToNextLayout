package com.mingle.pulltonextlayout;

import android.support.v4.app.FragmentManager;

/**
 * Created by zzz40500 on 15/3/28.
 */
public class PullToNextEntity {

    private PullToNextView pullToNextView;
    private  int frameId;
    private   int position;


    public PullToNextView getPullToNextView() {
        return pullToNextView;
    }

    public void setPullToNextView(PullToNextView pullToNextView) {
        this.pullToNextView = pullToNextView;
    }

    public int getFrameId() {
        return frameId;
    }

    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isAttach(){


        return pullToNextView != null && pullToNextView.getParent() != null;

    }

    public void reset(FragmentManager fm){
        getPullToNextView().reset(fm,getPosition());


    }
}
