package com.sodastudio.uictime.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.sodastudio.uictime.R;
import com.sodastudio.uictime.manager.ScheduleTableManager;
import com.sodastudio.uictime.manager.TableManager;
import com.sodastudio.uictime.model.DetailCourse;
import com.sodastudio.uictime.ui.CourseListFragment;
import com.sodastudio.uictime.ui.ScheduleFragment;
import com.sodastudio.uictime.utils.UICTimeUtils;


/**
 * Created by Jun on 8/18/2017.
 */

public class TableView extends View {

    private static String TAG = TableView.class.getSimpleName();

    private int selected_term = CourseListFragment.TERM_ID;

    private ScheduleTableManager mScheduleTableManager;
    private TableManager mTableManager;
    private Context mContext;

    private Paint basePainter;
    private Paint coursePainter;
    private Paint linePainter;
    private Paint textPainter;

    private float baseXpos;
    private float baseYpos;

    private int mondayWidth, mondayHeight;
    private int tuesdayWidth, tuesdayHeight;
    private int wednesdayWidth, wednesdayHeight;
    private int thursdayWidth, thursdayHeight;
    private int fridayWidth, fridayHeight;

    private static int default_color_idx = 0;

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        init();
    }

    private void init() {
        mScheduleTableManager = ScheduleTableManager.getInstance(getContext());
        mTableManager = TableManager.getInstance();

        basePainter = new Paint();
        coursePainter = new Paint();
        linePainter = new Paint();
        textPainter = new Paint();

        basePainter.setColor(Color.WHITE);

        linePainter.setStyle(Paint.Style.STROKE);
        linePainter.setStrokeWidth(2);

        textPainter.setColor(getResources().getColor(R.color.colorUIC_Dark));
        textPainter.setTypeface(Typeface.DEFAULT_BOLD);
        textPainter.setTextAlign(Paint.Align.CENTER);
        textPainter.setTextSize(23);
    }

    private void setValues(){
        baseXpos = ScheduleFragment.leftMargin;
        baseYpos = dpTopx(17);

        mondayWidth = ScheduleFragment.mondayWidth;
        tuesdayWidth = ScheduleFragment.tuesdayWidth;
        wednesdayWidth = ScheduleFragment.wednesdayWidth;
        thursdayWidth = ScheduleFragment.thursdayWidth;
        fridayWidth = ScheduleFragment.fridayWidth;

        mondayHeight = ScheduleFragment.mondayHeight;
        tuesdayHeight = ScheduleFragment.tuesdayHeight;
        wednesdayHeight = ScheduleFragment.wednesdayHeight;
        thursdayHeight = ScheduleFragment.thursdayHeight;
        fridayHeight = ScheduleFragment.fridayHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        setValues();
        drawTimeTable(canvas);
    }

    private void drawTimeTable(Canvas canvas){

        float baseX = baseXpos;
        float baseY = baseYpos;

        int width = mondayWidth;
        int height = mondayHeight;

        int colorIDX = Color.WHITE; // default

        float textX;
        float textY;

        String courseInfo;
        String location;

        for(DetailCourse detailCourse : mTableManager.getMonday()){

            if(detailCourse.getTime().equals("ARRANGED")) continue;

            baseX = baseXpos + getPositionByDay("M") + dpTopx(1);
            baseY = baseYpos + (mondayHeight * getPositionByTime(detailCourse)) + dpTopx(1);
            baseY = setBaseYPosition(detailCourse, baseY);
            width = mondayWidth - (int)dpTopx(2);
            height = mondayHeight * getDuration(detailCourse) - (int)dpTopx(2);

            textX = baseX + (width/2);
            textY = baseY + (height * 0.4f);
            courseInfo = detailCourse.getSubject() + " " + detailCourse.getNumber();
            location = detailCourse.getRoom();

            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);
            mTableManager.updateTableView(rectF, detailCourse);

            colorIDX = getResources().getColor(detailCourse.getBgColor());

            coursePainter.setColor(colorIDX);
            coursePainter.setAlpha(85);
            linePainter.setColor(colorIDX);

            canvas.drawRect(rectF, basePainter);
            canvas.drawRect(rectF, coursePainter);
            canvas.drawRect(rectF, linePainter);
            canvas.drawText(courseInfo, textX, textY, textPainter);
            canvas.drawText(location, textX, baseY + (height * 0.7f), textPainter);
        }

        for(DetailCourse detailCourse : mTableManager.getTuesday()){

            if(detailCourse.getTime().equals("ARRANGED")) continue;

            baseX = baseXpos + getPositionByDay("T") + dpTopx(1);
            baseY = baseYpos + (tuesdayHeight * getPositionByTime(detailCourse)) + dpTopx(1);
            baseY = setBaseYPosition(detailCourse, baseY);
            width = tuesdayWidth - (int)dpTopx(2);
            height = tuesdayHeight * getDuration(detailCourse) - (int)dpTopx(2);

            textX = baseX + (width/2);
            textY = baseY + (height * 0.4f);
            courseInfo = detailCourse.getSubject() + " " + detailCourse.getNumber();
            location = detailCourse.getRoom();

            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);
            mTableManager.updateTableView(rectF, detailCourse);

            colorIDX = getResources().getColor(detailCourse.getBgColor());

            coursePainter.setColor(colorIDX);
            coursePainter.setAlpha(85);
            linePainter.setColor(colorIDX);

            canvas.drawRect(rectF, basePainter);
            canvas.drawRect(rectF, coursePainter);
            canvas.drawRect(rectF, linePainter);
            canvas.drawText(courseInfo, textX, textY, textPainter);
            canvas.drawText(location, textX, baseY + (height * 0.7f), textPainter);
        }

        for(DetailCourse detailCourse : mTableManager.getWednesday()){

            if(detailCourse.getTime().equals("ARRANGED")) continue;

            baseX = baseXpos + getPositionByDay("W") + dpTopx(1);
            baseY = baseYpos + (wednesdayHeight * getPositionByTime(detailCourse)) + dpTopx(1);
            baseY = setBaseYPosition(detailCourse, baseY);
            width = wednesdayWidth - (int)dpTopx(2);
            height = wednesdayHeight * getDuration(detailCourse) - (int)dpTopx(2);

            textX = baseX + (width/2);
            textY = baseY + (height * 0.4f);
            courseInfo = detailCourse.getSubject() + " " + detailCourse.getNumber();
            location = detailCourse.getRoom();

            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);
            mTableManager.updateTableView(rectF, detailCourse);

            colorIDX = getResources().getColor(detailCourse.getBgColor());

            coursePainter.setColor(colorIDX);
            coursePainter.setAlpha(85);
            linePainter.setColor(colorIDX);

            canvas.drawRect(rectF, basePainter);
            canvas.drawRect(rectF, coursePainter);
            canvas.drawRect(rectF, linePainter);
            canvas.drawText(courseInfo, textX, textY, textPainter);
            canvas.drawText(location, textX, baseY + (height * 0.7f), textPainter);
        }

        for(DetailCourse detailCourse : mTableManager.getThursday()){

            if(detailCourse.getTime().equals("ARRANGED")) continue;

            baseX = baseXpos + getPositionByDay("R") + dpTopx(1);
            baseY = baseYpos + (thursdayHeight * getPositionByTime(detailCourse)) + dpTopx(1);
            baseY = setBaseYPosition(detailCourse, baseY);
            width = thursdayWidth - (int)dpTopx(2);
            height = thursdayHeight * getDuration(detailCourse) - (int)dpTopx(2);

            textX = baseX + (width/2);
            textY = baseY + (height * 0.4f);
            courseInfo = detailCourse.getSubject() + " " + detailCourse.getNumber();
            location = detailCourse.getRoom();

            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);
            mTableManager.updateTableView(rectF, detailCourse);

            colorIDX = getResources().getColor(detailCourse.getBgColor());

            coursePainter.setColor(colorIDX);
            coursePainter.setAlpha(85);
            linePainter.setColor(colorIDX);

            canvas.drawRect(rectF, basePainter);
            canvas.drawRect(rectF, coursePainter);
            canvas.drawRect(rectF, linePainter);
            canvas.drawText(courseInfo, textX, textY, textPainter);
            canvas.drawText(location, textX, baseY + (height * 0.7f), textPainter);
        }

        for(DetailCourse detailCourse : mTableManager.getFriday()){

            if(detailCourse.getTime().equals("ARRANGED")) continue;

            baseX = baseXpos + getPositionByDay("F") + dpTopx(1);
            baseY = baseYpos + (fridayHeight * getPositionByTime(detailCourse)) + dpTopx(1);
            baseY = setBaseYPosition(detailCourse, baseY);
            width = fridayWidth - (int)dpTopx(2);
            height = fridayHeight * getDuration(detailCourse) - (int)dpTopx(2);

            textX = baseX + (width/2);
            textY = baseY + (height * 0.4f);
            courseInfo = detailCourse.getSubject() + " " + detailCourse.getNumber();
            location = detailCourse.getRoom();

            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);
            mTableManager.updateTableView(rectF, detailCourse);

            colorIDX = getResources().getColor(detailCourse.getBgColor());

            coursePainter.setColor(colorIDX);
            coursePainter.setAlpha(85);
            linePainter.setColor(colorIDX);

            canvas.drawRect(rectF, basePainter);
            canvas.drawRect(rectF, coursePainter);
            canvas.drawRect(rectF, linePainter);
            canvas.drawText(courseInfo, textX, textY, textPainter);
            canvas.drawText(location, textX, baseY + (height * 0.7f), textPainter);
        }
    }

    private float setBaseYPosition(DetailCourse course, float yPos){

        int startHour = UICTimeUtils.getStartHour(course.getTime());
        int position = (startHour-8)*2;

        float temp = yPos;

        if(8 <= position && position <= 9)
        {
            yPos += dpTopx(1);
        }
        else if(10 <= position && position <= 11)
        {
            yPos += dpTopx(1);
        }
        else if(12 <= position && position <= 13)
        {
            yPos += dpTopx(2);
        }
        else if(14 <= position && position <= 15)
        {
            yPos += dpTopx(2);
        }
        else if(16 <= position && position <= 17)
        {
            yPos += dpTopx(2);
        }
        else if(18 <= position && position <= 19)
        {
            yPos += dpTopx(2);
        }
        else if(20 <= position && position <= 21)
        {
            yPos += dpTopx(3);
        }
        else if(22 <= position && position <= 23)
        {
            yPos += dpTopx(3);
        }

        return yPos;
    }

    private int getPositionByTime(DetailCourse course){

        int startHour = UICTimeUtils.getStartHour(course.getTime());
        int startMin = UICTimeUtils.getStartMin(course.getTime());

        int position = (startHour-8)*2;

        if(startMin > 0)
            return position + 1;
        else
            return position;
    }

    private int getDuration(DetailCourse course){

        long diff = course.getEndTime().getTime() - course.getStartTime().getTime();
        long diffMin = diff / (60 * 1000);

        if(diffMin < 60) return 2;
        else if(diffMin >= 60 && diffMin <= 75) return 3;
        else return 4;

    }

    // get starting base position by day
    private int getPositionByDay(String day){
        switch (day){
            case "M": return 0;
            case "T": return mondayWidth;
            case "W": return mondayWidth + tuesdayWidth;
            case "R": return mondayWidth + tuesdayWidth + wednesdayWidth;
            case "F": return mondayWidth + tuesdayWidth + wednesdayWidth + thursdayWidth;
        }

        return 0;
    }

    //dp to px
    private float dpTopx(float dp){
        Resources resources = mContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_UP){
            Log.d(TAG, "action up");

            DetailCourse detailCourse = mTableManager.contains(x,y);

            if(detailCourse != null){
                Log.d(TAG, "touched: " + detailCourse.getTitle());

                // set new color
                detailCourse.setBgColor(UICTimeUtils.getColor(default_color_idx));

                default_color_idx++;

                if(default_color_idx == UICTimeUtils.default_color_length)
                    default_color_idx = 0;

                mScheduleTableManager.updateSchedule(detailCourse);

                this.invalidate();  // redraw table
            }
        }

        return true;
    }
}
