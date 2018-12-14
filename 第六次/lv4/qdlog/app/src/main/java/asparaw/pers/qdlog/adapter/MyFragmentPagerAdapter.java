package asparaw.pers.qdlog.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

import asparaw.pers.qdlog.fragment.LogFragment;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<LogFragment> mFragments;

    private List<String> mTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, List<LogFragment> fragments, List<String> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }
}
