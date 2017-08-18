package com.sodastudio.uictime.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sodastudio.uictime.manager.TableManager;
import com.sodastudio.uictime.model.DetailCourse;
import com.sodastudio.uictime.ui.ScheduleFragment;

/**
 * Created by Jun on 8/18/2017.
 */

public class TableView extends View {

    private static String TAG = TableView.class.getSimpleName();

    private TableManager mTableManager;

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mTableManager = TableManager.getInstance();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw()");
        drawTimeTable(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void drawTimeTable(Canvas canvas){

        int width = ScheduleFragment.viewWidth;
        int height = ScheduleFragment.viewHeight;
        float x = ScheduleFragment.location[0];
        float y = ScheduleFragment.location[1];

        RectF r = new RectF(x, y, x+width, y+height);
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawRect(r, p);


        for(DetailCourse detailCourse : mTableManager.getMonday()){

        }
    }

    private void drawTimeTable(Canvas canvas, int canvasWidth, int canvasHeight){
        RectF r = new RectF(0, 0, 50, 30);
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawRect(r, p);
    }
}
