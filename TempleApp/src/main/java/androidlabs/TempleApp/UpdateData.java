package androidlabs.TempleApp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chromicle.
 */
public class UpdateData extends AppCompatActivity {

    private Button update;
    String id;
    String name;
    private EditText uid1ET, uid2, nameET;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data);
        update = (Button) findViewById(R.id.update_btn1);
        uid1ET = (EditText) findViewById(R.id.uid);
        nameET = (EditText) findViewById(R.id.name);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = uid1ET.getText().toString();
                name = nameET.getText().toString();
                new UpdateDataActivity().execute();
            }
        });
    }


    class UpdateDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        String result = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(UpdateData.this);
            dialog.setTitle("Hey Wait Please..." + x);
            dialog.setMessage("I am getting your JSON");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.updateData(id, name);
            Log.i(Controller.TAG, "Json obj ");

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
