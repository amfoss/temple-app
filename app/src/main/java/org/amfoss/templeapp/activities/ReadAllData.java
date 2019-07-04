package org.amfoss.templeapp.activities;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.adapter.MyArrayAdapter;
import org.amfoss.templeapp.adapter.MyDataModel;
import org.amfoss.templeapp.databinding.ReadAllBinding;
import org.amfoss.templeapp.json_api.Controller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadAllData extends AppCompatActivity {

    ReadAllBinding readAllBinding;
    private int flag;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readAllBinding = DataBindingUtil.setContentView(this, R.layout.read_all);
        readAllBinding.setReadAll(new ReadAll());

        list = new ArrayList<>();
        adapter = new MyArrayAdapter(this, list);
        readAllBinding.listView.setAdapter(adapter);
    }

    public class ReadAll {

        public void donatePaid(View view) {
            readAllBinding.totalView.setVisibility(View.VISIBLE);
            flag = 1;
            readAllBinding.heading.setText(getString(R.string.list_of_donated_people_who_paid_only));
            readAllBinding.poojaPaid.setChecked(false);
            readAllBinding.poojaNotPaid.setChecked(false);
        }

        public void donateNotPaid(View view) {
            readAllBinding.totalView.setVisibility(View.VISIBLE);
            flag = 2;
            readAllBinding.heading.setText(getString(R.string.list_of_donated_people_who_not_paid_only));
            readAllBinding.poojaPaid.setChecked(false);
            readAllBinding.poojaNotPaid.setChecked(false);
        }

        public void poojaPaid(View view) {
            readAllBinding.totalView.setVisibility(View.VISIBLE);
            flag = 3;
            readAllBinding.heading.setText(
                    getString(R.string.list_of_registered_poojas_people_who_paid_only));
            readAllBinding.donatePaid.setChecked(false);
            readAllBinding.donateNotPaid.setChecked(false);
        }

        public void poojaNotPaid(View view) {
            readAllBinding.totalView.setVisibility(View.VISIBLE);
            flag = 4;
            readAllBinding.heading.setText(
                    getString(R.string.list_of_registered_poojas_who_not_paid_only));
            readAllBinding.donatePaid.setChecked(false);
            readAllBinding.donateNotPaid.setChecked(false);
        }

        public void readAllButton(View view) {
            if (readAllBinding.donatePaid.isChecked() && readAllBinding.donateNotPaid.isChecked()) {
                flag = 5;
            }

            if (readAllBinding.poojaPaid.isChecked() && readAllBinding.poojaNotPaid.isChecked()) {
                flag = 6;
            }

            if (!(readAllBinding.poojaPaid.isChecked()
                    || readAllBinding.poojaNotPaid.isChecked()
                    || readAllBinding.donatePaid.isChecked()
                    || readAllBinding.donateNotPaid.isChecked())) {
                Toast.makeText(
                                getApplicationContext(), getString(R.string.error_notSelected), Toast.LENGTH_LONG)
                        .show();
            } else {
                new ReadData1().execute();
                readAllBinding.radiogroupView.setVisibility(View.GONE);
                readAllBinding.readAllBtn1.setVisibility(View.GONE);
            }
        }
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

                                String[] str = name.split(getString(R.string.empty));

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
                                            && str[3].equals(getString(R.string.PAID))) {
                                        model.setName(name);
                                        model.setCountry(id.substring(3, id.length()));
                                        list.add(model);
                                    }
                                } else if (flag == 4) {
                                    if (id.substring(0, 3).equals(getString(R.string.REG))
                                            && str[3].equals(getString(R.string.NOT_PAID))) {
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
                readAllBinding.heading.setVisibility(TextView.VISIBLE);

            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.no_data), Toast.LENGTH_LONG)
                        .show();
                readAllBinding.heading.setVisibility(TextView.INVISIBLE);
            }
        }
    }
}
