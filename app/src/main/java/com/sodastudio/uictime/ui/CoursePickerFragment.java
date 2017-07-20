package com.sodastudio.uictime.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sodastudio.uictime.R;

/**
 * Created by Jun on 7/20/2017.
 */

public class CoursePickerFragment extends DialogFragment {

    private Spinner termSpinner;
    private Spinner subjectSpinner;
    private ArrayAdapter mTermAdapter;
    private ArrayAdapter mSubjectAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_course_selector, null);

        termSpinner = (Spinner)v.findViewById(R.id.term_spinner);
        subjectSpinner = (Spinner)v.findViewById(R.id.subject_spinner);

        mTermAdapter = ArrayAdapter.createFromResource(getContext(), R.array.term, android.R.layout.simple_spinner_dropdown_item);
        mSubjectAdapter = ArrayAdapter.createFromResource(getContext(), R.array.subject, android.R.layout.simple_spinner_dropdown_item);

        termSpinner.setAdapter(mTermAdapter);
        subjectSpinner.setAdapter(mSubjectAdapter);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.select_term_and_subject)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
