package com.sodastudio.uictime.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.sodastudio.uictime.R;

/**
 * Created by Jun on 7/29/2017.
 */

public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_TIME =
            "com.sodastudio.uictime.ui.time";

    private TimePicker mStartTimePicker;
    private TimePicker mEndTimePicker;

    public static TimePickerFragment getInstance(){

        TimePickerFragment fragment = new TimePickerFragment();

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time_picker, null);

        mStartTimePicker = (TimePicker)v.findViewById(R.id.dialog_start_time_picker);
        mEndTimePicker = (TimePicker)v.findViewById(R.id.dialog_end_time_picker);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int startHour = mStartTimePicker.getCurrentHour();
                                int startMin = mStartTimePicker.getCurrentMinute();

                                int endHour = mEndTimePicker.getCurrentHour();
                                int endMin = mEndTimePicker.getCurrentMinute();

                                //TODO:: change time format

                                String time = startHour + ":" + startMin + " " + endHour + ":" + endMin;

                                sendResult(Activity.RESULT_OK, time);
                            }
                        })
                .create();
    }

    private void sendResult(int resultCode, String time_text){
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, time_text);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
