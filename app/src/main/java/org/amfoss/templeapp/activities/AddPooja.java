package org.amfoss.templeapp.activities;



import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import org.amfoss.templeapp.R;

public class AddPooja extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pooja);
    }

    public void confirm(View view) {
        Intent intent = new Intent(this, ConfirmDetails.class);
        startActivity(intent);
    }


}






