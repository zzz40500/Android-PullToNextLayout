package com.mingle.pulltonextlayout.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingle.pulltonextlayout.BaseAdapter;
import com.mingle.pulltonextlayout.PullToNextEntity;
import com.mingle.pulltonextlayout.model.PullToNextModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by zzz40500 on 15/5/30.
 */
public class PullToNextModelAdapter extends BaseAdapter<PullToNextModel> {
    private SparseArray<List<View>> holderMap = new SparseArray<>();

    private Context context;

    public PullToNextModelAdapter(Context context, List<PullToNextModel> list) {
        super(list);
        this.context = context;
    }

    @Override
    protected void cleanAll() {
        holderMap.clear();
    }

    @Override
    public void setOnItemVisibility(int position, boolean userVisibleHint) {

        PullToNextModel model = list.get(position);

        if (model.isUserVisibleHint() != userVisibleHint) {
            model.setUserVisibleHint(userVisibleHint);
        }
    }


    @Override
    protected void attachContentView(PullToNextEntity pullToNextEntity) {

        PullToNextModel model = list.get(pullToNextEntity.getPosition());
        View view = getConvertView(model.getLayoutViewId());

        if (!model.isCreate()) {
            model.onCreate(context);
            model.setCreate(true);
        }
        model.setView(view);
        ViewGroup parentView = (ViewGroup) pullToNextEntity.getPullToNextView().findViewById(pullToNextEntity.getContentId());
        parentView.removeAllViews();
        parentView.addView(view);
        model.onBindView(pullToNextEntity.getPosition(), view, pullToNextEntity.getPullToNextView());
        model.onResumeView(pullToNextEntity.getPosition(),view,pullToNextEntity.getPullToNextView());
    }


    @Override
    protected void detachContentView(PullToNextEntity pullToNextEntity) {
        PullToNextModel model = list.get(pullToNextEntity.getPosition());
        ViewGroup parentView = (ViewGroup) pullToNextEntity.getPullToNextView().findViewById(pullToNextEntity.getContentId());
        model.onPauseView(pullToNextEntity.getPosition(), model.getView(), pullToNextEntity.getPullToNextView());
        model.onUnBindView(pullToNextEntity.getPosition(), model.getView(), pullToNextEntity.getPullToNextView());
        parentView.removeView(model.getView());
        model.setView(null);
    }

    @Override
    public View getContentView(int position) {
        PullToNextModel model = list.get(position);
        return model.getView();
    }

    /**
     * 实现 contentView 的复用
     * @param id
     * @return
     */
    private View getConvertView(@LayoutRes int id) {

        List<View> views = holderMap.get(id);
        View v = null;
        if (views != null) {

            for (int i = 0; i < views.size(); i++) {
                View item = views.get(i);
                if (item.getParent() == null) {
                    v = item;
                    break;
                }
            }

            if (v == null) {
                v = LayoutInflater.from(context).inflate(id, null);
                views.add(v);
            }

        } else {
            views = new ArrayList<>();
            v = LayoutInflater.from(context).inflate(id, null);
            views.add(v);
            holderMap.put(id,views);
        }
        return v;

    }

    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }
}
