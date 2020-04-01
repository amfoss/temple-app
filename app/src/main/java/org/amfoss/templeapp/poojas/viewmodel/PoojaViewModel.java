package org.amfoss.templeapp.poojas.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import org.amfoss.templeapp.home.UserModel;
import org.amfoss.templeapp.poojas.adapter.PoojaModel;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 4/1/2020
*/
public class PoojaViewModel extends ViewModel {

    private DatabaseReference poojaDb;
    private ArrayList<PoojaModel> poojaArrayList = new ArrayList<PoojaModel>();
    MutableLiveData<ArrayList<PoojaModel>> pooja = new MutableLiveData<>();
    private boolean showProgressBar = true;

    private MutableLiveData<ArrayList<PoojaModel>> liveData;
    private Context mContext;

    public void init(Context context) {
        mContext = context;
        if (liveData != null) {
            return;
        }

        liveData = getPoojas();
    }

    public LiveData<ArrayList<PoojaModel>> getPoojaslist() {
        return liveData;
    }

    private MutableLiveData<ArrayList<PoojaModel>> getPoojas() {
        fetchPoojas();
        pooja.setValue(poojaArrayList);

        return pooja;
    }

    private void fetchPoojas() {
        addFirebaseInstance();
        poojaDb.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            PoojaModel poojaValue = snapshot.getValue(PoojaModel.class);
                            poojaArrayList.add(poojaValue);
                        }
                        pooja.postValue(poojaArrayList);
                        showProgressBar = false;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(mContext, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addFirebaseInstance() {
        UserModel user = new UserModel();
        String dbUserName = user.getDbUserName();
        poojaDb = FirebaseDatabase.getInstance().getReference(dbUserName).child("poojas");
    }

    public int displayProgress() {
        if (showProgressBar) {
            return View.VISIBLE;
        } else {
            return View.GONE;
        }
    }
}
