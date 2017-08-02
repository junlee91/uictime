package com.sodastudio.uictime.adapter;

import android.content.Context;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sodastudio.uictime.R;
import com.sodastudio.uictime.model.Notice;

import java.util.List;

/**
 * Created by Jun on 8/2/2017.
 */

public class NoticeListAdapter extends BaseAdapter {

    private Context context;
    private List<Notice> noticeList;

    public NoticeListAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.notice_view, null);
        TextView noticeText = (TextView)v.findViewById(R.id.notice_text);
        TextView nameText = (TextView)v.findViewById(R.id.name_text);
        TextView dateText = (TextView)v.findViewById(R.id.date_text);

        noticeText.setText(noticeList.get(position).getNotice());
        nameText.setText(noticeList.get(position).getName());
        dateText.setText("   " + noticeList.get(position).getDate());

        v.setTag(noticeList.get(position).getNotice());
        return v;
    }

}
