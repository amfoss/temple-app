package org.amfoss.templeapp.expenses.adapter;

import java.util.Comparator;

/**
* @author robustTechie(s.priyadarshi629@gmail.com)
* @since 09/04/2020
*/
public class ExpenseModel {
    private String expenseDate, expenseAmount, expenseDescription, expenseType;

    public ExpenseModel() {}

    public ExpenseModel(
            String expenseDate, String expenseAmount, String expenseDescription, String expenseType) {
        this.expenseDate = expenseDate;
        this.expenseAmount = expenseAmount;
        this.expenseDescription = expenseDescription;
        this.expenseType = expenseType;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setExpenseAmount(String expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public static final Comparator<ExpenseModel> BY_TITLE_DATE =
            new Comparator<ExpenseModel>() {
                @Override
                public int compare(ExpenseModel o1, ExpenseModel o2) {
                    return o1.getExpenseDate().compareTo(o2.getExpenseDate());
                }
            };
}
