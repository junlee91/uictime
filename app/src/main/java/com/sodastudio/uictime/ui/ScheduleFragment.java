package com.sodastudio.uictime.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

    private ScheduleManager mScheduleManager;

    private Toast mToast;

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

    private class CourseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private DetailCourse mCourse;

        private TextView mCrnText;
        private TextView mCourseText;
        private TextView mTitleText;
        private TextView mDayText;
        private TextView mTimeText;
        private TextView mInstructorText;
        private Button mDeleteButton;

        private CourseHolder(View view){
            super(view);

            view.setOnClickListener(this);

            mCrnText = (TextView)view.findViewById(R.id.concise_crn_text);
            mCourseText = (TextView)view.findViewById(R.id.concise_course_text);
            mTitleText = (TextView)view.findViewById(R.id.concise_title_text);
            mTimeText = (TextView)view.findViewById(R.id.concise_time_text);
            mDayText = (TextView)view.findViewById(R.id.concise_day_text);
            mInstructorText = (TextView)view.findViewById(R.id.concise_instructor_text);

            mDeleteButton = (Button)view.findViewById(R.id.concise_delete_button);
            mDeleteButton.setVisibility(View.INVISIBLE);
            mDeleteButton.setActivated(false);

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mScheduleManager = ScheduleManager.getInstance(getActivity());

                    mScheduleManager.deleteSchedule(mCourse);

                    showToast("Course deleted!!");

                    mDeleteButton.setVisibility(View.INVISIBLE);

                    mAdapter.notifyDataSetChanged();
                }
            });
        }

        public void bindCourse(DetailCourse course){
            mCourse = course;

            mCrnText.setText(String.valueOf( mCourse.getCRN()));
            mCourseText.setText(mCourse.getSubject() + " " + String.valueOf( mCourse.getNumber()));
            mTitleText.setText(mCourse.getTitle());
            mTimeText.setText(mCourse.getTime());
            mDayText.setText(mCourse.getDays());
            mInstructorText.setText(mCourse.getInstructor());
        }

        @Override
        public void onClick(View v) {
            if(mDeleteButton.isActivated())
            {
                mDeleteButton.setActivated(false);
                mDeleteButton.setVisibility(View.INVISIBLE);
            }
            else
            {
                mDeleteButton.setActivated(true);
                mDeleteButton.setVisibility(View.VISIBLE);
            }
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
            View view = layoutInflater.inflate(R.layout.concise_view, parent, false);
            return new CourseHolder(view);
        }

        @Override
        public void onBindViewHolder(CourseHolder holder, int position) {
            DetailCourse course = mCourseList.get(position);
            holder.bindCourse(course);
        }

        @Override
        public int getItemCount() {
            return mCourseList.size();
        }
    }

    private void showToast(String text) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        mToast.show();
    }

}
