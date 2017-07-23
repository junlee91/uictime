package com.sodastudio.uictime.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.sodastudio.uictime.R;
import com.sodastudio.uictime.model.Course;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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

    private int mCRN;
    private String mType;
    private String mDays;
    private String mTime;
    private String mRoom;
    private String mInstructor;

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

        new BackgroundTask().execute();
        
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.select_detail_course)
                .setPositiveButton(android.R.string.ok, null)
                .create();
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
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailPickerFragment.this.getContext());
                dialog = builder.setMessage(result)
                        .setPositiveButton("OK", null)
                        .create();
                dialog.show();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
