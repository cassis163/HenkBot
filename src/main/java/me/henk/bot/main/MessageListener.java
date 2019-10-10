package me.henk.bot.main;

import me.henk.bot.command.Command;
import me.henk.bot.handler.CommandHandler;
import me.henk.bot.util.CommandParser;
import me.henk.bot.util.PropertiesReader;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    private PropertiesReader configReader;
    private CommandRunner commandRunner;

    public MessageListener(PropertiesReader configReader) throws Exception {
        this.configReader = configReader;

        commandRunner = new CommandRunner();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent messageReceivedEvent) {
        String message = messageReceivedEvent.getMessage().getContentRaw();

        // Check if the message is a command by checking if it starts with the specified prefix
        // Also, check if the author is not a bot
        if (message.startsWith(configReader.get("prefix")) && !messageReceivedEvent.getAuthor().isBot()) {
            String[] parsedCommand = CommandParser.parse(message);
            Command command = CommandHandler.getCommand(parsedCommand[0]);

            commandRunner.run(
                    command,
                    parsedCommand,
                    messageReceivedEvent
            );
        }
    }

}
