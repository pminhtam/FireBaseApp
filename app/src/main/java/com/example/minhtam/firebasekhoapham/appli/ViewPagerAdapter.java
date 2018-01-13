package com.example.minhtam.firebasekhoapham.appli;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Minh Tam on 1/12/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;
    ArrayList<String> tabTitle;
    public ViewPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments, ArrayList<String> tabTitle){
        super(fm);
        this.fragments = fragments;
        this.tabTitle = tabTitle;

    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }
}
