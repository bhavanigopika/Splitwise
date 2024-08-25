package com.example.splitwise.Command;

import com.example.splitwise.controllers.SettleUpController;
import com.example.splitwise.dtos.SettleUpUserRequestDTO;
import com.example.splitwise.dtos.SettleUpUserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SettleUpUserCommand implements Command {

    SettleUpController settleUpController;

    public SettleUpUserCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        /*user1 SettleUp --> u1 is asking to see the list of transactions they should perform to settle up
        userId SettleUp
        1234 SettleUp --> here no. of words = 2*/

        //Extract the words and
        //here required type is list but we provide the string. So, create the list of (List.of) or Arrays.asList
        List<String> words = List.of(input.split(" "));
        //we need total word size = 2 and also 2nd word will be SettleUp -> saying true or false
        return words.size() == 2 && words.get(1).equals(CommandKeywords.SettleUpCommand);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        //extract the words... words is string, so convert into long
        //userId SettleUp -> This is the order to settleUp user command
        Long userID = Long.valueOf(words.get(0));
        //once we have the userId, call the requestDTO for settleUp the User
        SettleUpUserRequestDTO settleUpUserRequestDTO = new SettleUpUserRequestDTO();
        settleUpUserRequestDTO.setUserId(userID);
        //get the responseDTO from settleUpController and pass the requestDTO parameter
        SettleUpUserResponseDTO settleUpUserResponseDTO = settleUpController.settleUpUser(settleUpUserRequestDTO);
    }
}
