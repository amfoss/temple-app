package org.amfoss.templeapp.income.viewmodels;

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
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.Util.Constants;
import org.amfoss.templeapp.home.UserModel;
import org.amfoss.templeapp.income.adapter.DonationModel;

/**
* @author by Chromicle (ajayprabhakar369@gmail.com)
* @since 4/1/2020
*/
public class IncomeViewModel extends ViewModel {

    private DatabaseReference donationDb;
    private ArrayList<DonationModel> donationArrayList = new ArrayList<DonationModel>();
    MutableLiveData<ArrayList<DonationModel>> donation = new MutableLiveData<>();
    private boolean showProgressBar = true;

    private MutableLiveData<ArrayList<DonationModel>> liveData;
    private Context mContext;

    public void init(Context context) {
        mContext = context;
        if (liveData != null) {
            return;
        }

        liveData = getDonations();
    }

    public LiveData<ArrayList<DonationModel>> getDonationsList() {
        return liveData;
    }

    private MutableLiveData<ArrayList<DonationModel>> getDonations() {
        fetchDonations();
        donation.setValue(donationArrayList);

        return donation;
    }

    private void fetchDonations() {
        addFirebaseInstance();
        donationArrayList = new ArrayList<>();

        donationDb.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            DonationModel donationValue = postSnapshot.getValue(DonationModel.class);
                            donationArrayList.add(donationValue);
                        }
                        donation.postValue(donationArrayList);
                        showProgressBar = false;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(mContext, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void removeDonation(String value) {
        addFirebaseInstance();
        donationDb
                .orderByChild(Constants.PILGRIM_NAME)
                .equalTo(value)
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                    String key = childSnapshot.getKey();
                                    donationDb
                                            .child(key)
                                            .setValue(null)
                                            .addOnSuccessListener(
                                                    aVoid ->
                                                            Toast.makeText(mContext, R.string.remove_success, Toast.LENGTH_SHORT)
                                                                    .show())
                                            .addOnFailureListener(
                                                    e ->
                                                            Toast.makeText(
                                                                            mContext, R.string.unable_delete_donation, Toast.LENGTH_SHORT)
                                                                    .show());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}
                        });
    }

    private void addFirebaseInstance() {
        UserModel user = new UserModel();
        String dbUserName = user.getDbUserName();
        donationDb = FirebaseDatabase.getInstance().getReference(dbUserName).child("donations");
    }

    public int displayProgress() {
        if (showProgressBar) {
            return View.VISIBLE;
        } else {
            return View.GONE;
        }
    }
}
