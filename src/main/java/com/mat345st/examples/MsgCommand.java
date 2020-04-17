package com.mat345st.examples;

import com.mat345st.commands.AdvancedCommand;
import com.mat345st.commands.CommandArgument;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;

/**
 * Created by mat345st on 16.04.2020 / 14:32
 * JDACommands / com.mat345st.examples
 *
 * @author mat345st
 */

public class MsgCommand extends AdvancedCommand {

    public MsgCommand() {
        super("msg");
    }

    @Override
    public boolean cancel(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(Object[] args, MessageReceivedEvent event, boolean successfully_casted) throws Exception {
        if (!successfully_casted){
            sendError();
            return;
        }

        String userid = (String) args[0];
        String msg = (String) args[1];

        User user = event.getGuild().getMemberById(userid).getUser();

        try {
            PrivateChannel channel = user.openPrivateChannel().complete();
            channel.sendMessage(msg).queue();
            sendFeedback("Success", "Send " + msg + " to " + user.getAsMention());
        }catch (Exception e){
            sendError(e.getMessage());
        }


    }

    @Override
    public void error(String[] args, MessageReceivedEvent event) {
        sendError();
    }

    @Override
    public void setArguments() {
        addArgument("user", true, CommandArgument.ArgumentType.USER_MENTION);
        addArgument("text", true, CommandArgument.ArgumentType.STRING);

    }

    @Override
    public void setChannelTypes() {
        addChannelType(ChannelType.TEXT);
    }
}
