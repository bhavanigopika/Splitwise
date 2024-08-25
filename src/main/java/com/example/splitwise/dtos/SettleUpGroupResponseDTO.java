package com.example.splitwise.dtos;

import com.example.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponseDTO {
    private List<Expense> transactionList;
}
