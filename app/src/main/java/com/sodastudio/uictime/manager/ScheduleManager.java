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

    // 8am ~ 7pm
    // day[0] ~ day[3]  : AM    (8~11am)
    // day[4] ~ day[11] : PM    (12~7pm)
    private static String monday[] = new String[12];
    private static String tuesday[] = new String[12];
    private static String wednesday[] = new String[12];
    private static String thursday[] = new String[12];
    private static String friday[] = new String[12];

    public static ScheduleManager getInstance(Context context){
        if(sScheduleManager == null){
            sScheduleManager = new ScheduleManager(context);

            for(int i = 0; i < 12; i++){
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

    public int addSchedule(DetailCourse detailCourse){

        // check for duplicated CRN
        for(DetailCourse course : mCourses){
            if(detailCourse.getCRN() == course.getCRN() && detailCourse.getCRN() != 1000){
                return 1;
            }
        }


        // TODO:: check for time
        if(detailCourse.getDays().equals("TBA") || detailCourse.getTime().equals("TBA")
                || detailCourse.getTime().equals("ARRANGED")){
            return 0;
        }

        // invalid case
        if(!validate(detailCourse.getDays(), detailCourse.getTime())){
            return 2;
        }

        // TODO:: add to schedule table
        addCourseToScheduleTable(detailCourse.getDays(), detailCourse.getTime());

        mCourses.add(detailCourse);
        return 0;
    }

    // day: MWF T MW    time: 03:00 PM 03:50 PM    9:30 AM 9:50 PM
    private void addCourseToScheduleTable(String days, String times){

        for(int i = 0; i < days.length(); i++){
            char day = days.charAt(i);          // M

            switch (day){
                case 'M':
                    updateTime(monday, times);
                    break;
                case 'T':
                    updateTime(tuesday, times);
                    break;
                case 'W':
                    updateTime(wednesday, times);
                    break;
                case 'R':
                    updateTime(thursday, times);
                    break;
                case 'F':
                    updateTime(friday, times);
                    break;
            }
        }
    }

    private void updateTime(String day[], String times){
        String startHour = times.substring(0,2);    // 9
        String startMin = times.substring(3,5);     // 00
        String startDay = times.substring(6,8);     // AM

        String endHour = times.substring(9,11);     // 10
        String endMin = times.substring(12,14);     // 15
        String endDay = times.substring(15,17);     // PM


        int startH = Integer.parseInt(startHour);
        int startM = Integer.parseInt(startMin);

        int endH = Integer.parseInt(endHour);
        int endM = Integer.parseInt(endMin);

        if(startDay.equals("AM")){
            startH -= 8;
        }

        if(startDay.equals("PM")){
            if(startH != 12){
                startH += 4;
            }
            else
            {
                startH = 4;
            }
        }

        if(endDay.equals("AM")){
            endH -= 8;
        }

        if(endDay.equals("PM")){
            if(endH != 12){
                endH += 4;
            }
            else
            {
                endH = 4;
            }
        }

        //TODO: size: 12 -> 24
        if(startM >= 0 && startM < 30){         // class starts at 9:15
            // first half
        }
        else if(startM >= 30 && startM < 60){   // class starts at 9:45
            // second half
        }

        if(endM >= 0 && endM < 30){             // class ends at 3:15
            // first half
        }
        else if(endM >= 30 && endM < 60){       // class ends at 3:45
            // second half
        }

        if(startH < 0 || startH > 11 || endH < 0 ||  endH > 11){
            // error!
            return;
        }

        day[startH] = "start";
        day[endH] = "end";

    }

    private boolean validate(String days, String times){
        boolean isValid = false;

        for(int i = 0; i < days.length(); i++){
            char day = days.charAt(i);          // M

            switch (day){
                case 'M':
                    isValid = checkTime(monday, times);
                    break;
                case 'T':
                    isValid = checkTime(tuesday, times);
                    break;
                case 'W':
                    isValid = checkTime(wednesday, times);
                    break;
                case 'R':
                    isValid = checkTime(thursday, times);
                    break;
                case 'F':
                    isValid = checkTime(friday, times);
                    break;
            }
        }

        return isValid;
    }

    private boolean checkTime(String day[], String times){
        String startHour = times.substring(0,2);    // 9
        String startMin = times.substring(3,5);     // 00
        String startDay = times.substring(6,8);     // AM

        String endHour = times.substring(9,11);     // 10
        String endMin = times.substring(12,14);     // 15
        String endDay = times.substring(15,17);     // PM


        int startH = Integer.parseInt(startHour);
        int startM = Integer.parseInt(startMin);

        int endH = Integer.parseInt(endHour);
        int endM = Integer.parseInt(endMin);

        if(startDay.equals("AM")){
            startH -= 8;
        }

        if(startDay.equals("PM")){
            if(startH != 12){
                startH += 4;
            }
            else
            {
                startH = 4;
            }
        }

        if(endDay.equals("AM")){
            endH -= 8;
        }

        if(endDay.equals("PM")){
            if(endH != 12){
                endH += 4;
            }
            else
            {
                endH = 4;
            }
        }

        if(!day[startH].equals("") || !day[endH].equals("")){
            return false;
        }

        return true;
    }

}
