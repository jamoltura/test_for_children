package com.example.test_for_adults.classes;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.test_for_adults.R;
import com.example.test_for_adults.Test.ControlTest;
import com.example.test_for_adults.Test.OnlyTest;

public class AutoEditorBase {

    private static final String TAG = "myLogs";

    public static final String APP_PREFERENCES = "mysettings";
    private static final String KEY_BASE_LOAD = "base_load";

    private Context context;

    public AutoEditorBase(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        if (!isValue()){

            SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

            SharedPreferences.Editor ed = mSettings.edit();

            ed.putString(KEY_BASE_LOAD, "0");

            ed.apply();
        }
    }

    private boolean isValue(){
        return context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).contains(KEY_BASE_LOAD);
    }

    public void initsializationKey(){
        SharedPreferences mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        String value = mSettings.getString(KEY_BASE_LOAD, "");

        assert value != null;
        if (value.equals("0")){

            loadBase();

            SharedPreferences.Editor ed = mSettings.edit();

            ed.putString(KEY_BASE_LOAD, "1");

            ed.apply();
        }
    }

    private void loadBase(){
        String[] answers = new String[OnlyTest.COUNT_ANSWER];
        ControlTest controlTest = new ControlTest();

        String question = context.getString(R.string.question_0);
        answers[0] = context.getString(R.string.answer_a_0);
        answers[1] = context.getString(R.string.answer_b_0);
        answers[2] = context.getString(R.string.answer_c_0);
        answers[3] = context.getString(R.string.answer_d_0);
        int num = Integer.parseInt(context.getString(R.string.is_answer_0));
        OnlyTest onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_1);
        answers[0] = context.getString(R.string.answer_a_1);
        answers[1] = context.getString(R.string.answer_b_1);
        answers[2] = context.getString(R.string.answer_c_1);
        answers[3] = context.getString(R.string.answer_d_1);
        num = Integer.parseInt(context.getString(R.string.is_answer_1));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_2);
        answers[0] = context.getString(R.string.answer_a_2);
        answers[1] = context.getString(R.string.answer_b_2);
        answers[2] = context.getString(R.string.answer_c_2);
        answers[3] = context.getString(R.string.answer_d_2);
        num = Integer.parseInt(context.getString(R.string.is_answer_2));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_3);
        answers[0] = context.getString(R.string.answer_a_3);
        answers[1] = context.getString(R.string.answer_b_3);
        answers[2] = context.getString(R.string.answer_c_3);
        answers[3] = context.getString(R.string.answer_d_3);
        num = Integer.parseInt(context.getString(R.string.is_answer_3));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_4);
        answers[0] = context.getString(R.string.answer_a_4);
        answers[1] = context.getString(R.string.answer_b_4);
        answers[2] = context.getString(R.string.answer_c_4);
        answers[3] = context.getString(R.string.answer_d_4);
        num = Integer.parseInt(context.getString(R.string.is_answer_4));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_5);
        answers[0] = context.getString(R.string.answer_a_5);
        answers[1] = context.getString(R.string.answer_b_5);
        answers[2] = context.getString(R.string.answer_c_5);
        answers[3] = context.getString(R.string.answer_d_5);
        num = Integer.parseInt(context.getString(R.string.is_answer_5));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_6);
        answers[0] = context.getString(R.string.answer_a_6);
        answers[1] = context.getString(R.string.answer_b_6);
        answers[2] = context.getString(R.string.answer_c_6);
        answers[3] = context.getString(R.string.answer_d_6);
        num = Integer.parseInt(context.getString(R.string.is_answer_6));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_7);
        answers[0] = context.getString(R.string.answer_a_7);
        answers[1] = context.getString(R.string.answer_b_7);
        answers[2] = context.getString(R.string.answer_c_7);
        answers[3] = context.getString(R.string.answer_d_7);
        num = Integer.parseInt(context.getString(R.string.is_answer_7));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_8);
        answers[0] = context.getString(R.string.answer_a_8);
        answers[1] = context.getString(R.string.answer_b_8);
        answers[2] = context.getString(R.string.answer_c_8);
        answers[3] = context.getString(R.string.answer_d_8);
        num = Integer.parseInt(context.getString(R.string.is_answer_8));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_9);
        answers[0] = context.getString(R.string.answer_a_9);
        answers[1] = context.getString(R.string.answer_b_9);
        answers[2] = context.getString(R.string.answer_c_9);
        answers[3] = context.getString(R.string.answer_d_9);
        num = Integer.parseInt(context.getString(R.string.is_answer_9));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_10);
        answers[0] = context.getString(R.string.answer_a_10);
        answers[1] = context.getString(R.string.answer_b_10);
        answers[2] = context.getString(R.string.answer_c_10);
        answers[3] = context.getString(R.string.answer_d_10);
        num = Integer.parseInt(context.getString(R.string.is_answer_10));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_11);
        answers[0] = context.getString(R.string.answer_a_11);
        answers[1] = context.getString(R.string.answer_b_11);
        answers[2] = context.getString(R.string.answer_c_11);
        answers[3] = context.getString(R.string.answer_d_11);
        num = Integer.parseInt(context.getString(R.string.is_answer_11));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_12);
        answers[0] = context.getString(R.string.answer_a_12);
        answers[1] = context.getString(R.string.answer_b_12);
        answers[2] = context.getString(R.string.answer_c_12);
        answers[3] = context.getString(R.string.answer_d_12);
        num = Integer.parseInt(context.getString(R.string.is_answer_12));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_13);
        answers[0] = context.getString(R.string.answer_a_13);
        answers[1] = context.getString(R.string.answer_b_13);
        answers[2] = context.getString(R.string.answer_c_13);
        answers[3] = context.getString(R.string.answer_d_13);
        num = Integer.parseInt(context.getString(R.string.is_answer_13));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_14);
        answers[0] = context.getString(R.string.answer_a_14);
        answers[1] = context.getString(R.string.answer_b_14);
        answers[2] = context.getString(R.string.answer_c_14);
        answers[3] = context.getString(R.string.answer_d_14);
        num = Integer.parseInt(context.getString(R.string.is_answer_14));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_15);
        answers[0] = context.getString(R.string.answer_a_15);
        answers[1] = context.getString(R.string.answer_b_15);
        answers[2] = context.getString(R.string.answer_c_15);
        answers[3] = context.getString(R.string.answer_d_15);
        num = Integer.parseInt(context.getString(R.string.is_answer_15));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_16);
        answers[0] = context.getString(R.string.answer_a_16);
        answers[1] = context.getString(R.string.answer_b_16);
        answers[2] = context.getString(R.string.answer_c_16);
        answers[3] = context.getString(R.string.answer_d_16);
        num = Integer.parseInt(context.getString(R.string.is_answer_16));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_17);
        answers[0] = context.getString(R.string.answer_a_17);
        answers[1] = context.getString(R.string.answer_b_17);
        answers[2] = context.getString(R.string.answer_c_17);
        answers[3] = context.getString(R.string.answer_d_17);
        num = Integer.parseInt(context.getString(R.string.is_answer_17));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_18);
        answers[0] = context.getString(R.string.answer_a_18);
        answers[1] = context.getString(R.string.answer_b_18);
        answers[2] = context.getString(R.string.answer_c_18);
        answers[3] = context.getString(R.string.answer_d_18);
        num = Integer.parseInt(context.getString(R.string.is_answer_18));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);

        question = context.getString(R.string.question_19);
        answers[0] = context.getString(R.string.answer_a_19);
        answers[1] = context.getString(R.string.answer_b_19);
        answers[2] = context.getString(R.string.answer_c_19);
        answers[3] = context.getString(R.string.answer_d_19);
        num = Integer.parseInt(context.getString(R.string.is_answer_19));
        onlyTest = new OnlyTest(question, answers, num);
        controlTest.setTest(context, onlyTest);
    }
}
