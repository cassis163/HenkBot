package me.henk.bot.util;

import net.dv8tion.jda.api.entities.MessageChannel;

public abstract class MessageHelper {

    public static void sendMessage(String message, MessageChannel messageChannel) {
        messageChannel.sendMessage(
                styleMessage(message)
        ).queue();
    }

    public static String styleMessage(String message) {
        return String.format("**%s**", message);
    }

}
