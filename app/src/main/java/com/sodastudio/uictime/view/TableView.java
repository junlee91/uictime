package com.sodastudio.uictime.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.sodastudio.uictime.manager.TableManager;
import com.sodastudio.uictime.model.DetailCourse;
import com.sodastudio.uictime.ui.ScheduleFragment;
import com.sodastudio.uictime.utils.UICTimeUtils;

/**
 * Created by Jun on 8/18/2017.
 */

public class TableView extends View {

    private static String TAG = TableView.class.getSimpleName();

    private TableManager mTableManager;
    private Context mContext;

    private float baseXpos;
    private float baseYpos;

    private int mondayWidth;
    private int tuesdayWidth;
    private int wednesdayWidth;
    private int thursdayWidth;
    private int fridayWidth;
    private int viewHeight;

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mTableManager = TableManager.getInstance();

    }

    private void setValues(){
        baseXpos = ScheduleFragment.leftMargin;
        baseYpos = dpTopx(17);

        mondayWidth = ScheduleFragment.mondayWidth;
        tuesdayWidth = ScheduleFragment.tuesdayWidth;
        wednesdayWidth = ScheduleFragment.wednesdayWidth;
        thursdayWidth = ScheduleFragment.thursdayWidth;
        fridayWidth = ScheduleFragment.fridayWidth;

        viewHeight = ScheduleFragment.viewHeight;
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
        int height = viewHeight;

        Paint basePainter = new Paint();
        Paint p = new Paint();

//        // Monday  8-9
//        RectF r = new RectF(baseX, baseY, baseX + width, baseY + height);
//
//        p.setColor(Color.RED);
//        p.setAlpha(80);
//        canvas.drawRect(r, p);
//
//        // Tuesday
//        baseX = baseXpos + getPositionByDay("T");
//        baseY = baseYpos + viewHeight*5;
//        width = tuesdayWidth;
//        height = viewHeight*2;
//
//        RectF r2 = new RectF(baseX, baseY, baseX + width, baseY + height);
//        p.setColor(Color.CYAN);
//        p.setAlpha(80);
//        canvas.drawRect(r2, p);
//
//        // Wednesday
//        baseX = baseXpos + getPositionByDay("W");
//        baseY = baseYpos + viewHeight*15;
//        width = wednesdayWidth;
//        height = viewHeight * 2;
//
//        RectF r3 = new RectF(baseX, baseY, baseX + width, baseY + height);
//
//        p.setColor(Color.CYAN);
//        p.setAlpha(80);
//        canvas.drawRect(r3, p);
        for(DetailCourse detailCourse : mTableManager.getMonday()){

            baseX = baseXpos + getPositionByDay("M");
            baseY = baseYpos + (viewHeight * getPositionByTime(detailCourse));
            width = mondayWidth;
            height = viewHeight * getDuration(detailCourse);

            RectF baseRect = new RectF(baseX, baseY, baseX + width, baseY + height);
            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);

            basePainter.setColor(Color.WHITE);

            p.setColor(detailCourse.getBgColor());
            p.setAlpha(80);

            canvas.drawRect(baseRect, basePainter);
            canvas.drawRect(rectF, p);
        }

        for(DetailCourse detailCourse : mTableManager.getTuesday()){

            baseX = baseXpos + getPositionByDay("T");
            baseY = baseYpos + (viewHeight * getPositionByTime(detailCourse));
            width = tuesdayWidth;
            height = viewHeight * getDuration(detailCourse);  //TODO: duration

            RectF baseRect = new RectF(baseX, baseY, baseX + width, baseY + height);
            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);

            basePainter.setColor(Color.WHITE);

            p.setColor(detailCourse.getBgColor());
            p.setAlpha(80);

            canvas.drawRect(baseRect, basePainter);
            canvas.drawRect(rectF, p);
        }

        for(DetailCourse detailCourse : mTableManager.getWednesday()){

            baseX = baseXpos + getPositionByDay("W");
            baseY = baseYpos + (viewHeight * getPositionByTime(detailCourse));
            width = wednesdayWidth;
            height = viewHeight * getDuration(detailCourse);  //TODO: duration

            RectF baseRect = new RectF(baseX, baseY, baseX + width, baseY + height);
            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);

            basePainter.setColor(Color.WHITE);

            p.setColor(detailCourse.getBgColor());
            p.setAlpha(80);

            canvas.drawRect(baseRect, basePainter);
            canvas.drawRect(rectF, p);
        }

        for(DetailCourse detailCourse : mTableManager.getThursday()){

            baseX = baseXpos + getPositionByDay("R");
            baseY = baseYpos + (viewHeight * getPositionByTime(detailCourse));
            width = thursdayWidth;
            height = viewHeight * getDuration(detailCourse);  //TODO: duration

            RectF baseRect = new RectF(baseX, baseY, baseX + width, baseY + height);
            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);

            basePainter.setColor(Color.WHITE);

            p.setColor(detailCourse.getBgColor());
            p.setAlpha(80);

            canvas.drawRect(baseRect, basePainter);
            canvas.drawRect(rectF, p);
        }

        for(DetailCourse detailCourse : mTableManager.getFriday()){

            baseX = baseXpos + getPositionByDay("F");
            baseY = baseYpos + (viewHeight * getPositionByTime(detailCourse));
            width = fridayWidth;
            height = viewHeight * getDuration(detailCourse);  //TODO: duration

            RectF baseRect = new RectF(baseX, baseY, baseX + width, baseY + height);
            RectF rectF = new RectF(baseX, baseY, baseX + width, baseY + height);

            basePainter.setColor(detailCourse.getBgColor());

            p.setColor(detailCourse.getBgColor());
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(2);

            canvas.drawRect(baseRect, basePainter);
            canvas.drawRect(rectF, p);
        }
    }

    private int getPositionByTime(DetailCourse course){

        int startHour = UICTimeUtils.getStartHour(course.getTime());
        int startMin = UICTimeUtils.getStartMin(course.getTime());

        if(startMin > 0)
            return (startHour-8)*2+1;
        else
            return (startHour-8)*2;
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
}
