package org.amfoss.templeapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.amfoss.templeapp.R;
import org.amfoss.templeapp.databinding.ActivityMainBinding;
import org.amfoss.templeapp.json_api.InternetConnection;
import org.amfoss.templeapp.ui.main.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setPresenter(new Presenter());

        mainBinding.checkBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });
    }

    protected void createNetErrorDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage(getString(R.string.internet_warn))
                .setTitle(getString(R.string.unable_to_connect))
                .setCancelable(false)
                .setPositiveButton(
                        getString(R.string.settings),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                startActivity(i);
                            }
                        })
                .setNegativeButton(
                        getString(R.string.Cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public class Presenter {
        public void onInsertClick(View view) {
            if (InternetConnection.checkConnection(getBaseContext())) {
                Intent intent = new Intent(getBaseContext(), InsertData.class);
                startActivity(intent);
            } else {
                createNetErrorDialog();
            }
        }

        public void onReadAllClick(View view) {
            if (InternetConnection.checkConnection(getBaseContext())) {
                Intent intent = new Intent(getBaseContext(), ReadAllData.class);
                startActivity(intent);
            } else {
                createNetErrorDialog();
            }
        }

        public void onUpdateClick(View view) {
            if (InternetConnection.checkConnection(getBaseContext())) {
                Intent intent = new Intent(getBaseContext(), UpdateData.class);
                startActivity(intent);
            } else {
                createNetErrorDialog();
            }
        }

        public void onReadSingleData(View view) {
            if (InternetConnection.checkConnection(getBaseContext())) {
                Intent intent = new Intent(getBaseContext(), ReadSingleData.class);
                startActivity(intent);
            } else {
                createNetErrorDialog();
            }
        }

        public void onDeleteClick(View view) {
            if (InternetConnection.checkConnection(getBaseContext())) {
                Intent intent = new Intent(getBaseContext(), DeleteData.class);
                startActivity(intent);
            } else {
                createNetErrorDialog();
            }
        }
    }
}
