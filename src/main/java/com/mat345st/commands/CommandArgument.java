package com.mat345st.commands;

/**
 * Created by mat345st on 13.04.2020 / 14:41
 * JDACommands / com.mat345st.commands
 *
 * @author mat345st
 */

public class CommandArgument {

    String name;
    boolean required;
    ArgumentType type;


    /**
     *
     * @param name          the argument name
     * @param required      if the argument is required or is optional
     * @param type          type of CommandArgument.ArgumentType
     */


    public CommandArgument(String name, boolean required, ArgumentType type){
        this.name = name;
        this.required = required;
        this.type = type;
    }


    public String getUsage(){
        switch (type){
            case WORD:
                if (required)
                    return "[" + name + "]";
                return "(" + name + ")";

            case STRING:
                if (required)
                    return "[\"string\": " + name + "]";
                return "(\"string\": " + name + ")";


            case USER_MENTION:
                if (required)
                    return "[@user: " + name + "]";
                return "(@user: " + name + ")";

            case CHANNEL_MENTION:
                if (required)
                    return "[@channel: " + name + "]";
                return "(@channel: " + name + ")";

            case ROLE_MENTION:
                if (required)
                    return "[@role: " + name + "]";
                return "(@role: " + name + ")";

            case INTEGER:
                if (required)
                    return "[int: " + name + "]";
                return "(int: " + name + ")";

            case BOOLEAN:
                if (required)
                    return "[boolean: " + name + "]";
                return "(boolean: " + name + ")";

        }
        return "[" + name + "]";
    }

    public boolean isRequired() {
        return required;
    }

    public enum ArgumentType{
        WORD,
        STRING,
        USER_MENTION,
        CHANNEL_MENTION,
        ROLE_MENTION,
        INTEGER,
        BOOLEAN
    }

}
