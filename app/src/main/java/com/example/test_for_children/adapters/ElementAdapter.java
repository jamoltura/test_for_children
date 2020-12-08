package com.example.test_for_children.adapters;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test_for_children.R;
import com.example.test_for_children.Test.OnlyTest;
import com.example.test_for_children.Test.Test;
import com.example.test_for_children.interfaces.ElementListEvent;
import com.example.test_for_children.ui.elements.ElemetntsFragment;

public class ElementAdapter extends BaseAdapter {

    private static final String TAG = "myLogs";

    private final LayoutInflater lInflater;
    private final Test test;
    private ElementListEvent elementListEvent;
    private boolean[] selections;

    public ElementAdapter(LayoutInflater lInflater, Test test, ElemetntsFragment elemetntsFragment) {
        this.lInflater = lInflater;
        this.test = test;
        elementListEvent = elemetntsFragment;
        this.selections = new boolean[test.getOnlyTests().length];
    }

    public void switchSelection(int position){

        int count = selections.length;

        if (count > 0) {

            for (int i = 0; i < count; i++) {
                if (i != position){
                    selections[i] = false;
                }else {
                    selections[i] = true;
                }
            }
            //оповещаем адаптер об изменениях, чтобы он обновил все элементы списка.
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return test.getOnlyTests().length;
    }

    @Override
    public Object getItem(int position) {
        return test.getOnlyTests()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (convertView == null){
            convertView = lInflater.inflate(R.layout.base_item, parent, false);
        }

        boolean isSelected = selections[position];

        TextView textView = convertView.findViewById(R.id.element_text_finish);

        final OnlyTest onlyTest = (OnlyTest) getItem(position);

        String value = onlyTest.getQuestion();

        textView.setText(value);

        if (isSelected){
            textView.setBackgroundResource(R.drawable.text_question_select);
            elementListEvent.elementClick(onlyTest);
        }else {
            textView.setBackgroundResource(R.drawable.text_question);
        }

        return convertView;
    }
}
