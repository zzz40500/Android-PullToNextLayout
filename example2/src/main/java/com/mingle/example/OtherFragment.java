package com.mingle.example;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class OtherFragment extends Fragment {




    private  String[] names={"于文文","张钧甯","陈乔恩","贾青"};


    private  String[] birthday={"1989年11月7日","1982年9月4日","1979年04月04日",
            "1986年11月2日"
    };

    private int[] imgRes={R.mipmap.ic_icon1,R.mipmap.ic_icon2,R.mipmap.ic_icon3,
            R.mipmap.ic_icon4
    };

    private int  index;
    public OtherFragment(int index) {

      this.index=index;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Log.e(" onCreateView  "," "+index);

        View   v= inflater.inflate(R.layout.fragment_other, container, false);

        TextView nameTV= (TextView) v.findViewById(R.id.nameTV);
        TextView birthdayTV= (TextView) v.findViewById(R.id.birthdayTV);
        ImageView imageView= (ImageView) v.findViewById(R.id.image);
        nameTV.setText(names[index]);

        birthdayTV.setText("出生日期: "+birthday[index]);
        imageView.setImageResource(imgRes[index]);

        Log.e(" onCreateView  "," "+(getActivity()==null));


        v.setClickable(true);

        return  v;
    }

    @Override
    public void onResume() {
        Log.e(" onResume  "," "+index);
        super.onResume();
    }


    @Override
    public void onDestroy() {
        Log.e(" onDestroy  "," "+index);
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.e(" onDestroyView  "," "+index);


        super.onDestroyView();
    }
}
