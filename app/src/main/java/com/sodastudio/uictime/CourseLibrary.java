package com.sodastudio.uictime;

/**
 * Created by Jun on 7/22/2017.
 */

public class CourseLibrary {

    public int getTermValue(String term){
        if(term.equals("Fall 2017"))
            return 220178;

        return 0;
    }

    public String getSubjectValue(String subject){
        if(subject.equals("Computer Science")) return "CS";

        return null;
    }
}
