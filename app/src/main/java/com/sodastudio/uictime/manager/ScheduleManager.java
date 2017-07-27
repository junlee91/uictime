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
    private List<DetailCourse> mSchedules;

    private Context mContext;

    public static ScheduleManager getInstance(Context context){
        if(sScheduleManager == null){
            sScheduleManager = new ScheduleManager(context);
        }

        return sScheduleManager;
    }

    //TODO:: Check validity before adding to ScheduleManager

    public ScheduleManager(Context context){
        mContext = context;

        mSchedules = new ArrayList<>();
    }

    public List<DetailCourse> getSchedules(){
        return mSchedules;
    }

    public boolean addSchedule(DetailCourse detailCourse){
        return mSchedules.add(detailCourse);
    }

    public boolean deleteSchedule(DetailCourse detailCourse){
        return mSchedules.remove(detailCourse);
    }

    public void clearCourse(){
        mSchedules.clear();
    }

}
