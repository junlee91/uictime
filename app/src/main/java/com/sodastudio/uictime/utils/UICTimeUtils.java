package com.sodastudio.uictime.utils;

/**
 * Created by Jun on 8/1/2017.
 */

public class UICTimeUtils {

    public static int getStartHour(String times){

        String startDay = times.substring(6,8);     // AM or PM

        int startHour = Integer.parseInt(times.substring(0,2));

        if(startDay.equals("PM"))
        {
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