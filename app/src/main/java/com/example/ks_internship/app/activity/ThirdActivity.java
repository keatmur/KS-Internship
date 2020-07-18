package com.example.ks_internship.app.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.ks_internship.R;
import com.example.ks_internship.app.base.BaseActivity;
import com.example.ks_internship.app.fragment.FirstFragment;
import com.example.ks_internship.app.fragment.SecondFragment;
import com.example.ks_internship.app.fragment.ThirdFragment;
import com.example.ks_internship.app.utils.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ThirdActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initToolbarWithNavigation(getString(R.string.app_name));

        initViews();

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();

        adapter.addFragment(firstFragment, "First Cat");
        adapter.addFragment(secondFragment, " Second Cat");
        adapter.addFragment(thirdFragment, "Third Cat");

        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        getToolbar().setTitle(adapter.getPageTitle(0));

        setListeners();
    }

    private void initViews() {

        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

    }

    private void setListeners() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getToolbar().setTitle(adapter.setTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}