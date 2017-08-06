package com.sodastudio.uictime.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.sodastudio.uictime.model.DetailCourse;
import com.sodastudio.uictime.database.ScheduleDbSchema.ScheduleTable;

/**
 * Created by Jun on 8/6/2017.
 */

public class ScheduleCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ScheduleCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public DetailCourse getCourse(){
        String termId = getString(getColumnIndex(ScheduleTable.Cols.TERMID));
        String subject = getString(getColumnIndex(ScheduleTable.Cols.SUBJECT));
        int number = getInt(getColumnIndex(ScheduleTable.Cols.NUMBER));
        String title = getString(getColumnIndex(ScheduleTable.Cols.TITLE));
        String credits = getString(getColumnIndex(ScheduleTable.Cols.CREDITS));
        String crn = getString(getColumnIndex(ScheduleTable.Cols.CRN));
        String type = getString(getColumnIndex(ScheduleTable.Cols.TYPE));
        String days = getString(getColumnIndex(ScheduleTable.Cols.DAYS));
        String time = getString(getColumnIndex(ScheduleTable.Cols.TIME));
        String room = getString(getColumnIndex(ScheduleTable.Cols.ROOM));
        String instructor = getString(getColumnIndex(ScheduleTable.Cols.INSTR));

        return new DetailCourse(Integer.valueOf(termId), subject, number, title, credits, Integer.valueOf(crn), type, days, time, room, instructor);
    }
}
