package androidlabs.TempleApp;

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
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chromicle.
 */
public class InsertData extends AppCompatActivity{

    private Button insert;
    String id;
    String name;
    String poojaTyp;
    String overall;
    String thinkiID;
    String paidCheck="NOT PAID";
    private EditText uid1ET,mPoojaName,nameET;
    private CheckBox paid;
    Spinner poojaType;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_data);
        insert=(Button)findViewById(R.id.insert_btn);
        uid1ET=(EditText)findViewById(R.id.uid);
        nameET=(EditText)findViewById(R.id.name);
        paid=(CheckBox)findViewById(R.id.paid_check);
        poojaType=(Spinner)findViewById(R.id.spinner1);


        paid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    paidCheck="PAID";

                }
                else{
                    paidCheck="NOT PAID";
                }

            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 id=uid1ET.getText().toString();
                 name=nameET.getText().toString();
                 poojaTyp=String.valueOf(poojaType.getSelectedItem());

                overall=poojaTyp+getResources().getString(R.string.empty)+name+getResources().getString(R.string.empty)+paidCheck;

                new InsertDataActivity().execute();
            }
        });
    }



    class InsertDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        String result=null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(InsertData.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Inserting your values..");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.insertData(id,overall);
            Log.i(Controller.TAG, "Json obj ");

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                     result=jsonObject.getString("result");

                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, "exception" + je.getLocalizedMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
           Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }
    }
}
