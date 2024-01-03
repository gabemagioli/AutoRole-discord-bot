package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

//interface responsible to keep the methods with name of the command and action that the command will execute
public interface ICommand {

    public abstract String getName();//name of the command
    public abstract void execute(SlashCommandInteractionEvent event);//action that will ahppen when this command is called
}
