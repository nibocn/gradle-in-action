package me.nibo.todo;

import me.nibo.todo.util.CommandLineInput;
import me.nibo.todo.util.CommandLineInputHandler;
import org.apache.commons.lang3.CharUtils;

public class ToDoApp {

    private static final char DEFAULT_INPUT = '\u0000';

    public static void main(String[] args) {
        CommandLineInputHandler commandLineInputHandler = new CommandLineInputHandler();
        char command = DEFAULT_INPUT;

        while (CommandLineInput.EXIT.getShortCmd() != command) {
            commandLineInputHandler.printOptions();
            String input = commandLineInputHandler.readInput();
            System.out.println("----------> " + CharUtils.toChar(input, DEFAULT_INPUT));
            command = CharUtils.toChar(input, DEFAULT_INPUT);
            CommandLineInput commandLineInput = CommandLineInput
                    .getCommandLineInputForInput(command);
            commandLineInputHandler.processInput(commandLineInput);
        }
    }
}
