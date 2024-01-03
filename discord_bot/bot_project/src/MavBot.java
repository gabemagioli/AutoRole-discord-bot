
import javax.security.auth.login.LoginException;

import commands.MemberRoles;
import commands.manager.CommandManager;
import listeners.Listener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class MavBot {

    public static void main(String[] args) throws LoginException{

        Token botToken = new Token();

        JDA jda = JDABuilder.create(botToken.getToken(), GatewayIntent.GUILD_MEMBERS).build();

        CommandManager commandManager = new CommandManager();
        commandManager.add(new MemberRoles());//adding to the JDA the memberRoles class that containd the logic to promote someone(using /role request)

        jda.addEventListener(new Listener());
        jda.addEventListener(commandManager);//adding the event listener to promote user to a new role
    }
}
