package com.sodastudio.uictime.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.sodastudio.uictime.R;
import com.sodastudio.uictime.model.Course;

import java.io.Serializable;

/**
 * Created by Jun on 7/24/2017.
 */

public class DetailPickerFragment extends DialogFragment {

    private static final String ARG_COURSE = "course";

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

        Toast.makeText(getActivity(), "Selected: " + course.getSubject() + " " + course.getNumber() + ": " + course.getTitle()
                , Toast.LENGTH_SHORT).show();

        
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.select_detail_course)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
