package com.mat345st.core;

import com.mat345st.commands.AdvancedCommand;
import com.mat345st.commands.Command;
import com.mat345st.commands.CommandArgument;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mat345st on 13.04.2020 / 14:15
 * JDACommands / com.mat345st.commands
 *
 * @author mat345st
 */

public class cmdTest extends AdvancedCommand {


    public cmdTest() {
        super("Test");
    }

    @Override
    public boolean cancel(String[] args, MessageReceivedEvent e) {
        return false;
    }

    @Override
    public void action(Object[] args, MessageReceivedEvent e, boolean successfully_casted) throws Exception {
        System.out.println(successfully_casted);
        System.out.println("Bool: " + (args[1] instanceof Boolean));
        for (Object arg : args) {
            System.out.println(arg);

        }
    }

    @Override
    public void error(String[] args, MessageReceivedEvent e) {
        System.out.println("Error");
    }

    @Override
    public void setArguments() {
        addArgument("int", true, CommandArgument.ArgumentType.INTEGER);
        addArgument("bool", true, CommandArgument.ArgumentType.BOOLEAN);
        addArgument("user", true, CommandArgument.ArgumentType.USER_MENTION);
        addArgument("channel", true, CommandArgument.ArgumentType.CHANNEL_MENTION);
        addArgument("role", true, CommandArgument.ArgumentType.ROLE_MENTION);
        addArgument("string", true, CommandArgument.ArgumentType.STRING);
        addArgument("word", true, CommandArgument.ArgumentType.WORD);

    }

    @Override
    public void setChannelTypes() {
        addChannelType(ChannelType.TEXT);
    }


}
