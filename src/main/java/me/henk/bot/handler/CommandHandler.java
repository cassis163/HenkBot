package me.henk.bot.handler;

import me.henk.bot.command.Command;
import me.henk.bot.command.NullCommand;
import org.reflections.Reflections;

import me.henk.bot.command.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public abstract class CommandHandler {

    // Contains every alias as a key of its command
    // commandHashMaps[aliasIndex][alias] = command

    // TODO: Replace ArrayList with single HashMap
    private static HashMap<String, Command> commandHashMap;
    private static ArrayList<Command> commands;

    private static NullCommand nullCommand;

    public static void initialize() throws Exception {
        nullCommand = new NullCommand();
        commands = new ArrayList<>();
        commandHashMap = new HashMap<>();

        loadCommands();
    }


    public static Command getCommand(String name) {
        if (commandHashMap.containsKey(name))
            return commandHashMap.get(name);

        // No command found so return the NullCommand class instance
        return nullCommand;
    }

    public static ArrayList<Command> getCommands() {
        return commands;
    }

    public static boolean commandExists(String name) {
        if (commandHashMap.containsKey(name))
            return true;
        // No command found
        return false;
    }


    private static void loadCommands() throws Exception {
        Reflections reflections = new Reflections("me.henk.bot.command");
        // Get all sub types of the Command class
        Set<Class<? extends Command>> commandClasses = reflections.getSubTypesOf(Command.class);
        // Loop through every class that extends of Command
        for (Class<? extends Command> commandClass : commandClasses) {
            if (commandClass != NullCommand.class) {
                // Create a new instance of the Command
                Command command = commandClass.getConstructor().newInstance();
                String[] commandAliases = command.getAliases();

                // Add the command to the commands ArrayList
                commands.add(command);

                // Loop through every alias
                for (String commandAlias : commandAliases) {
                    // Store the Command as a value of the alias
                    if (!commandHashMap.containsKey(commandAlias)) {
                        commandHashMap.put(commandAlias, command);
                    }
                    else {
                        System.out.println("The alias: '" + commandAlias + "' is a duplicate");
                    }
                }
            }
        }
    }

}
