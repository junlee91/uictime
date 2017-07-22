package com.sodastudio.uictime;

import android.content.Context;

import com.sodastudio.uictime.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 7/20/2017.
 */

public class CourseManager {

    private static CourseManager sCourseManager;
    private List<Course> mCourses;

    private Context mContext;

    public static CourseManager getInstance(Context context){
        if(sCourseManager == null){
            sCourseManager = new CourseManager(context);
        }

        return sCourseManager;
    }

    private CourseManager(Context context){
        mContext = context.getApplicationContext();

        mCourses = new ArrayList<>();

    }

    public List<Course> getCourses() {
        return mCourses;
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
    }

    public void addCourse(Course course){
        mCourses.add(course);
    }

    public void deleteCourse(Course course){
        mCourses.remove(course);
    }

    public void clearCourse(){
        mCourses.clear();
    }
}
