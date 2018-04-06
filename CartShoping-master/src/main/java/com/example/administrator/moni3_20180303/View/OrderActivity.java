package com.example.administrator.moni3_20180303.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.moni3_20180303.Model.Bean.DingDanBean;
import com.example.administrator.moni3_20180303.R;
import com.example.administrator.moni3_20180303.Util.Api;
import com.example.administrator.moni3_20180303.Util.RetrofitHelper;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity {

    private TextView jie_price;
    private TextView xiadan;
    private String price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
    }

    private void initView() {
        jie_price = (TextView) findViewById(R.id.jie_price);
        xiadan = (TextView) findViewById(R.id.xiadan);
         price1 = getIntent().getStringExtra("price1");

        jie_price.setText("实付款:¥"+ price1);
        xiadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params=new HashMap<>();
                params.put("price",price1);
                params.put("uid","2776");
                params.put("source","android");
                RetrofitHelper.getApiService(Api.base_url).get(Api.chuangjian,params)
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
                                DingDanBean dingDanBean = gson.fromJson(s, DingDanBean.class);
                                if ("0".equals(dingDanBean.getCode())){
                                    Toast.makeText(OrderActivity.this,"创建订单成功",Toast.LENGTH_LONG).show();
                                    Intent intent1 = new Intent(OrderActivity.this, XiaDanActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }else {
                                    Toast.makeText(OrderActivity.this,"创建订单不成功",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
