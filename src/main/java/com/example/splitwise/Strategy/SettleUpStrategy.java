package com.example.splitwise.Strategy;

import com.example.splitwise.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    //Return List<Expense> and also accept the List<Expense>
    List<Expense> settleUp(List<Expense> expenseList);

    /*
    Given a list of expenses, the function should return the transactions to be done in order to
    settle up all expenses.
     */
}
