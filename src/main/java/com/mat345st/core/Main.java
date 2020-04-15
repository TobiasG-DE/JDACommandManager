package com.mat345st.core;

import com.mat345st.commands.CommandHandler;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

/**
 * Created by mat345st on 13.04.2020 / 13:37
 * JDACommands / com.mat345st.core
 *
 * @author mat345st
 */

public class Main {

    static JDABuilder builder = new JDABuilder(AccountType.BOT);

    public static void main(String[] args) {

        System.out.println("Starting...");


        builder.setToken("NDg3MjkxNDE3MDAyMjQ2MTY1.XpXM1w.RAzGFTbtX-CrJ-EIziRZjGtdYZE");
        builder.setAutoReconnect(true);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("auf BattleMC"));



        CommandHandler commandHandler = new CommandHandler(".");
        builder.addEventListeners(commandHandler.getListener());


        JDA jda;


        try {
            System.out.println("Logging in:");
            jda = builder.build();
            jda.awaitReady();
        } catch (LoginException | club.minnced.discord.webhook.exception.HttpException e) {
            System.err.println("Discord is not reachable: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        commandHandler.addCommands(new cmdTest());

        /*CommandListener listener = new CommandListener(".");
        Scanner scan = new Scanner(System.in);

        while (true){
            for (String s : listener.parse(scan.nextLine())) {
                System.out.println(s);
            }


        }*/



    }
}
