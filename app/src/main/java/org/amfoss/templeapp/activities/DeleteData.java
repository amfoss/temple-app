package org.amfoss.templeapp.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import org.amfoss.templeapp.json_api.Controller;
import org.amfoss.templeapp.R;

public class DeleteData extends AppCompatActivity {

    String id;
    String name;
    RadioGroup radioGroup;
    RelativeLayout totalLayout;
    int flag;
    private Button delete;
    private EditText uid1ET;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_data);
        delete = (Button) findViewById(R.id.delete_btn);
        uid1ET = (EditText) findViewById(R.id.uid);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        totalLayout = (RelativeLayout) findViewById(R.id.total_View);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_donate:
                        totalLayout.setVisibility(View.VISIBLE);
                        flag = 0;
                        Toast.makeText(getBaseContext(), getString(R.string.delete_donated), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radio_pooja:
                        totalLayout.setVisibility(View.VISIBLE);
                        flag = 1;
                        Toast.makeText(getBaseContext(), getString(R.string.delete_pooja), Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag == 1) {
                    id = getString(R.string.REG) + uid1ET.getText().toString();
                } else {
                    id = getString(R.string.DON) + uid1ET.getText().toString();
                }

                new DeleteDataActivity().execute();
            }
        });
    }


    class DeleteDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        String result = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(DeleteData.this);
            dialog.setTitle(getString(R.string.wait));
            dialog.setMessage(getString(R.string.deleting));
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Controller.TAG, getString(R.string.id_value) + id);
            JSONObject jsonObject = Controller.deleteData(id);
            Log.i(Controller.TAG, getString(R.string.json_obj) + jsonObject);

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
