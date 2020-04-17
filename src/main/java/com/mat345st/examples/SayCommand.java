package com.mat345st.examples;

import com.mat345st.commands.Command;
import com.mat345st.commands.CommandArgument;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Created by mat345st on 16.04.2020 / 14:15
 * JDACommands / com.mat345st.examples
 *
 * @author mat345st
 */

public class SayCommand extends Command {

    public SayCommand() {
        super("say");
    }

    @Override
    public boolean cancel(String[] args, MessageReceivedEvent event) {
        if (args.length == 0) return true;
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws Exception {
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
        }

        getChannel().sendMessage(builder.toString()).queue();

    }

    @Override
    public void error(String[] args, MessageReceivedEvent event) {
        sendError();
    }

    @Override
    public void setArguments() {
        addArgument("text", true, CommandArgument.ArgumentType.STRING);
    }

    @Override
    public void setChannelTypes() {
        //All channel types
    }
}
