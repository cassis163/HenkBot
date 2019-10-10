package me.henk.bot.command;

import me.henk.bot.util.MessageHelper;
import net.dv8tion.jda.api.entities.MessageChannel;

public class PingCommand implements Command {

    @Override
    public void run(String[] parsedCommand, MessageChannel messageChannel) {
        long timeStamp0 = System.currentTimeMillis();

        messageChannel.sendMessage(MessageHelper.styleMessage("Checking ping...")).queue(message -> {
            long timeStamp1 = System.currentTimeMillis();
            long deltaTime = timeStamp1 - timeStamp0;

            message.editMessage(MessageHelper.styleMessage("Ping: " + deltaTime + "ms")).queue();
        });
    }

    @Override
    public String getDescription() {
        return "Returns the amount of milliseconds the received ping and the sent pong message";
    }

    @Override
    public String[] getUsage() {
        return new String[] {
                ""
        };
    }

    @Override
    public String[] getAliases() {
        return new String[] {
                "ping"
        };
    }

    @Override
    public CommandCategory getCommandCategory() {
        return null;
    }

}
