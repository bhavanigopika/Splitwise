package com.example.splitwise.dtos;

import com.example.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDTO {
    private List<Expense> transactionList;
}
