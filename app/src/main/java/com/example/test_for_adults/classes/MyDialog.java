package com.example.test_for_adults.classes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.test_for_adults.interfaces.PageEvents;

public class MyDialog extends DialogFragment {

    private PageEvents pageEvents;

    public MyDialog(PageEvents pageEvents) {
        this.pageEvents = pageEvents;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Внимания!")
                .setPositiveButton("Да", click)
                .setNegativeButton("нет", click)
                .setMessage("Закончить?")
                .create();
    }

    DialogInterface.OnClickListener click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case android.app.Dialog.BUTTON_POSITIVE:
                    pageEvents.cancelTimer();
                    break;
                case android.app.Dialog.BUTTON_NEGATIVE:
            }
        }
    };
}
