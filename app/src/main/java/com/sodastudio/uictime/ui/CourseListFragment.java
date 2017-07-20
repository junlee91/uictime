package com.sodastudio.uictime.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sodastudio.uictime.CourseManager;
import com.sodastudio.uictime.R;
import com.sodastudio.uictime.model.Course;

import java.util.List;

/**
 * Created by Jun on 7/20/2017.
 */

public class CourseListFragment extends Fragment {

    private static final String COURSE_SELECTOR = "CourseSelector";

    private RecyclerView mRecyclerView;
    private CourseAdapter mAdapter;

    private Button mselectButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_courselist, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.course_list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mselectButton = (Button)view.findViewById(R.id.select_button);
        mselectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                CoursePickerFragment dialog = new CoursePickerFragment();
                dialog.show(manager, COURSE_SELECTOR);
            }
        });


        updateUI();

        return view;
    }

    private void updateUI(){

        CourseManager courseManager = CourseManager.getInstance(getActivity());
        List<Course> courses = courseManager.getCourses();

        mAdapter = new CourseAdapter(courses);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class CourseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private Course mCourse;
        private TextView subjectText;
        private TextView numberText;
        private TextView titleText;
        private Button addButton;

        public CourseHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);

            subjectText = (TextView)itemView.findViewById(R.id.course_subject_text);
            numberText = (TextView)itemView.findViewById(R.id.course_number_text);
            titleText = (TextView)itemView.findViewById(R.id.course_title_text);
            addButton = (Button)itemView.findViewById(R.id.course_add_button);

            addButton.setVisibility(View.INVISIBLE);
            addButton.setActivated(false);
        }

        public void bindCourse(Course course){
            mCourse = course;

            subjectText.setText(mCourse.getSubject());
            numberText.setText(String.valueOf(mCourse.getNumber()));
            titleText.setText(mCourse.getTitle());
        }

        @Override
        public void onClick(View v) {

            if( addButton.isActivated() )
            {
                addButton.setActivated(false);
                addButton.setVisibility(View.INVISIBLE);
            }
            else
            {
                addButton.setActivated(true);
                addButton.setVisibility(View.VISIBLE);
            }


        }
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder>{

        private List<Course> mCourses;

        public CourseAdapter(List<Course> courses){
            mCourses = courses;
        }

        @Override
        public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.course, parent, false);

            return new CourseHolder(view);
        }

        @Override
        public void onBindViewHolder(CourseHolder holder, int position) {
            Course course = mCourses.get(position);

            holder.bindCourse(course);
        }

        @Override
        public int getItemCount() {

            return mCourses.size();
        }

    }
}
