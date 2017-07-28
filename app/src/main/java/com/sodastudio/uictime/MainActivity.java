package com.sodastudio.uictime;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import com.sodastudio.uictime.ui.MainFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO:: LoadingActivity with animation
    }

    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }

    private long lastTimeBackPressed;

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500)
        {
            finish();
            return;
        }

        Toast.makeText(this, "Press again to end the UICTime", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
