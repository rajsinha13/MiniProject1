package com.example.miniproject;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewPagerClass extends FragmentPagerAdapter {

    public viewPagerClass(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment f = null;
        switch (position) {
            case 0:
                f = new RegisterActivity();
                break;
            case 1:
                f = new LoginActivity();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
