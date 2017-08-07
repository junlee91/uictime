package com.sodastudio.uictime.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sodastudio.uictime.model.DetailCourse;
import com.sodastudio.uictime.database.ScheduleBaseHelper;
import com.sodastudio.uictime.database.ScheduleCursorWrapper;
import com.sodastudio.uictime.database.ScheduleDbSchema.ScheduleTable;
import com.sodastudio.uictime.ui.CourseListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 8/4/2017.
 */

public class ScheduleTableManager {

    private static ScheduleTableManager sScheduleTableManager;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private TableManager mTableManager;

    public static ScheduleTableManager getInstance(Context context){
        if(sScheduleTableManager == null){
            sScheduleTableManager = new ScheduleTableManager(context);
        }
        return sScheduleTableManager;
    }

    private ScheduleTableManager(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ScheduleBaseHelper(mContext)    // open database
                .getWritableDatabase();

        mTableManager = TableManager.getInstance();
        mTableManager.updateTable(getSchedules(CourseListFragment.TERM_ID));    // update table
    }

    public List<DetailCourse> getSchedules(int termID){
        List<DetailCourse> mCourses = new ArrayList<>();

        ScheduleCursorWrapper cursor = querySchedule(ScheduleTable.Cols.TERMID + "= ?", new String[]{ String.valueOf(termID)});

        try {
            cursor.moveToFirst();
            while( !cursor.isAfterLast() ){
                mCourses.add(cursor.getCourse());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return mCourses;
    }

    public int addSchedule(DetailCourse detailCourse){

        //TODO: duplication check and time conflict check

        ContentValues values = getContentValue(detailCourse);   // get content value by course
        mDatabase.insert(ScheduleTable.NAME, null, values);     // add to database

        updateSchedule(detailCourse);                           // update database

        mTableManager.addToTable(detailCourse);                 // add to table

        return 0; // success
    }

    public boolean deleteSchedule(DetailCourse detailCourse){

        //TODO: more delete condition CRN and title    and success or fail

        mDatabase.delete(ScheduleTable.NAME,
                ScheduleTable.Cols.CRN + "= ?", new String[]{ detailCourse.getCRN() });

        //mTableManager.updateTable(getSchedules(CourseListFragment.TERM_ID));               // delete in Table
        mTableManager.deleteCourse(detailCourse);

        return true; // success
    }

    private void updateSchedule(DetailCourse course){
        ContentValues values = getContentValue(course);

        mDatabase.update(ScheduleTable.NAME, values,
                ScheduleTable.Cols.CRN + "= ?",
                new String[]{ course.getCRN() });
    }

    private static ContentValues getContentValue(DetailCourse course){
        ContentValues values = new ContentValues();

        values.put(ScheduleTable.Cols.TERMID, course.getTerm());        // string
        values.put(ScheduleTable.Cols.SUBJECT, course.getSubject());    // string
        values.put(ScheduleTable.Cols.NUMBER, course.getNumber());      // int
        values.put(ScheduleTable.Cols.TITLE, course.getTitle());        // string
        values.put(ScheduleTable.Cols.CREDITS, course.getCredits());    // string
        values.put(ScheduleTable.Cols.CRN, course.getCRN());            // string
        values.put(ScheduleTable.Cols.TYPE, course.getType());          // string
        values.put(ScheduleTable.Cols.DAYS, course.getDays());          // string
        values.put(ScheduleTable.Cols.TIME, course.getTime());          // string
        values.put(ScheduleTable.Cols.ROOM, course.getRoom());          // string
        values.put(ScheduleTable.Cols.INSTR, course.getInstructor());   // string

        return values;
    }

    private ScheduleCursorWrapper querySchedule(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                ScheduleTable.NAME,
                null,               // Column name : if NULL (all columns)
                whereClause,
                whereArgs,
                null,   // group by
                null,   // having
                null    // order by
        );

        return new ScheduleCursorWrapper(cursor);
    }
}
