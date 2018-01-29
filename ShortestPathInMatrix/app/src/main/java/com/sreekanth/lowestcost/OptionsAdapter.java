package com.sreekanth.lowestcost;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class OptionsAdapter extends FragmentPagerAdapter {

    public OptionsAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LowestCostExamplesFragment();
            case 1:
                return new LowestCostCustomFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Example Grids";
            case 1:
                return "Custom Matrix";
            default:
                return null;
        }
    }
}
