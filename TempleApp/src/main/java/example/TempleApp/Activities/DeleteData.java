package example.TempleApp.Activities;

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

import example.TempleApp.JSON_API.Controller;
import example.TempleApp.R;

/**
 * Created by Chromicle.
 */
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
                        Toast.makeText(getBaseContext(), "Selected To Delete Donated Money", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radio_pooja:
                        totalLayout.setVisibility(View.VISIBLE);
                        flag = 1;
                        Toast.makeText(getBaseContext(), "Selected To Delete Registered Pooja", Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag == 1) {
                    id = "REG" + uid1ET.getText().toString();
                } else {
                    id = "DON" + uid1ET.getText().toString();
                }

                new DeleteDataActivity().execute();
            }
        });
    }


    class DeleteDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;
        String result = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(DeleteData.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Deleting... ");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Controller.TAG, "IDVALUE" + id);
            JSONObject jsonObject = Controller.deleteData(id);
            Log.i(Controller.TAG, "Json obj " + jsonObject);

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    result = jsonObject.getString("result");


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
