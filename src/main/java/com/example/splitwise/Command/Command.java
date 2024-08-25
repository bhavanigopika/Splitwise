package com.example.splitwise.Command;

public interface Command {
    //this command interface have 2 methods.
    //To check particular command that is given in the input which is matching.
    public boolean matches(String input);
    public void execute (String input);
}
