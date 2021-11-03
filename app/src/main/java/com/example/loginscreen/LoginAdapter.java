package com.example.loginscreen;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LoginAdapter extends FragmentStateAdapter {


    public LoginAdapter(FragmentActivity fa) {
        super(fa);
    }


    @Override
    public int getItemCount() {
        return 2;
    }

    @NonNull
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoginTabFragment();
            case 1:
                return new SignupTabFragment();
            default:
                return null;
        }
    }


}
