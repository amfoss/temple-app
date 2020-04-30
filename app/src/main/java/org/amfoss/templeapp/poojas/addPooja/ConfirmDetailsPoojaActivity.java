package org.amfoss.templeapp.poojas.addPooja;

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
import org.amfoss.templeapp.poojas.adapter.PoojaModel;

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
        UserModel user = new UserModel();
        String dbUserName = user.getRealDbUserName(getApplicationContext());
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

        PoojaModel poojaDetails = new PoojaModel(poojaDate, pilgrimName, poojaName, poojaAmount);
        poojaDb
                .child(Constants.DB_POOJA_NAME)
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
        finish();
    }

    @OnClick(R.id.btnDetailsInCorrect)
    public void correctDetails() {
        finish();
        super.onBackPressed();
    }
}
