package com.mingle.pulltonextlayout;

/**
 * Created by zzz40500 on 15/3/28.
 */
public class PullToNextEntity {

    private  PullToNextView pullToNextView;
    private  int contentId;
    private  int position=-1;


    public PullToNextView getPullToNextView() {
        return pullToNextView;
    }

    public void setPullToNextView(PullToNextView pullToNextView) {
        this.pullToNextView = pullToNextView;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
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

    public void reset(BaseAdapter adapter){
        getPullToNextView().reset(adapter,getPosition());

    }
}
