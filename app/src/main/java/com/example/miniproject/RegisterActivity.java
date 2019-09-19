package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends Fragment {

    EditText user,pass, confpass, name, phone;
    Button register;
    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_page, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = getView().findViewById(R.id.username);
        pass = getView().findViewById(R.id.password);
        confpass = getView().findViewById(R.id.confirm_password);
        name = getView().findViewById(R.id.name);
        phone = getView().findViewById(R.id.phone);
        register = getView().findViewById(R.id.register);
        firebaseAuth = FirebaseAuth.getInstance();
//        private DatabaseReference mdb;

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String u = user.getText().toString().trim();
                String p = pass.getText().toString().trim();
                String n = name.getText().toString().trim();
                String cp = confpass.getText().toString().trim();
                String ph = phone.getText().toString().trim();


                if(TextUtils.isEmpty(u)) {

                    Toast.makeText(getActivity(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(p)) {

                    Toast.makeText(getActivity(), "Please enter password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(p.length()<6) {

                    Toast.makeText(getActivity(), "Password too short", Toast.LENGTH_SHORT).show();

                }

                firebaseAuth.createUserWithEmailAndPassword(u, p)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(getActivity(), "Registration Failed", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }
        });
    }
}
