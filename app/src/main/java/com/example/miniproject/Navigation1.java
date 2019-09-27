package com.example.miniproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Navigation1 extends optionmenu implements NavigationView.OnNavigationItemSelectedListener{

    Boolean exit=false;
    static int a=1;
    Context context=Navigation1.this;
    Button submit;
    RadioGroup radioGroup;
    int answer=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation1);

        submit=findViewById(R.id.button);
        radioGroup=findViewById(R.id.radiogroup);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radiogroup2=radioGroup.getCheckedRadioButtonId();
                if(radiogroup2==R.id.first)
                    answer=1;
                else if(radiogroup2==R.id.second)
                    answer=2;
                else if(radiogroup2==R.id.third)
                    answer=3;
                else if(radiogroup2==R.id.fourth)
                    answer=4;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("answer",String.valueOf(answer));
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(Gravity.LEFT)) {

            drawer.closeDrawer(Gravity.LEFT);

            exit = false;

        } else {
            if (context == Navigation1.this) {
                if(a==1) {
                    if (exit) {
                        super.onBackPressed();
                        finishAffinity();
                        finish();
                        return;
                    }
                    this.exit = true;
                    Toasty.info(this, "Press Back again to exit", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            exit = false;
                        }
                    }, 3000);
                }
                else
                {
                    super.onBackPressed();
                    a=1;
                }
            }
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        item.setChecked(true);


        Log.i("abcd",String.valueOf(id));
        switch (id)
        {
            case R.id.myaccount:
                startActivity(new Intent(Navigation1.this,myaccount.class));
                break;
            case R.id.leaderboard:
                startActivity(new Intent(Navigation1.this,Leaderboard.class));
                break;
            case R.id.prefer:
                startActivity(new Intent(Navigation1.this,Preferences.class));
                break;
            case R.id.logout:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
