package me.henk.bot.command;

import me.henk.bot.util.ModerationUtil;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClearCommand implements Command {

    @Override
    public void run(String[] parsedCommand, MessageChannel messageChannel) {
        if (parsedCommand.length > 1) {
            int amountOfMessages = Integer.parseInt(parsedCommand[1]) + 1;
            List<Message> messageHistory = messageChannel
                    .getHistory()
                    .retrievePast(amountOfMessages)
                    .complete();

            for (Message message : messageHistory)
                message.delete().queue();
        }
    }

    @Override
    public String getDescription() {
        return "Clears the chat channel";
    }

    @Override
    public String[] getUsage() {
        return new String[] {
                "<amountOfMessages>"
        };
    }

    @Override
    public String[] getAliases() {
        return new String[] {
                "clear"
        };
    }

    @Override
    public CommandCategory getCommandCategory() {
        return null;
    }

}
