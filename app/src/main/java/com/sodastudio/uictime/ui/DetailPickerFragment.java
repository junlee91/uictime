package com.sodastudio.uictime.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sodastudio.uictime.R;
import com.sodastudio.uictime.model.Course;
import com.sodastudio.uictime.model.DetailCourse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Jun on 7/24/2017.
 */

public class DetailPickerFragment extends DialogFragment {

    private static final String ARG_COURSE = "course";

    private int mTerm;          // Fall 2017
    private String mSubject;    // CS
    private int mNumber;        // 141
    private String mTitle;      // Program Desgin II
    private String mCredits;    // 3 Hours

    private TextView mSelectedCourseView;
    private RecyclerView mRecyclerView;
    private DetailCourseAdapter mAdapter;

    public static DetailPickerFragment newInstance(Course course){
        Bundle args = new Bundle();
        args.putParcelable(ARG_COURSE, course);

        DetailPickerFragment fragment = new DetailPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Course course = getArguments().getParcelable(ARG_COURSE);

        mTerm = course.getTerm();
        mSubject = course.getSubject();
        mNumber = course.getNumber();
        mTitle = course.getTitle();
        mCredits = course.getCredits();

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_detail_selector, null);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.detail_list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSelectedCourseView = (TextView)v.findViewById(R.id.selected_subject_text);
        mSelectedCourseView.setText(mSubject + " " + mNumber + " " + mTitle + "\n" + mCredits);

        new BackgroundTask().execute();

        updateUI();
        
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

    private void updateUI(){
        DetailCourseManager detailCourseManager = DetailCourseManager.getInstance(getActivity());
        List<DetailCourse> detailCourses = detailCourseManager.getDetailCourses();

        mAdapter = new DetailCourseAdapter(detailCourses);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class DetailCourseHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        private DetailCourse mCourse;
        private TextView CRNText;
        private TextView SectionText;
        private TextView InstructorText;
        private TextView DaysText;
        private TextView TimeText;
        private TextView RoomText;
        private Button addButton;

        private DetailCourseHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            CRNText = (TextView)itemView.findViewById(R.id.detail_crn_text);
            SectionText = (TextView)itemView.findViewById(R.id.detail_section_text);
            InstructorText = (TextView)itemView.findViewById(R.id.detail_instructor_text);
            DaysText = (TextView)itemView.findViewById(R.id.detail_days_text);
            TimeText = (TextView)itemView.findViewById(R.id.detail_time_text);
            RoomText = (TextView)itemView.findViewById(R.id.detail_room_text);

            addButton = (Button)(TextView)itemView.findViewById(R.id.detail_add_button);
            addButton.setVisibility(View.INVISIBLE);
            addButton.setActivated(false);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        private void bindCourse(DetailCourse course){
            mCourse = course;

            CRNText.setText(String.valueOf( mCourse.getCRN()));
            SectionText.setText(mCourse.getType());
            InstructorText.setText(mCourse.getInstructor());
            DaysText.setText(mCourse.getDays());
            TimeText.setText(mCourse.getTime());
            RoomText.setText(mCourse.getRoom());
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

    private class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseHolder>{

        private List<DetailCourse> mDetailCourses;
        private DetailCourseAdapter(List<DetailCourse> courses){
            mDetailCourses = courses;
        }

        @Override
        public DetailCourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.detail_course, parent, false);

            return new DetailCourseHolder(view);
        }

        @Override
        public void onBindViewHolder(DetailCourseHolder holder, int position) {
            DetailCourse course = mDetailCourses.get(position);

            holder.bindCourse(course);
        }

        @Override
        public int getItemCount() {
            return mDetailCourses.size();
        }
    }

    private class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute(){
            try {
                target = "http://junlee7.cafe24.com/uictime/DetailList.php?courseSubject=" + URLEncoder.encode(mSubject, "UTF-8")
                        + "&courseNumber=" + URLEncoder.encode(String.valueOf(mNumber), "UTF-8");
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
                // TODO:: Parse json result
//                AlertDialog dialog;
//                AlertDialog.Builder builder = new AlertDialog.Builder(DetailPickerFragment.this.getContext());
//                dialog = builder.setMessage(result)
//                        .setPositiveButton("OK", null)
//                        .create();
//                dialog.show();

                DetailCourseManager detailCourseManager = DetailCourseManager.getInstance(getActivity());
                detailCourseManager.clearCourse();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                String detailCRN;
                String detailType;
                String detailDays;
                String detailTime;
                String detailRoom;
                String detailInstructor;

                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    detailCRN = object.getString("detailCRN");
                    detailType = object.getString("detailType");
                    detailDays = object.getString("detailDays");
                    detailTime = object.getString("detailTime");
                    detailRoom = object.getString("detailRoom");
                    detailInstructor = object.getString("detailInstructor");

                    DetailCourse detailCourse = new DetailCourse(mTerm, mSubject, mNumber, mTitle, mCredits,
                            Integer.valueOf(detailCRN), detailType, detailDays, detailTime, detailRoom, detailInstructor);

                    detailCourseManager.addDetailCourse(detailCourse);
                    count++;
                }

                if(count == 0){
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailPickerFragment.this.getContext());
                    dialog = builder.setMessage("No result found..\nYou can add course manually!\n\nPlease click the button on the right corner.")
                            .setPositiveButton("Ok", null)
                            .create();
                    dialog.show();
                }

                mAdapter.notifyDataSetChanged();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
