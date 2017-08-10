package com.sodastudio.uictime.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sodastudio.uictime.R;

/**
 * Created by Jun on 7/26/2017.
 */

public class MainFragment extends Fragment {

    public static final String TAG = "MainFragment";

    private Button mListButton;
    private Button mScheduleButton;
    private Button mInfoButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_view, container, false);

        mListButton = (Button)view.findViewById(R.id.courseButton);
        mScheduleButton = (Button)view.findViewById(R.id.scheduleButton);
        mInfoButton = (Button)view.findViewById(R.id.infoButton);

        setButtonClickListener();

        mListButton.callOnClick();

        return view;
    }

    private void setButtonClickListener(){
        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mScheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mInfoButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseListFragment());
                fragmentTransaction.commit();
            }
        });

        mScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mScheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mInfoButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ScheduleFragment());
                fragmentTransaction.commit();
            }
        });

        mInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mScheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                mInfoButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new InfoFragment());
                fragmentTransaction.commit();
            }
        });
    }
}
