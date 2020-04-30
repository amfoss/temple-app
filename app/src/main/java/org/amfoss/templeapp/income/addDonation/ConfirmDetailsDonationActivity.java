package org.amfoss.templeapp.income.addDonation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.Util.Constants;
import org.amfoss.templeapp.home.UserModel;
import org.amfoss.templeapp.income.adapter.DonationModel;

public class ConfirmDetailsDonationActivity extends AppCompatActivity {
    @BindView(R.id.textViewDonationAmount)
    TextView textViewDonationAmount;

    @BindView(R.id.textViewDonationDate)
    TextView textViewDonationDate;

    @BindView(R.id.textViewDonationCause)
    TextView textViewDonationCause;

    @BindView(R.id.textViewPilgrimName)
    TextView textViewPilgrimName;

    @BindView(R.id.btnDetailsCorrect)
    ImageButton btnDetailsCorrect;

    @BindView(R.id.btnDetailsInCorrect)
    ImageButton btnDetailsInCorrect;

    DatabaseReference donationDb;

    private String donationDate, pilgrimName, donationCause, donationAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_details_donation);

        ButterKnife.bind(this);

        createFirebaseInstance();
        getPoojaDetails();
        setTextViews();
    }

    private void createFirebaseInstance() {
        UserModel user = new UserModel();
        String dbUserName = user.getRealDbUserName(getApplicationContext());
        donationDb = FirebaseDatabase.getInstance().getReference(dbUserName);
    }

    private void setTextViews() {
        textViewPilgrimName.setText(pilgrimName);
        textViewDonationAmount.setText(donationAmount);
        textViewDonationDate.setText(donationDate);
        textViewDonationCause.setText(donationCause);
    }

    private void getPoojaDetails() {

        donationDate = getIntent().getStringExtra("donationDate");
        donationCause = getIntent().getStringExtra("donationCause");
        donationAmount = getIntent().getStringExtra("donationAmount");
        pilgrimName = getIntent().getStringExtra("pilgrimName");
    }

    @OnClick(R.id.btnDetailsCorrect)
    public void uploadDonationDetails(View view) {
        String id = donationDb.push().getKey();

        DonationModel donationDetails =
                new DonationModel(donationDate, pilgrimName, donationCause, donationAmount);
        donationDb
                .child(Constants.DB_DONATION_NAME)
                .child(id)
                .setValue(donationDetails)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(
                                                ConfirmDetailsDonationActivity.this, "Donation Added", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(
                                                ConfirmDetailsDonationActivity.this, e.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
        finish();
    }

    @OnClick(R.id.btnDetailsInCorrect)
    public void correctDetails() {
        finish();
        super.onBackPressed();
    }
}
