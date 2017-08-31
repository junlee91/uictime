package com.sodastudio.uictime.database;

/**
 * Created by Jun on 8/6/2017.
 */

public class ScheduleDbSchema {
    public static final class ScheduleTable{
        public static final String NAME = "Schedules";

        // Name of Columns
        public static final class Cols{
            public static final String TERMID = "termid";
            public static final String SUBJECT = "subject";
            public static final String NUMBER = "number";
            public static final String TITLE = "title";
            public static final String CREDITS = "credits";
            public static final String CRN = "crn";
            public static final String TYPE = "type";
            public static final String DAYS = "days";
            public static final String TIME = "time";
            public static final String ROOM = "room";
            public static final String INSTR = "instructor";
            public static final String COLOR = "color";
        }

    }
}
