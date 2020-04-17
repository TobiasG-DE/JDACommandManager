# JDACommandManager
manage the added commands, checks the arguments und cast it to the argument objects


## Create the command handler
```java
CommandHandler commandHandler = new CommandHandler(".");
```


## Add the listener
```java
jdabuilder.addEventListeners(commandHandler.getListener());
```

## Create a command
```java
public class SayCommand extends Command {
}
```

## Create a advanced command
```java
public class MsgCommand extends AdvancedCommand {
}
```

## Add commands
```java
commandHandler.addCommands(new MsgCommand(), new SayCommand());
```

## Command class methods to implement:

- `public boolean cancel(String[] args, MessageReceivedEvent event){}`

   returns if the command will be excecuted. If true error will triggered
   
- Command: `public void action(String[] args, MessageReceivedEvent event) throws Exception{}`
- AdvancedCommand: `public void action(Object[] args, MessageReceivedEvent event) throws Exception{}`

   execute the command
   
- `public void error(String[] args, MessageReceivedEvent event){}`:

   called if an exception was thrown, cancel returns true
   
- `public void setArguments(){}`
- `public void setChannelTypes(){}`

   called on creating command. Can be used to set the command arguments and set the channel types which can trigger the command
   


### Set the command arguments


* name = the function of the argument
* required = if you have to use this argument
* type = type of CommandArgument.ArgumentType
```java
addArgument("user", true, CommandArgument.ArgumentType.USER_MENTION);
addArgument("text", true, CommandArgument.ArgumentType.STRING);
```

### Set the channel types

```java
addChannelType(ChannelType.TEXT);
```
