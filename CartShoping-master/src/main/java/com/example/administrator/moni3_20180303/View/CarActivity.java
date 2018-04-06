package com.example.administrator.moni3_20180303.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.moni3_20180303.Model.Bean.MyCartBean;
import com.example.administrator.moni3_20180303.Presenter.ShowPresenter;
import com.example.administrator.moni3_20180303.R;
import com.example.administrator.moni3_20180303.Util.Api;
import com.example.administrator.moni3_20180303.View.adapter.MyExpandAdapter;

public class CarActivity extends AppCompatActivity implements ShowVImp{

    private TextView bianji;
    private MyExpandListView expanable_listview;
    private RelativeLayout relative_progress;
    private CheckBox check_all;
    private TextView text_total;
    private TextView text_buy;
    private LinearLayout linear_layout;
    private MyCartBean myCartBean1;
    private ShowPresenter sp;
    private MyExpandAdapter ma;
    private int count;
    private double price;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                count = msg.arg1;
                price = (double) msg.obj;
                //赋值
                text_total.setText("合计：￥"+price);
                text_buy.setText("结算（"+count+")");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        initView();
        check_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调适配器的方法将全选的状态传过去 将组和子的状态改变
                ma.isCheckAllChecked(check_all.isChecked());
            }
        });
        //去除指示器
        expanable_listview.setGroupIndicator(null);
        //获取查询数据的接口
        //https://www.zhaoapi.cn/product/getCarts
        sp = new ShowPresenter(this);

        sp.getData(Api.BASE_URL4);
    }

    private void initView() {
        bianji = (TextView) findViewById(R.id.bianji);
        expanable_listview = (MyExpandListView) findViewById(R.id.expanable_listview);
        relative_progress = (RelativeLayout) findViewById(R.id.relative_progress);
        check_all = (CheckBox) findViewById(R.id.check_all);
        text_total = (TextView) findViewById(R.id.text_total);
        text_buy = (TextView) findViewById(R.id.text_buy);
        linear_layout = (LinearLayout) findViewById(R.id.linear_layout);
        text_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarActivity.this, OrderActivity.class);
                intent.putExtra("price1",price+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(MyCartBean myCartBean) {
        //回传数据
        Log.d("++++++","查询的数据"+myCartBean.toString());
        myCartBean1=myCartBean;
        //根据组中的子条目是否选中  决定该组是否选中初始化一下每一组中isGroupCheck这个数据
        for (int i = 0; i< myCartBean.getData().size(); i++){
            if(isAllChildSelected(i)){
                myCartBean.getData().get(i).setGroupChecked(true);
            }
        }
        //2.根据每一个组是否选中的状态,,,初始化全选是否选中
        check_all.setChecked(isAllGroupChecked());
        //3.将数据显示在expandableListview中  设置适配器
        ma = new MyExpandAdapter(myCartBean,CarActivity.this,handler,sp);
        expanable_listview.setAdapter(ma);
        //展开
        for (int i=0;i<myCartBean.getData().size();i++){
            expanable_listview.expandGroup(i);
        }
        //价格进行改变
        ma.sendPriceAndCount();

    }
    /*
    * 判断所有的组是否选中
    * */
    private boolean isAllGroupChecked() {
        for(int i=0;i<myCartBean1.getData().size();i++){
            if(!myCartBean1.getData().get(i).isGroupChecked())  {
                return false;
            }
        }
        return true;

    }

    /*
    * 判断组里子条目是否都选中
    * */
    private boolean isAllChildSelected(int i) {
        for(int j=0;j<myCartBean1.getData().get(i).getList().size();j++){
            if( myCartBean1.getData().get(i).getList().get(j).getSelected()==0){
                return false;
            }
        }
        return true;

    }

    @Override
    public void onError() {

    }
}
