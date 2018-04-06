package com.example.administrator.moni3_20180303.Presenter;

import com.example.administrator.moni3_20180303.Model.Bean.MyCartBean;
import com.example.administrator.moni3_20180303.Model.ShowModel;
import com.example.administrator.moni3_20180303.View.CarActivity;
import com.example.administrator.moni3_20180303.View.ShowVImp;

/**
 * Created by Administrator on 2018/3/3.
 */


public class ShowPresenter implements ShowPImp {
    ShowVImp showVImp;

    public ShowPresenter(ShowVImp showVImp) {
        this.showVImp = showVImp;
    }

    /*
     * 调model层方法
     * */
    public void getData(String base_url) {
        ShowModel sm = new ShowModel(this);
        sm.getData(base_url);

    }

    @Override
    public void onSuccess(MyCartBean myCartBean) {
        showVImp.onSuccess(myCartBean);
    }

    @Override
    public void onError() {

    }
}
