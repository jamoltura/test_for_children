package com.example.test_for_children;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.test_for_children.Test.ControlTest;
import com.example.test_for_children.Test.OnlyTest;
import com.example.test_for_children.interfaces.NavigationEvents;
import com.example.test_for_children.viewmodels.TestViewModel;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements NavigationEvents {

    private static final String TAG = "myLogs";

    private TestViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String question = getRandomString() + " " + i;
            String[] answers = new String[OnlyTest.COUNT_ANSWER];

            answers[0] = getRandomString() + " " + i;
            answers[1] = getRandomString() + " " + i;
            answers[2] = getRandomString() + " " + i;
            answers[3] = getRandomString() + " " + i;

            int num = random.nextInt(4);


            OnlyTest onlyTest = new OnlyTest(question, answers, num);


            ControlTest controlTest = new ControlTest();

            controlTest.setTest(getApplicationContext(), onlyTest);
        }


     //   ControlTest controlTest = new ControlTest();
      //  OnlyTest[] onlyTests = controlTest.getRandomTest(getApplicationContext());
      //  Test test = new Test(onlyTests);

        /**
        TestBase testBase = new TestBase(getApplicationContext());
        if(testBase.delete(getApplicationContext())){
            Toast.makeText(getApplicationContext(), "deleti", Toast.LENGTH_LONG).show();
        }
        */

        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        mViewModel.init(getApplicationContext());
    }

    @Override
    public void home_to_work(){
        mViewModel.initRand(getApplicationContext());
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        navController.navigate(R.id.workFragment);
    }

    @Override
    public void work_to_home(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        navController.navigate(R.id.homeFragment);
    }

    @Override
    public void work_to_finish(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        navController.popBackStack();
        navController.navigate(R.id.finishFragment);

    }

    @Override
    public void finish_to_home(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        navController.navigate(R.id.homeFragment);
    }

    @Override
    public void home_to_elements(){
        mViewModel.init(getApplicationContext());
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        navController.navigate(R.id.elemetntsFragment);
    }

    @Override
    public void element_to_home(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        navController.navigate(R.id.homeFragment);
    }

    @Override
    public void element_to_onlyelement() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        navController.navigate(R.id.onlyElementFragment);
    }

    @Override
    public void element_to_onlyelement(Bundle bundle) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        navController.navigate(R.id.onlyElementFragment, bundle);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // random create test
    private String getRandomString(){

        String symbols = "qwerty";
        StringBuilder randString = new StringBuilder();

        int countStr = (int)(Math.random() * 30);

        for (int j = 0; j < countStr; j++) {

            int count = (int) (Math.random() * 10);

            for (int i = 0; i < count; i++)
                randString.append(symbols.charAt((int) (Math.random() * symbols.length())));

            if (j != countStr - 1){
                randString.append(" ");
            }
        }

        return randString.toString();
    }
}