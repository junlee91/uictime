package com.sodastudio.uictime;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.sodastudio.uictime.ui.CourseListFragment;

/**
 * Created by Jun on 7/20/2017.
 */

public class CourseListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CourseListFragment();
    }


    // This part will be moved to MainActivity
    private long lastTimeBackPressed;

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500)
        {
            finish();
            return;
        }

        Toast.makeText(this, "Press back button to end the program", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
