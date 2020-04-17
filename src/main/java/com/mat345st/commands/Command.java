package com.mat345st.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mat345st on 13.04.2020 / 14:14
 * JDACommands / com.mat345st
 *
 * @author mat345st
 */

public abstract class Command {

    private static final Color ERROR_COLOR = Color.RED;
    private static final String ERROR_FOOTHER_TEXT = "Command error";
    private static final String ERROR_TITLE = "Usage error";


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
     * @param event    the message received event
     * @return     if the command have to be executed
     */
    public abstract boolean cancel(String[] args, MessageReceivedEvent event);


    /**
     *
     * @param args      the given arguments
     * @param event         the message received event
     * @throws Exception
     */

    public abstract void action(String[] args, MessageReceivedEvent event) throws Exception;



    /**
     * when the command was cancelled or an exception was threw
     *
     * @param args the arguments
     * @param event    the message received event
     */

    public abstract void error(String[] args, MessageReceivedEvent event);




    public abstract void setArguments();
    private String getUsage(){
        StringBuilder builder =  new StringBuilder().append(handler.getPrefix() + invoke);
        arguments.stream().forEach(a -> builder.append(" " + a.getUsage()));
        return builder.toString();
    }

    public abstract void setChannelTypes();
    protected void addChannelType(ChannelType type){
        channelTypes.add(type);
    }


    protected void sendEmbed(Color color, String title, String description, String foother_text, String foother_url){
        getChannel().sendMessage(
                new EmbedBuilder()
                        .setColor(color)
                        .setTitle(title)
                        .setDescription(description)
                        .setFooter(foother_text, foother_url)
                        .build()
        ).queue();
    }

    protected void sendError(String message){
       sendEmbed(handler.getErrorColor(), "Command error", message, handler.getFootherText(), handler.getFootherUrl());
    }

    protected void sendError(){
        sendError(getUsage());
    }

    protected void sendFeedback(String title, String message){
        sendEmbed(handler.getEmbedColor(), title, message, handler.getFootherText(), handler.getFootherUrl());
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

    public TextChannel getChannel(){return last_event.getTextChannel();}
}

