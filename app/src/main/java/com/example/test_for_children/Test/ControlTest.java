package com.example.test_for_children.Test;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

public class ControlTest {

    public static final int COUNT_TESTS = 10;


    public ControlTest() {
    }

    public boolean setTest(Context context, OnlyTest onlyTest){
        TestBase testBase = new TestBase(context);
        return testBase.saveOnlyTest(context, onlyTest);
    }

    public boolean updateTest(Context context, OnlyTest onlyTest, int index){
        TestBase testBase = new TestBase(context);
        return testBase.editTest(context, onlyTest, index);
    }

    public boolean deliteTest(Context context, int index){
        TestBase testBase = new TestBase(context);
        return testBase.deliteOnlyTest(context, index);
    }

    public boolean deliteAllTest(Context context){
        TestBase testBase = new TestBase(context);
        return testBase.deleteAllTest(context);
    }

    public OnlyTest getOnlyTest(Context context, int index){
        TestBase testBase = new TestBase(context);
        return testBase.getOnlyTest(context, index);
    }

    public OnlyTest[] getRandomTest(Context context){
        ArrayList<OnlyTest> list = getTest(context);

        int countArr = list.size();

        OnlyTest[] answers;

        if (countArr > 0) {
            ArrayList<Integer> numberGenerated = getRandomNumber(countArr);

            answers = new OnlyTest[COUNT_TESTS];

            for (int i = 0; i < COUNT_TESTS; i++) {
                answers[i] = list.get(numberGenerated.get(i));
            }
        }else {
            answers = new OnlyTest[0];
        }

        return answers;
    }

    public OnlyTest[] getAllTest(Context context){
        ArrayList<OnlyTest> list = getTest(context);

        int countArr = list.size();

        OnlyTest[] answers;

        if (countArr > 0) {

            answers = new OnlyTest[countArr];

            for (int i = 0; i < countArr; i++) {
                answers[i] = list.get(i);
            }
        }else {
            answers = new OnlyTest[0];
        }

        return answers;
    }

    private ArrayList<OnlyTest> getTest(Context context){
        TestBase testBase = new TestBase(context);
        return testBase.getAllOnlyTest(context);
    }

    private ArrayList<Integer> getRandomNumber(int endNum){
        ArrayList<Integer> numberGenerated = new ArrayList<>();
        Random rand = new Random();
        do {
            int number = rand.nextInt(endNum);
            if (!numberGenerated.contains(number)) {
                numberGenerated.add(number);
            }
        }while (numberGenerated.size() < COUNT_TESTS);
        return numberGenerated;
    }


}
