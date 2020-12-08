package com.example.test_for_children.ui.elements;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test_for_children.MainActivity;
import com.example.test_for_children.R;
import com.example.test_for_children.Test.OnlyTest;
import com.example.test_for_children.Test.Test;
import com.example.test_for_children.adapters.ElementAdapter;
import com.example.test_for_children.interfaces.ElementListEvent;
import com.example.test_for_children.interfaces.NavigationEvents;
import com.example.test_for_children.viewmodels.TestViewModel;

public class ElemetntsFragment extends Fragment implements ElementListEvent {

    private ListView listView;

    private TextView textView_a;
    private TextView textView_b;
    private TextView textView_c;
    private TextView textView_d;

    public static ElemetntsFragment newInstance() {
        return new ElemetntsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.elemetnts_fragment, container, false);

        listView = v.findViewById(R.id.element_list);

        textView_a = v.findViewById(R.id.element_text_answer_a);
        textView_b = v.findViewById(R.id.element_text_answer_b);
        textView_c = v.findViewById(R.id.element_text_answer_c);
        textView_d = v.findViewById(R.id.element_text_answer_d);

        ImageButton img_add = (ImageButton) v.findViewById(R.id.img_add);
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationEvents navigationEvents = (MainActivity) getActivity();
                assert navigationEvents != null;
                navigationEvents.element_to_onlyelement();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TestViewModel mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);

        mViewModel.getDataBase().observe(getViewLifecycleOwner(), new Observer<Test>() {
            @Override
            public void onChanged(Test test) {
                LayoutInflater lInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ElementAdapter adapter = new ElementAdapter(lInflater, test, ElemetntsFragment.this);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(itemClickListener);
                listView.setSoundEffectsEnabled(false);
                listView.performItemClick(listView, 0, 0);
                listView.setSoundEffectsEnabled(true);
            }
        });
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            ElementAdapter adapter = (ElementAdapter) parent.getAdapter();
            adapter.switchSelection(position);

        }
    };

    @Override
    public void elementClick(OnlyTest onlyTest) {
        textView_a.setText(onlyTest.getAnswers()[0]);
        textView_b.setText(onlyTest.getAnswers()[1]);
        textView_c.setText(onlyTest.getAnswers()[2]);
        textView_d.setText(onlyTest.getAnswers()[3]);

        textView_a.setBackgroundResource(R.drawable.text_answer);
        textView_b.setBackgroundResource(R.drawable.text_answer);
        textView_c.setBackgroundResource(R.drawable.text_answer);
        textView_d.setBackgroundResource(R.drawable.text_answer);

        switch (onlyTest.getIsAnswer()){
            case 0: textView_a.setBackgroundResource(R.drawable.text_answer_select);
                break;
            case 1: textView_b.setBackgroundResource(R.drawable.text_answer_select);
                break;
            case 2: textView_c.setBackgroundResource(R.drawable.text_answer_select);
                break;
            case 3: textView_d.setBackgroundResource(R.drawable.text_answer_select);
        }
    }
}