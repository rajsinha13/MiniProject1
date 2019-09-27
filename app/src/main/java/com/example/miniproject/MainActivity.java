package com.example.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Switch ss;
    ViewPager vp;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vp = findViewById(R.id.viewPager);
        ss = findViewById(R.id.switch1);
        viewPagerClass vp1 = new viewPagerClass(getSupportFragmentManager());
        vp.setAdapter(vp1);

        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ss.isChecked())
                {
                    vp.setCurrentItem(1);
                }
                else
                {
                    vp.setCurrentItem(0);
                }
            }
        });

        vp.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                page.setCameraDistance(20000);


                if (position < -1) {     // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    page.setAlpha(0);

                } else if (position <= 0) {    // [-1,0]
                    page.setAlpha(1);
                    page.setPivotX(page.getWidth());
                    page.setRotationY(90 * Math.abs(position));

                } else if (position <= 1) {    // (0,1]
                    page.setAlpha(1);
                    page.setPivotX(0);
                    page.setRotationY(-90 * Math.abs(position));

                } else {    // (1,+Infinity]
                    // This page is way off-screen to the right.
                    page.setAlpha(0);

                }


                if (Math.abs(position) <= 0.5) {
                    page.setScaleY(Math.max(.4f, 1 - Math.abs(position)));
                } else if (Math.abs(position) <= 1) {
                    page.setScaleY(Math.max(.4f, Math.abs(position)));

                }
            }
        });

    }
}
