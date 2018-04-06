package com.example.administrator.moni3_20180303.View.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.moni3_20180303.Model.Bean.ZhuangTai_bean;
import com.example.administrator.moni3_20180303.R;
import com.example.administrator.moni3_20180303.Util.Api;
import com.example.administrator.moni3_20180303.Util.RetrofitHelper;
import com.example.administrator.moni3_20180303.View.MyListView;
import com.example.administrator.moni3_20180303.View.MyScrollView;
import com.example.administrator.moni3_20180303.View.XiaDanActivity;
import com.example.administrator.moni3_20180303.View.adapter.MyDingDanAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/5.
 */

public class MyFragment_1 extends Fragment {
    private MyScrollView dingdanMyScrollView;
    private MyListView dingdanMyListView;
    List<ZhuangTai_bean.DataBean> list = new ArrayList<>();
    private MyDingDanAdapter myDingDanAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment_layout, container, false);
        dingdanMyScrollView = view.findViewById(R.id.dingdanMyScrollView);
        dingdanMyListView = view.findViewById(R.id.dingdanMyListView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        dingdanMyScrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView myScrollView, int x, int y, int oldx, int oldy) {
            }
        });
        Map<String, String> params = new HashMap<>();
        params.put("uid", "2776");
        params.put("status", "2");
        params.put("orderId", "1");
        params.put("token", "android");
        RetrofitHelper.getApiService(Api.BASE_URL1).post(Api.liebiao, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Gson gson = new Gson();
                        ZhuangTai_bean zhuangTai_bean = gson.fromJson(s, ZhuangTai_bean.class);
                        if ("0".equals(zhuangTai_bean.getCode())) {
                            String ss = XiaDanActivity.ss;
                            List<ZhuangTai_bean.DataBean> data = zhuangTai_bean.getData();
                            if (ss.equals("全部")) {
                                list.clear();
                                list.addAll(data);
                            } else if (ss.equals("待支付")) {
                                list.clear();
                                for (ZhuangTai_bean.DataBean d : data) {
                                    if (d.getStatus() == 0) {
                                        list.add(d);
                                    }
                                }
                            } else if (ss.equals("已支付")) {
                                list.clear();
                                for (ZhuangTai_bean.DataBean d : data) {
                                    if (d.getStatus() == 1) {
                                        list.add(d);
                                    }
                                }
                            } else if (ss.equals("已取消")) {
                                list.clear();
                                for (ZhuangTai_bean.DataBean d : data) {
                                    if (d.getStatus() == 2) {
                                        list.add(d);
                                    }
                                }
                            }
                            if (list.size() > 0) {
                                setDingDanAdapter();
                            } else {
                                Toast.makeText(getActivity(), "该状态下没有订单", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), zhuangTai_bean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }


                });
    }
   public void setDingDanAdapter(){
        if(myDingDanAdapter==null){
            myDingDanAdapter = new MyDingDanAdapter(getActivity(),list);
            dingdanMyListView.setAdapter(myDingDanAdapter);
        }else{
            myDingDanAdapter.notifyDataSetChanged();
        }

    }
}
