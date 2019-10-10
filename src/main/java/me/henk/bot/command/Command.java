package me.henk.bot.command;

import net.dv8tion.jda.api.entities.MessageChannel;

public interface Command {

    void run(
            String[] parsedCommand,
            MessageChannel messageChannel
    );

    String getDescription();

    // Returns the usage of the command
    // Return format: "... <keyword1> <...> <@target1> <...>"
    String[] getUsage();

    String[] getAliases();

    CommandCategory getCommandCategory();

}
