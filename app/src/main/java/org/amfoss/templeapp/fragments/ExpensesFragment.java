package org.amfoss.templeapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import org.amfoss.templeapp.R;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 17/10/2019
*/
public class ExpensesFragment extends Fragment {
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_expenses, container, false);
        return rootView;
    }
}
