package me.henk.bot.main;

import me.henk.bot.command.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandRunner {

    public void run(Command command, String[] parsedCommand, MessageReceivedEvent messageReceivedEvent) {
        command.run(
                parsedCommand,
                messageReceivedEvent.getChannel()
        );
    }

}
