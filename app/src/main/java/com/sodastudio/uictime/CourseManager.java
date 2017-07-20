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

        Course c1 = new Course(220178, "CS", 141, "Program Design II",
                "3 Hours", 10050, "LBD", "MWF", "10:00 - 11:00", "LCA1", "Reed, Dale");
        Course c2 = new Course(220178, "CS", 251, "Data Structures",
                "4 Hours", 10050, "LBD", "MWF", "10:00 - 11:00", "LCA1", "Liilis, John");
        Course c3 = new Course(220178, "CS", 211, "Programming Practicum",
                "2 Hours", 10050, "LBD", "MWF", "9:00 - 10:00", "LCA1", "Troy, Patrick");
        Course c4 = new Course(220178, "CS", 151, "Mathematical Computation",
                "3 Hours", 10050, "LBD", "MWF", "15:00 - 16:00", "LCA1", "Hogan, Doug");

        mCourses.add(c1);
        mCourses.add(c2);
        mCourses.add(c3);
        mCourses.add(c4);
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
}
