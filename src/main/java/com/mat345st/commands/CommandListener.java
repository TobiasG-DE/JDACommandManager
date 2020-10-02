package com.mat345st.commands;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by mat345st on 13.04.2020 / 15:08
 * JDACommands / com.mat345st.commands
 *
 * @author mat345st
 */
public class CommandListener extends ListenerAdapter {

    String prefix;
    CommandHandler handler;
    public CommandListener(String prefix, CommandHandler handler){
        this.prefix = prefix;
        this.handler = handler;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith(prefix)){
            handler.handle(parse(event.getMessage().getContentRaw().replaceFirst(prefix, "")), event);
        }
    }

    public String[] parse(String raw) {
        String[] split = raw.split("\"");
        if (split.length < 3){
            return raw.split(" ");
        }else {
            List<String> args = new ArrayList<String>();
            for (int i = 0; i < split.length; i++) {
                if (i % 2 == 0){
                    //1., 3., 5., ... arg
                    for (String s : split[i].trim().split(" ")) {
                        if (!args.equals(""))
                        args.add(s);
                    }
                }else {
                    //2., 4., 6.,.. arg ("")
                    args.add(split[i]);
                }
            }
            String[] a = new String[args.size()];
            args.toArray(a);
            return a;
        }
    }
}
