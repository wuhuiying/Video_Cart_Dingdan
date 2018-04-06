package com.example.administrator.moni3_20180303.Model;

import android.util.Log;

import com.example.administrator.moni3_20180303.Model.Bean.XQDataBean;
import com.example.administrator.moni3_20180303.Presenter.Main_P;
import com.example.administrator.moni3_20180303.Presenter.Main_Presenter;
import com.example.administrator.moni3_20180303.Util.Api;
import com.example.administrator.moni3_20180303.Util.RetrofitHelper;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/3/3.
 */

public class Main_Model {
   Main_P main_p;

    public Main_Model(Main_P main_p) {
        this.main_p = main_p;
    }

    public void getDataUrl() {
       Map<String, String> map=new HashMap<>();
        map.put("uid",2776+"");
        map.put("pid",1+"");
        RetrofitHelper.getApiService(Api.BASE_URL1).get(Api.BASE_URL3)

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
                        XQDataBean xqDataBean = gson.fromJson(s, XQDataBean.class);
                        main_p.success(xqDataBean);
                    }
                });
    }
}
