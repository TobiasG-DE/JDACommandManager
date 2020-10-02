package com.mat345st.commands;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Created by mat345st on 14.04.2020 / 14:22
 * JDACommands / com.mat345st.commands
 *
 * @author mat345st
 */
public abstract class AdvancedCommand extends Command {

    public AdvancedCommand(String invoke) {
        super(invoke);
    }

    @Override
    public abstract boolean cancel(String[] args, MessageReceivedEvent event);

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws Exception{}


    public abstract void action(Object[] args, MessageReceivedEvent e, boolean successfully_casted) throws Exception;

    @Override
    public abstract void error(String[] args, MessageReceivedEvent event);

    @Override
    public abstract void setArguments();

    @Override
    public abstract void setChannelTypes();

}
