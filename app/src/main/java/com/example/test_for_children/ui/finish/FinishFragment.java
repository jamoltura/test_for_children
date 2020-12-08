package com.example.test_for_children.ui.finish;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_for_children.R;
import com.example.test_for_children.Test.ControlTest;
import com.example.test_for_children.Test.Test;
import com.example.test_for_children.adapters.ViewFinishItemAdapter;
import com.example.test_for_children.viewmodels.TestViewModel;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class FinishFragment extends Fragment {

    private TextView textView2;
    private TextView textViewProgress;
    private ProgressBar progressBar;
    private ImageButton imageButtonPrint;

    private ListView listView;

    public static FinishFragment newInstance() {
        return new FinishFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.finish_fragment, container, false);

        textView2 = v.findViewById(R.id.textView2);
        textViewProgress = v.findViewById(R.id.tv_progress_horizontalFinish);
        progressBar = v.findViewById(R.id.progressBarFinish);
        imageButtonPrint = v.findViewById(R.id.imageButtonPrint);
        listView = v.findViewById(R.id.listview);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TestViewModel mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);

        mViewModel.getDataRand().observe(getViewLifecycleOwner(), new Observer<Test>() {
            @Override
            public void onChanged(Test test) {

                ArrayList<String> list = new ArrayList<>();

                int count = test.getOnlyTests().length;
                int countTrue = 0;

                if (count > 0){
                    for (int i = 0; i < count; i++){
                        if (test.getOnlyTests()[i].getIsAnswer() != test.getOnlyTests()[i].getAnswerForUser()){
                            list.add(test.getOnlyTests()[i].getQuestion());
                        }else {
                            countTrue++;
                        }
                    }
                }

                String text = getString(R.string.result_test_count) + " " + countTrue + " из " + ControlTest.COUNT_TESTS;
                textView2.setText(text);

                progressBar.setProgress(countTrue);
                double pros = (double) countTrue / ControlTest.COUNT_TESTS * 100;
                String textProgress = (int)pros + "%";
                textViewProgress.setText(textProgress);

                LayoutInflater lInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewFinishItemAdapter adapter = new ViewFinishItemAdapter(list, lInflater);
                listView.setAdapter(adapter);

                imageButtonPrint.setOnClickListener(clickListenerPrint);
            }
        });
    }

    View.OnClickListener clickListenerPrint = new View.OnClickListener() {



        @Override
        public void onClick(View v) {

            TestViewModel mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);

            Test test = mViewModel.getDataRand().getValue();

            Toast.makeText(getContext(), "printing", Toast.LENGTH_LONG).show();

        }
    };



}