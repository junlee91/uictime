package com.sodastudio.uictime.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.sodastudio.uictime.R;

/**
 * Created by Jun on 7/27/2017.
 */

public class CourseAddFragment extends DialogFragment {

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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_course_add, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.add_course_text)
                .create();
    }

}