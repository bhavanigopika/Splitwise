package com.example.splitwise.Strategy;

import com.example.splitwise.models.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeapSettleUpStrategy implements SettleUpStrategy{

    @Override
    public List<Expense> settleUp(List<Expense> expenseList) {
        /*
        Iterate through all expenses and find out total extra or lesser amount paid by
        every user involved.

        Apply the min or max heap algorithm to settle up everyone
         */

        return List.of();
    }
}
