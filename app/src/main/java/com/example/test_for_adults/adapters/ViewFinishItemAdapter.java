package com.example.test_for_adults.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test_for_adults.R;

import java.util.ArrayList;

public class ViewFinishItemAdapter extends BaseAdapter {

    private static final String TAG = "myLogs";

    private final LayoutInflater lInflater;
    private ArrayList<String> list;

    public ViewFinishItemAdapter(ArrayList<String> list, LayoutInflater lInflater) {
        this.lInflater = lInflater;
        this.list = list;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = lInflater.inflate(R.layout.finish_item, parent, false);
        }

        TextView textView = view.findViewById(R.id.text_finish);

        String value = (String) getItem(position);

        textView.setText(value);

        return view;
    }
}
