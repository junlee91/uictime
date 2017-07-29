package com.sodastudio.uictime.manager;

import android.content.Context;

import com.sodastudio.uictime.model.DetailCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 7/27/2017.
 */

public class ScheduleManager {

    private static ScheduleManager sScheduleManager;
    private List<DetailCourse> mCourses;

    private Context mContext;

    private static String monday[] = new String[28];
    private static String tuesday[] = new String[28];
    private static String wednesday[] = new String[28];
    private static String thursday[] = new String[28];
    private static String friday[] = new String[28];

    public static ScheduleManager getInstance(Context context){
        if(sScheduleManager == null){
            sScheduleManager = new ScheduleManager(context);

            for(int i = 0; i < 28; i++){
                monday[i] = "";
                tuesday[i] = "";
                wednesday[i] = "";
                thursday[i] = "";
                friday[i] = "";
            }
        }

        return sScheduleManager;
    }

    //TODO:: Check validity before adding to ScheduleManager

    public ScheduleManager(Context context){
        mContext = context;

        mCourses = new ArrayList<>();
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

    public boolean addSchedule(DetailCourse detailCourse){

        // check for duplicated CRN
        for(DetailCourse course : mCourses){
            if(detailCourse.getCRN() == course.getCRN()){
                return false;
            }
        }

        // TODO:: check for time

        // add to schedule table
        addCourseToScheduleTable(detailCourse.getDays(), detailCourse.getTime());

        return mCourses.add(detailCourse);
    }

    // day: MWF T MW    time: 03:00 PM 03:50 PM    9:30 AM 9:50 PM
    private void addCourseToScheduleTable(String days, String times){

        for(int i = 0; i < days.length(); i++){
            char day = days.charAt(i);          // M

            switch (day){
                case 'M': break;
                case 'T': break;
                case 'W': break;
                case 'R': break;
                case 'F': break;
            }
        }
    }

}
