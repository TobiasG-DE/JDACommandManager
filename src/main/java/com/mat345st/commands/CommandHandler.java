package com.mat345st.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mat345st on 13.04.2020 / 15:08
 * JDACommands / com.mat345st.commands
 *
 * @author mat345st
 */

public class CommandHandler {

    private CommandListener listener;
    private String prefix;

    private HashMap<String, Command> commands = new HashMap<>();

    public CommandHandler(String prefix) {
        this.prefix = prefix;
        this.listener = new CommandListener(prefix, this);
    }

    public void handle(String[] args, MessageReceivedEvent event) {
        String invoke = args[0].toLowerCase();
        args = Arrays.copyOfRange(args, 1, args.length);

        if (commands.containsKey(invoke)) {
            Command c = commands.get(invoke);

            if (c.getChannelTypes().size()==0 || c.getChannelTypes().contains(event.getChannelType())) {


                c.last_event = event;
                if (!(c instanceof AdvancedCommand)){
                    if (!c.cancel(args, event)){

                        try {
                            c.action(args, event);
                        } catch (Exception e) {
                            System.err.println("An error occurred while executing the command " + c.getInvoke() + ": " + e.getMessage());
                            e.printStackTrace();
                        }
                    }else
                        c.error(args, event);
                }else {
                    AdvancedCommand ac = (AdvancedCommand) c;


                    if (!ac.cancel(args, event)){
                        Object[] a = args;
                        boolean success = true;


                        for (int i = 0; i < ac.getArguments().size();i++) {
                            CommandArgument argument = ac.getArguments().get(i);
                            try {

                                a[i] = getObject(args[i], argument);
                            } catch (Exception e) {
                                if (argument.isRequired())
                                success = false;
                                //e.printStackTrace();
                            }
                        }



                        try {
                            ac.action(a, event, success);
                        } catch (Exception e) {
                            System.err.println("An error occurred while executing the command " + c.getInvoke() + ": " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    else {
                        ac.error(args, event);
                    }
                }

            }
        }

    }





    public Object getObject(String arg, CommandArgument argument) throws Exception {

            switch (argument.type) {
                case WORD:
                case STRING:
                    return arg;

                case BOOLEAN:
                    if (arg.equals("1") || arg.equals("true") || arg.equals("yes"))
                        return true;
                    else if (arg.equals("0") || arg.equals("false") || arg.equals("no"))
                        return false;
                    else {
                        throw new Exception();
                    }

                case INTEGER:
                    int i = Integer.parseInt(arg);
                    return i;

                case USER_MENTION:
                case CHANNEL_MENTION:
                case ROLE_MENTION:
                    String id = arg.replace("<@&", "").replace("<@!", "").replace("<#", "").replace(">", "");
                    return id;

            }
            throw new Exception();

    }



    public void addCommands(Command... c){
        for (Command command : c) {
            if (!commands.containsValue(command)) {
                command.setHandler(this);
                commands.put(command.getInvoke(), command);

            }
        }
    }


    public CommandListener getListener() {
        return listener;
    }

    public String getPrefix() {
        return prefix;
    }
}
