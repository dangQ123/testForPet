package edu.njust.testforpet.bottombar.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import edu.njust.testforpet.bottombar.fragment.TabFirstFragment;
import edu.njust.testforpet.bottombar.fragment.TabSecondFragment;


public class TabPagerAdapter extends FragmentPagerAdapter {


    public TabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new TabFirstFragment();
        }
        else if(position==1){
            return new TabSecondFragment();
        }
        else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
