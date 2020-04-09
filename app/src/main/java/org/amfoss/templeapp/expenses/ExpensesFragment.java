package org.amfoss.templeapp.expenses;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.Util.ActivityUtils;
import org.amfoss.templeapp.expenses.adapter.ExpenseAdapter;
import org.amfoss.templeapp.expenses.adapter.ExpenseModel;
import org.amfoss.templeapp.expenses.addExpense.AddExpenseActivity;
import org.amfoss.templeapp.expenses.viewModel.ExpenseViewModel;

/**
* @author robustTechie(s.priyadarshi629@gmail.com)
* @since 09/04/2020
*/
public class ExpensesFragment extends Fragment
        implements RecyclerItemTouchHelperExpense.RecyclerItemTouchHelperListener {

    @BindView(R.id.fab_expense)
    FloatingActionButton fab;

    @BindView(R.id.expenseRecyclerView)
    RecyclerView expenseRecyclerView;

    @BindView(R.id.expenseProgressBarLoading)
    ProgressBar expenseProgressBar;

    @BindView(R.id.textViewAddExpense)
    TextView textViewAddExpense;

    ExpenseAdapter expenseAdapter;
    private ExpenseViewModel expenseViewModel;

    public ExpensesFragment() {}

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_expenses, container, false);
        ButterKnife.bind(this, rootView);
        expenseRecyclerView.setHasFixedSize(true);
        expenseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        expenseViewModel = ViewModelProviders.of(requireActivity()).get(ExpenseViewModel.class);
        expenseViewModel.init(getContext());

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new RecyclerItemTouchHelperExpense(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(expenseRecyclerView);

        updateRecycleView();
        return rootView;
    }

    private void updateRecycleView() {
        expenseViewModel
                .getExpenseslist()
                .observe(
                        getViewLifecycleOwner(),
                        new Observer<ArrayList<ExpenseModel>>() {
                            @Override
                            public void onChanged(ArrayList<ExpenseModel> expenseModels) {
                                expenseAdapter.notifyDataSetChanged();

                                expenseProgressBar.setVisibility(expenseViewModel.displayProgress());
                                if (expenseAdapter.getItemCount() > 0) {
                                    changeFabPosition();
                                }
                            }
                        });

        expenseAdapter =
                new ExpenseAdapter(getActivity(), expenseViewModel.getExpenseslist().getValue());
        expenseRecyclerView.setAdapter(expenseAdapter);
    }

    private void changeFabPosition() {
        RelativeLayout.LayoutParams lay =
                new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lay.setMargins(2, 2, 75, 75);
        fab.setLayoutParams(lay);
        textViewAddExpense.setVisibility(View.GONE);
    }

    @OnClick(R.id.fab_expense)
    public void setUpFab(View view) {
        ActivityUtils.launchActivity(getActivity(), AddExpenseActivity.class, false);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ExpenseAdapter.ExpenseViewHolder) {
            Log.d("fab", "before");
            String id = expenseAdapter.getDescription(viewHolder.getAdapterPosition());
            dialog(id, viewHolder.getAdapterPosition());
            Log.d("fab", "after");
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
                    expenseAdapter.removeItem(position);
                    expenseViewModel.removeExpense(name);
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
