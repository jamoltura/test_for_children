package com.example.test_for_children.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test_for_children.MainActivity;
import com.example.test_for_children.Test.ControlTest;
import com.example.test_for_children.Test.OnlyTest;
import com.example.test_for_children.Test.Test;
import com.example.test_for_children.Test.TestBase;

public class TestViewModel extends ViewModel {

    private static final String TAG = "myLogs";

    private MutableLiveData<Test> dataRand;
    private MutableLiveData<Integer> dataBaseCount;
    private MutableLiveData<Test> dataBase;

    public TestViewModel() {
        super();
    }

    public void init(Context context){
        TestBase testBase = new TestBase(context.getApplicationContext());
        int countBase = testBase.getCountTest(context.getApplicationContext());

        dataBaseCount = new MutableLiveData<>();
        dataBaseCount.postValue(countBase);

        dataBase = new MutableLiveData<>();
        dataBase.postValue(createData(context));
    }

    public void initRand(Context context){
        TestBase testBase = new TestBase(context.getApplicationContext());
        int countBase = testBase.getCountTest(context.getApplicationContext());

        if (countBase >= ControlTest.COUNT_TESTS) {
            dataRand = new MutableLiveData<>();
            dataRand.postValue(createDataRand(context));
        }
    }

    private Test createData(Context context){
        ControlTest controlTest = new ControlTest();
        OnlyTest[] onlyTests = controlTest.getAllTest(context);
        return new Test(onlyTests);
    }

    private Test createDataRand(Context context){
        ControlTest controlTest = new ControlTest();
        OnlyTest[] onlyTests = controlTest.getRandomTest(context);
        return new Test(onlyTests);
    }

    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared");
        super.onCleared();
    }

    public MutableLiveData<Test> getDataRand() {
        return dataRand;
    }

    public MutableLiveData<Test> getDataBase() {
        return dataBase;
    }

    public MutableLiveData<Integer> getDataBaseCount() {
        return dataBaseCount;
    }
}
