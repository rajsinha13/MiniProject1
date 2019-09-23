package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Preferences extends AppCompatActivity {

    Spinner s,s1,s2;
    String[] lang = {"C", "C++", "Java"};
    String[] diff = {"Easy", "Medium", "Hard"};
    String[] time = {"Enabled", "Disabled"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        s = findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.spinner_item, lang);
        s.setAdapter(aa);

        s1 = findViewById(R.id.spinner1);
        ArrayAdapter a1 = new ArrayAdapter(this, R.layout.spinner_item, diff);
        s1.setAdapter(a1);

        s2 = findViewById(R.id.spinner2);
        ArrayAdapter a2 = new ArrayAdapter(this, R.layout.spinner_item, time);
        s2.setAdapter(a2);
    }
}
