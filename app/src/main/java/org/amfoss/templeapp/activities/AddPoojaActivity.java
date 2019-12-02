package org.amfoss.templeapp.activities;

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
import org.amfoss.templeapp.utils.UserUtils;

/**
* @author Chromicle (ajayprabhakar369@gmail.com)
* @since 02/12/2019
*/
public class AddPoojaActivity extends AppCompatActivity {

    @BindView(R.id.editTextDate)
    EditText editTextDate;

    @BindView(R.id.btn_date)
    Button btnDate;

    @BindView(R.id.btnRegister)
    Button btnRegister;

    @BindView(R.id.btnPrint)
    Button btnPrint;

    @BindView(R.id.editTextPilgrimName)
    EditText editTextPilgrimName;

    @BindView(R.id.editTextPoojaName)
    EditText editTextPoojaName;

    @BindView(R.id.editTextPoojaAmount)
    EditText editTextPoojaAmount;

    private Bundle bundle;

    private UserUtils user;
    private int mYear, mMonth, mDay;
    private String poojaDate, pilgrimName, poojaName, poojaAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pooja);
        ButterKnife.bind(this);

        user = new UserUtils();
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

                                poojaDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                editTextDate.setText(poojaDate);
                            }
                        },
                        mYear,
                        mMonth,
                        mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.btnRegister)
    public void registerPooja(View view) {

        getPoojaValues();
        if (!checkErrors()) {
            return;
        }
        verifyDetails();
    }

    private void verifyDetails() {
        Intent intent = new Intent(this, ConfirmDetailsPoojaActivity.class);
        intent.putExtra("pilgrimName", pilgrimName);
        intent.putExtra("poojaAmount", poojaAmount);
        intent.putExtra("poojaName", poojaName);
        intent.putExtra("poojaDate", poojaDate);
        startActivity(intent);
    }

    private boolean checkErrors() {
        EditText[] allFields = {
            editTextDate, editTextPilgrimName, editTextPoojaAmount, editTextPoojaName
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

    private void getPoojaValues() {
        pilgrimName = editTextPilgrimName.getText().toString().trim();
        poojaAmount = editTextPoojaAmount.getText().toString().trim();
        poojaName = editTextPoojaName.getText().toString().trim();
    }
}
