package com.example.administrator.moni3_20180303.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2018/3/5.
 */

public class MyScrollView extends ScrollView {
    public interface ScrollViewListener{
        void onScrollChanged(MyScrollView myScrollView, int x, int y, int oldx, int oldy);
    }

    private ScrollViewListener scrollViewListener = null;

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if(getScrollY() + getHeight() >=  computeVerticalScrollRange()) {
            if (scrollViewListener != null) {
                scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
            }
        }
    }
}
