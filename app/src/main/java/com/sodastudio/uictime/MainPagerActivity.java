package com.sodastudio.uictime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sodastudio.uictime.ui.CourseListFragment;
import com.sodastudio.uictime.ui.InfoFragment;
import com.sodastudio.uictime.ui.ScheduleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 8/10/2017.
 */

public class MainPagerActivity extends FragmentActivity {

    private ViewPager pager;
    private MyPageAdapter mMyPageAdapter;

    private Button mListButton;
    private Button mScheduleButton;
    private Button mInfoButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        List<Fragment> fragments = getFragments();

        mMyPageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        // Button Setting
        mListButton = (Button)findViewById(R.id.courseButton);
        mScheduleButton = (Button)findViewById(R.id.scheduleButton);
        mInfoButton = (Button)findViewById(R.id.infoButton);
        setButtonClickListener();
        mListButton.callOnClick();

        // ViewPager Setting
        pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(mMyPageAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment fragment = mMyPageAdapter.getItem(position);

                if(fragment instanceof ScheduleFragment){
                    ((ScheduleFragment)fragment).update();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setButtonClickListener(){
        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mScheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mInfoButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                if(pager != null)
                    pager.setCurrentItem(0, true);
            }
        });

        mScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mScheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mInfoButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                if(pager != null)
                    pager.setCurrentItem(1, true);
            }
        });

        mInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mScheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mInfoButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                if(pager != null)
                    pager.setCurrentItem(2, true);
            }
        });
    }

    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<>();

        fList.add(new CourseListFragment());
        fList.add(new ScheduleFragment());
        fList.add(new InfoFragment());

        return fList;
    }

    private class MyPageAdapter extends FragmentStatePagerAdapter{
        private List<Fragment> mFragments;

        private MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.mFragments.get(position);
        }
        @Override
        public int getCount() {
            return this.mFragments.size();
        }

    }

    private long lastTimeBackPressed;

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500)
        {
            finish();
            return;
        }

        Toast.makeText(this, "Press again to end the UICTime", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }

}
