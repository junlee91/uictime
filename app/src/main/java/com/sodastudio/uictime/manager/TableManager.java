package com.sodastudio.uictime.manager;

import android.content.Context;

import com.sodastudio.uictime.model.DetailCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 8/1/2017.
 */

public class TableManager {
    static TableManager sTableManager;

    private Context mContext;

    private List<DetailCourse> mMonday;
    private List<DetailCourse> mTuesday;
    private List<DetailCourse> mWednesday;
    private List<DetailCourse> mThursday;
    private List<DetailCourse> mFriday;

    public static TableManager getInstance(Context context){
        if(sTableManager == null)
            sTableManager = new TableManager(context);

        return sTableManager;
    }

    private TableManager(Context context){
        mContext = context;

        mMonday = new ArrayList<>();
        mTuesday = new ArrayList<>();
        mWednesday = new ArrayList<>();
        mThursday = new ArrayList<>();
        mFriday = new ArrayList<>();
    }

    public List<DetailCourse> getMonday() {
        return mMonday;
    }

    public List<DetailCourse> getTuesday() {
        return mTuesday;
    }

    public List<DetailCourse> getWednesday() {
        return mWednesday;
    }

    public List<DetailCourse> getThursday() {
        return mThursday;
    }

    public List<DetailCourse> getFriday() {
        return mFriday;
    }


}
