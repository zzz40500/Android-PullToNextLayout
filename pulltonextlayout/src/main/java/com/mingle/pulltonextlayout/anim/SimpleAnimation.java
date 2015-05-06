package com.mingle.pulltonextlayout.anim;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.mingle.pulltonextlayout.PullToNextEntity;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by zzz40500 on 15/5/3.
 */
public class SimpleAnimation  implements  PullToNextAnimationI {

    private static final int ANIMATION_DURATION = 500;


    public Animator getPullDownAnimIn(View view) {

        ObjectAnimator in = ObjectAnimator.ofFloat(view, "translationY", -view.getMeasuredHeight(), 0);

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.6f, 1);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.6f, 1);

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new DecelerateInterpolator());
        in.setDuration(ANIMATION_DURATION);
        scaleY.setDuration(ANIMATION_DURATION);
        scaleX.setDuration(ANIMATION_DURATION);
        set.setDuration(ANIMATION_DURATION);
        set.playTogether(scaleY, scaleX, in);


        return set;
    }

    private Animator getPullDownAnimOut(View view) {

        ObjectAnimator out = ObjectAnimator.ofFloat(view, "translationY", 0, view.getMeasuredHeight());

        out.setDuration(ANIMATION_DURATION);
        return out;
    }


    private Animator getPullUpAnimIn(View view) {

        ObjectAnimator in =   ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "rotationX", 20, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.6f, 1);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.6f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new DecelerateInterpolator());
        in.setDuration(ANIMATION_DURATION);
        scaleY.setDuration(ANIMATION_DURATION);
        scaleX.setDuration(ANIMATION_DURATION);
        set.setDuration(ANIMATION_DURATION);
        set.playTogether(scaleY, scaleX, in);


        return set;
    }


    private Animator getPullUpAnimOut(View view) {
        ObjectAnimator out = ObjectAnimator.ofFloat(view, "translationY", 0, -view.getMeasuredHeight());
        out.setDuration(ANIMATION_DURATION);
        return out;
    }



    public Animator getPullDownAnim(View showView, View dismissView) {
        Animator out = getPullDownAnimOut(dismissView);
        Animator in = getPullDownAnimIn(showView);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(ANIMATION_DURATION);
        set.playTogether(out, in);
        return set;
    }


    public Animator getPullUpAnim(View showView, View dismissView) {

        Animator out = getPullUpAnimOut(dismissView);

        Animator in =getPullUpAnimIn(showView);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION);
        set.setInterpolator(new DecelerateInterpolator());
        set.playTogether(out, in);

        return set;
    }

    public Animator getDeleteItemDisMissAnimation(View view) {

        return getPullDownAnimOut(view);
    }

    public Animator getDeleteItemShowAnimation(View view) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.7f, 1);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.7f, 1);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new DecelerateInterpolator());
        scaleY.setDuration(ANIMATION_DURATION);
        scaleX.setDuration(ANIMATION_DURATION);
        set.setDuration(ANIMATION_DURATION);
        set.playTogether(scaleY, scaleX);
        return set;
    }

    @Override
    public Animator getDeleteItemAnim(View showView, View dismissView) {

        Animator out = getDeleteItemDisMissAnimation(dismissView);

        Animator in =getDeleteItemShowAnimation(showView);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_DURATION);
        set.setInterpolator(new DecelerateInterpolator());
        set.playTogether(out, in);
        return set;
    }
}
