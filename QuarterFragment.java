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
    ViewPager mPager ;
    SlidingTabLayout mSlidingTabLayout;
    ScrollView mScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.quarter_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPager.setAdapter(new ViewPagerAdapter());

       /* mScrollView = (ScrollView) view.findViewById(R.id.quarterScrollView);*/

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.tabs);
        mSlidingTabLayout.setViewPager(mPager);

       /* mPager.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mPager.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });*/

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
            // Inflate a new layout from our resources


            View view = getActivity().getLayoutInflater().inflate(R.layout.view_mode,
                    container, false);
            // Add the newly created View to the ViewPager
            container.addView(view);


           /* TextView title = (TextView)view.findViewById(R.id.selectLevelText0);
            title.setText("CHANGED!!!!\t" + position );
*/
            // Retrieve a TextView from the inflated View, and update it's text
            //TextView title = (TextView) view.findViewById(R.id.item_title);
            //title.setText("Quarter " + String.valueOf(position + 1));

            // Return the View
            return view;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}


/*TODO
 * link ViewMode methods with instatiate Item
 * change some color crap
 * put file on github?
 *
 */



