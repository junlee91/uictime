package com.sodastudio.uictime.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sodastudio.uictime.R;
import com.sodastudio.uictime.manager.ScheduleManager;
import com.sodastudio.uictime.model.DetailCourse;

import java.util.List;

/**
 * Created by Jun on 7/26/2017.
 */

public class ScheduleFragment extends Fragment {

    public static final String TAG = "ScheduleFragment";

    private RecyclerView mScheduleListView;
    private CourseAdapter mAdapter;

    private ImageButton mImageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule_view, container, false);

        mScheduleListView = (RecyclerView)view.findViewById(R.id.schedule_list_view);
        mScheduleListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mImageButton = (ImageButton)view.findViewById(R.id.concise_view_button);
        mImageButton.setActivated(false);

        mImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!mImageButton.isActivated()){
                    mScheduleListView.setVisibility(View.VISIBLE);
                    mImageButton.setActivated(true);
                } else {
                    mScheduleListView.setVisibility(View.INVISIBLE);
                    mImageButton.setActivated(false);
                }
            }
        });

        upDateUI();

        return view;
    }

    private void upDateUI(){
        ScheduleManager scheduleManager = ScheduleManager.getInstance(getActivity());
        List<DetailCourse> mCourseList = scheduleManager.getCourses();

        mAdapter = new CourseAdapter(mCourseList);
        mScheduleListView.setAdapter(mAdapter);
    }

    private class CourseHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;

        public CourseHolder(View view){
            super(view);

            mTextView = (TextView)view;
        }
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder>{
        private List<DetailCourse> mCourseList;

        public CourseAdapter(List<DetailCourse> list){
            mCourseList = list;
        }

        //TODO:: Customizing list view
        @Override
        public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new CourseHolder(view);
        }

        @Override
        public void onBindViewHolder(CourseHolder holder, int position) {
            DetailCourse course = mCourseList.get(position);
            holder.mTextView.setText(course.getCRN() + " " + course.getSubject() + " " + course.getNumber() + " " + course.getTitle());
        }

        @Override
        public int getItemCount() {
            return mCourseList.size();
        }
    }

}
