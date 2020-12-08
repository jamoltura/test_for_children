package com.example.test_for_children.ui.onlyelement;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.test_for_children.R;
import com.example.test_for_children.Test.ControlTest;
import com.example.test_for_children.Test.OnlyTest;

public class OnlyElementFragment extends Fragment {

    private TextView text_answer_a_num;
    private TextView text_answer_b_num;
    private TextView text_answer_c_num;
    private TextView text_answer_d_num;

    private int IsAnswer;

    public static OnlyElementFragment newInstance() {
        return new OnlyElementFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.only_element_fragment, container, false);

        Button btn_cancel = (Button) v.findViewById(R.id.element_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        setIsAnswer(-1);

        final EditText editText_question = (EditText) v.findViewById(R.id.editText_question);

        final EditText editText_answer_a = (EditText) v.findViewById(R.id.editText_answer_a);
        final EditText editText_answer_b = (EditText) v.findViewById(R.id.editText_answer_b);
        final EditText editText_answer_c = (EditText) v.findViewById(R.id.editText_answer_c);
        final EditText editText_answer_d = (EditText) v.findViewById(R.id.editText_answer_d);

        text_answer_a_num = (TextView) v.findViewById(R.id.text_answer_a_num);
        text_answer_b_num = (TextView) v.findViewById(R.id.text_answer_b_num);
        text_answer_c_num = (TextView) v.findViewById(R.id.text_answer_c_num);
        text_answer_d_num = (TextView) v.findViewById(R.id.text_answer_d_num);

        text_answer_a_num.setOnClickListener(click);
        text_answer_b_num.setOnClickListener(click);
        text_answer_c_num.setOnClickListener(click);
        text_answer_d_num.setOnClickListener(click);

        Button btn_ok = (Button) v.findViewById(R.id.element_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnlyTest onlyTest = new OnlyTest();
                onlyTest.setQuestion(editText_question.getText().toString());

                String[] answers = new String[OnlyTest.COUNT_ANSWER];

                answers[0] = editText_answer_a.getText().toString();
                answers[1] = editText_answer_b.getText().toString();
                answers[2] = editText_answer_c.getText().toString();
                answers[3] = editText_answer_d.getText().toString();

                onlyTest.setAnswers(answers);

                onlyTest.setIsAnswer(getIsAnswer());

                ControlTest controlTest = new ControlTest();

                controlTest.setTest(getContext(), onlyTest);

                editText_question.setText("");

                editText_answer_a.setText("");
                editText_answer_b.setText("");
                editText_answer_c.setText("");
                editText_answer_d.setText("");

                ViewSelect(-1);
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.text_answer_a_num: ViewSelect(0);
                    break;
                case R.id.text_answer_b_num: ViewSelect(1);
                    break;
                case R.id.text_answer_c_num: ViewSelect(2);
                    break;
                case R.id.text_answer_d_num: ViewSelect(3);
            }
        }
    };

    private void ViewSelect(int index){
        text_answer_a_num.setBackgroundResource(R.drawable.text_question);
        text_answer_b_num.setBackgroundResource(R.drawable.text_question);
        text_answer_c_num.setBackgroundResource(R.drawable.text_question);
        text_answer_d_num.setBackgroundResource(R.drawable.text_question);

        setIsAnswer(index);

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

    public int getIsAnswer() {
        return IsAnswer;
    }

    public void setIsAnswer(int isAnswer) {
        IsAnswer = isAnswer;
    }
}