package org.amfoss.templeapp.expenses.addExpense;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.home.UserModel;

/**
* @author robustTechie(s.priyadarshi629@gmail.com)
* @since 09/04/2020
*/
public class AddExpenseActivity extends AppCompatActivity {

    @BindView(R.id.editTextExpenseDate)
    EditText editTextExpenseDate;

    @BindView(R.id.btn_date)
    Button btndata;

    @BindView(R.id.btnPrint)
    Button btnPrint;

    @BindView(R.id.editTextExpenseType)
    EditText editTextExpenseType;

    @BindView(R.id.editTextExpenseDescription)
    EditText editTextExpenseDescription;

    @BindView(R.id.editTextExpenseAmount)
    EditText editTextExpenseAmount;

    private UserModel user;
    private int mYear, mMonth, mDay;
    private String expenseDate, expenseId, expenseAmount, expenseDescription, expenseType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);
        ButterKnife.bind(this);

        user = new UserModel();
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

                                expenseDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                editTextExpenseDate.setText(expenseDate);
                            }
                        },
                        mYear,
                        mMonth,
                        mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.btnRegister)
    public void registerExpense(View view) {

        getExpenseValues();
        if (!checkErrors()) {
            return;
        }
        verifyDetails();
    }

    private void verifyDetails() {
        Intent intent = new Intent(this, ConfirmDetailsExpenseActivity.class);
        intent.putExtra("expenseType", expenseType);
        intent.putExtra("expenseDescription", expenseDescription);
        intent.putExtra("expenseAmount", expenseAmount);
        intent.putExtra("expenseDate", expenseDate);
        startActivity(intent);
    }

    private boolean checkErrors() {
        EditText[] allFields = {
            editTextExpenseType, editTextExpenseDate, editTextExpenseDescription, editTextExpenseAmount
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

    private void getExpenseValues() {
        expenseType = editTextExpenseType.getText().toString().trim();
        expenseDescription = editTextExpenseDescription.getText().toString().trim();
        expenseAmount = editTextExpenseAmount.getText().toString().trim();
    }
}
