package example.TempleApp.Activities;

//MainActivity.java

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import example.TempleApp.JSON_API.InternetConnection;
import example.TempleApp.R;

/**
 * Created by Chromicle.
 */


public class MainActivity extends AppCompatActivity {


    private Button read, readAll, insert, delete, update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        read = (Button) findViewById(R.id.read_btn);
        readAll = (Button) findViewById(R.id.read_all_btn);
        insert = (Button) findViewById(R.id.insert_btn);
        update = (Button) findViewById(R.id.update_btn);
        delete = (Button) findViewById(R.id.delete_btn);


        readAll.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {

                                           if (InternetConnection.checkConnection(getApplicationContext())) {
                                               Intent intent = new Intent(getApplicationContext(), ReadAllData.class);
                                               startActivity(intent);

                                           } else {
                                               createNetErrorDialog();
                                               // Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                                           }


                                       }
                                   }
        );


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent = new Intent(getApplicationContext(), InsertData.class);
                    startActivity(intent);


                } else {
                    createNetErrorDialog();
                    // Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }


            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent = new Intent(getApplicationContext(), UpdateData.class);
                    startActivity(intent);


                } else {
                    createNetErrorDialog();
                    // Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }


            }
        });


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent = new Intent(getApplicationContext(), ReadSingleData.class);
                    startActivity(intent);


                } else {

                    createNetErrorDialog();
                    // Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (InternetConnection.checkConnection(getApplicationContext())) {
                    Intent intent = new Intent(getApplicationContext(), DeleteData.class);
                    startActivity(intent);


                } else {
                    createNetErrorDialog();
                    // Toast.makeText(getApplicationContext(), "Check your internet connection", Toast.LENGTH_LONG).show();
                }


            }
        });


    }


    protected void createNetErrorDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You need internet connection for this app. Please turn on mobile network or Wi-Fi in Settings.")
                .setTitle("Unable to connect")
                .setCancelable(false)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                startActivity(i);
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }

}