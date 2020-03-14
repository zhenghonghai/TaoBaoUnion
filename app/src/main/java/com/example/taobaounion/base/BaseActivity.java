package com.example.taobaounion.base;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taobaounion.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mbind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mbind = ButterKnife.bind(this);
        initView();
        initEvent();
    }

    /**
     * 需要覆写
     */
    protected void initEvent() {
    }

    protected abstract void initView();

    protected abstract int getLayoutResId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mbind != null) {
            mbind.unbind();
        }
    }

}
