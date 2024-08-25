package com.example.splitwise.controllers;

import com.example.splitwise.dtos.SettleUpGroupRequestDTO;
import com.example.splitwise.dtos.SettleUpGroupResponseDTO;
import com.example.splitwise.dtos.SettleUpUserRequestDTO;
import com.example.splitwise.dtos.SettleUpUserResponseDTO;
import com.example.splitwise.models.Expense;
import com.example.splitwise.services.SettleUpService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private SettleUpService settleUpService;

    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }
    public SettleUpUserResponseDTO settleUpUser(SettleUpUserRequestDTO settleUpUserRequestDTO){
        SettleUpUserResponseDTO settleUpUserResponseDTO = new SettleUpUserResponseDTO();

        List<Expense> expenseList = settleUpService.settleUpUser(settleUpUserRequestDTO.getUserId());
        //once you get the request from requestDTO
        //set the response via expenseList
        settleUpUserResponseDTO.setTransactionList(expenseList);
        return settleUpUserResponseDTO;
    }


    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO settleUpGroupRequestDTO){
        SettleUpGroupResponseDTO settleUpGroupResponseDTO = new SettleUpGroupResponseDTO();

        List<Expense> expenseList2 = settleUpService.settleUpGroup(settleUpGroupRequestDTO.getGroupId());
        //once you get the request from requestDTO
        //set the response via expenseList
        settleUpGroupResponseDTO.setTransactionList(expenseList2);
        return settleUpGroupResponseDTO;
    }
}
