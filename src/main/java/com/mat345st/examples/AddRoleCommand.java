package com.mat345st.examples;

import com.mat345st.commands.AdvancedCommand;
import com.mat345st.commands.CommandArgument;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.jws.soap.SOAPBinding;
import java.util.function.Consumer;

/**
 * Created by mat345st on 16.04.2020 / 13:40
 * JDACommands / com.mat345st.examples
 *
 * @author mat345st
 */

public class AddRoleCommand extends AdvancedCommand {

    public AddRoleCommand() {
        super("addrole");
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
        String userId = (String) args[0];
        String roleid = (String) args[1];

        Member member = event.getGuild().getMemberById(userId);
        Role role = event.getGuild().getRoleById(roleid);

        if (member==null || role == null){
            sendError("Invalid role or user");
            return;
        }


        try {
            event.getGuild().addRoleToMember(member, role).queue(new Consumer<Void>() {
                @Override
                public void accept(Void aVoid) {
                    sendFeedback("Success", "Added " + role.getAsMention() + " successfully to " + member.getAsMention());
                }
            });
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
        addArgument("role", true, CommandArgument.ArgumentType.ROLE_MENTION);
    }

    @Override
    public void setChannelTypes() {
        addChannelType(ChannelType.TEXT);
    }
}
