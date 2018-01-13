package com.example.minhtam.firebasekhoapham.appli;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.minhtam.firebasekhoapham.R;

import java.util.ArrayList;

public class AppActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ArrayList<Fragment> fragments;
    ArrayList<String> tabTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        fragments = new ArrayList<Fragment>();
        tabTitles = new ArrayList<>();
        fragments.add(new HienThiFragment());
        fragments.add(new ChupAnhFragment());
        tabTitles.add("Hiển thị");
        tabTitles.add("Chụp Ảnh");

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments,tabTitles);

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
