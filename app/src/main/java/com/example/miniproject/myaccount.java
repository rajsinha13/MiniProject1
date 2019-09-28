package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myaccount extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth.AuthStateListener authStateListener;
    DatabaseReference databaseReference;
    String userID;
    TextView name, phone, email, pass;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        edit = findViewById(R.id.edit);

        name.setFocusable(false);
        phone.setFocusable(false);
        email.setFocusable(false);
        pass.setFocusable(false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser u = firebaseAuth.getCurrentUser();
        userID = u.getUid();

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            int flag = 1;
            @Override
            public void onClick(View view) {
                if (flag == 1) {
                    edit.setText("Confirm");
                    name.setFocusableInTouchMode(true);
                    phone.setFocusableInTouchMode(true);
                    name.setFocusable(true);
                    phone.setFocusable(true);
                    flag = 0;
                }
                else if (flag == 0) {
                    edit.setText("Edit");
                    name.setFocusable(false);
                    phone.setFocusable(false);
                    users u1 = new users(name.getText().toString(), phone.getText().toString(), email.getText().toString(), pass.getText().toString());
                    databaseReference.child("users").child(userID).setValue(u1);
                    flag = 1;
                }
            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            users u1 = new users();
            u1.setName(ds.child(userID).getValue(users.class).getName());
            u1.setPhone(ds.child(userID).getValue(users.class).getPhone());
            u1.setEmail(ds.child(userID).getValue(users.class).getEmail());
            u1.setPass(ds.child(userID).getValue(users.class).getPass());
            name.setText(u1.name);
            phone.setText(u1.phone);
            email.setText(u1.email);
            pass.setText(u1.pass);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
