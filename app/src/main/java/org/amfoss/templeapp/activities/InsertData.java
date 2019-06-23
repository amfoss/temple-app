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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import org.amfoss.templeapp.json_api.Controller;
import org.amfoss.templeapp.R;


public class InsertData extends AppCompatActivity {

    String id,name,poojaTyp,overall,money,paidCheck ="NOT PAID";
    Spinner poojaType;
    RadioGroup radioGroup;
    LinearLayout totalLayout;
    EditText moneyDonated;
    int flag;
    private Button insert;
    private EditText uid1ET, nameET;
    private CheckBox paid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_data);
        insert = (Button) findViewById(R.id.insert_btn);
        uid1ET = (EditText) findViewById(R.id.uid);
        nameET = (EditText) findViewById(R.id.name);
        paid = (CheckBox) findViewById(R.id.paid_check);
        poojaType = (Spinner) findViewById(R.id.spinner1);
        totalLayout = (LinearLayout) findViewById(R.id.total_View);
        moneyDonated = (EditText) findViewById(R.id.money_donated);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_donate:
                        totalLayout.setVisibility(View.VISIBLE);
                        poojaType.setVisibility(View.GONE);
                        moneyDonated.setVisibility(View.VISIBLE);
                        id = getResources().getString(R.string.DON);
                        flag = 0;
                        Toast.makeText(getBaseContext(), getString(R.string.selected_donate), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radio_pooja:
                        totalLayout.setVisibility(View.VISIBLE);
                        moneyDonated.setVisibility(View.GONE);
                        poojaType.setVisibility(View.VISIBLE);
                        id = getString(R.string.REG);
                        flag = 1;
                        Toast.makeText(getBaseContext(), getString(R.string.selected_register), Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });


        paid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    paidCheck = getString(R.string.PAID);

                } else {
                    paidCheck = getString(R.string.NOT_PAID);
                }

            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = id + uid1ET.getText().toString();
                name = nameET.getText().toString();
                if (id.length() == 3) {
                    uid1ET.setError(getString(R.string.valid_ID));
                }
                else {
                    if (flag == 1) {
                        poojaTyp = String.valueOf(poojaType.getSelectedItem());

                        overall = poojaTyp + getResources().getString(R.string.empty) + name + getResources().getString(R.string.empty) + paidCheck;
                        new InsertDataActivity().execute();
                    } else {
                        money = moneyDonated.getText().toString();
                        overall = money + getResources().getString(R.string.empty) + name + getResources().getString(R.string.empty) + paidCheck;
                        new InsertDataActivity().execute();
                    }
                }
            }
        });
    }


    class InsertDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        String result = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(InsertData.this);
            dialog.setTitle(getString(R.string.wait));
            dialog.setMessage(getString(R.string.insert));
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.insertData(id, overall);
            Log.i(Controller.TAG, getString(R.string.json_obj));

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    result = jsonObject.getString(getString(R.string.result));

                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, getString(R.string.exception) + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
}
