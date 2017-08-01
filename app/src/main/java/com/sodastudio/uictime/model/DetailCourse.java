package com.sodastudio.uictime.model;

import com.sodastudio.uictime.utils.UICTimeUtils;

import java.util.Date;

/**
 * Created by Jun on 7/22/2017.
 */

public class DetailCourse {
    private int mTerm;          // Fall 2017
    private String mSubject;    // CS
    private int mNumber;        // 141
    private String mTitle;      // Program Desgin II
    private String mCredits;    // 3 Hours
    private int mCRN;
    private String mType;
    private String mDays;
    private String mTime;
    private String mRoom;
    private String mInstructor;
    private Date mStartTime;
    private Date mEndTime;

    public DetailCourse(int term, String subject, int number, String title, String credits, int CRN, String type, String days, String time, String room, String instructor) {
        mTerm = term;
        mSubject = subject;
        mNumber = number;
        mTitle = title;
        mCredits = credits;
        mCRN = CRN;
        mType = type;
        mDays = days;
        mTime = time;
        mRoom = room;
        mInstructor = instructor;

        mStartTime = new Date();
        mEndTime = new Date();

        if( !(mTime.equals("ARRANGED") || mTime.equals("") || mTime.equals("TBA")))
            setDate(mTime);
    }

    private void setDate(String times){
        int startH = UICTimeUtils.getStartHour(times);
        int startM = UICTimeUtils.getStartMin(times);
        int endH = UICTimeUtils.getEndHour(times);
        int endM = UICTimeUtils.getEndMin(times);

        mStartTime.setHours(startH);
        mStartTime.setMinutes(startM);

        mEndTime.setHours(endH);
        mEndTime.setMinutes(endM);
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    public int getTerm() {
        return mTerm;
    }

    public void setTerm(int term) {
        mTerm = term;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getCredits() {
        return mCredits;
    }

    public void setCredits(String credits) {
        mCredits = credits;
    }

    public int getCRN() {
        return mCRN;
    }

    public void setCRN(int CRN) {
        mCRN = CRN;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getDays() {
        if(mDays.equals(" ") || mDays.equals(""))
            return "TBA";
        return mDays;
    }

    public void setDays(String days) {
        mDays = days;
    }

    public String getTime() {
        if(mTime.equals(" ") || mTime.equals(""))
            return "TBA";
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getRoom() {
        if(mRoom.equals(" ") || mRoom.equals(""))
            return "TBA";
        return mRoom;
    }

    public void setRoom(String room) {
        mRoom = room;
    }

    public String getInstructor() {
        if(mInstructor.equals("") || mInstructor.equals(" "))
            return "TBA";
        return mInstructor;
    }

    public void setInstructor(String instructor) {
        mInstructor = instructor;
    }
}
