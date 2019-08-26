package org.amfoss.templeapp.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.databinding.DeleteDataBinding;
import org.amfoss.templeapp.json_api.Controller;
import org.json.JSONException;
import org.json.JSONObject;

public class DeleteData extends AppCompatActivity {

    String id;
    int flag;
    DeleteDataBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.delete_data);
        binding.setDelete(new Delete());
    }

    public class Delete {
        public void deleteButton(View view) {
            if (flag == 1) {
                id = getString(R.string.REG) + binding.uid.getText().toString();
            } else {
                id = getString(R.string.DON) + binding.uid.getText().toString();
            }
            new DeleteDataActivity().execute();
        }

        public void radioGroupOnCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio_donate:
                    binding.totalView.setVisibility(View.VISIBLE);
                    flag = 0;
                    Toast.makeText(getBaseContext(), getString(R.string.delete_donated), Toast.LENGTH_SHORT)
                            .show();
                    break;
                case R.id.radio_pooja:
                    binding.totalView.setVisibility(View.VISIBLE);
                    flag = 1;
                    Toast.makeText(getBaseContext(), getString(R.string.delete_pooja), Toast.LENGTH_SHORT)
                            .show();
                    break;
            }
        }
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
                /* Check Whether Its NULL??? */
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
            Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}
