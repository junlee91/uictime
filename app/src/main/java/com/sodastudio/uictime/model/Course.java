package com.sodastudio.uictime.model;

/**
 * Created by Jun on 7/20/2017.
 */

public class Course {
    private int mTerm;          // Fall 2017
    private String mSubject;    // CS
    private int mNumber;        // 141
    private String mTitle;      // Program Desgin II
    private String mCredits;    // 3 Hours

    public Course(int term, String subject, int number, String title,String credits) {
        mTerm = term;
        mSubject = subject;
        mNumber = number;
        mTitle = title;
        mCredits = credits;
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

}
