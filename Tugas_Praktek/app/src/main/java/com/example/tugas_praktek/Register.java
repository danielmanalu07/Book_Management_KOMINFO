package com.example.tugas_praktek;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Register extends BottomSheetDialogFragment {
    private DatabaseHelper db;
    public static final String TAG = "Register";

    public static Register newInstance(){
        return new Register();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText username = view.findViewById(R.id.etUsername);
        EditText password = view.findViewById(R.id.etPassword);
        EditText confirm_password = view.findViewById(R.id.etConfirmPassword);
        Button daftar = view.findViewById(R.id.btnRegister);

        db = new DatabaseHelper(getActivity());

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inusername = username.getText().toString();
                String inpassword = password.getText().toString();
                String inConfirmPassword = confirm_password.getText().toString();

                if (inusername.isEmpty() && inpassword.isEmpty() && inConfirmPassword.isEmpty()) {
                    Toast.makeText(getContext(), "Fill is required", Toast.LENGTH_SHORT).show();
                } else {
                    if (!inConfirmPassword.equals(inpassword)) {
                        Toast.makeText(getContext(), "Password and Confirm Password doesn't match", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean register = db.saveData(inusername, inpassword);
                        if (register) {
                            Toast.makeText(getContext(), "Register Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Register Failure", Toast.LENGTH_SHORT).show();
                        }
                        dismiss();
                    }
                }
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity =getActivity();
        if (activity instanceof CloseDialogI) {
            ((CloseDialogI)activity).onClose(dialog);
        }
    }
}
