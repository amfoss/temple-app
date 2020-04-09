package org.amfoss.templeapp.expenses.adapter;

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
}
