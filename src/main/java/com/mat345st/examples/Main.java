package com.mat345st.examples;

import com.mat345st.commands.CommandHandler;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

/**
 * Created by mat345st on 15.04.2020 / 21:30
 * JDACommands / com.mat345st.examples
 *
 * @author mat345st
 */

public class Main {


    static JDABuilder builder = new JDABuilder();

    public static CommandHandler commandHandler = new CommandHandler(".");

    public static void main(String[] args) {

        builder.setToken("");


        //Don't forget to add the listener
        builder.addEventListeners(commandHandler.getListener());


        JDA jda;
        try {
            System.out.println("Logging in:");
            jda = builder.build();
            jda.awaitReady();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        commandHandler.addCommands(new AddRoleCommand(), new MsgCommand(), new SayCommand());
        commandHandler.setFootherText("https://github.com/mat345st");
        commandHandler.setFootherUrl(null);

    }

}
