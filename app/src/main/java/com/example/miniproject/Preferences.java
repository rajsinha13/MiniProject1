package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Preferences extends AppCompatActivity {

    Button btn;
    Spinner s,s1,s2;
    String[] lang = {"C", "C++", "Java"};
    String[] diff = {"Easy", "Medium", "Hard"};
    String[] time = {"Enabled", "Disabled"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btn=findViewById(R.id.button);
        s = findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.spinner_item, lang);
        s.setAdapter(aa);

        s1 = findViewById(R.id.spinner1);
        ArrayAdapter a1 = new ArrayAdapter(this, R.layout.spinner_item, diff);
        s1.setAdapter(a1);

        s2 = findViewById(R.id.spinner2);
        ArrayAdapter a2 = new ArrayAdapter(this, R.layout.spinner_item, time);
        s2.setAdapter(a2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Preferences.this,Navigation1.class);
                startActivity(i);
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
