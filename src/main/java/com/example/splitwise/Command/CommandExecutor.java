package com.example.splitwise.Command;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandExecutor {
    RegisterUserCommand registerUserCommand;
    SettleUpUserCommand settleUpUserCommand;
    CreateGroupCommand createGroupCommand;

    public CommandExecutor(RegisterUserCommand registerUserCommand, SettleUpUserCommand settleUpUserCommand, CreateGroupCommand createGroupCommand) {
        this.registerUserCommand = registerUserCommand;
        this.createGroupCommand = createGroupCommand;
        this.settleUpUserCommand = settleUpUserCommand;
    }

    public List<Command> commandList = List.of(registerUserCommand, settleUpUserCommand, createGroupCommand);

    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void removeCommand(Command command) {
        commandList.remove(command);
    }

    public void execute(String input) {

        for (Command command : commandList) {
            if (command.matches(input)) {
                command.execute(input);
                break;
            }
        }

    }
}
