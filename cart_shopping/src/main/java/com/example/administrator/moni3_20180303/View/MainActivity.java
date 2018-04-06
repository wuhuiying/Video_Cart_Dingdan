package com.example.administrator.moni3_20180303.View;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.moni3_20180303.Model.Bean.XQDataBean;
import com.example.administrator.moni3_20180303.Presenter.Main_Presenter;
import com.example.administrator.moni3_20180303.R;
import com.youth.banner.Banner;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Main_View{


    private ImageView backImage;
    private RelativeLayout relative01;
    private View view;
    private Banner ProductImage;
    private RelativeLayout relative;
    private View view01;
    private TextView title1;
    private TextView yuanJia;
    private TextView youHui;
    private LinearLayout line1;
    private Button goToCart;
    private Button addCart;
    private Main_Presenter main_presenter;

    private JCVideoPlayerStandard jcVideoPlayerStandard;
    String s1="http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jcVideoPlayerStandard= (JCVideoPlayerStandard) findViewById(R.id.js);
        jcVideoPlayerStandard.setUp(s1,jcVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"");
        Glide.with(getApplicationContext()).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640") .into(jcVideoPlayerStandard.thumbImageView);
        initView();

    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    private void initView() {
        relative01 = (RelativeLayout) findViewById(R.id.relative01);
        view = (View) findViewById(R.id.view);
        ProductImage = (Banner) findViewById(R.id.ProductImage);
        relative = (RelativeLayout) findViewById(R.id.relative);
        view01 = (View) findViewById(R.id.view01);
        title1 = (TextView) findViewById(R.id.title1);
        yuanJia = (TextView) findViewById(R.id.yuanJia);
        youHui = (TextView) findViewById(R.id.youHui);
        line1 = (LinearLayout) findViewById(R.id.line1);
        goToCart = (Button) findViewById(R.id.goToCart);
        addCart = (Button) findViewById(R.id.addCart);

        goToCart.setOnClickListener(this);
        addCart.setOnClickListener(this);

        main_presenter = new Main_Presenter(this);
        main_presenter.getDataUrl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goToCart:

                break;
            case R.id.addCart:
              startActivity(new Intent(MainActivity.this,CarActivity.class));
                break;
        }
    }

    @Override
    public void success(final XQDataBean xqDataBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //设置图片 下标为0的图片数据
                String images = xqDataBean.getData().getImages();
                String[] split = images.split("\\|");
                //轮播下面样式属性
                ProductImage.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);//设置圆形指示器与标题
                ProductImage.setIndicatorGravity(Banner.CENTER);//设置指示器位置
                ProductImage.setDelayTime(2000);//设置轮播时间
                ProductImage.isAutoPlay(false);
                //设置图片集合
                ProductImage.setImages(split);//设置图片源
                //设置商品信息显示
                title1.setText(xqDataBean.getData().getTitle());
                yuanJia.setText("原价:￥" + xqDataBean.getData().getPrice());
                //设置原价中间横线（删除线）
                yuanJia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                youHui.setText("优惠价:" + xqDataBean.getData().getBargainPrice());

            }
        });
    }
}
