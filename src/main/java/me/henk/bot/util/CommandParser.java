package me.henk.bot.util;

public abstract class CommandParser {

    public static String[] parse(String string) {
        return string
                .toLowerCase()
                .replaceAll("!", "")
                .split(" ", 0);
    }

}
