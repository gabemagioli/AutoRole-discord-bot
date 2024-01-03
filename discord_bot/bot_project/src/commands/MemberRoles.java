package commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

//class responsible to promote yourself(if you have permission) when manually typed "/role"
public class MemberRoles implements ICommand {

    @Override
    public String getName() {
        return "role";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        Member member = event.getMember();
        Guild guild = event.getGuild();
        Role role;

        if(member.getRoles().isEmpty()){//if member has no role -> add @new member role to it
            role = guild.getRoleById(1191859482112839781L);
            guild.addRoleToMember(member, role).queue();
            event.reply(" Nice!!! " + member.getUser().getName() + " just turned into " + role.getAsMention()).queue();
        }
        else if(member.getRoles().size() >= 1){

            if(member.getRoles().size() == 1){//in this case the member will have role @regular member so he/ she can go to the next one

                role = guild.getRoleById(1191860425592152064L);//setting role to Regular Member
                guild.addRoleToMember(member, role).queue();
                event.reply(" Nice!!! " + member.getUser().getName() + " just turned into " + role.getAsMention()).queue();
            }
            else if(member.getRoles().size() == 2){

                role = guild.getRoleById(1191860797912129686L);
                guild.addRoleToMember(member, role).queue();
                event.reply(" Nice!!! " + member.getUser().getName() + " just turned into " + role.getAsMention() + " soon you will be turning into a War Veteran").queue();
            }
            else {//if he has all the roles he will get this last one

                role = guild.getRoleById(1191860881055830016L);
                guild.addRoleToMember(member, role).queue();
                event.reply(" Nice!!! " + member.getUser().getName() + " just turned into " + role.getAsMention() + ", that is the top level you could reach, congrats :)").queue();
            }
        }
        
        System.out.println(member.getRoles().size() + "Member promoted");
    }
    
}
