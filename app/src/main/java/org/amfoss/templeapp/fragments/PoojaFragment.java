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
import org.amfoss.templeapp.activities.AddPoojaActivity;
import org.amfoss.templeapp.adapters.PoojaAdapter;
import org.amfoss.templeapp.utils.PoojaUtils;
import org.amfoss.templeapp.utils.UserUtils;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 17/10/2019
*/
public class PoojaFragment extends Fragment {

    @BindView(R.id.fab_pooja)
    FloatingActionButton fab;

    @BindView(R.id.poojaRecyclerView)
    RecyclerView poojaRecycleView;

    @BindView(R.id.poojaprogressBarLoading)
    ProgressBar poojaProgressBar;

    @BindView(R.id.textViewAddPooja)
    TextView textViewAddPooja;

    List<PoojaUtils> poojaUtilsArrayList;
    PoojaAdapter poojaAdapter;

    DatabaseReference poojaDb;

    public PoojaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pooja, container, false);
        ButterKnife.bind(this, rootView);

        addFirebaseInstance();

        fetchPoojas();

        return rootView;
    }

    private void fetchPoojas() {

        poojaRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        poojaUtilsArrayList = new ArrayList<>();

        poojaDb.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            PoojaUtils poojaValue = postSnapshot.getValue(PoojaUtils.class);
                            poojaUtilsArrayList.add(poojaValue);
                        }

                        poojaAdapter = new PoojaAdapter(getActivity(), poojaUtilsArrayList);
                        poojaRecycleView.setAdapter(poojaAdapter);
                        poojaProgressBar.setVisibility(View.GONE);
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
        poojaDb = FirebaseDatabase.getInstance().getReference(dbUserName).child("poojas");
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
        Intent intent = new Intent(getActivity(), AddPoojaActivity.class);
        startActivity(intent);
    }
}
