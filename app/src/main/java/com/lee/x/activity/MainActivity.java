package com.lee.x.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.lee.x.R;
import com.lee.x.adapter.MyFragmentAdapter;
import com.lee.x.fragment.MovieFragment;
import com.lee.x.fragment.MyFragment;
import com.lee.x.fragment.SendFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mNavigationView;

    private ViewPager mViewPager;
    private MyFragmentAdapter adapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentList = new ArrayList<>();
        fragmentList.add(new MovieFragment());
        fragmentList.add(new MyFragment());
        fragmentList.add(new SendFragment());


        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(adapter);

        mNavigationView = (BottomNavigationView) findViewById(R.id.bnv);

        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.item2:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.item3:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mNavigationView.setSelectedItemId(R.id.item1);
                        break;
                    case 1:
                        mNavigationView.setSelectedItemId(R.id.item2);
                        break;
                    case 2:
                        mNavigationView.setSelectedItemId(R.id.item3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}
