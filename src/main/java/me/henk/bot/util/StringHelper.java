package me.henk.bot.util;

public abstract class StringHelper {

    public static final String TAB = "      ";

    // Converts a text block to a single line
    public static String linesToString(String[] lines) {
        String string = "";
        for (String line : lines) {
            string += line + "\n";
        }

        return string;
    }

    public static String getTabs(int amount) {
        String tabs = "";
        for (int i = 0; i < amount; i++)
            tabs += TAB;

        return tabs;
    }

}
