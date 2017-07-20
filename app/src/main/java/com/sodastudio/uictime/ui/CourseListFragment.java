package com.sodastudio.uictime.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sodastudio.uictime.R;

/**
 * Created by Jun on 7/20/2017.
 */

public class CourseListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CourseAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_courselist, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.course_list_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){

        mAdapter = new CourseAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private class CourseHolder extends RecyclerView.ViewHolder{

        public TextView mCourseTextView;

        public CourseHolder(View itemView){
            super(itemView);

            mCourseTextView = (TextView)itemView;
        }
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder>{

        // private List<Course> mCourses;

        @Override
        public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(android.R.layout.simple_list_item_1, parent, false);

            return new CourseHolder(view);
        }

        @Override
        public void onBindViewHolder(CourseHolder holder, int position) {
            holder.mCourseTextView.setText("This is test 1");
            holder.mCourseTextView.setText("This is test 2");
            holder.mCourseTextView.setText("This is test 3");
            holder.mCourseTextView.setText("This is test 4");
        }

        @Override
        public int getItemCount() {

            return 4;
        }

    }
}
