package org.amfoss.templeapp.home;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.expenses.ExpensesFragment;
import org.amfoss.templeapp.income.IncomeFragment;
import org.amfoss.templeapp.poojas.PoojaFragment;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 17/10/2019
*/

/**
* A [FragmentPagerAdapter] that returns a fragment corresponding to one of the sections/tabs/pages.
*/
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PoojaFragment();
        } else if (position == 1) {
            return new IncomeFragment();
        } else {
            return new ExpensesFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.pooja);
            case 1:
                return mContext.getString(R.string.income);
            case 2:
                return mContext.getString(R.string.expenses);
            default:
                return null;
        }
    }
}
