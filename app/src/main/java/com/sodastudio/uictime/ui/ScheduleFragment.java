package com.sodastudio.uictime.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sodastudio.uictime.manager.ScheduleTableManager;
import com.sodastudio.uictime.manager.TableManager;
import com.sodastudio.uictime.utils.CourseLibrary;
import com.sodastudio.uictime.R;
import com.sodastudio.uictime.model.DetailCourse;
import com.sodastudio.uictime.view.TableView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jun on 7/26/2017.
 */

public class ScheduleFragment extends Fragment {

    public static final String TAG = "ScheduleFragment";

    //private Spinner mTermSpinner;
    //private ArrayAdapter mTermAdapter;

    private ScheduleTableManager mScheduleTableManager;
    private TableManager mTableManager;

    private LinearLayout mLinearLayout;
    private RecyclerView mScheduleListView;     // Concise Course Information

    private CourseAdapter mAdapter;
    private ImageButton mConciseViewButton;
    private ImageButton mSaveButton;

    private ImageButton mShareButton;

    private TextView mTotalCreditTextView;

    private FrameLayout mTableLayout;
    private TableView mTableView;

    // TextView
    private TextView mTableMondayTextView;
    private TextView mTableTuesdayTextView;
    private TextView mTableWednesdayTextView;
    private TextView mTableThursdayTextView;
    private TextView mTableFridayTextView;
    private TextView mTableTimeTextView;
    // size
    public static int mondayWidth;
    public static int tuesdayWidth;
    public static int wednesdayWidth;
    public static int thursdayWidth;
    public static int fridayWidth;
    public static int viewHeight;
    public static int leftMargin;

    private TextView mTableTextView;    // testing
    private TextView mTextView;     // testing
    public static int viewWidth;    // testing

    //This is for testing
    private TextView mondayText;
    private TextView tuesdayText;
    private TextView wednesdayText;
    private TextView thursdayText;
    private TextView fridayText;

    private Toast mToast;
    private CourseLibrary mCourseLibrary;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule_view, container, false);

        //mTermAdapter = ArrayAdapter.createFromResource(getContext(), R.array.term, android.R.layout.simple_spinner_dropdown_item);
        //mTermSpinner = (Spinner)view.findViewById(R.id.schedule_term_spinner);
        //mTermSpinner.setAdapter(mTermAdapter);

        mLinearLayout = (LinearLayout)view.findViewById(R.id.schedule_linear_layout);
        mLinearLayout.setVisibility(View.INVISIBLE);
        mScheduleListView = (RecyclerView)view.findViewById(R.id.schedule_list_view);
        mScheduleListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mTotalCreditTextView = (TextView)view.findViewById(R.id.schedule_total_credits_text);

        mConciseViewButton = (ImageButton)view.findViewById(R.id.concise_view_button);
        mConciseViewButton.setActivated(false);

        mConciseViewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!mConciseViewButton.isActivated()){
                    mLinearLayout.setVisibility(View.VISIBLE);
                    mConciseViewButton.setActivated(true);
                    mConciseViewButton.setAlpha(0.4f);
                } else {
                    mLinearLayout.setVisibility(View.INVISIBLE);
                    mConciseViewButton.setActivated(false);
                    mConciseViewButton.setAlpha(1.0f);
                }
            }
        });

        mSaveButton = (ImageButton)view.findViewById(R.id.save_button);
        mShareButton = (ImageButton)view.findViewById(R.id.share_button);
        setSaveShareButtonListener();

        mTableLayout = (FrameLayout)view.findViewById(R.id.table_layout);
        mTableView = (TableView)view.findViewById(R.id.table_view);
        mTableTimeTextView = (TextView)view.findViewById(R.id.time_text);

        mTableMondayTextView = (TextView)view.findViewById(R.id.monday_0);
        mTableTuesdayTextView = (TextView)view.findViewById(R.id.tuesday_0);
        mTableWednesdayTextView = (TextView)view.findViewById(R.id.wednesday_0);
        mTableThursdayTextView = (TextView)view.findViewById(R.id.thursday_0);
        mTableFridayTextView = (TextView)view.findViewById(R.id.friday_0);

        // wait for UI set on screen
        mTableLayout.post(new Runnable() {
            @Override
            public void run() {

                mondayWidth = mTableMondayTextView.getWidth();
                tuesdayWidth = mTableTuesdayTextView.getWidth();
                wednesdayWidth = mTableWednesdayTextView.getWidth();
                thursdayWidth = mTableThursdayTextView.getWidth();
                fridayWidth = mTableFridayTextView.getWidth();

                viewHeight = mTableMondayTextView.getHeight();
                leftMargin = mTableTimeTextView.getWidth();
            }
        });

        // this is for testing
        mondayText = (TextView)view.findViewById(R.id.mondayCourse);
        tuesdayText = (TextView)view.findViewById(R.id.tuesdayCourse);
        wednesdayText = (TextView)view.findViewById(R.id.wednesdayCourse);
        thursdayText = (TextView)view.findViewById(R.id.thursdayCourse);
        fridayText = (TextView)view.findViewById(R.id.fridayCourse);

        mCourseLibrary = new CourseLibrary();
        upDateUI();

        return view;
    }

    private void upDateUI(){

        int term_id = CourseListFragment.TERM_ID;   // get a term value from previous fragment

        // TODO:: get course list with specified term.  onItemSelectedListener? to notify adapter
        // String selected_term = mTermSpinner.getSelectedItem().toString();
        // int selected_term_id = mCourseLibrary.getTermValue(selected_term);

        mScheduleTableManager = ScheduleTableManager.getInstance(getActivity());
        List<DetailCourse> mCourseList = mScheduleTableManager.getSchedules(term_id);

        mTableManager = TableManager.getInstance();
        mTableManager.updateTable(mScheduleTableManager.getSchedules(CourseListFragment.TERM_ID));    // update table

        if(mTotalCreditTextView != null)
            mTotalCreditTextView.setText("Total: " + getTotalCredits(mCourseList) + " Hours");

        Log.d(TAG, "updateUI with new table view");
        tempView();

        mAdapter = new CourseAdapter(mCourseList);
        mScheduleListView.setAdapter(mAdapter);

        mTableView.invalidate();    // redraw table
    }

    private void setSaveShareButtonListener(){
        mSaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(isExternalStorageWritable()){


                    Log.d(TAG, "External Storage is writable");

                    File storageDir = getAlbumStorageDir("/UICTime");
                    File newFile = new File(storageDir.getPath() + "/text.png");    // TODO: file name change to term

                    if(storageDir.exists()){
                        Log.d(TAG, "Directory exists");

                        mTableLayout.setDrawingCacheEnabled(true);
                        mTableLayout.buildDrawingCache();

                        Bitmap cache = mTableLayout.getDrawingCache();

                        try{
                            FileOutputStream fileOutputStream = new FileOutputStream(newFile.getPath());
                            cache.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

                            fileOutputStream.flush();
                            fileOutputStream.close();

                        }catch (Exception e){
                            e.printStackTrace();
                            showToast("Oops! Saving failed.. try again later");
                        }finally {
                            mTableLayout.destroyDrawingCache();
                            scanFile(newFile.getPath());
                            showToast("Table Saved!");
                        }
                    }

                } else {
                    Log.d(TAG, "External Storage is not writable");
                }
            }

        });

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: sharing intent
            }
        });
    }

    private Bitmap layoutToBitmap(){
        Bitmap bitmap = Bitmap.createBitmap(mTableLayout.getWidth(), mTableLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mTableLayout.draw(canvas);
        return bitmap;
    }

    private void scanFile(String path){
        MediaScannerConnection.scanFile(getActivity(),
                new String[]{path}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i(TAG, "Finished Scanning " + path);
                    }
                });
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + albumName);

        if (!file.mkdir()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private class CourseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private DetailCourse mCourse;

        // private TextView mCrnText;
        private TextView mCourseText;
        private TextView mTitleText;
        private TextView mTypeText;
        private TextView mDayText;
        private TextView mTimeText;
        private TextView mRoomText;
        private TextView mInstructorText;
        private Button mDeleteButton;
        private LinearLayout mTypeLayout;


        private CourseHolder(View view){
            super(view);

            view.setOnClickListener(this);

            //mCrnText = (TextView)view.findViewById(R.id.concise_crn_text);
            mCourseText = (TextView)view.findViewById(R.id.concise_course_text);
            mTitleText = (TextView)view.findViewById(R.id.concise_title_text);
            mTypeText = (TextView)view.findViewById(R.id.concise_type_text);
            mTimeText = (TextView)view.findViewById(R.id.concise_time_text);
            mDayText = (TextView)view.findViewById(R.id.concise_day_text);
            mRoomText = (TextView)view.findViewById(R.id.concise_room_text);
            mInstructorText = (TextView)view.findViewById(R.id.concise_instructor_text);
            mTypeLayout = (LinearLayout) view.findViewById(R.id.concise_type_layout);

            mTypeLayout.setVisibility(View.INVISIBLE);

            mDeleteButton = (Button)view.findViewById(R.id.concise_delete_button);
            mDeleteButton.setVisibility(View.INVISIBLE);
            mDeleteButton.setActivated(false);

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if( mScheduleTableManager.deleteSchedule(mCourse) ){
                        showToast(mCourse.getSubject() + " " + mCourse.getNumber() + " " + mCourse.getTitle() + " removed!!");
                        mDeleteButton.setVisibility(View.INVISIBLE);
                        mAdapter.notifyDataSetChanged();
                        upDateUI();

                    }
                    else{
                        showToast("Unable to delete the course.. try again later");
                    }
                }
            });
        }

        public void bindCourse(DetailCourse course){
            mCourse = course;
            String tmp = mCourse.getDays() + " | ";

            //mCrnText.setText(String.valueOf( mCourse.getCRN()));
            mCourseText.setText(mCourse.getSubject() + " " + String.valueOf( mCourse.getNumber()));
            mTitleText.setText(mCourse.getTitle());
            mDayText.setText(tmp);
            mTimeText.setText(mCourse.getTime());
            mRoomText.setText(mCourse.getRoom());
            mInstructorText.setText(mCourse.getInstructor());

            if(mCourse.getInstructor().equals("TBA")){
                mTypeLayout.setVisibility(View.VISIBLE);
                mTypeText.setText(mCourse.getType());
            }
        }

        @Override
        public void onClick(View v) {
            if(mDeleteButton.isActivated())
            {
                mDeleteButton.setActivated(false);
                mDeleteButton.setVisibility(View.INVISIBLE);
            }
            else
            {
                mDeleteButton.setActivated(true);
                mDeleteButton.setVisibility(View.VISIBLE);
            }
        }
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder>{
        private List<DetailCourse> mCourseList;

        private CourseAdapter(List<DetailCourse> list){
            mCourseList = list;
        }

        @Override
        public CourseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.concise_view_detail, parent, false);
            return new CourseHolder(view);
        }

        @Override
        public void onBindViewHolder(CourseHolder holder, int position) {
            DetailCourse course = mCourseList.get(position);
            holder.bindCourse(course);
        }

        @Override
        public int getItemCount() {
            return mCourseList.size();
        }

    }

    private int getTotalCredits(List<DetailCourse> mCourseList){
        // TODO: Need more tests
        String creditText, Type;
        int total = 0;

        Scanner scanner;

        for(DetailCourse course : mCourseList){
            Type = course.getType();
            if(Type.contains("LEC") || Type.contains("LCD")) {

                creditText = course.getCredits();

                scanner = new Scanner(creditText);

                total += scanner.nextInt();
            }
        }

        return total;
    }

    private void showToast(String text) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    public void update(){
        upDateUI();
        //mTableView.invalidate();    // redraw table
    }

    private void tempView(){
        //this is for testing
        String temp = "";
        TableManager tableManager = TableManager.getInstance();
        for(DetailCourse detailCourse : tableManager.getMonday()){
            Log.d(TAG, "tempView: " + detailCourse.getTitle());
            temp += detailCourse.getSubject() + " " + detailCourse.getNumber()+ " " + detailCourse.getTitle() + "\n";
        }
        mondayText.setText("Monday: \n" + temp);

        temp = "";
        for(DetailCourse detailCourse : tableManager.getTuesday()){
            Log.d(TAG, "tempView: " + detailCourse.getTitle());
            temp += detailCourse.getSubject() + " " + detailCourse.getNumber()+ " " + detailCourse.getTitle() + "\n";
        }
        tuesdayText.setText("Tuesday: \n" + temp);

        temp = "";
        for(DetailCourse detailCourse : tableManager.getWednesday()){
            Log.d(TAG, "tempView: " + detailCourse.getTitle());
            temp += detailCourse.getSubject() + " " + detailCourse.getNumber()+ " " + detailCourse.getTitle() + "\n";
        }
        wednesdayText.setText("Wednesday: \n" + temp);

        temp = "";
        for(DetailCourse detailCourse : tableManager.getThursday()){
            Log.d(TAG, "tempView: " + detailCourse.getTitle());
            temp += detailCourse.getSubject() + " " + detailCourse.getNumber()+ " " + detailCourse.getTitle() + "\n";
        }
        thursdayText.setText("Thursday: \n" + temp);

        temp = "";
        for(DetailCourse detailCourse : tableManager.getFriday()){
            Log.d(TAG, "tempView: " + detailCourse.getTitle());
            temp += detailCourse.getSubject() + " " + detailCourse.getNumber()+ " " + detailCourse.getTitle() + "\n";
        }
        fridayText.setText("Friday: \n" + temp);
    }




}
