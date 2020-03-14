package com.example.taobaounion.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.taobaounion.R;

public class AutoLoopViewPager extends ViewPager {
    private boolean isLoop = false;

    // 切换间隔时长，单位毫秒
    public static final long DEFAULT_DURATION = 3000;
    private long mDuration = DEFAULT_DURATION;


    public AutoLoopViewPager(@NonNull Context context) {
        this(context,null);
    }

    public AutoLoopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 读取属性
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray t  = context.obtainStyledAttributes(attrs, R.styleable.AutoLoopStyle);
        // 获取属性
        mDuration = t.getInteger(R.styleable.AutoLoopStyle_duration, (int) DEFAULT_DURATION);
        //回收
        t.recycle();
    }

    public void startLoop() {
        isLoop = true;
        post(mTask);
    }

    /**
     * 设置切换时长
     * @param duration 时长，单位：毫秒
     */
    public void setDuration(long duration) {
        this.mDuration = duration;
    }

    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            // 先拿到当前的位置
            int currentItem = getCurrentItem();
            currentItem++;
            setCurrentItem(currentItem);
            if (isLoop) {
                postDelayed(this, 3000);
            }
        }
    };

    public void stopLoop() {
        isLoop = false;
        removeCallbacks(mTask);
    }
}
