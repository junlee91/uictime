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

}
