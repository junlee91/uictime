package com.sodastudio.uictime.manager;

import android.content.Context;
import android.graphics.RectF;
import android.util.Log;

import com.sodastudio.uictime.model.DetailCourse;
import com.sodastudio.uictime.view.TableView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jun on 8/1/2017.
 */

public class TableManager {

    private static String Tag = TableManager.class.getSimpleName();

    private static TableManager sTableManager;

    private List<DetailCourse> mMonday;
    private List<DetailCourse> mTuesday;
    private List<DetailCourse> mWednesday;
    private List<DetailCourse> mThursday;
    private List<DetailCourse> mFriday;

    private List<RectF> mRectFList;
    private HashMap<RectF, DetailCourse> mRectFDetailCourseHashMap;

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

        mRectFList = new ArrayList<>();
        mRectFDetailCourseHashMap = new HashMap<>();
    }

    public void updateTableView(RectF rectF, DetailCourse detailCourse){
        mRectFList.add(rectF);
        mRectFDetailCourseHashMap.put(rectF, detailCourse);

    }

    public DetailCourse contains(float touch_x, float touch_y){

        for(RectF rectF : mRectFList){
            if(rectF.contains(touch_x, touch_y)){

                return mRectFDetailCourseHashMap.get(rectF);
            }
        }
        return null;
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

        mRectFList.clear();
        mRectFDetailCourseHashMap.clear();
    }

    public void deleteCourse(DetailCourse mCourse){

        for(DetailCourse course : mMonday){
            if(mCourse.getCRN().equals(course.getCRN()) && mCourse.getTitle().equals(course.getTitle())){
                mMonday.remove(course);
                break;
            }
        }
        for(DetailCourse course : mTuesday){
            if(mCourse.getCRN().equals(course.getCRN()) && mCourse.getTitle().equals(course.getTitle())){
                mTuesday.remove(course);
                break;
            }
        }
        for(DetailCourse course : mWednesday){
            if(mCourse.getCRN().equals(course.getCRN()) && mCourse.getTitle().equals(course.getTitle())){
                mWednesday.remove(course);
                break;
            }
        }
        for(DetailCourse course : mThursday){
            if(mCourse.getCRN().equals(course.getCRN()) && mCourse.getTitle().equals(course.getTitle())){
                mThursday.remove(course);
                break;
            }
        }
        for(DetailCourse course : mFriday){
            if(mCourse.getCRN().equals(course.getCRN()) && mCourse.getTitle().equals(course.getTitle())){
                mFriday.remove(course);
                break;
            }
        }

    }

    public boolean checkValidTime(DetailCourse detailCourse){
        String days = detailCourse.getDays();
        Date startTime = detailCourse.getStartTime();
        Date endTime = detailCourse.getEndTime();

        for(int i = 0; i < days.length(); i++){
            char day = days.charAt(i);

            switch (day){
                case 'M':
                    for(DetailCourse course : mMonday){

                        if( startTime.after(course.getStartTime()) && startTime.before(course.getEndTime()) || startTime.equals(course.getStartTime())){
                            return false;
                        }
                        if( endTime.after(course.getStartTime()) && endTime.before(course.getEndTime()) || endTime.equals(course.getEndTime())){
                            return false;
                        }
                    }
                    break;
                case 'T':
                    for(DetailCourse course : mTuesday){

                        if( startTime.after(course.getStartTime()) && startTime.before(course.getEndTime()) || startTime.equals(course.getStartTime())){
                            return false;
                        }
                        if( endTime.after(course.getStartTime()) && endTime.before(course.getEndTime()) || endTime.equals(course.getEndTime())){
                            return false;
                        }
                    }
                    break;
                case 'W':
                    for(DetailCourse course : mWednesday){

                        if( startTime.after(course.getStartTime()) && startTime.before(course.getEndTime()) || startTime.equals(course.getStartTime())){
                            return false;
                        }
                        if( endTime.after(course.getStartTime()) && endTime.before(course.getEndTime()) || endTime.equals(course.getEndTime())){
                            return false;
                        }
                    }
                    break;
                case 'R':
                    for(DetailCourse course : mThursday){

                        if( startTime.after(course.getStartTime()) && startTime.before(course.getEndTime()) || startTime.equals(course.getStartTime())){
                            return false;
                        }
                        if( endTime.after(course.getStartTime()) && endTime.before(course.getEndTime()) || endTime.equals(course.getEndTime())){
                            return false;
                        }
                    }
                    break;
                case 'F':
                    for(DetailCourse course : mFriday){

                        if( startTime.after(course.getStartTime()) && startTime.before(course.getEndTime()) || startTime.equals(course.getStartTime())){
                            return false;
                        }
                        if( endTime.after(course.getStartTime()) && endTime.before(course.getEndTime()) || endTime.equals(course.getEndTime())){
                            return false;
                        }
                    }
                    break;
            }
        }

        return true;
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
