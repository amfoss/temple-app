package org.amfoss.templeapp.income;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.home.UserModel;
import org.amfoss.templeapp.income.adapter.DonationAdapter;
import org.amfoss.templeapp.income.adapter.DonationModel;
import org.amfoss.templeapp.income.addDonation.AddDonationActivity;
import org.amfoss.templeapp.income.viewmodels.IncomeViewModel;

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

    private List<DonationModel> donationUtilsArrayList;
    private DonationAdapter donationAdapter;

    private DatabaseReference donationDb;
    IncomeViewModel incomeViewModel;

    public IncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_income, container, false);

        ButterKnife.bind(this, rootView);

        incomeRecycleView.setHasFixedSize(true);
        incomeRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        incomeViewModel = ViewModelProviders.of(requireActivity()).get(IncomeViewModel.class);
        incomeViewModel.init(getContext());

        updateRecycleView();
        return rootView;
    }

    private void updateRecycleView() {
        incomeViewModel
                .getDonationsList()
                .observe(
                        getViewLifecycleOwner(),
                        new Observer<ArrayList<DonationModel>>() {
                            @Override
                            public void onChanged(ArrayList<DonationModel> DonationModels) {
                                donationAdapter.notifyDataSetChanged();

                                incomeProgressBar.setVisibility(incomeViewModel.displayProgress());
                                if (donationAdapter.getItemCount() > 0) {
                                    changeFabPosition();
                                }
                            }
                        });

        donationAdapter =
                new DonationAdapter(getActivity(), incomeViewModel.getDonationsList().getValue());
        incomeRecycleView.setAdapter(donationAdapter);
    }

    private void fetchDonations() {

        incomeRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        donationUtilsArrayList = new ArrayList<>();

        donationDb.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            DonationModel donationValue = postSnapshot.getValue(DonationModel.class);
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
        UserModel user = new UserModel();
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
