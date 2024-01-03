package listeners;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter{

    //variable that basically "counts" the time a member spent in the server
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static Role role;//role defined with global scope
    int size_roleList = 0;//counter of the promotions 
    
    @Override
    public void onReady(@NotNull ReadyEvent event){
        JDA jda = event.getJDA();
       
        for(int i = 0; i < jda.getGuilds().size(); i++){
            for(TextChannel tc : jda.getTextChannels()){
                tc.sendMessage("I'm ready to Fly ;)").queue();//send this message in every text channel of the discord server when the bot gets online
            }
        }
    }

    //method that takes care of promoting members when they get in the server
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {//when a new member join, if he hasn't any role, he will get the @Hangman role
        JDA jda = event.getJDA();
        Guild guild = event.getGuild();
        Member member = event.getMember();
        
        System.out.println("new member joined!");

        if(member.getRoles().isEmpty()){
            role = guild.getRoleById(1191860797912129686L);//setting role to Goose
            guild.addRoleToMember(member, role).queue();
            for(TextChannel tc : jda.getTextChannels()){
                tc.sendMessage(" Nice!!! " + member.getUser().getName() + " was promoted to " + role.getAsMention()).queue();//sends a message alerting about the member's promotion
            }
            System.out.println("promoted to Goose");
            size_roleList++;
        }
        if(size_roleList == 1) {
            role = guild.getRoleById(1191860881055830016L);//war veteran role last role someone can get in the server
            scheduler.schedule(() -> {
                guild.addRoleToMember(member, role).queue();
                for(TextChannel tc : jda.getTextChannels()){
                    tc.sendMessage(" Nice!!! " + member.getUser().getName() + " was promoted to " + role.getAsMention() + ", well done :)").queue();//sends a message alerting about the member's promotion
                }
                System.out.println("promoted to Maverick");
                size_roleList++;
            }, 10, TimeUnit.SECONDS);
        }

    System.out.println(size_roleList);
    }
}
