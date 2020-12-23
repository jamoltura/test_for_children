package com.example.test_for_adults;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.test_for_adults.classes.AutoEditorBase;
import com.example.test_for_adults.interfaces.NavigationEvents;
import com.example.test_for_adults.viewmodels.TestViewModel;

public class MainActivity extends AppCompatActivity implements NavigationEvents {

    private static final String TAG = "myLogs";
    private static final String KEY_BASE_LOAD = "base_load";

    private TestViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onLoad();

        mViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        mViewModel.init(getApplicationContext());
    }

    private void onLoad(){
        AutoEditorBase autoEditorBase = new AutoEditorBase(getApplicationContext());
        autoEditorBase.initsializationKey();
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