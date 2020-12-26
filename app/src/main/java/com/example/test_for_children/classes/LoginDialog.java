package com.example.test_for_children.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.test_for_children.MainActivity;
import com.example.test_for_children.R;
import com.example.test_for_children.interfaces.NavigationEvents;

public class LoginDialog extends DialogFragment {

    private static final String TAG = "myLogs";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_login, null);

        ImageButton imgbtnclose = (ImageButton) v.findViewById(R.id.imgbtnclose);
        imgbtnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        final EditText edit_login = (EditText) v.findViewById(R.id.edit_login);
        final EditText edit_password = (EditText) v.findViewById(R.id.edit_password);

        Button btn_cancel = (Button) v.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Button btn_ok = (Button) v.findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edit_login.getText().toString();
                String password = edit_password.getText().toString();

                if (login.equals("mchs") && (password.equals("mchs"))){
                    NavigationEvents navigationEvents = (MainActivity) getActivity();
                    assert navigationEvents != null;
                    navigationEvents.home_to_elements();
                    dismiss();
                }else {
                    Toast.makeText(getContext(), "Логин или пароль неверный", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;

    }
}
