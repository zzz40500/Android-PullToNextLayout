package com.mingle.example;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class WebViewFragment extends Fragment {




    private WebView webView;





    private int  index;
    public WebViewFragment(int index) {

      this.index=index;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View   v= inflater.inflate(R.layout.fragment_web_view, container, false);

        webView= (WebView) v.findViewById(R.id.webView);

        String[]  urls=getResources().getStringArray(R.array.urls);

        webView.loadUrl(urls[index]);
        webView.setWebChromeClient(new WebChromeClient());
//        webView.getSettings().setJavaScriptEnabled(true);

        return  v;
    }

   
}
