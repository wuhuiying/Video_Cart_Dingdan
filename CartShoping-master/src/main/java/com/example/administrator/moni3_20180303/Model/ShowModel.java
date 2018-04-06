package com.example.administrator.moni3_20180303.Model;


import android.util.Log;

import com.example.administrator.moni3_20180303.Model.Bean.MyCartBean;
import com.example.administrator.moni3_20180303.Presenter.ShowPImp;
import com.example.administrator.moni3_20180303.Util.RetrofitHelper;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/1/9.
 */

public class ShowModel {
    ShowPImp showPInter;
    public ShowModel(ShowPImp showPInter) {
        this.showPInter=showPInter;
    }

    /*
        * 获取网络数据的方法
        * */
    public void getData(String baseUrl) {
        //https://www.zhaoapi.cn/product/getCarts?uid=2776&&token=0FD73B33D357A6A30ACD546FE320D828&&source=android
        Map<String, String> params = new HashMap<>();
        params.put("uid","2776");
        params.put("source","android");
        RetrofitHelper.getApiService(baseUrl).post("product/getCarts",params)
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
                        MyCartBean myCartBean = gson.fromJson(s, MyCartBean.class);
                        Log.d("+++++++","数据"+s);
                        showPInter.onSuccess(myCartBean);
                    }
                });
    }

}
