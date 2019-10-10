package me.henk.bot.command;

import me.henk.bot.handler.CommandHandler;
import net.dv8tion.jda.api.entities.MessageChannel;

public class NullCommand implements Command {

    @Override
    public void run(String[] parsedCommand, MessageChannel messageChannel) {
        messageChannel.sendMessage(
                "'" + parsedCommand[0] + "' is not a command"
        ).queue();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String[] getUsage() {
        return new String[0];
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCommandCategory() {
        return null;
    }

}
