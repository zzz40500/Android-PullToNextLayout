package com.mingle.pulltonextlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mingle.pulltonextlayout.anim.PullToNextAnimationI;
import com.mingle.pulltonextlayout.anim.SimpleAnimation;
import com.mingle.pulltonextlayout.observable.PullToNextDataObserver;
import com.nineoldandroids.animation.Animator;

import java.util.List;

/**
 * Created by zzz40500 on 15/3/21.
 */
public class PullToNextLayout extends FrameLayout {


    private boolean isAnimating;

    private int mItemCount = 0;

    private PullToNextAdapter mAdapter;

    private OnItemSelectListener mOnItemSelectListener;

    private PUllToNextDataSetObservable mDataSetObserver;

    private PullToNextAnimationI simpleAnimation = new SimpleAnimation();

    private int bgColor;


    private PullToNextView.PullToNextI mPullToNextI = new PullToNextView.PullToNextI() {
        @Override
        public void previous() {
            PullToNextLayout.this.previous();
        }

        @Override
        public void next() {
            PullToNextLayout.this.next();
        }
    };

    public PullToNextLayout(Context context) {
        super(context);
        init(null);
    }

    public PullToNextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PullToNextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PullToNextLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(AttributeSet attrs) {


        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.PullToNextLayout);
            bgColor = typedArray.getColor(R.styleable.PullToNextLayout_underLayoutColor, getResources().getColor(R.color.bg));
            typedArray.recycle();
        } else {
            bgColor = getResources().getColor(R.color.bg);
        }

        initPullToNextLayout();
    }

    private void initPullToNextLayout() {
        currentPTE = newPullToNextView(R.id.contentFL1);
        previousPTE = newPullToNextView(R.id.contentFL2);
        nextPTE = newPullToNextView(R.id.contentFL3);
        setUnderLayerBackground(bgColor);
    }


    private PullToNextEntity currentPTE;
    private PullToNextEntity previousPTE;
    private PullToNextEntity nextPTE;

    public void setAdapter(PullToNextAdapter pullToNextAdapter) {

        this.setAdapter(pullToNextAdapter, 0);
    }

    public void setUnderLayerBackground(int bgColor) {
        this.setBackgroundColor(bgColor);
        setPullToNextViewBackground(currentPTE, bgColor);
        setPullToNextViewBackground(previousPTE, bgColor);
        setPullToNextViewBackground(nextPTE, bgColor);
    }

    private void setPullToNextViewBackground(PullToNextEntity pte, int bgColor) {
        if (null != pte.getPullToNextView()) {
            pte.getPullToNextView()
                    .setBackgroundColor(bgColor);
        }
    }

    public void setAdapter(PullToNextAdapter pullToNextAdapter, int currentIndex) {
        if (mAdapter != null && mDataSetObserver != null) {
            cleanAlFragment();
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        if (null == pullToNextAdapter) {
            return;
        }
        if (currentIndex < 0) {
            currentIndex = 0;

        } else if (currentIndex >= pullToNextAdapter.getCount()) {
            currentIndex = pullToNextAdapter.getCount() - 1;
        }
        removeAllViews();

        this.mAdapter = pullToNextAdapter;

        mItemCount = pullToNextAdapter.getCount();

        mDataSetObserver = new PUllToNextDataSetObservable();
        mAdapter.registerDataSetObserver(mDataSetObserver);
        currentPTE.setPosition(currentIndex);


        if (mAdapter.getCount() > 0) {
            addPullToNextView(0, currentPTE);
            invalidateView(currentIndex);
        }
    }


    public void setCurrentItem(int i) {

        if (i < 0 || i >= mAdapter.getCount()) {
            return;
        }
        removeView(currentPTE.getPullToNextView());
        removeView(nextPTE.getPullToNextView());
        removeView(previousPTE.getPullToNextView());
        cleanAlFragment();
        setAdapter(mAdapter, i);

    }

    private void cleanAlFragment() {
        List<Fragment> list = mAdapter.getFm().getFragments();
        if (list == null || list.size() == 0) {
            return;
        }

        FragmentTransaction transaction = mAdapter.getFm().beginTransaction();


        for (int j = 0; j < list.size(); j++) {
            Fragment fragment = list.get(j);
            if (fragment != null) {
                transaction.remove(list.get(j));
            }
        }
        transaction.commitAllowingStateLoss();
        if (null != mAdapter.getFm()) {
            mAdapter.getFm().executePendingTransactions();
        }

    }


    private void invalidateView(int mCurItem) {

        mAdapter.getItem(mCurItem).setUserVisibleHint(true);
        mAdapter.getItem(mCurItem).setMenuVisibility(true);

        if (mCurItem - 1 >= 0) {


            previousPTE.setPosition(mCurItem - 1);
            addPullToNextView(0, previousPTE);
            previousPTE.reset(mAdapter.getFm());


            if (mAdapter.getItem(mCurItem - 1).getUserVisibleHint()) {
                mAdapter.getItem(mCurItem - 1).setUserVisibleHint(false);
                mAdapter.getItem(mCurItem - 1).setMenuVisibility(false);
            }
        }
        if (mCurItem + 1 >= 0 && mCurItem + 1 < mAdapter.getCount()) {


            nextPTE.setPosition(mCurItem + 1);
            nextPTE.reset(mAdapter.getFm());

            addPullToNextView(0, nextPTE);
            if (mAdapter.getItem(mCurItem + 1).getUserVisibleHint()) {
                mAdapter.getItem(mCurItem + 1).setUserVisibleHint(false);
                mAdapter.getItem(mCurItem + 1).setMenuVisibility(false);
            }

        }
        currentPTE.reset(mAdapter.getFm());
        if (null != mOnItemSelectListener) {
            mOnItemSelectListener.onSelectItem(currentPTE.getPosition(), currentPTE.getPullToNextView());
        }

    }


    /**
     * 初始化 PullToNextEntity实体
     *
     * @param frameLayoutId
     * @return
     */
    public PullToNextEntity newPullToNextView(int frameLayoutId) {


        PullToNextEntity entity = new PullToNextEntity();
        PullToNextView pullToNextView = new PullToNextView(getContext());
        FrameLayout frameLayout = new FrameLayout(getContext());

        frameLayout.setId(frameLayoutId);
        pullToNextView.setContentView(frameLayout);

        pullToNextView.setPullToNextI(mPullToNextI);
        pullToNextView.setTag(frameLayoutId);
        entity.setFrameId(frameLayoutId);
        entity.setPullToNextView(pullToNextView);
        return entity;
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
    }

    /**
     * 将pullToNextView 添加到 PullToNextLayout 控件中
     *
     * @param index
     * @param pullToNextView
     */
    public void addPullToNextView(int index, PullToNextEntity pullToNextView) {

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = pullToNextView.getPullToNextView();
        if (view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
        }
        this.addView(pullToNextView.getPullToNextView(), index, layoutParams);
        FragmentTransaction transaction = mAdapter.getFm().beginTransaction();
        Fragment f
                = mAdapter.getFm().findFragmentByTag("position" + pullToNextView.getPosition());

        if (f != null) {
            transaction.attach(f);
        } else {
            transaction.add(pullToNextView.getFrameId(),
                    mAdapter.getItem(pullToNextView.getPosition())
                    , "position" + pullToNextView.getPosition());
        }

        transaction.commitAllowingStateLoss();

        mAdapter.getFm().executePendingTransactions();


        initPullToNextEnable(pullToNextView);

    }

    private void initPullToNextEnable(PullToNextEntity pullToNextView) {


        if (null == pullToNextView) {
            return;
        }
        if (pullToNextView.getPosition() == 0) {
            pullToNextView.getPullToNextView().setHashPrevious(false);
        } else {
            pullToNextView.getPullToNextView().setHashPrevious(true);
        }
        if (pullToNextView.getPosition() == mItemCount - 1) {
            pullToNextView.getPullToNextView().setHashNext(false);
        } else {
            pullToNextView.getPullToNextView().setHashNext(true);
        }
    }


    private void previous() {

        remove(nextPTE);

        Animator anim = simpleAnimation.getPullDownAnim(previousPTE.getPullToNextView(), currentPTE.getPullToNextView());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                removeView(currentPTE.getPullToNextView());
                PullToNextEntity tempPTE = currentPTE;
                PullToNextEntity temp2PTE = nextPTE;
                currentPTE = previousPTE;
                previousPTE = temp2PTE;
                nextPTE = tempPTE;
                isAnimating = false;
                invalidateView(currentPTE.getPosition());
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        anim.start();
    }


    public PullToNextAnimationI getSimpleAnimation() {
        return simpleAnimation;
    }

    public void setSimpleAnimation(PullToNextAnimationI simpleAnimation) {
        this.simpleAnimation = simpleAnimation;
    }

    private void remove(PullToNextEntity pullToNextEntity) {


        if (pullToNextEntity.getPullToNextView() != null) {


            if (pullToNextEntity.isAttach()) {

                removeView(pullToNextEntity.getPullToNextView());
                Fragment f
                        = mAdapter.getFm().findFragmentByTag("position" + pullToNextEntity.getPosition());

                if (f != null) {
                    mAdapter.getFm().beginTransaction().detach(
                            f).commit();

                }
                if (null != mAdapter.getFm()) {

                    mAdapter.getFm().executePendingTransactions();
                }


            }
        }

    }

    private void next() {

        remove(previousPTE);
        Animator anim = simpleAnimation.getPullUpAnim(nextPTE.getPullToNextView(), currentPTE.getPullToNextView());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                removeView(currentPTE.getPullToNextView());
                PullToNextEntity tempPTE = currentPTE;
                PullToNextEntity temp2PTE = previousPTE;
                currentPTE = nextPTE;
                previousPTE = tempPTE;
                nextPTE = temp2PTE;
                isAnimating = false;
                invalidateView(currentPTE.getPosition());

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        anim.start();


    }


    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        this.mOnItemSelectListener = onItemSelectListener;


    }


    /**
     * 播放动画的时候  不能响应点击事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isAnimating || super.onInterceptTouchEvent(ev);
    }


    class PUllToNextDataSetObservable extends PullToNextDataObserver {

        public void onChanged() {
            int oldCount = mItemCount;
            mItemCount = mAdapter.getCount();
            int currentPosition = currentPTE.getPosition();
            if (currentPosition > mItemCount - 1) {
                setCurrentItem(mItemCount - 1);
            } else if (oldCount == 0) {
                setCurrentItem(0);
            } else {
                setCurrentItem(currentPosition);
            }

        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();

            cleanAlFragment();
        }


        @Override
        public void onNewData() {
            super.onNewData();

            int oldCount = mItemCount;
            mItemCount = mAdapter.getCount();
            if (oldCount == 0) {
                setCurrentItem(0);
            } else {

                initPullToNextEnable(currentPTE);
                invalidateView(currentPTE.getPosition());
            }
        }
    }


    /**
     * 删除当前的 Item
     */
    public void deleteCurrentItem() {

        if (mItemCount <= 0 || isAnimating) {
            return;
        }
        if (currentPTE.getPosition() == 0) {
            remove(previousPTE);
            Animator anim = simpleAnimation.getDeleteItemAnim(nextPTE.getPullToNextView(), currentPTE.getPullToNextView());


            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAnimating = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mAdapter.remove(currentPTE.getPosition());
                    mItemCount = mAdapter.getCount();
                    isAnimating = false;
                    PullToNextEntity temp = nextPTE;

                    nextPTE = currentPTE;
                    currentPTE = temp;
                    setCurrentItem(0);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });

            anim.start();


        } else {
            remove(nextPTE);
            Animator anim = simpleAnimation.getDeleteItemAnim(previousPTE.getPullToNextView(), currentPTE.getPullToNextView());


            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAnimating = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    mAdapter.remove(currentPTE.getPosition());
                    isAnimating = false;
                    mItemCount = mAdapter.getCount();
                    PullToNextEntity temp = previousPTE;

                    previousPTE = currentPTE;
                    currentPTE = temp;
                    setCurrentItem(currentPTE.getPosition());


                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            anim.start();

        }
    }


}
