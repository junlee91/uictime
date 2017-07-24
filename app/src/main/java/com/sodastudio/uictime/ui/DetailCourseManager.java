package com.sodastudio.uictime.ui;

import android.content.Context;

import com.sodastudio.uictime.model.DetailCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 7/24/2017.
 */

public class DetailCourseManager {

    private static DetailCourseManager sDetailCourseManager;
    private List<DetailCourse> mDetailCourses;

    private Context mContext;

    public static DetailCourseManager getInstance(Context context){
        if(sDetailCourseManager == null){
            sDetailCourseManager = new DetailCourseManager(context);
        }

        return sDetailCourseManager;
    }

    private DetailCourseManager(Context context){
        mContext = context;

        mDetailCourses = new ArrayList<>();
    }

    public List<DetailCourse> getDetailCourses(){
        return mDetailCourses;
    }

    public void addDetailCourse(DetailCourse detailCourse){
        mDetailCourses.add(detailCourse);
    }

    public void clearCourse(){
        mDetailCourses.clear();
    }
}
