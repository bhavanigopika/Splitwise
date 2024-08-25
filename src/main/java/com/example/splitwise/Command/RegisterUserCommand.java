package com.example.splitwise.Command;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegisterUserCommand implements Command{
    @Override
    public boolean matches(String input) {
        //Register vinsmokesanji 003 namisswwaann --> u1 is registering with the username "vinsmokesanji", phone "003" and password as "namisswwaann"
        List<String> words = List.of(input.split(" "));

        //return words.size() == 4 && words.get(0).equals("Register");
        return words.size() == 4 && words.get(0).equals(CommandKeywords.RegisterCommand);
    }

    @Override
    public void execute(String input) {

    }
}
