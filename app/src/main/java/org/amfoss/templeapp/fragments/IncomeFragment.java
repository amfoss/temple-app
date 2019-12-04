package org.amfoss.templeapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.amfoss.templeapp.R;
import org.amfoss.templeapp.activities.AddDonationActivity;
import org.amfoss.templeapp.adapters.DonationAdapter;
import org.amfoss.templeapp.utils.DonationUtils;
import org.amfoss.templeapp.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Chromicle (ajayprabhakar369@gmail.com)
 * @since 17/10/2019
 */
public class IncomeFragment extends Fragment {


    @BindView(R.id.fab_income)
    FloatingActionButton fab;

    @BindView(R.id.incomeRecyclerView)
    RecyclerView incomeRecycleView;

    @BindView(R.id.incomeProgressBarLoading)
    ProgressBar incomeProgressBar;

    @BindView(R.id.textViewAddIncome)
    TextView textViewAddIncome;


    private List<DonationUtils> donationUtilsArrayList;
    private DonationAdapter donationAdapter;

    private DatabaseReference donationDb;

    public IncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_income, container, false);

        ButterKnife.bind(this, rootView);

        addFirebaseInstance();

        fetchDonations();
        return rootView;
    }


    private void fetchDonations() {

        incomeRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        donationUtilsArrayList = new ArrayList<>();

        donationDb.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            DonationUtils donationValue = postSnapshot.getValue(DonationUtils.class);
                            donationUtilsArrayList.add(donationValue);
                        }

                        donationAdapter = new DonationAdapter(getActivity(), donationUtilsArrayList);
                        incomeRecycleView.setAdapter(donationAdapter);
                        incomeProgressBar.setVisibility(View.GONE);
                        changeFabPosition();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addFirebaseInstance() {
        UserUtils user = new UserUtils();
        String dbUserName = user.getDbUserName();
        donationDb = FirebaseDatabase.getInstance().getReference(dbUserName).child("donations");
    }

    private void changeFabPosition() {
        RelativeLayout.LayoutParams lay =
                new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lay.setMargins(2, 2, 75, 75);
        fab.setLayoutParams(lay);
        textViewAddIncome.setVisibility(View.GONE);
    }

    @OnClick(R.id.fab_income)
    void setUpFab(View view) {
        Intent intent = new Intent(getActivity(), AddDonationActivity.class);
        startActivity(intent);
    }
}
