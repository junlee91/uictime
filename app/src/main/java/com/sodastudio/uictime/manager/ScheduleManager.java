package com.sodastudio.uictime.manager;

import android.content.Context;

import com.sodastudio.uictime.model.DetailCourse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jun on 7/27/2017.
 */

public class ScheduleManager {

    static ScheduleManager sScheduleManager;

    private List<DetailCourse> mCourses;

    private Context mContext;

    private TableManager mTableManager;


    public static ScheduleManager getInstance(Context context){
        if(sScheduleManager == null){
            sScheduleManager = new ScheduleManager(context);

        }

        return sScheduleManager;
    }

    private ScheduleManager(Context context){
        mContext = context;

        mCourses = new ArrayList<>();
        mTableManager = TableManager.getInstance();
    }

    public List<DetailCourse> getCourses(){
        return mCourses;
    }

    public boolean deleteSchedule(DetailCourse detailCourse){
        return mCourses.remove(detailCourse);
    }

    public void clearCourse(){
        mCourses.clear();
    }

    public int addSchedule(DetailCourse detailCourse){

        mTableManager = TableManager.getInstance();

        // check for duplicated CRN
        for(DetailCourse course : mCourses){
            if(detailCourse.getCRN() == course.getCRN() && !detailCourse.getCRN().equals("9999")){
                return 1;   // CRN duplication
            }
        }

        String days = detailCourse.getDays();
        String mTime = detailCourse.getTime();
        // TODO: Special Case  "TBA" "ARRANGED"
        if(days.equals("ARRANGED") || days.equals("TBA") || days.equals("") ||
                mTime.equals("ARRANGED") || mTime.equals("") || mTime.equals("TBA")){
            mCourses.add(detailCourse);
            return 0;
        }

        // TODO: check for time
        Date startTime = detailCourse.getStartTime();       // 9:00
        Date endTime = detailCourse.getEndTime();           // 9:50

        if( !checkValidateTime(days, startTime, endTime) )
        {
            return 2;       // course time conflict
        }

        updateTableManager(days, detailCourse); // add to TableManager

        mCourses.add(detailCourse);     // add to ScheduleManager
        return 0;                       // success
    }

    private boolean checkValidateTime(String days, Date startTime, Date endTime){

        for(int i = 0; i < days.length(); i++) {
            char day = days.charAt(i);

            switch (day){
                case 'M':
                    for(DetailCourse course : mTableManager.getMonday()){

                        if( startTime.after(course.getStartTime()) && startTime.before(course.getEndTime()) || startTime.equals(course.getStartTime())){
                            return false;
                        }
                        if( endTime.after(course.getStartTime()) && endTime.before(course.getEndTime()) || endTime.equals(course.getEndTime())){
                            return false;
                        }
                    }
                    break;
                case 'T':
                    for(DetailCourse course : mTableManager.getTuesday()){

                        if( startTime.after(course.getStartTime()) && startTime.before(course.getEndTime()) || startTime.equals(course.getStartTime())){
                            return false;
                        }
                        if( endTime.after(course.getStartTime()) && endTime.before(course.getEndTime()) || endTime.equals(course.getEndTime())){
                            return false;
                        }
                    }
                    break;
                case 'W':
                    for(DetailCourse course : mTableManager.getWednesday()){

                        if( startTime.after(course.getStartTime()) && startTime.before(course.getEndTime()) || startTime.equals(course.getStartTime())){
                            return false;
                        }
                        if( endTime.after(course.getStartTime()) && endTime.before(course.getEndTime()) || endTime.equals(course.getEndTime())){
                            return false;
                        }
                    }
                    break;
                case 'R':
                    for(DetailCourse course : mTableManager.getThursday()){

                        if( startTime.after(course.getStartTime()) && startTime.before(course.getEndTime()) || startTime.equals(course.getStartTime())){
                            return false;
                        }
                        if( endTime.after(course.getStartTime()) && endTime.before(course.getEndTime()) || endTime.equals(course.getEndTime())){
                            return false;
                        }
                    }
                    break;
                case 'F':
                    for(DetailCourse course : mTableManager.getFriday()){

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

    private void updateTableManager(String days, DetailCourse detailCourse){
        for(int i = 0; i < days.length(); i++){
            char day = days.charAt(i);          // M

            switch (day){
                case 'M':
                    mTableManager.addToMonday(detailCourse);
                    break;
                case 'T':
                    mTableManager.addToTuesday(detailCourse);
                    break;
                case 'W':
                    mTableManager.addToWednesday(detailCourse);
                    break;
                case 'R':
                    mTableManager.addToThursday(detailCourse);
                    break;
                case 'F':
                    mTableManager.addToFriday(detailCourse);
                    break;
            }
        }
    }

}
