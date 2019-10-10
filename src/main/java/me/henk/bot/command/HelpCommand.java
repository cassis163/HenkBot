package me.henk.bot.command;

import me.henk.bot.handler.CommandHandler;
import me.henk.bot.util.MessageHelper;
import me.henk.bot.util.StringHelper;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.ArrayList;

public class HelpCommand implements Command {

    private String generateHelpMessage(Command command) {
        // Format:
        /*
            !<aliases>:
                Description:
                    <description>
                Usage:
                    <usage1>
                    <usage2>
                    ...
        */

        String[] aliases = command.getAliases();
        String[] usage = command.getUsage();

        StringBuilder aliasHelpLine = new StringBuilder(
                String.format("'%s'", aliases[0])
        );
        for (int i = 1; i < aliases.length; i++)
            aliasHelpLine.append(String.format(" or '%s'", aliases[i]));

        StringBuilder usageHelpLine = new StringBuilder();
        if (usage.length > 0) {
            usageHelpLine.append(String.format("%s- Usage:\n", StringHelper.TAB));
            for (int i = 0; i < usage.length; i++)
                usageHelpLine.append(
                        String.format("%s- !(%s) %s\n", StringHelper.getTabs(2), aliasHelpLine.toString(), usage[i])
                );
        }

        String[] lines = new String[] {
                String.format("!(%s):", aliasHelpLine.toString()),
                String.format("%s- Description:\n%s- %s", StringHelper.TAB, StringHelper.getTabs(2) ,command.getDescription()),
                usageHelpLine.toString()
        };

        return StringHelper.linesToString(lines);
    }


    @Override
    public void run(String[] parsedCommand, MessageChannel messageChannel) {
        if (parsedCommand.length > 1) {
            if (CommandHandler.commandExists(parsedCommand[1])) {
                // The command exists

                Command command = CommandHandler.getCommand(parsedCommand[1]);
                MessageHelper.sendMessage(generateHelpMessage(command), messageChannel);
            }
            else {
                // The command does not exist

                MessageHelper.sendMessage("'" + parsedCommand[0] + "' is not a command", messageChannel);
            }
        }
        else {
            // List every command
            StringBuilder helpMessage = new StringBuilder();
            ArrayList<Command> commands = CommandHandler.getCommands();

            for (int i = 0; i < commands.size(); i++) {
                helpMessage.append(generateHelpMessage(commands.get(i)));
            }

            MessageHelper.sendMessage(helpMessage.toString(), messageChannel);
        }
    }

    @Override
    public String getDescription() {
        return "Attempt to help";
    }

    @Override
    public String[] getUsage() {
        return new String[] {
                "",
                "<command>"
        };
    }

    @Override
    public String[] getAliases() {
        return new String [] {
                "",
                "help"
        };
    }

    @Override
    public CommandCategory getCommandCategory() {
        return CommandCategory.INFORMATION;
    }

}
