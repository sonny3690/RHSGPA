package com.app.sonny.rhsgpa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.app.sonny.rhsgpa.slidingtab.SlidingTabLayout;

/**
 * Created by Sonny on 9/7/2015.
 */
public class QuarterFragment extends Fragment {
    ViewPager mPager;
    SlidingTabLayout mSlidingTabLayout;
    ScrollView mScrollView;
    ViewModeAdapter viewModeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.quarter_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new ViewPagerAdapter());

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.tabs);
        mSlidingTabLayout.setViewPager(mPager);

    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 4;
        }

        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Quarter " + String.valueOf(position + 1);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            viewModeAdapter = new ViewModeAdapter(getActivity().getLayoutInflater(), container, false, position);
            View view = viewModeAdapter.getView();

            /*View view = getActivity().getLayoutInflater().inflate(R.layout.view_mode,
                    container, false);*/
            container.addView(view);

            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        viewModeAdapter.saveFile();
    }
}




/*TODO
 * link ViewMode methods with instatiate Item
 * change some color crap
 * put file on github?
 * actionbar title
 */



