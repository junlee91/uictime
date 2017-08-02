package com.sodastudio.uictime.ui;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sodastudio.uictime.R;
import com.sodastudio.uictime.adapter.NoticeListAdapter;
import com.sodastudio.uictime.model.Notice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 7/26/2017.
 */

public class InfoFragment extends Fragment {

    public static final String TAG = "InfoFragment";

    private ListView notice_list_view;
    private NoticeListAdapter mNoticeListAdapter;
    private List<Notice> mNoticeList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new BackgroundTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info_view, container, false);

        notice_list_view = (ListView)view.findViewById(R.id.notice_list_view);
        mNoticeList = new ArrayList<>();

        //mNoticeList.add(new Notice( "Why Not", "Soda", "2018-05-05"));

        mNoticeListAdapter = new NoticeListAdapter(getContext(), mNoticeList);
        notice_list_view.setAdapter(mNoticeListAdapter);


        return view;
    }

    private class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute(){
            target = "http://junlee7.cafe24.com/uictime/NoticeList.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputstream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputstream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            }catch (Exception e)            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public void onPostExecute(String result) {
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                String noticeContent;
                String noticeName;
                String noticeDate;

                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);

                    noticeContent = object.getString("noticeContent");
                    noticeName = object.getString("noticeName");
                    noticeDate = object.getString("noticeDate");

                    Notice notice = new Notice(noticeContent, noticeName, noticeDate);
                    mNoticeList.add(notice);
                    count++;
                }

                mNoticeListAdapter.notifyDataSetChanged();

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
