package org.amfoss.templeapp.income;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.income.adapter.DonationAdapter;
import org.amfoss.templeapp.income.adapter.DonationModel;
import org.amfoss.templeapp.income.addDonation.AddDonationActivity;
import org.amfoss.templeapp.income.viewmodels.IncomeViewModel;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 17/10/2019
*/
public class IncomeFragment extends Fragment
        implements RecyclerItemTouchHelperIncome.RecyclerItemTouchHelperListener {

    @BindView(R.id.fab_income)
    FloatingActionButton fab;

    @BindView(R.id.incomeRecyclerView)
    RecyclerView incomeRecycleView;

    @BindView(R.id.incomeProgressBarLoading)
    ProgressBar incomeProgressBar;

    @BindView(R.id.textViewAddIncome)
    TextView textViewAddIncome;

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

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new RecyclerItemTouchHelperIncome(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(incomeRecycleView);

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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof DonationAdapter.DonationViewHolder) {
            String name = donationAdapter.getName(viewHolder.getAdapterPosition());
            dialog(name, viewHolder.getAdapterPosition());
        }
    }

    public void dialog(String name, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // Set the message show for the Alert time
        builder.setMessage(R.string.ask_delete);
        builder.setTitle(R.string.alert);
        builder.setCancelable(false);
        builder.setPositiveButton(
                R.string.yes,
                (dialog, which) -> {
                    donationAdapter.removeItem(position);
                    incomeViewModel.removeDonation(name);
                });
        builder.setNegativeButton(
                R.string.no,
                (dialog, which) -> {
                    dialog.cancel();
                });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
