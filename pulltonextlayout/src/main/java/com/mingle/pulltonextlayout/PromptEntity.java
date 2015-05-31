package com.mingle.pulltonextlayout;

import android.content.Context;

/**
 * Created by zzz40500 on 15/5/31.
 */
public class PromptEntity {



    private String noMoreDatePtn;
    private String releaseToNextPtn;
    private String pullUpToNextPtn;
    private String theFirstPtn;
    private String releaseToPreviousPtn;
    private String pullDownToPreviousPtn;



    public PromptEntity(Context context){


        setNoMoreDatePtn(context.getString(R.string.ptn_no_more_data));
        setPullDownToPreviousPtn(context.getString(R.string.ptn_pull_down_to_previous));
        setPullUpToNextPtn(context.getString(R.string.ptn_pull_up_to_next));
        setReleaseToNextPtn(context.getString(R.string.ptn_release_to_next));
        setReleaseToPreviousPtn(context.getString(R.string.ptn_release_to_previous));
        setTheFirstPtn(context.getString(R.string.ptn_the_first));
    }


    public PromptEntity(String noMoreDatePtn, String releaseToNextPtn, String pullUpToNextPtn, String theFirstPtn, String releaseToPreviousPtn, String pullDownToPreviousPtn) {
        this.noMoreDatePtn = noMoreDatePtn;
        this.releaseToNextPtn = releaseToNextPtn;
        this.pullUpToNextPtn = pullUpToNextPtn;
        this.theFirstPtn = theFirstPtn;
        this.releaseToPreviousPtn = releaseToPreviousPtn;
        this.pullDownToPreviousPtn = pullDownToPreviousPtn;
    }

    public PromptEntity() {
    }

    public String getNoMoreDatePtn() {
        return noMoreDatePtn;
    }

    public void setNoMoreDatePtn(String noMoreDatePtn) {
        this.noMoreDatePtn = noMoreDatePtn;
    }

    public String getReleaseToNextPtn() {
        return releaseToNextPtn;
    }

    public void setReleaseToNextPtn(String releaseToNextPtn) {
        this.releaseToNextPtn = releaseToNextPtn;
    }

    public String getPullUpToNextPtn() {
        return pullUpToNextPtn;
    }

    public void setPullUpToNextPtn(String pullUpToNextPtn) {
        this.pullUpToNextPtn = pullUpToNextPtn;
    }

    public String getTheFirstPtn() {
        return theFirstPtn;
    }

    public void setTheFirstPtn(String theFirstPtn) {
        this.theFirstPtn = theFirstPtn;
    }

    public String getReleaseToPreviousPtn() {
        return releaseToPreviousPtn;
    }

    public void setReleaseToPreviousPtn(String releaseToPreviousPtn) {
        this.releaseToPreviousPtn = releaseToPreviousPtn;
    }

    public String getPullDownToPreviousPtn() {
        return pullDownToPreviousPtn;
    }

    public void setPullDownToPreviousPtn(String pullDownToPreviousPtn) {
        this.pullDownToPreviousPtn = pullDownToPreviousPtn;
    }




}
