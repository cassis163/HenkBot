package me.henk.bot.util;

import net.dv8tion.jda.api.entities.Message;

public abstract class ModerationUtil {

    public static void deleteMessage(Message message, String reason) {
        message.delete().reason(reason).queue();
    }

    public static void deleteMessage(Message message) {
        message.delete().queue();
    }

}
