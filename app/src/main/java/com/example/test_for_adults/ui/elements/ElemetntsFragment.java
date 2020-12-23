package com.example.test_for_adults.ui.elements;

import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_for_adults.MainActivity;
import com.example.test_for_adults.R;
import com.example.test_for_adults.Test.ControlTest;
import com.example.test_for_adults.Test.OnlyTest;
import com.example.test_for_adults.Test.Test;
import com.example.test_for_adults.adapters.ElementAdapter;
import com.example.test_for_adults.interfaces.ElementListEvent;
import com.example.test_for_adults.interfaces.NavigationEvents;
import com.example.test_for_adults.viewmodels.TestViewModel;

public class ElemetntsFragment extends Fragment implements ElementListEvent {

    private ListView listView;

    private TextView textView_a;
    private TextView textView_b;
    private TextView textView_c;
    private TextView textView_d;

    private TextView text_answer_a_num;
    private TextView text_answer_b_num;
    private TextView text_answer_c_num;
    private TextView text_answer_d_num;

    private TextView quistion_count;

    private ElementAdapter adapter;

    public static ElemetntsFragment newInstance() {
        return new ElemetntsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.elemetnts_fragment, container, false);

        listView = v.findViewById(R.id.element_list);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
                return true;
            }
        });

        textView_a = v.findViewById(R.id.element_text_answer_a);
        textView_b = v.findViewById(R.id.element_text_answer_b);
        textView_c = v.findViewById(R.id.element_text_answer_c);
        textView_d = v.findViewById(R.id.element_text_answer_d);

        text_answer_a_num = (TextView) v.findViewById(R.id.element_text_answer_a_num);
        text_answer_b_num = (TextView) v.findViewById(R.id.element_text_answer_b_num);
        text_answer_c_num = (TextView) v.findViewById(R.id.element_text_answer_c_num);
        text_answer_d_num = (TextView) v.findViewById(R.id.element_text_answer_d_num);

        ImageButton img_add = (ImageButton) v.findViewById(R.id.img_add);
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationEvents navigationEvents = (MainActivity) getActivity();
                assert navigationEvents != null;
                navigationEvents.element_to_onlyelement();
            }
        });

        quistion_count = v.findViewById(R.id.quistion_count);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TestViewModel mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
        updateView(mViewModel);
    }

    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            ElementAdapter adapter = (ElementAdapter) parent.getAdapter();
            adapter.switchSelection(position);

        }
    };

    private void showPopupMenu(View v, final int position){
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        popupMenu.inflate(R.menu.element_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.edit:
                        NavigationEvents navigationEvents = (MainActivity) getActivity();
                        assert navigationEvents != null;
                        Bundle bundle = new Bundle();
                        bundle.putInt("index", position);
                        navigationEvents.element_to_onlyelement(bundle);
                        break;
                    case R.id.del:
                        ControlTest controlTest = new ControlTest();
                        if (controlTest.deliteTest(getContext(), position)) {
                            Toast.makeText(getContext(), "Данные успешно удалено", Toast.LENGTH_SHORT).show();
                            TestViewModel mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
                            mViewModel.init(getActivity().getApplicationContext());
                            updateView(mViewModel);
                        }else {
                            Toast.makeText(getContext(), "Данные не удалено", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.del_all:
                        ControlTest controlTest1 = new ControlTest();
                        if (controlTest1.deliteAllTest(getContext())){
                            Toast.makeText(getContext(), "Все данные успешно удалено", Toast.LENGTH_SHORT).show();
                            TestViewModel mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);
                            mViewModel.init(getActivity().getApplicationContext());
                            updateView(mViewModel);
                        }else {
                            Toast.makeText(getContext(), "Данные не удалено", Toast.LENGTH_SHORT).show();
                        }
                }

                return true;
            }
        });
        popupMenu.show();
    }

    @Override
    public void elementClick(OnlyTest onlyTest) {
        textView_a.setText(onlyTest.getAnswers()[0]);
        textView_b.setText(onlyTest.getAnswers()[1]);
        textView_c.setText(onlyTest.getAnswers()[2]);
        textView_d.setText(onlyTest.getAnswers()[3]);

        ViewSelect(onlyTest.getIsAnswer());
    }

    private void ViewSelect(int index){
        text_answer_a_num.setBackgroundResource(R.drawable.text_question);
        text_answer_b_num.setBackgroundResource(R.drawable.text_question);
        text_answer_c_num.setBackgroundResource(R.drawable.text_question);
        text_answer_d_num.setBackgroundResource(R.drawable.text_question);

        switch (index){
            case 0: text_answer_a_num.setBackgroundResource(R.drawable.text_answer);
                break;
            case 1: text_answer_b_num.setBackgroundResource(R.drawable.text_answer);
                break;
            case 2: text_answer_c_num.setBackgroundResource(R.drawable.text_answer);
                break;
            case 3: text_answer_d_num.setBackgroundResource(R.drawable.text_answer);
        }
    }

    private void clearView(){
        textView_a.setText("");
        textView_b.setText("");
        textView_c.setText("");
        textView_d.setText("");

        ViewSelect(-1);
    }

    private void updateView(TestViewModel mViewModel){
        clearView();
        mViewModel.getDataBase().observe(getViewLifecycleOwner(), new Observer<Test>() {
            @Override
            public void onChanged(Test test) {
                LayoutInflater lInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                adapter = new ElementAdapter(lInflater, test, ElemetntsFragment.this);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(itemClickListener);
                listView.setSoundEffectsEnabled(false);
                listView.performItemClick(listView, 0, 0);
                listView.setSoundEffectsEnabled(true);
            }
        });

        mViewModel.getDataBaseCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                String value = getString(R.string.list_question) + " :" + integer + " шт";
                quistion_count.setText(value);
            }
        });
    }
}