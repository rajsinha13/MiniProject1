package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText user,pass;
    Button login,register;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String u = user.getText().toString().trim();
                String p = pass.getText().toString().trim();

                if(TextUtils.isEmpty(u)) {

                    Toast.makeText(MainActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(p)) {

                    Toast.makeText(MainActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(p.length()<6) {

                    Toast.makeText(MainActivity.this, "Password too short", Toast.LENGTH_SHORT).show();

                }

                firebaseAuth.createUserWithEmailAndPassword(u, p)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String u = user.getText().toString().trim();
                String p = pass.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(u, p)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}
