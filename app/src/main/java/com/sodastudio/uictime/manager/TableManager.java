package com.sodastudio.uictime.manager;

import android.content.Context;
import android.util.Log;

import com.sodastudio.uictime.model.DetailCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 8/1/2017.
 */

public class TableManager {
    private static TableManager sTableManager;

    private List<DetailCourse> mMonday;
    private List<DetailCourse> mTuesday;
    private List<DetailCourse> mWednesday;
    private List<DetailCourse> mThursday;
    private List<DetailCourse> mFriday;

    public static TableManager getInstance(){
        if(sTableManager == null)
            sTableManager = new TableManager();

        return sTableManager;
    }

    private TableManager(){
        mMonday = new ArrayList<>();
        mTuesday = new ArrayList<>();
        mWednesday = new ArrayList<>();
        mThursday = new ArrayList<>();
        mFriday = new ArrayList<>();
    }

    public void updateTable(List<DetailCourse> mCourses){
        Log.d("TableManager", "This is only called once");
        clearTable();

        String days;
        for(DetailCourse course : mCourses){
            days = course.getDays();

            for(int i = 0; i < days.length(); i++){
                char day = days.charAt(i);

                switch (day){
                    case 'M': mMonday.add(course); break;
                    case 'T': mTuesday.add(course); break;
                    case 'W': mWednesday.add(course); break;
                    case 'R': mThursday.add(course); break;
                    case 'F': mFriday.add(course); break;
                }
            }
        }
    }

    public void addToTable(DetailCourse course){
        String days = course.getDays();

        for(int i = 0; i < days.length(); i++){
            char day = days.charAt(i);

            switch (day){
                case 'M': mMonday.add(course); break;
                case 'T': mTuesday.add(course); break;
                case 'W': mWednesday.add(course); break;
                case 'R': mThursday.add(course); break;
                case 'F': mFriday.add(course); break;
            }
        }
    }

    private void clearTable(){
        mMonday.clear();
        mTuesday.clear();
        mWednesday.clear();
        mThursday.clear();
        mFriday.clear();
    }

    public void deleteCourse(DetailCourse mCourse){

        for(DetailCourse course : mMonday){
            if(mCourse.getCRN().equals(course.getCRN())){
                mMonday.remove(course);
                break;
            }
        }
        for(DetailCourse course : mTuesday){
            if(mCourse.getCRN().equals(course.getCRN())){
                mTuesday.remove(course);
                break;
            }
        }
        for(DetailCourse course : mWednesday){
            if(mCourse.getCRN().equals(course.getCRN())){
                mWednesday.remove(course);
                break;
            }
        }
        for(DetailCourse course : mThursday){
            if(mCourse.getCRN().equals(course.getCRN())){
                mThursday.remove(course);
                break;
            }
        }
        for(DetailCourse course : mFriday){
            if(mCourse.getCRN().equals(course.getCRN())){
                mFriday.remove(course);
                break;
            }
        }

    }




//    public void addToMonday(DetailCourse detailCourse){
//        mMonday.add(detailCourse);
//    }
//
//    public void addToTuesday(DetailCourse detailCourse){
//        mTuesday.add(detailCourse);
//    }
//
//    public void addToWednesday(DetailCourse detailCourse){
//        mWednesday.add(detailCourse);
//    }
//
//    public void addToThursday(DetailCourse detailCourse){
//        mThursday.add(detailCourse);
//    }
//
//    public void addToFriday(DetailCourse detailCourse){
//        mFriday.add(detailCourse);
//    }

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
