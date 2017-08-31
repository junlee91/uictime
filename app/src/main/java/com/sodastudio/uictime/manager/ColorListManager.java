package com.sodastudio.uictime.manager;

import android.graphics.Color;

import java.util.HashMap;

/**
 * Created by Jun on 8/31/2017.
 */

public class ColorListManager {

    private static ColorListManager sColorListManager;

                    // CRN, colorIDX
    private HashMap<String, Integer> mColorHashMap;

    public static ColorListManager getInstance(){

        if(sColorListManager == null){
            sColorListManager = new ColorListManager();
        }

        return sColorListManager;
    }

    private ColorListManager(){
        mColorHashMap = new HashMap<>();
    }

    public void setColorHashMap(String courseCRN, int colorIDX){
        mColorHashMap.put(courseCRN, colorIDX);
    }

    public int getColor(String courseCRN){

        if(mColorHashMap.isEmpty()) return 0;

        return mColorHashMap.get(courseCRN);
    }

}
