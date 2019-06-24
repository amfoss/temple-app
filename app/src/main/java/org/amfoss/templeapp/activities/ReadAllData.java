package org.amfoss.templeapp.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.amfoss.templeapp.R;
import org.amfoss.templeapp.adapter.MyArrayAdapter;
import org.amfoss.templeapp.adapter.MyDataModel;
import org.amfoss.templeapp.json_api.Controller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadAllData extends AppCompatActivity {

    LinearLayout totalLayout;
    LinearLayout radioButtonView;
    private int flag;
    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;
    private Button readAll;
    private TextView heading;
    private CheckBox donatePaid;
    private CheckBox donateNotPaid;
    private CheckBox poojaPaid;
    private CheckBox poojaNotPaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_all);

        readAll = findViewById(R.id.readAll_btn1);
        list = new ArrayList<>();
        adapter = new MyArrayAdapter(this, list);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        heading = findViewById(R.id.heading);
        radioButtonView = findViewById(R.id.radiogroup_view);

        totalLayout = findViewById(R.id.total_View);
        donatePaid = findViewById(R.id.donate_paid);
        donateNotPaid = findViewById(R.id.donate_not_paid);
        poojaPaid = findViewById(R.id.pooja_paid);
        poojaNotPaid = findViewById(R.id.pooja_not_paid);

        readAll.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (donatePaid.isChecked() && donateNotPaid.isChecked()) {
                        flag = 5;
                    }

                    if (poojaPaid.isChecked() && poojaNotPaid.isChecked()) {
                        flag = 6;
                    }

                    if (!(poojaPaid.isChecked()
                        || poojaNotPaid.isChecked()
                        || donatePaid.isChecked()
                        || donateNotPaid.isChecked())) {

                        Toast.makeText(
                            getApplicationContext(),
                            getString(R.string.error_notSelected),
                            Toast.LENGTH_LONG)
                            .show();

                    } else {
                        new ReadData1().execute();
                        radioButtonView.setVisibility(View.GONE);
                        readAll.setVisibility(View.GONE);
                    }
                }
            });

        donatePaid.setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    totalLayout.setVisibility(View.VISIBLE);
                    flag = 1;
                    heading.setText(getString(R.string.list_of_donated_people_who_paid_only));
                    poojaPaid.setChecked(false);
                    poojaNotPaid.setChecked(false);
                }
            });

        donateNotPaid.setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    totalLayout.setVisibility(View.VISIBLE);
                    flag = 2;
                    heading.setText(getString(R.string.list_of_donated_people_who_not_paid_only));
                    poojaPaid.setChecked(false);
                    poojaNotPaid.setChecked(false);
                }
            });

        poojaPaid.setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    totalLayout.setVisibility(View.VISIBLE);
                    flag = 3;
                    heading.setText(getString(R.string.list_of_registered_poojas_people_who_paid_only));
                    donatePaid.setChecked(false);
                    donateNotPaid.setChecked(false);
                }
            });

        poojaNotPaid.setOnCheckedChangeListener(
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    totalLayout.setVisibility(View.VISIBLE);
                    flag = 4;
                    heading.setText(getString(R.string.list_of_registered_poojas_who_not_paid_only));
                    donatePaid.setChecked(false);
                    donateNotPaid.setChecked(false);
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
            /** Progress Dialog for User Interaction */
            x = list.size();

            if (x == 0) jIndex = 0;
            else jIndex = x;

            dialog = new ProgressDialog(ReadAllData.this);
            dialog.setTitle(getString(R.string.wait));
            dialog.setMessage(getString(R.string.fetch));
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.readAllData();
            try {
                /* Check Whether Its NULL??? */
                if (jsonObject != null) {
                    /* Check Length... */
                    if (jsonObject.length() > 0) {
                        /* Getting Array named "contacts" From MAIN Json Object */
                        JSONArray array = jsonObject.getJSONArray(getString(R.string.records));

                        /* Check Length of Array... */
                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (; jIndex < lenArray; jIndex++) {

                                /* Creating Every time New Object and Adding into List */
                                MyDataModel model = new MyDataModel();

                                JSONObject innerObject = array.getJSONObject(jIndex);

                                String id = innerObject.getString(getString(R.string.ID_));
                                String name = innerObject.getString(getString(R.string.name_));

                                String[] str = name.split(getResources().getString(R.string.empty));

                                if (flag == 1) {
                                    if (id.substring(0, 3).equals(getString(R.string.DON))
                                        && str[2].equals(getString(R.string.PAID))) {
                                        model.setName(name);

                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);
                                    }
                                } else if (flag == 2) {
                                    if (id.substring(0, 3).equals(getString(R.string.DON))
                                        && str[2].equals(getString(R.string.NOT_PAID))) {
                                        model.setName(name);
                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);
                                    }
                                } else if (flag == 3) {
                                    if (id.substring(0, 3).equals(getString(R.string.REG))
                                        && str[2].equals(getString(R.string.PAID))) {
                                        model.setName(name);
                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);
                                    }
                                } else if (flag == 4) {
                                    if (id.substring(0, 3).equals(getString(R.string.REG))
                                        && str[2].equals(getString(R.string.NOT_PAID))) {
                                        model.setName(name);
                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);
                                    }
                                } else if (flag == 5) {
                                    if (id.substring(0, 3).equals(getString(R.string.DON))) {
                                        model.setName(name);
                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);
                                    }

                                } else if (flag == 6) {
                                    if (id.substring(0, 3).equals(getString(R.string.REG))) {
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
            /* Checking if List size if more than zero then Update ListView */
            if (list.size() > 0) {
                adapter.notifyDataSetChanged();
                heading.setVisibility(TextView.VISIBLE);

            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.no_data), Toast.LENGTH_LONG)
                    .show();
                heading.setVisibility(TextView.INVISIBLE);
            }
        }
    }
}
