package org.amfoss.templeapp.activities;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.databinding.UpdateDataBinding;
import org.amfoss.templeapp.json_api.Controller;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateData extends AppCompatActivity {

    int flag;
    String paidCheck, poojaTyp, overall, money, name, id, amnt;

    UpdateDataBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.update_data);
        binding.setUpdate(new Update());
    }

    public class Update {

        public void updateButton(View view) {
            id = id + binding.uid.getText().toString();
            String sp = String.valueOf(binding.spinner1.getSelectedItem());
            name = binding.name.getText().toString();
            if (flag == 1) {
                if (sp.contentEquals(getString(R.string.custompooja))) {
                    String name = binding.custompooja.getText().toString();
                    poojaTyp = name;
                    amnt = binding.amount.getText().toString();
                } else {
                    poojaTyp = String.valueOf(binding.spinner1.getSelectedItem());
                    amnt = binding.amount.getText().toString();
                }

                overall =
                        poojaTyp
                                + getResources().getString(R.string.empty)
                                + amnt
                                + getResources().getString(R.string.empty)
                                + name
                                + getResources().getString(R.string.empty)
                                + paidCheck;
            } else {
                money = binding.moneyDonated.getText().toString();
                overall =
                        money
                                + getResources().getString(R.string.empty)
                                + name
                                + getResources().getString(R.string.empty)
                                + paidCheck;
            }
            new UpdateDataActivity().execute();
        }

        public void paidCheck(View v) {
            if (((CheckBox) v).isChecked()) {
                paidCheck = getString(R.string.PAID);
            } else {
                paidCheck = getString(R.string.NOT_PAID);
            }
        }

        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio_donate:
                    binding.totalView.setVisibility(View.VISIBLE);
                    binding.spinner1.setVisibility(View.GONE);
                    binding.moneyDonated.setVisibility(View.VISIBLE);
                    binding.Select.setVisibility(View.GONE);
                    binding.custompooja.setVisibility(View.GONE);
                    id = getString(R.string.DON);
                    flag = 0;
                    Toast.makeText(getBaseContext(), getString(R.string.UPDATE), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radio_pooja:
                    binding.totalView.setVisibility(View.VISIBLE);
                    binding.moneyDonated.setVisibility(View.GONE);
                    binding.spinner1.setVisibility(View.VISIBLE);
                    binding.Select.setVisibility(View.VISIBLE);

                    id = getString(R.string.REG);
                    flag = 1;
                    Toast.makeText(getBaseContext(), getString(R.string.update_pooja), Toast.LENGTH_SHORT)
                            .show();
                    break;
            }
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            if (i == 4) {
                binding.custompooja.setVisibility(View.VISIBLE);
                binding.amount.setVisibility(View.VISIBLE);
            } else {
                binding.custompooja.setVisibility(View.GONE);
                binding.amount.setVisibility(View.VISIBLE);
            }
        }
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
                /* Check Whether Its NULL??? */
                if (jsonObject != null) {
                    result = jsonObject.getString(getString(R.string.result));
                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, "" + je.getLocalizedMessage());
            }
            if (flag == 1) {
                id = "REG";
            } else {
                id = "DON";
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
