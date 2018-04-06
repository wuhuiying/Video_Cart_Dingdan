package com.example.administrator.moni3_20180303.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.administrator.moni3_20180303.R;
import com.example.administrator.moni3_20180303.View.Fragment.MyFragment_1;

public class XiaDanActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private FrameLayout frameLayout;
    String[] title={"全部","待支付","已支付","已取消"};
    public static String ss="全部";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xia_dan);
        initView();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new MyFragment_1()).commit();

    }
    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < title.length; i++) {
            //添加tab
            tabLayout.addTab(tabLayout.newTab().setText(title[i]));
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().equals(title[0])){
                    ss=title[0];
                }else if(tab.getText().equals(title[1])){
                    ss=title[1];
                }else if(tab.getText().equals(title[2])){
                    ss=title[2];
                }else if(tab.getText().equals(title[3])){
                    ss=title[3];
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new MyFragment_1()).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
