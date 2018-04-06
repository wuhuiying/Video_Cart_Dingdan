package com.example.administrator.moni3_20180303.View.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.moni3_20180303.Model.Bean.ZhuangTai_bean;
import com.example.administrator.moni3_20180303.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/5.
 */

public class MyDingDanAdapter extends BaseAdapter {
        Context context;
        List<ZhuangTai_bean.DataBean> list;

    public MyDingDanAdapter(Context context, List<ZhuangTai_bean.DataBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=View.inflate(context, R.layout.dingdanbuju,null);
            }
            TextView dingdanTitle = view.findViewById(R.id.dingdanTitle);
            TextView dingdanStute = view.findViewById(R.id.dingdanStute);
            TextView dingdanPrice = view.findViewById(R.id.dingdanPrice);
            TextView dingdanTime = view.findViewById(R.id.dingdanTime);
            Button dingdanAnNiu = view.findViewById(R.id.dingdanAnNiu);
            ZhuangTai_bean.DataBean dataBean = list.get(i);
            dingdanTitle.setText(dataBean.getTitle());
            if(dataBean.getStatus()==0){
                dingdanStute.setText("待支付");
                dingdanAnNiu.setText("取消订单");
            }else if(dataBean.getStatus()==1){
                dingdanStute.setText("已支付");
                dingdanAnNiu.setText("查看订单");
            }else if(dataBean.getStatus()==2){
                dingdanStute.setText("已取消");
                dingdanAnNiu.setText("查看订单");
            }
            dingdanPrice.setText("价格：￥"+dataBean.getPrice());
            dingdanTime.setText(dataBean.getCreatetime());
            return view;
        }
}
