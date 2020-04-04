package org.amfoss.templeapp.poojas;

import android.app.AlertDialog;
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
import org.amfoss.templeapp.Util.ActivityUtils;
import org.amfoss.templeapp.poojas.adapter.PoojaAdapter;
import org.amfoss.templeapp.poojas.adapter.PoojaModel;
import org.amfoss.templeapp.poojas.addPooja.AddPoojaActivity;
import org.amfoss.templeapp.poojas.viewmodel.PoojaViewModel;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 17/10/2019
*/
public class PoojaFragment extends Fragment
        implements RecyclerItemTouchHelperPooja.RecyclerItemTouchHelperListener {

    DatabaseReference poojaDb;

    @BindView(R.id.fab_pooja)
    FloatingActionButton fab;

    @BindView(R.id.poojaRecyclerView)
    RecyclerView poojaRecycleView;

    @BindView(R.id.poojaprogressBarLoading)
    ProgressBar poojaProgressBar;

    @BindView(R.id.textViewAddPooja)
    TextView textViewAddPooja;

    PoojaAdapter poojaAdapter;
    private PoojaViewModel poojaViewModel;

    public PoojaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pooja, container, false);
        ButterKnife.bind(this, rootView);
        poojaRecycleView.setHasFixedSize(true);
        poojaRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        poojaViewModel = ViewModelProviders.of(requireActivity()).get(PoojaViewModel.class);
        poojaViewModel.init(getContext());

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new RecyclerItemTouchHelperPooja(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(poojaRecycleView);
        updateRecycleView();
        return rootView;
    }

    private void updateRecycleView() {
        poojaViewModel
                .getPoojaslist()
                .observe(
                        getViewLifecycleOwner(),
                        new Observer<ArrayList<PoojaModel>>() {
                            @Override
                            public void onChanged(ArrayList<PoojaModel> poojaModels) {
                                poojaAdapter.notifyDataSetChanged();

                                poojaProgressBar.setVisibility(poojaViewModel.displayProgress());
                                if (poojaAdapter.getItemCount() > 0) {
                                    changeFabPosition();
                                }
                            }
                        });

        poojaAdapter = new PoojaAdapter(getActivity(), poojaViewModel.getPoojaslist().getValue());
        poojaRecycleView.setAdapter(poojaAdapter);
    }

    private void changeFabPosition() {
        RelativeLayout.LayoutParams lay =
                new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lay.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lay.setMargins(2, 2, 75, 75);
        fab.setLayoutParams(lay);
        textViewAddPooja.setVisibility(View.GONE);
    }

    @OnClick(R.id.fab_pooja)
    public void setUpFab(View view) {
        ActivityUtils.launchActivity(getActivity(), AddPoojaActivity.class, false);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof PoojaAdapter.PoojaViewHolder) {
            String name = poojaAdapter.getName(viewHolder.getAdapterPosition());
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
                    poojaAdapter.removeItem(position);
                    poojaViewModel.removePooja(name);
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
