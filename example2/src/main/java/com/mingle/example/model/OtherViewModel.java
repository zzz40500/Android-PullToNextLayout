package com.mingle.example.model;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mingle.example.R;
import com.mingle.pulltonextlayout.PullToNextView;
import com.mingle.pulltonextlayout.model.PullToNextModel;

/**
 * Created by zzz40500 on 15/5/30.
 */
public class OtherViewModel extends PullToNextModel {

    private  String[] names={"于文文","张钧甯","陈乔恩","贾青"};


    private  String[] birthday={"1989年11月7日","1982年9月4日","1979年04月04日",
            "1986年11月2日"
    };

    private int[] imgRes={R.mipmap.ic_icon1,R.mipmap.ic_icon2,R.mipmap.ic_icon3,
            R.mipmap.ic_icon4
    };


    private int index=0;


    public OtherViewModel(int index) {
        this.index = index;
    }

    @Override
    public int getLayoutViewId() {
        return R.layout.fragment_other;
    }

    @Override
    public void onBindView(int position, View v, PullToNextView pullToNextView) {
        TextView nameTV= (TextView) v.findViewById(R.id.nameTV);
        TextView birthdayTV= (TextView) v.findViewById(R.id.birthdayTV);
        ImageView imageView= (ImageView) v.findViewById(R.id.image);
        nameTV.setText(names[index]);

        birthdayTV.setText("出生日期: " + birthday[index]);
        imageView.setImageResource(imgRes[index]);

        v.setClickable(true);
    }




}
