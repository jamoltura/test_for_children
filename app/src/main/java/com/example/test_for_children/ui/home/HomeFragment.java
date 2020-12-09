package com.example.test_for_children.ui.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.test_for_children.MainActivity;
import com.example.test_for_children.R;
import com.example.test_for_children.Test.ControlTest;
import com.example.test_for_children.Test.Test;
import com.example.test_for_children.Test.TestBase;
import com.example.test_for_children.classes.LoginDialog;
import com.example.test_for_children.interfaces.NavigationEvents;
import com.example.test_for_children.viewmodels.TestViewModel;

public class HomeFragment extends Fragment {

    private Button btn;
    private ImageButton imageButton;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_fragment, container, false);

        btn = v.findViewById(R.id.btn_start_test);
        imageButton = v.findViewById(R.id.btn_add);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationEvents navigationEvents = (MainActivity) getActivity();
                assert navigationEvents != null;
                navigationEvents.home_to_elements();
            }
        });

        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .add(new LoginDialog(), "logindialog")
                        .commit();
                return true;
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TestViewModel mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);

        mViewModel.getDataBaseCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer >= ControlTest.COUNT_TESTS){
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NavigationEvents navigationEvents = (MainActivity) getActivity();
                            assert navigationEvents != null;
                            navigationEvents.home_to_work();
                        }
                    });
                }
            }
        });
    }
}