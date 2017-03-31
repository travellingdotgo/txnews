package com.zt.txnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zt.txnews.R;
import com.zt.txnews.newsdata.NewsDate;

/**
 * Created by Administrator on 2016/9/9.
 * 单列设计模式：懒汉式 3
 * Fragment$InstantiationException: Unable to instantiate fragment com.zt.txnews.fragment.Fragment2:
 *      make sure class name exists, is public, and has an empty constructor that is public
 *      //public 4.0系统，如不public则崩溃
 */
public class Fragment2 extends Fragment {
    private static Fragment2 instance;
    private   static View view;

    public Fragment2(){}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //防止多次初始化布局,布局非空判断 实列化一次即可
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_listview, container, false);
            initView();
            return view;
        }else {
            return view;
        }
    }

    public static Fragment2 genInstance(){
        if (instance==null) {
            instance = new Fragment2();
            return instance;
        }
        return instance;
    }
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;
    private Button updateBut;
    private PullToRefreshListView pullToRefreshListView;

    private void initView() {
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.update_relateLayout);
        updateBut = (Button) view.findViewById(R.id.but_update);
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.pulltorefresh);

        NewsDate newsDate = new NewsDate(getActivity(),"shehui",progressBar,relativeLayout,updateBut,pullToRefreshListView);
        newsDate.requestJuheServiceGetData();
    }
}
