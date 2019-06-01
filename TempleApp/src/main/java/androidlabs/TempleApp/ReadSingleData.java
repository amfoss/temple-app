package androidlabs.TempleApp;

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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chromicle
 */
public class ReadSingleData extends AppCompatActivity {

    private Button read;
    String id;
    String name;
    private EditText uid1ET;
    private TextView id_l, name_l, id_v, name_v, paid_l, paid_v, pooja_l, pooja_v;
    View view;

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

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = uid1ET.getText().toString();

                new ReadDataActivity().execute();
            }
        });
    }


    class ReadDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(ReadSingleData.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Fetching your values");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Controller.TAG, "IDVALUE" + id);
            JSONObject jsonObject = Controller.readData(id);
            Log.i(Controller.TAG, "Json obj " + jsonObject);

            try {
                /*
                  Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    JSONObject user = jsonObject.getJSONObject("user");
                    name = user.getString("name");

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
                id_l.setText("ID: ");
                name_l.setText("Name: ");
                id_v.setText(id);
                String[] str = name.split(getResources().getString(R.string.empty));
                name_v.setText(str[1]);
                pooja_l.setText(str[0]);
                paid_v.setText(str[2]);
                pooja_v.setText("Type of pooja: ");
                paid_l.setText("Paid Staus: ");

            } else
                Toast.makeText(getApplicationContext(), "ID not found", Toast.LENGTH_LONG).show();
        }
    }
}