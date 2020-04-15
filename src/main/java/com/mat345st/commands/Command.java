package com.mat345st.commands;

import jdk.nashorn.internal.objects.Global;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by mat345st on 13.04.2020 / 14:14
 * JDACommands / com.mat345st
 *
 * @author mat345st
 */

public abstract class Command {


    private static final Color ERROR_COLOR = Color.RED;
    private static final String FOOTHER_TEXT = "Command usage";


    private List<CommandArgument> arguments = new ArrayList<>();
    private List<ChannelType> channelTypes = new ArrayList<>();


    private String invoke;
    private CommandHandler handler;

    MessageReceivedEvent last_event;

    public Command(String invoke){
        this.invoke = invoke.toLowerCase();
        setArguments();
        setChannelTypes();
    }



    /**
     *
     * @param args the given arguments
     * @param e    the message received event
     * @return     if the command have to be executed
     */
    public abstract boolean cancel(String[] args, MessageReceivedEvent e);


    /**
     *
     * @param args      the given arguments
     * @param e         the message received event
     * @throws Exception
     */

    public abstract void action(String[] args, MessageReceivedEvent e) throws Exception;


    /*
     *
     * @param args      the given arguments
     * @param e         the message received event
     * @param casted    if the arguments could be casted
     * @throws Exception
     */
    //public abstract void action(String[] args, MessageReceivedEvent e, boolean casted) throws Exception;


    /**
     * when the command was cancelled
     *
     * @param args the arguments
     * @param e    the message received event
     */

    public abstract void error(String[] args, MessageReceivedEvent e);




    public abstract void setArguments();
    private String getUsageString(){
        StringBuilder builder =  new StringBuilder().append(handler.getPrefix() + invoke);
        arguments.stream().forEach(a -> builder.append(" " + a.getUsage()));
        return builder.toString();
    }

    public abstract void setChannelTypes();
    protected void addChannelType(ChannelType type){
        channelTypes.add(type);
    }



    protected EmbedBuilder help(String message) {
        return (new EmbedBuilder()
                .setColor(ERROR_COLOR)
                .setTitle("Error")
                .setDescription(message)
                .setFooter(FOOTHER_TEXT ,null));
    }

    protected EmbedBuilder help(){
        return help(getUsageString());
    }


    /**
     * required arguments have to be added finally
     *
     * @param name      name of the argument
     * @param required  if the argument have to be used
     * @param type      the argument type
     */

    protected void addArgument(String name, boolean required, CommandArgument.ArgumentType type){
        arguments.add(new CommandArgument(name, required, type));
    }

    void setHandler(CommandHandler handler) {
        this.handler = handler;
    }


    public String getInvoke() {
        return invoke;
    }

    public List<CommandArgument> getArguments() {
        return arguments;
    }

    public List<ChannelType> getChannelTypes() {
        return channelTypes;
    }
}
