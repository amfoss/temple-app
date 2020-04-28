package org.amfoss.templeapp.income.addDonation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.home.UserModel;

public class AddDonationActivity extends AppCompatActivity {

    @BindView(R.id.btn_date)
    Button btnDate;

    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindView(R.id.editTextDonationCause)
    EditText editTextDonationCause;

    @BindView(R.id.editTextDonationAmount)
    EditText editTextDonationAmount;

    @BindView(R.id.editTextPilgrimName)
    EditText editTextPlgrimName;

    @BindView(R.id.editTextDate)
    EditText editTextDate;

    private UserModel user;
    private int mYear, mMonth, mDay;
    private String donationDate;
    private String donationCause, donationAmount, pilgrimName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_donation);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_date)
    public void datePickerDialoge(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(
                        this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                donationDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                editTextDate.setText(donationDate);
                            }
                        },
                        mYear,
                        mMonth,
                        mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.btnRegister)
    public void registerPooja(View view) {

        getDonationValues();
        if (!checkErrors()) {
            return;
        }
        verifyDetails();
    }

    private void verifyDetails() {
        Intent intent = new Intent(this, ConfirmDetailsDonationActivity.class);
        intent.putExtra("pilgrimName", pilgrimName);
        intent.putExtra("donationAmount", donationAmount);
        intent.putExtra("donationCause", donationCause);
        intent.putExtra("donationDate", donationDate);
        startActivity(intent);
    }

    private boolean checkErrors() {
        EditText[] allFields = {
            editTextPlgrimName, editTextDate, editTextDonationCause, editTextDonationAmount
        };
        List<EditText> ErrorFields = new ArrayList<EditText>();
        for (EditText edit : allFields) {
            if (TextUtils.isEmpty(edit.getText())) {
                ErrorFields.add(edit);
                for (int i = 0; i < ErrorFields.size(); i++) {
                    EditText currentField = ErrorFields.get(i);
                    currentField.setError("this field required");
                    currentField.requestFocus();
                    return false;
                }
            }
        }
        return true;
    }

    private void getDonationValues() {
        pilgrimName = editTextPlgrimName.getText().toString().trim();
        donationAmount = editTextDonationAmount.getText().toString().trim();
        donationCause = editTextDonationCause.getText().toString().trim();
    }
}
