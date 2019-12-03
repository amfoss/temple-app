package org.amfoss.templeapp.activities;

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
import org.amfoss.templeapp.utils.PoojaUtils;
import org.amfoss.templeapp.utils.UserUtils;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 02/12/2019
*/
public class ConfirmDetailsPoojaActivity extends AppCompatActivity {

    @BindView(R.id.textViewPoojaAmount)
    TextView textViewPoojaAmount;

    @BindView(R.id.textViewPoojaDate)
    TextView textViewPoojaDate;

    @BindView(R.id.textViewPoojaName)
    TextView textViewPoojaName;

    @BindView(R.id.textViewPilgrimName)
    TextView textViewPilgrimName;

    @BindView(R.id.btnDetailsCorrect)
    ImageButton btnDetailsCorrect;

    @BindView(R.id.btnDetailsInCorrect)
    ImageButton btnDetailsInCorrect;

    Bundle bundle;
    DatabaseReference poojaDb;

    private String poojaDate, pilgrimName, poojaName, poojaAmount;
    private static String DB_POOJAS_NAME = "poojas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_details_pooja);

        ButterKnife.bind(this);

        createFirebaseInstance();
        getPoojaDetails();
        setTextViews();
    }

    private void createFirebaseInstance() {
        UserUtils user = new UserUtils();
        String dbUserName = user.getDbUserName();
        poojaDb = FirebaseDatabase.getInstance().getReference(dbUserName);
    }

    private void setTextViews() {
        textViewPilgrimName.setText(pilgrimName);
        textViewPoojaAmount.setText(poojaAmount);
        textViewPoojaDate.setText(poojaDate);
        textViewPoojaName.setText(poojaName);
    }

    private void getPoojaDetails() {
        bundle = getIntent().getExtras();

        poojaDate = getIntent().getStringExtra("poojaDate");
        poojaName = getIntent().getStringExtra("poojaName");
        poojaAmount = getIntent().getStringExtra("poojaAmount");
        pilgrimName = getIntent().getStringExtra("pilgrimName");
    }

    @OnClick(R.id.btnDetailsCorrect)
    public void uploadPoojaDetails(View view) {
        String id = poojaDb.push().getKey();

        PoojaUtils poojaDetails = new PoojaUtils(poojaDate, pilgrimName, poojaName, poojaAmount);
        poojaDb
                .child(DB_POOJAS_NAME)
                .child(id)
                .setValue(poojaDetails)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ConfirmDetailsPoojaActivity.this, "Pooja Added", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ConfirmDetailsPoojaActivity.this, e.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
    }

    @OnClick(R.id.btnDetailsInCorrect)
    public void correctDetails() {
        finish();
        super.onBackPressed();
    }
}
