package com.example.splitwise.Command;

import org.springframework.stereotype.Component;

@Component
public class CreateGroupCommand implements Command {
    @Override
    public boolean matches(String input) {
        //u1 AddGroup Roommates -> u1 is creating a group titled "Roommates"
        return false;
    }

    @Override
    public void execute(String input) {

    }
}
