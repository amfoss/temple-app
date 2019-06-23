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


public class UpdateData extends AppCompatActivity {

    String id;
    String name;
    RadioGroup radioGroup;
    LinearLayout totalLayout;
    int flag;
    Spinner poojaType;
    CheckBox paid;
    EditText moneyDonated;
    String paidCheck, poojaTyp, overall, money;
    private Button update;
    private EditText uid1ET, uid2, nameET;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data);
        update = (Button) findViewById(R.id.update_btn1);
        uid1ET = (EditText) findViewById(R.id.uid);
        nameET = (EditText) findViewById(R.id.name);


        paid = (CheckBox) findViewById(R.id.paid_check);
        poojaType = (Spinner) findViewById(R.id.spinner1);
        totalLayout = (LinearLayout) findViewById(R.id.total_View);
        moneyDonated = (EditText) findViewById(R.id.money_donated);


        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        totalLayout = (LinearLayout) findViewById(R.id.total_View);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_donate:
                        totalLayout.setVisibility(View.VISIBLE);
                        poojaType.setVisibility(View.GONE);
                        moneyDonated.setVisibility(View.VISIBLE);
                        id = getString(R.string.DON);
                        flag = 0;
                        Toast.makeText(getBaseContext(), getString(R.string.UPDATE), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radio_pooja:
                        totalLayout.setVisibility(View.VISIBLE);
                        moneyDonated.setVisibility(View.GONE);
                        poojaType.setVisibility(View.VISIBLE);
                        id = getString(R.string.REG);
                        flag = 1;
                        Toast.makeText(getBaseContext(), getString(R.string.update_pooja), Toast.LENGTH_LONG).show();
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


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = id + uid1ET.getText().toString();
                name = nameET.getText().toString();

                if (flag == 1) {
                    poojaTyp = String.valueOf(poojaType.getSelectedItem());

                    overall = poojaTyp + getResources().getString(R.string.empty) + name + getResources().getString(R.string.empty) + paidCheck;
                } else {
                    money = moneyDonated.getText().toString();
                    overall = money + getResources().getString(R.string.empty) + name + getResources().getString(R.string.empty) + paidCheck;
                }
                new UpdateDataActivity().execute();
            }
        });
    }


    class UpdateDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        String result = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(UpdateData.this);
            dialog.setTitle(getString(R.string.wait));
            dialog.setMessage(getString(R.string.JSON));
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.updateData(id, overall);
            Log.i(Controller.TAG, getString(R.string.json_obj));

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    result = jsonObject.getString(getString(R.string.result));

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
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
}
