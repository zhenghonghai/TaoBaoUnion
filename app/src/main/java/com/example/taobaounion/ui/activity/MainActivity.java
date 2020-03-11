package com.example.taobaounion.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.taobaounion.R;
import com.example.taobaounion.base.BaseFragment;
import com.example.taobaounion.ui.fragment.HomeFragment;
import com.example.taobaounion.ui.fragment.RedPacketFragment;
import com.example.taobaounion.ui.fragment.SearchFragment;
import com.example.taobaounion.ui.fragment.SelectedFragment;
import com.example.taobaounion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.main_navigation_bar)
    BottomNavigationView mNavigationView;
    private FragmentManager mfm;
    private HomeFragment mhomeFragment;
    private SelectedFragment mselectedFragment;
    private RedPacketFragment mredPacketFragment;
    private SearchFragment msearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        switchFragment(mhomeFragment);
        initListener();

    }

    private void initFragment() {
        mhomeFragment = new HomeFragment();
        mselectedFragment = new SelectedFragment();
        mredPacketFragment = new RedPacketFragment();
        msearchFragment = new SearchFragment();
        mfm = getSupportFragmentManager();
    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                switchFragment(mhomeFragment);
            }else if (item.getItemId() == R.id.selected) {
                switchFragment(mselectedFragment);
            } else if (item.getItemId() == R.id.red_packet) {
                switchFragment(mredPacketFragment);
            }else if (item.getItemId() == R.id.search) {
                switchFragment(msearchFragment);
            }
            return true;
        });
    }

    private void switchFragment(BaseFragment targetFragment) {
        FragmentTransaction transaction = mfm.beginTransaction();
        transaction.replace(R.id.main_page_container, targetFragment);
        transaction.commit();
    }

}
