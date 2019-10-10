package me.henk.bot.main;

import me.henk.bot.handler.CommandHandler;
import me.henk.bot.util.PropertiesReader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Bot extends ListenerAdapter {

    private static JDA jda;

    private static PropertiesReader configReader;
    private static PropertiesReader tokenReader;

    public static void main(String[] args) throws Exception {
        configReader = new PropertiesReader("/config.properties");
        tokenReader = new PropertiesReader("/token.properties");

        jda = new JDABuilder(tokenReader.get("token"))
                .build()
                .awaitReady();

        CommandHandler.initialize();

        jda.addEventListener(new MessageListener(configReader));
    }

}
