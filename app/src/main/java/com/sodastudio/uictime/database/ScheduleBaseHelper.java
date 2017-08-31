package com.sodastudio.uictime.database;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sodastudio.uictime.database.ScheduleDbSchema.ScheduleTable;

/**
 * Created by Jun on 8/6/2017.
 */

public class ScheduleBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static String DATABASE_NAME = "scheduleBase.db";

    public ScheduleBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ScheduleTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ScheduleTable.Cols.TERMID + ", " +
                ScheduleTable.Cols.SUBJECT + ", " +
                ScheduleTable.Cols.NUMBER + ", " +
                ScheduleTable.Cols.TITLE + ", " +
                ScheduleTable.Cols.CREDITS + ", " +
                ScheduleTable.Cols.CRN + ", " +
                ScheduleTable.Cols.TYPE + ", " +
                ScheduleTable.Cols.DAYS + ", " +
                ScheduleTable.Cols.TIME + ", " +
                ScheduleTable.Cols.ROOM + ", " +
                ScheduleTable.Cols.INSTR + ", " +
                ScheduleTable.Cols.COLOR +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
