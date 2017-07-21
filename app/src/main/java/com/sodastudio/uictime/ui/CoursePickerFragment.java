package com.sodastudio.uictime.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

    public static final String EXTRA_TERM =
            "com.sodastudio.uictime.course.term";
    public static final String EXTRA_SUBJECT =
            "com.sodastudio.uictime.course.subject";

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
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String term = termSpinner.getSelectedItem().toString();
                                String subject = subjectSpinner.getSelectedItem().toString();

                                sendResult(Activity.RESULT_OK, term, subject);
                            }
                        })
                .create();
    }

    private void sendResult(int resultCode, String term, String subject){
        if(getTargetFragment() == null) return;

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TERM, term);
        intent.putExtra(EXTRA_SUBJECT, subject);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
