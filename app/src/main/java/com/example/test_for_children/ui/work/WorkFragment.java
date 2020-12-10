package com.example.test_for_children.ui.work;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import com.example.test_for_children.MainActivity;
import com.example.test_for_children.R;
import com.example.test_for_children.Test.ControlTest;
import com.example.test_for_children.Test.Test;
import com.example.test_for_children.adapters.ViewPageAdapter;
import com.example.test_for_children.classes.MyDialog;
import com.example.test_for_children.interfaces.NavigationEvents;
import com.example.test_for_children.interfaces.PageEvents;
import com.example.test_for_children.viewmodels.TestViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class WorkFragment extends Fragment implements PageEvents {

    private static final String TAG = "myLogs";

    private ViewPager2 viewPager2;
    private TextView tv_time;

    public static WorkFragment newInstance() {
        return new WorkFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.work_fragment, container, false);

        final ProgressBar progressBar = v.findViewById(R.id.progressBar);
        final TextView tv_progress_horizontal = v.findViewById(R.id.tv_progress_horizontal);
        tv_time = v.findViewById(R.id.tv_time);

        viewPager2 = v.findViewById(R.id.viewPager);

        viewPager2.setOffscreenPageLimit(ControlTest.COUNT_TESTS);

        viewPager2.registerOnPageChangeCallback(new OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                progressBar.setProgress(position + 1);
                String text = "10/" + (position + 1);
                tv_progress_horizontal.setText(text);
            }
        });

        countDownTimer.start();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TestViewModel mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);

        mViewModel.getDataRand().observe(getViewLifecycleOwner(), new Observer<Test>() {
            @Override
            public void onChanged(Test test) {
                viewPager2.setAdapter(new ViewPageAdapter(getContext(), WorkFragment.this, test));
            }
        });
    }

    @Override
    public void clicked(int index) {

        int position = viewPager2.getCurrentItem();

        if (position != ControlTest.COUNT_TESTS - 1){
            viewPager2.setCurrentItem(position + 1);
        }else {
            MyDialog dlg = getNewInstance();
            dlg.show(((MainActivity) getActivity()).getSupportFragmentManager(), "dlg");
        }

        TestViewModel mViewModel = ViewModelProviders.of(getActivity()).get(TestViewModel.class);

        MutableLiveData<Test> data = mViewModel.getDataRand();

        data.getValue().getOnlyTests()[position].setAnswerForUser(index);

    }

    @Override
    public void cancelTimer() {
        countDownTimer.cancel();
        NavigationEvents navigationEvents = (MainActivity) getActivity();
        assert navigationEvents != null;
        navigationEvents.work_to_finish();
    }

    private final CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            try {
                setTexttime();
            } catch (ParseException e) {
                Log.d(TAG, e.getMessage());
            }
        }

        @Override
        public void onFinish() {
            NavigationEvents navigationEvents = (MainActivity) getActivity();
            assert navigationEvents != null;
            navigationEvents.work_to_finish();
        }
    };

    private void setTexttime() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("mm:ss");

        Calendar cal_s = Calendar.getInstance();

        if (tv_time.getText().toString().isEmpty()) {
            Date date = new Date();
            date.setTime(30000);
            cal_s.setTime(Objects.requireNonNull(format.parse(format.format(date))));
            tv_time.setText(format.format(cal_s.getTime()));
        }else {
            String value = tv_time.getText().toString();
            cal_s.setTime(Objects.requireNonNull(format.parse(value)));
            cal_s.roll(Calendar.SECOND, false);
            tv_time.setText(format.format(cal_s.getTime()));
        }
    }

    public MyDialog getNewInstance(){
        return new MyDialog(this);
    }

    @Override
    public void onDestroy() {
        countDownTimer.cancel();
        super.onDestroy();
    }
}