package com.sodastudio.uictime.utils;

import com.sodastudio.uictime.R;

/**
 * Created by Jun on 8/1/2017.
 */

public class UICTimeUtils {

    public static int default_color_length = 10;
    private static int DEFAULT_COLOR[] = {  // 0 - 9
            R.color.colorCourse_1,
            R.color.colorCourse_2,
            R.color.colorCourse_3,
            R.color.colorCourse_4,
            R.color.colorCourse_5,
            R.color.colorCourse_6,
            R.color.colorCourse_7,
            R.color.colorCourse_8,
            R.color.colorCourse_9,
            R.color.colorCourse_10
    };

    public static int getColor(int idx){
        return DEFAULT_COLOR[idx];
    }

    public static int getStartHour(String times){

        String startDay = times.substring(6,8);     // AM or PM

        int startHour = Integer.parseInt(times.substring(0,2));

        if(startDay.equals("PM"))
        {
            if(startHour == 12){
                startHour = 12;
            }
            else
                startHour += 12;
        }

        return startHour;
    }

    public static int getStartMin(String times){
        return Integer.parseInt(times.substring(3,5));
    }

    public static int getEndHour(String times){

        String endDay = times.substring(15,17);     // AM or PM

        int endHour = Integer.parseInt(times.substring(9,11));

        if(endDay.equals("PM"))
        {
            if(endHour == 12){
                endHour = 12;
            }
            else
                endHour += 12;
        }

        return endHour;
    }

    public static int getEndMin(String times){
        return Integer.parseInt(times.substring(12,14));
    }

}


//    String startHour = times.substring(0,2);    // 9
//    String startMin = times.substring(3,5);     // 00
//    String startDay = times.substring(6,8);     // AM
//
//    String endHour = times.substring(9,11);     // 10
//    String endMin = times.substring(12,14);     // 15
//    String endDay = times.substring(15,17);     // PM
//
//
//    int startH = Integer.parseInt(startHour);
//    int startM = Integer.parseInt(startMin);
//
//    int endH = Integer.parseInt(endHour);
//    int endM = Integer.parseInt(endMin);