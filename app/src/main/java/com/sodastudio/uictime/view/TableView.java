package com.sodastudio.uictime.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.sodastudio.uictime.R;
import com.sodastudio.uictime.manager.TableManager;
import com.sodastudio.uictime.model.DetailCourse;
import com.sodastudio.uictime.ui.ScheduleFragment;

/**
 * Created by Jun on 8/18/2017.
 */

public class TableView extends View {

    private static String TAG = TableView.class.getSimpleName();

    private TableManager mTableManager;
    private Context mContext;

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mTableManager = TableManager.getInstance();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        drawTimeTable(canvas);
    }

    private void drawTimeTable(Canvas canvas){

        float baseX = ScheduleFragment.leftMargin;
        float baseY = dpTopx(17);

        int width = ScheduleFragment.viewWidth;
        int height = ScheduleFragment.viewHeight;

        RectF r = new RectF(baseX, baseY, baseX + width, baseY + height + height);
        Paint p = new Paint();

        p.setColor(Color.RED);
        p.setAlpha(80);
        canvas.drawRect(r, p);

        float x = baseX + (width*2);
        float y = baseY + (height*10);

        RectF r2 = new RectF(x, y, x+width, y+height);
        p.setColor(Color.CYAN);
        canvas.drawRect(r2, p);


        for(DetailCourse detailCourse : mTableManager.getMonday()){

        }
    }

    //dp to px
    private float dpTopx(float dp){
        Resources resources = mContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    private void drawTimeTable(Canvas canvas, int canvasWidth, int canvasHeight){
        RectF r = new RectF(0, 0, 50, 30);
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawRect(r, p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure");

        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        setMeasuredDimension(width, height);
    }
}
