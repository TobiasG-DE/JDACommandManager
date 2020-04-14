package com.mat345st.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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
        System.out.println(args.length);
        String invoke = args[0].toLowerCase();
        args = Arrays.copyOfRange(args, 1, args.length);

        /*for (String arg : args) {
            System.out.println(arg);

        }*/
        //System.out.println("1 " + args.length);
        System.out.println(invoke + " " + commands.size());
        //System.out.println("1");
        if (commands.containsKey(invoke)) {
            Command c = commands.get(invoke);
            //System.out.println("2");

            if (c.getChannelTypes().size()==0 || c.getChannelTypes().contains(event.getChannelType())) {

          //      System.out.println("3");


                if (!(c instanceof AdvancedCommand)){
            //        System.out.println("4");
                    if (!c.cancel(args, event)){
              //          System.out.println("5");
                        try {
                            c.action(args, event);
                //            System.out.println("6");
                        } catch (Exception e) {
                            System.err.println("An error occurred while executing the command " + c.getInvoke() + ": " + e.getMessage());
                            e.printStackTrace();
                        }
                    }else {
                        c.error(args, event);
                  //      System.out.println("7");
                    }
                }else {
                    AdvancedCommand ac = (AdvancedCommand) c;

                    //System.out.println("8");
                    if (!ac.cancel(args, event)){
                      //  System.out.println("9");
                        Object[] a = args;
                        boolean sucess = true;
                        try {

                            getObjects(args, c);
                        //    System.out.println("10");
                        } catch (Exception e) {
                            sucess = false;
                            e.printStackTrace();
                          //  System.out.println("11");
                        }

                        try {
                            ac.action(a, event, sucess);
                            //System.out.println("12");
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



    public Object[] getObjects(String[] args, Command command) throws Exception {


        Object[] result = new Object[command.getArguments().size()];

        for (int i = 0; i < command.getArguments().size(); i++) {

            CommandArgument argument = command.getArguments().get(i);

            if (argument.isRequired()){
                if (args.length < i +1 ) throw new Exception();
                switch (argument.type){
                    case WORD:
                    case STRING:
                        result[i] = args[i];
                        break;
                    case BOOLEAN:
                        if (args[i].equals("1") || args[i].equals("true") || args[i].equals("yes"))
                            result[i] = true;
                        else if (args[i].equals("0") || args[i].equals("false") || args[i].equals("no"))
                            result[i] = false;
                        else {
                            throw new Exception();
                        }
                        break;
                    case INTEGER:
                        System.out.println("int " + args[i]);
                        int arg = Integer.parseInt(args[i]);
                        result[i] = arg;
                        break;
                    case USER_MENTION:
                    case CHANNEL_MENTION:
                    case ROLE_MENTION:
                        String id = args[i].replace("<@","").replace("<#", "").replace("<@&", "").replace(">", "");
                        args[i] = id;
                        break;

                }
            }

        }
        return result;
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
