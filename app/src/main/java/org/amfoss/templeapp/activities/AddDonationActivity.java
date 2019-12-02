package org.amfoss.templeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.amfoss.templeapp.R;

public class AddDonationActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_donation);
    }

    public void confirm(View view) {
        Intent intent = new Intent(this, ConfirmDetailsPoojaActivity.class);
        startActivity(intent);
    }
}
