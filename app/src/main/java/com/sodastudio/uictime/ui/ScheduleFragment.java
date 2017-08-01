package com.sodastudio.uictime.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sodastudio.uictime.utils.CourseLibrary;
import com.sodastudio.uictime.R;
import com.sodastudio.uictime.manager.ScheduleManager;
import com.sodastudio.uictime.model.DetailCourse;

import java.util.List;

/**
 * Created by Jun on 7/26/2017.
 */

public class ScheduleFragment extends Fragment {

    public static final String TAG = "ScheduleFragment";

    private Spinner mTermSpinner;
    private ArrayAdapter mTermAdapter;

    private RecyclerView mScheduleListView;
    private CourseAdapter mAdapter;

    private ImageButton mConciseViewButton;
    private ScheduleManager mScheduleManager;

    private TextView mTotalCreditTextView;

    private Toast mToast;

    private CourseLibrary mCourseLibrary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule_view, container, false);

        mTermAdapter = ArrayAdapter.createFromResource(getContext(), R.array.term, android.R.layout.simple_spinner_dropdown_item);
        mTermSpinner = (Spinner)view.findViewById(R.id.schedule_term_spinner);
        mTermSpinner.setAdapter(mTermAdapter);

        mScheduleListView = (RecyclerView)view.findViewById(R.id.schedule_list_view);
        mScheduleListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTotalCreditTextView = (TextView)view.findViewById(R.id.schedule_total_credits_text);

        mConciseViewButton = (ImageButton)view.findViewById(R.id.concise_view_button);
        mConciseViewButton.setActivated(false);

        mConciseViewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!mConciseViewButton.isActivated()){
                    mScheduleListView.setVisibility(View.VISIBLE);
                    mConciseViewButton.setActivated(true);
                } else {
                    mScheduleListView.setVisibility(View.INVISIBLE);
                    mConciseViewButton.setActivated(false);
                }
            }
        });

        mCourseLibrary = new CourseLibrary();
        upDateUI();

        return view;
    }

    private void upDateUI(){
        int term_id = CourseListFragment.TERM_ID;   // get a term value from previous fragment

        String selected_term = mTermSpinner.getSelectedItem().toString();
        int selected_term_id = mCourseLibrary.getTermValue(selected_term);

        // TODO:: at first show list from term_id
        // TODO:: get course list with specified term.  onItemSelectedListener? to notify adapter

        ScheduleManager scheduleManager = ScheduleManager.getInstance(getActivity());
        List<DetailCourse> mCourseList = scheduleManager.getCourses();

        mTotalCreditTextView.setText("Total: " + getTotalCredits(mCourseList) + " Hours");

        mAdapter = new CourseAdapter(mCourseList);
        mScheduleListView.setAdapter(mAdapter);
    }

    private class CourseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private DetailCourse mCourse;

        private TextView mCrnText;
        private TextView mCourseText;
        private TextView mTitleText;
        private TextView mTypeText;
        private TextView mDayText;
        private TextView mTimeText;
        private TextView mRoomText;
        private TextView mInstructorText;
        private Button mDeleteButton;
        private FrameLayout mTypeLayout;


        private CourseHolder(View view){
            super(view);

            view.setOnClickListener(this);

            mCrnText = (TextView)view.findViewById(R.id.concise_crn_text);
            mCourseText = (TextView)view.findViewById(R.id.concise_course_text);
            mTitleText = (TextView)view.findViewById(R.id.concise_title_text);
            mTypeText = (TextView)view.findViewById(R.id.concise_type_text);
            mTimeText = (TextView)view.findViewById(R.id.concise_time_text);
            mDayText = (TextView)view.findViewById(R.id.concise_day_text);
            mRoomText = (TextView)view.findViewById(R.id.concise_room_text);
            mInstructorText = (TextView)view.findViewById(R.id.concise_instructor_text);
            mTypeLayout = (FrameLayout)view.findViewById(R.id.concise_type_layout);

            mTypeLayout.setVisibility(View.INVISIBLE);

            mDeleteButton = (Button)view.findViewById(R.id.concise_delete_button);
            mDeleteButton.setVisibility(View.INVISIBLE);
            mDeleteButton.setActivated(false);

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mScheduleManager = ScheduleManager.getInstance(getActivity());

                    mScheduleManager.deleteSchedule(mCourse);

                    showToast(mCourse.getSubject() + " " + mCourse.getNumber() + " " + mCourse.getTitle() + " deleted!!");

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
            mRoomText.setText(mCourse.getRoom());
            mInstructorText.setText(mCourse.getInstructor());

            if(mCourse.getInstructor().equals("TBA")){
                mTypeLayout.setVisibility(View.VISIBLE);
                mTypeText.setText(mCourse.getType());
            }
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

    private int getTotalCredits(List<DetailCourse> mCourseList){
        // TODO:: return total credit hours
        return 18;
    }

    private void showToast(String text) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        mToast.show();
    }

}
