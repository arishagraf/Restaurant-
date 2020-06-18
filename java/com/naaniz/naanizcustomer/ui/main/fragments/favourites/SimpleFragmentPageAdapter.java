package com.naaniz.naanizcustomer.ui.main.fragments.favourites;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPageAdapter(Context mContext, @NonNull FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SavedItemsFragment();
        } else {
            return new PastOrders();

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Saved Items";
        } else {
            return "PastOrders";
        }
    }
}
