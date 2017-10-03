package com.sodastudio.uictime.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sodastudio.uictime.utils.CourseLibrary;
import com.sodastudio.uictime.manager.CourseManager;
import com.sodastudio.uictime.R;
import com.sodastudio.uictime.model.Course;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Jun on 7/20/2017.
 */

public class CourseListFragment extends Fragment {

    public static final String TAG = "CourseListFragment";

    private static final String COURSE_SELECTOR = "CourseSelector";
    private static final String DETAIL_SELECTOR = "DetailSelector";

    private static final int COURSE_SELECT = 0;
    private static final int DETAIL_SELECT = 1;

    static final String COURSE_ADD = "CourseAdd";

    private RecyclerView mRecyclerView;
    private View emptyView;
    private ImageView arrowPointer;
    private CourseAdapter mAdapter;

    private ImageButton manAddButton;
    private Button mselectButton;
    private TextView mSelectedText;

    private CourseLibrary mCourseLibrary = new CourseLibrary();
    private int mTerm;
    private String mSubject;

    public static int TERM_ID = 220178; // THIS IS SET TO DEFAULT

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_courselist_view, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.course_list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        emptyView = view.findViewById(R.id.empty_list_view);
        arrowPointer = (ImageView)view.findViewById(R.id.empty_arrow_pointer);

        mselectButton = (Button)view.findViewById(R.id.select_button);
        mSelectedText = (TextView)view.findViewById(R.id.selected_term_text);
        manAddButton = (ImageButton)view.findViewById(R.id.list_man_add_button);

        setButtonClickListener();

        updateUI();

        return view;
    }

    private void updateUI(){

        CourseManager courseManager = CourseManager.getInstance(getActivity());
        List<Course> courses = courseManager.getCourses();

        if(courses.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            arrowPointer.setVisibility(View.VISIBLE);
        }
        else{
            emptyView.setVisibility(View.INVISIBLE);
            arrowPointer.setVisibility(View.INVISIBLE);
        }

        mAdapter = new CourseAdapter(courses);
        mRecyclerView.setAdapter(mAdapter);

        mSelectedText.setText("Selected Term: " + mCourseLibrary.getTermString(TERM_ID));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == COURSE_SELECT){
            String term = (String)data.getSerializableExtra(CoursePickerFragment.EXTRA_TERM);
            String subject = (String)data.getSerializableExtra(CoursePickerFragment.EXTRA_SUBJECT);

            //Toast.makeText(getActivity(), "Selected: " + term + ", " + subject, Toast.LENGTH_SHORT).show();

            mTerm = mCourseLibrary.getTermValue(term);
            mSubject = mCourseLibrary.getSubjectValue(subject);

            TERM_ID = mTerm;   // to use in other activities

            mSelectedText.setText("Selected Term: " + term);

            if(mSubject != null)
                new BackgroundTask().execute();
            else
            {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(CourseListFragment.this.getContext());
                dialog = builder.setMessage("No result found")
                        .setPositiveButton("Ok", null)
                        .create();
                dialog.show();
            }

            mselectButton.setAlpha(1.0f);
        }

        if(requestCode == DETAIL_SELECT){
            boolean onClick = (boolean)data.getSerializableExtra(DetailPickerFragment.EXTRA_CLICK);

            if(onClick){

            }
        }

    }

    private void setButtonClickListener(){

        mselectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                CoursePickerFragment dialog = new CoursePickerFragment();
                dialog.setTargetFragment(CourseListFragment.this, COURSE_SELECT);
                dialog.show(manager, COURSE_SELECTOR);

                mselectButton.setAlpha(0.4f);
            }
        });

        manAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                CourseAddFragment dialog = new CourseAddFragment();
                dialog.show(manager, COURSE_ADD);
            }
        });
    }

    private class CourseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private Course mCourse;
        private TextView subjectText;
        private TextView numberText;
        private TextView titleText;
        private Button addButton;
        private TextView creditsText;
        private LinearLayout mLinearLayout;

        private CourseHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);

            subjectText = (TextView)itemView.findViewById(R.id.course_subject_text);
            numberText = (TextView)itemView.findViewById(R.id.course_number_text);
            titleText = (TextView)itemView.findViewById(R.id.course_title_text);
            addButton = (Button)itemView.findViewById(R.id.course_add_button);
            creditsText = (TextView)itemView.findViewById(R.id.course_credit_text);

            mLinearLayout = (LinearLayout)itemView.findViewById(R.id.course_view_layout);

            addButton.setVisibility(View.INVISIBLE);
            addButton.setActivated(false);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager manager = getFragmentManager();
                    DetailPickerFragment dialog = DetailPickerFragment.newInstance(mCourse);
                    dialog.setTargetFragment(CourseListFragment.this, DETAIL_SELECT);
                    dialog.show(manager, DETAIL_SELECTOR);
                }
            });
        }

        private void bindCourse(Course course){
            mCourse = course;

            subjectText.setText(mCourse.getSubject());
            numberText.setText(String.valueOf(mCourse.getNumber()));
            titleText.setText(mCourse.getTitle());
            creditsText.setText(" " + mCourse.getCredits().replace(".", ""));
        }

        private void setOnFocusChange(int position){

            if(selected_row_index == position){
                subjectText.setTextColor(getResources().getColor(R.color.colorTextIdle));
                numberText.setTextColor(getResources().getColor(R.color.colorTextIdle));
                titleText.setTextColor(getResources().getColor(R.color.colorTextIdle));
                mLinearLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                creditsText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                addButton.setActivated(true);
                addButton.setVisibility(View.VISIBLE);
            }
            else
            {
                subjectText.setTextColor(getResources().getColor(R.color.colorAccent));
                numberText.setTextColor(getResources().getColor(R.color.colorAccent));
                titleText.setTextColor(getResources().getColor(R.color.colorAccent));
                mLinearLayout.setBackgroundColor(getResources().getColor(R.color.colorCourseListIdle));
                creditsText.setBackgroundColor(getResources().getColor(R.color.colorCourseTimeIdle));
                addButton.setActivated(false);
                addButton.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onClick(View v) {

            if(getAdapterPosition() == RecyclerView.NO_POSITION) return;

            mAdapter.notifyItemChanged(selected_row_index);
            selected_row_index = getAdapterPosition();
            mAdapter.notifyItemChanged(selected_row_index);
        }
    }

    static int selected_row_index = -1;
    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder>{

        private List<Course> mCourses;
        private CourseAdapter(List<Course> courses){
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
            holder.setOnFocusChange(position);
        }

        @Override
        public int getItemCount() {

            return mCourses.size();
        }

    }

    private class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute(){
            try {
                target = "http://junlee7.cafe24.com/uictime/CourseList.php?courseSubject=" + URLEncoder.encode(mSubject, "UTF-8");
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... params) {

            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputstream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputstream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            }catch (Exception e)            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                CourseManager courseManager = CourseManager.getInstance(getActivity());
                courseManager.clearCourse();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                String courseSubject;
                String courseNumber;
                String courseTitle;
                String courseCredits;

                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseSubject = object.getString("courseSubject");
                    courseNumber = object.getString("courseNumber");
                    courseTitle = object.getString("courseTitle");
                    courseCredits = object.getString("courseCredits");

                    Course course = new Course(mTerm, courseSubject, Integer.valueOf(courseNumber), courseTitle, courseCredits);

                    courseManager.addCourse(course);
                    count++;
                }

                emptyView.setVisibility(View.INVISIBLE);
                arrowPointer.setVisibility(View.INVISIBLE);

                if(count == 0){
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(CourseListFragment.this.getContext());
                    dialog = builder.setMessage("No result found")
                            .setPositiveButton("Ok", null)
                            .create();
                    dialog.show();

                    emptyView.setVisibility(View.VISIBLE);
                    arrowPointer.setVisibility(View.VISIBLE);
                }

                mAdapter.notifyDataSetChanged();    // refresh the course list
                selected_row_index = -1;            // reset row index

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
