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

    public void addToMonday(DetailCourse detailCourse){
        mMonday.add(detailCourse);
    }

    public void addToTuesday(DetailCourse detailCourse){
        mTuesday.add(detailCourse);
    }

    public void addToWednesday(DetailCourse detailCourse){
        mWednesday.add(detailCourse);
    }

    public void addToThursday(DetailCourse detailCourse){
        mThursday.add(detailCourse);
    }

    public void addToFriday(DetailCourse detailCourse){
        mFriday.add(detailCourse);
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

    public void deleteCourse(DetailCourse mCourse){
        if(mMonday.contains(mCourse))
            mMonday.remove(mCourse);

        if(mTuesday.contains(mCourse))
            mTuesday.remove(mCourse);

        if(mWednesday.contains(mCourse))
            mWednesday.remove(mCourse);

        if(mThursday.contains(mCourse))
            mThursday.remove(mCourse);

        if(mFriday.contains(mCourse))
            mFriday.remove(mCourse);
    }


}
