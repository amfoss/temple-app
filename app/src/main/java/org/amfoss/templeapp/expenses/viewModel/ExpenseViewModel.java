package org.amfoss.templeapp.expenses.viewModel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import org.amfoss.templeapp.R;
import org.amfoss.templeapp.Util.Constants;
import org.amfoss.templeapp.expenses.adapter.ExpenseModel;
import org.amfoss.templeapp.home.UserModel;

public class ExpenseViewModel extends ViewModel {

    private DatabaseReference expenseDb;
    private ArrayList<ExpenseModel> expenseArrayList = new ArrayList<ExpenseModel>();
    MutableLiveData<ArrayList<ExpenseModel>> expense = new MutableLiveData<>();
    private boolean showProgressBar = true;

    private MutableLiveData<ArrayList<ExpenseModel>> liveData;
    private Context mContext;

    public void init(Context context) {
        mContext = context;
        if (liveData != null) {
            return;
        }

        liveData = getExpenses();
    }

    public LiveData<ArrayList<ExpenseModel>> getExpenseslist() {
        return liveData;
    }

    private MutableLiveData<ArrayList<ExpenseModel>> getExpenses() {
        fetchExpense();
        expense.setValue(expenseArrayList);

        return expense;
    }

    private void fetchExpense() {
        addFirebaseInstance();
        expenseDb.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ExpenseModel expensevalue = snapshot.getValue(ExpenseModel.class);
                            expenseArrayList.add(expensevalue);
                        }
                        expense.postValue(expenseArrayList);
                        showProgressBar = false;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(mContext, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void removeExpense(String id) {
        addFirebaseInstance();
        expenseDb
                .orderByChild(Constants.EXPENSE_DESCRIPTION)
                .equalTo(id)
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    String key = snapshot.getKey();
                                    expenseDb
                                            .child(key)
                                            .setValue(null)
                                            .addOnSuccessListener(
                                                    new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(mContext, R.string.remove_success, Toast.LENGTH_SHORT)
                                                                    .show();
                                                        }
                                                    })
                                            .addOnFailureListener(
                                                    new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(
                                                                            mContext, R.string.unable_delete_expense, Toast.LENGTH_SHORT)
                                                                    .show();
                                                        }
                                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(mContext, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private void addFirebaseInstance() {
        UserModel user = new UserModel();
        String dbUserName = user.getRealDbUserName(mContext);
        expenseDb =
                FirebaseDatabase.getInstance().getReference(dbUserName).child(Constants.DB_EXPENSE_NAME);
    }

    public int displayProgress() {
        if (showProgressBar) {
            return View.VISIBLE;
        } else {
            return View.GONE;
        }
    }
}
