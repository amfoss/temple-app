package org.amfoss.templeapp.expenses.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collections;
import java.util.List;
import org.amfoss.templeapp.R;

/**
* @author robustTechie(s.priyadarshi629@gmail.com)
* @since 09/04/2020
*/
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    Context context;
    private List<ExpenseModel> expenseList;

    public ExpenseAdapter(Context context, List<ExpenseModel> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExpenseViewHolder(
                LayoutInflater.from(context).inflate(R.layout.individual_expense, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ExpenseViewHolder holder, int position) {
        ExpenseModel expense = expenseList.get(position);
        holder.listExpenseType.setText(expense.getExpenseType());
        holder.listExpenseDate.setText(expense.getExpenseDate());
        holder.listExpenseDescription.setText(expense.getExpenseDescription());
        holder.listExpenseAmount.setText(expense.getExpenseAmount());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public void sortFilter() {
        final String[] sort = {"Date"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sort By");
        builder.setItems(
                sort,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Collections.sort(expenseList, ExpenseModel.BY_TITLE_DATE);
                            notifyDataSetChanged();
                        }
                    }
                });
        builder.create().show();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout viewBackground, viewForeground;
        TextView listExpenseType;
        TextView listExpenseId;
        TextView listExpenseDate;
        TextView listExpenseDescription;
        TextView listExpenseAmount;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);

            listExpenseType = itemView.findViewById(R.id.listExpenseType);
            listExpenseDate = itemView.findViewById(R.id.listExpenseDate);
            listExpenseDescription = itemView.findViewById(R.id.listExpenseDescription);
            listExpenseAmount = itemView.findViewById(R.id.listExpenseAmount);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }

    public void removeItem(int position) {
        expenseList.remove(position);
        expenseList.clear();
        notifyDataSetChanged();
    }

    public String getDescription(int position) {
        ExpenseModel expense = expenseList.get(position);
        return expense.getExpenseDescription();
    }
}
