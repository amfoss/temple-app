package example.TempleApp.Activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import example.TempleApp.Adapter.MyArrayAdapter;
import example.TempleApp.Adapter.MyDataModel;
import example.TempleApp.JSON_API.Controller;
import example.TempleApp.R;

/**
 * Created by Chromicle.
 */
public class ReadAllData extends AppCompatActivity {

    RadioGroup radioGroup;
    LinearLayout totalLayout;
    LinearLayout radioButtonView;
    private int flag;
    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;
    private Button readAll;
    private TextView heading;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_all);

        readAll = findViewById(R.id.readAll_btn1);
        list = new ArrayList<>();
        adapter = new MyArrayAdapter(this, list);
        listView =  findViewById(R.id.listView);
        listView.setAdapter(adapter);
        heading =  findViewById(R.id.heading);
        radioButtonView = findViewById(R.id.radiogroup_view);

        readAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ReadData1().execute();
                radioButtonView.setVisibility(View.GONE);
                readAll.setVisibility(View.GONE);
            }
        });

        totalLayout =  findViewById(R.id.total_View);


        radioGroup = findViewById(R.id.radiogroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.donate_paid:
                        totalLayout.setVisibility(View.VISIBLE);
                        flag = 1;
                        heading.setText("List of all Donations who paid only");
                        break;
                    case R.id.donate_not_paid:
                        totalLayout.setVisibility(View.VISIBLE);
                        flag = 2;
                        heading.setText("List of all Donations who not paid only");
                        break;
                    case R.id.pooja_paid:
                        totalLayout.setVisibility(View.VISIBLE);
                        flag = 3;
                        heading.setText("List of all registrations who paid only");
                        break;

                    case R.id.pooja_not_paid:
                        totalLayout.setVisibility(View.VISIBLE);
                        flag = 4;
                        heading.setText("List of all registrations who not paid only");
                        break;

                }
            }
        });


    }


    class ReadData1 extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */

            x = list.size();

            if (x == 0)
                jIndex = 0;
            else
                jIndex = x;

            dialog = new ProgressDialog(ReadAllData.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Fetching all the Values");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.readAllData();
            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if (jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray("records");

                        /**
                         * Check Length of Array...
                         */


                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (; jIndex < lenArray; jIndex++) {

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */
                                MyDataModel model = new MyDataModel();

                                JSONObject innerObject = array.getJSONObject(jIndex);

                                String id = innerObject.getString("ID");
                                String name = innerObject.getString("NAME");

                                String[] str = name.split(getResources().getString(R.string.empty));

                                if (flag == 1) {
                                    if (id.substring(0, 3).equals("DON") && str[2].equals("PAID")) {
                                        model.setName(name);
                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);
                                    }
                                } else if (flag == 2) {
                                    if (id.substring(0, 3).equals("DON") && str[2].equals("NOT PAID")) {
                                        model.setName(name);
                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);

                                    }
                                } else if (flag == 3) {
                                    if (id.substring(0, 3).equals("REG") && str[2].equals("PAID")) {
                                        model.setName(name);
                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);

                                    }
                                } else if (flag == 4) {
                                    if (id.substring(0, 3).equals("DON") && str[2].equals("NOT PAID")) {
                                        model.setName(name);
                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);

                                    }
                                }


                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */
            if (list.size() > 0) {
                adapter.notifyDataSetChanged();
                heading.setVisibility(TextView.VISIBLE);


            } else {
                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
                heading.setVisibility(TextView.INVISIBLE);

            }
        }
    }
}



