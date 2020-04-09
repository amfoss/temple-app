package org.amfoss.templeapp.expenses.addExpense;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.Util.Constants;
import org.amfoss.templeapp.expenses.adapter.ExpenseModel;
import org.amfoss.templeapp.home.UserModel;

/**
* @author robustTechie(s.priyadarshi629@gmail.com)
* @since 09/04/2020
*/
public class ConfirmDetailsExpenseActivity extends AppCompatActivity {

    @BindView(R.id.textViewExpenseAmount)
    TextView textViewExpenseAmount;

    @BindView(R.id.textViewExpenseDate)
    TextView textViewExpenseDate;

    @BindView(R.id.textViewExpenseType)
    TextView textViewExpenseType;

    @BindView(R.id.textViewExpenseDescription)
    TextView textViewExpenseDescription;

    @BindView(R.id.btnDetailsCorrect)
    ImageButton btnDetailsCorrect;

    @BindView(R.id.btnDetailsInCorrect)
    ImageButton btnDetailsInCorrect;

    Bundle bundle;
    DatabaseReference expenseDb;

    private String expenseDate, expenseId, expenseAmount, expenseDescription, expenseType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_details_expense);

        ButterKnife.bind(this);

        createFirebaseInstance();
        getExpenseDetails();
        setTextViews();
    }

    private void createFirebaseInstance() {
        UserModel user = new UserModel();
        String dbUserName = user.getRealDbUserName(getApplicationContext());
        expenseDb = FirebaseDatabase.getInstance().getReference(dbUserName);
    }

    private void setTextViews() {
        textViewExpenseType.setText(expenseType);
        textViewExpenseDate.setText(expenseDate);
        textViewExpenseDescription.setText(expenseDescription);
        textViewExpenseAmount.setText(expenseAmount);
    }

    public void getExpenseDetails() {
        bundle = getIntent().getExtras();

        expenseId = getIntent().getStringExtra("expenseId");
        expenseType = getIntent().getStringExtra("expenseType");
        expenseDescription = getIntent().getStringExtra("expenseDescription");
        expenseAmount = getIntent().getStringExtra("expenseAmount");
        expenseDate = getIntent().getStringExtra("expenseDate");
    }

    @OnClick(R.id.btnDetailsCorrect)
    public void uploadExpenseDetails(View view) {
        String id = expenseDb.push().getKey();

        ExpenseModel expenseDetails =
                new ExpenseModel(expenseDate, expenseAmount, expenseDescription, expenseType);
        expenseDb
                .child(Constants.DB_EXPENSE_NAME)
                .child(id)
                .setValue(expenseDetails)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(
                                                ConfirmDetailsExpenseActivity.this,
                                                R.string.expense_added,
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(
                                                ConfirmDetailsExpenseActivity.this, e.getMessage(), Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });
    }

    @OnClick(R.id.btnDetailsInCorrect)
    public void correctDetails() {
        finish();
        super.onBackPressed();
    }
}
