package com.sodastudio.uictime.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jun on 7/20/2017.
 */

public class Course implements Parcelable {
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

    protected Course(Parcel in) {
        mTerm = in.readInt();
        mSubject = in.readString();
        mNumber = in.readInt();
        mTitle = in.readString();
        mCredits = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mTerm);
        dest.writeString(mSubject);
        dest.writeInt(mNumber);
        dest.writeString(mTitle);
        dest.writeString(mCredits);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

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
