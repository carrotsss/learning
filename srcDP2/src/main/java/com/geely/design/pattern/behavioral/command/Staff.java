package com.geely.design.pattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carrots on 2019/1/26.
 */
public class Staff {
    private List<Command> commandList = new ArrayList<Command>();

    public void addCommand(Command command) {
        commandList.add(command);
    }

    public void executeCommands() {
        for (Command command : commandList) {
            command.execute();
        }
        commandList.clear();
    }
}