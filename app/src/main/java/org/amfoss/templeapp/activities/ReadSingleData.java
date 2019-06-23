package org.amfoss.templeapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import org.amfoss.templeapp.json_api.Controller;
import org.amfoss.templeapp.R;



public class ReadSingleData extends AppCompatActivity {

    String id;
    String name;
    View view;
    RadioGroup radioGroup;
    int flag;
    RelativeLayout totalLayout;
    private Button read;
    private EditText uid1ET;
    private TextView id_l, name_l, id_v, name_v, paid_l, paid_v, pooja_l, pooja_v;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_data);
        read = (Button) findViewById(R.id.insert_btn);
        uid1ET = (EditText) findViewById(R.id.uid);

        id_l = (TextView) findViewById(R.id.id_l);
        name_l = (TextView) findViewById(R.id.name_l);
        id_v = (TextView) findViewById(R.id.id_v);
        name_v = (TextView) findViewById(R.id.name_v);
        pooja_l = (TextView) findViewById(R.id.pooja_l);
        pooja_v = (TextView) findViewById(R.id.pooja_v);
        paid_l = (TextView) findViewById(R.id.paid_l);
        paid_v = (TextView) findViewById(R.id.paid_v);

        view = this.getCurrentFocus();


        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        totalLayout = (RelativeLayout) findViewById(R.id.total_View);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_donate:
                        totalLayout.setVisibility(View.VISIBLE);
                        flag = 0;
                        Toast.makeText(getBaseContext(), getString(R.string.selected_donate), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radio_pooja:
                        totalLayout.setVisibility(View.VISIBLE);
                        flag = 1;
                        Toast.makeText(getBaseContext(), getString(R.string.selected_register), Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag == 1) {
                    id = getString(R.string.REG) + uid1ET.getText().toString();
                } else {
                    id = getString(R.string.DON) + uid1ET.getText().toString();
                }

                new ReadDataActivity().execute();
            }
        });
    }


    class ReadDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(ReadSingleData.this);
            dialog.setTitle(getString(R.string.wait));
            dialog.setMessage(getString(R.string.fetch));
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Controller.TAG, getString(R.string.ID_value) + id);
            JSONObject jsonObject = Controller.readData(id);
            Log.i(Controller.TAG, getString(R.string.json_obj) + jsonObject);

            try {
                /*
                  Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    JSONObject user = jsonObject.getJSONObject(getString(R.string.user));
                    name = user.getString(getString(R.string.name_));

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

            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            if (name != null) {
                id_l.setText(getString(R.string.ID));
                name_l.setText(getString(R.string.name));
                int flag = 0;
                if (id.substring(0, 3).equals(getString(R.string.DON))) {
                    id_v.setText(id.substring(3, id.length()));
                    flag = 1;
                } else if (id.substring(0, 3).equals(getString(R.string.REG))) {
                    id_v.setText(id.substring(3, id.length()));
                    flag = 0;
                }

                String[] str = name.split(getString(R.string.empty));
                name_v.setText(str[1]);
                pooja_l.setText(str[0]);
                paid_v.setText(str[2]);
                if (flag == 0) {
                    pooja_v.setText(getString(R.string.type_of_pooja));
                } else {
                    pooja_v.setText(getString(R.string.money_donated));
                }
                paid_l.setText(getString(R.string.paid_status));

            } else
                Toast.makeText(getApplicationContext(), getString(R.string.id_not_found), Toast.LENGTH_LONG).show();
        }
    }
}