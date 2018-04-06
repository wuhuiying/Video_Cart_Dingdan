package com.example.administrator.moni3_20180303.Presenter;

import com.example.administrator.moni3_20180303.Model.Bean.XQDataBean;
import com.example.administrator.moni3_20180303.Model.Main_Model;
import com.example.administrator.moni3_20180303.View.MainActivity;
import com.example.administrator.moni3_20180303.View.Main_View;

/**
 * Created by Administrator on 2018/3/3.
 */

public class Main_Presenter implements Main_P{

    private  Main_Model main_model;
    Main_View main_view;

    public Main_Presenter(Main_View main_view) {
        this.main_view = main_view;
        main_model = new Main_Model(this);
    }
    public void getDataUrl() {
        main_model.getDataUrl();
    }

    @Override
    public void success(XQDataBean xqDataBean) {
        main_view.success(xqDataBean);
    }
}
