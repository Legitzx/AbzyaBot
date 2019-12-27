package me.legitzx.bot.modTools;

import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Date;

public class PromoteDemoteCommands extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Role recruit = event.getGuild().getRolesByName("Member", true).get(0);
        Role member = event.getGuild().getRolesByName("Mod", true).get(0);
        Role moderator = event.getGuild().getRolesByName("Mod+", true).get(0);
        Role coleader = event.getGuild().getRolesByName("Co Leader", true).get(0);
        Role leader = event.getGuild().getRolesByName("Leader", true).get(0);
        String[] args = event.getMessage().getContentRaw().split(" ");
        MessageChannel channel = event.getChannel();
        Member member1 = event.getMember();

        if(event.getMessage().getAuthor().isBot()) return;



        if(args[0].equalsIgnoreCase(Info.PREFIX + "promote")) {
            Member target = event.getMessage().getMentionedMembers().get(0);
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if(target.getRoles().contains(recruit)){
                    event.getGuild().getController().removeSingleRoleFromMember(target, recruit).queue();
                    event.getGuild().getController().addSingleRoleToMember(target, member).queue();
                    promoteBroadcast(target, event.getMember(), event.getGuild().getTextChannelById("496906430679351296"));
                } else if(target.getRoles().contains(member)) {
                    event.getGuild().getController().removeSingleRoleFromMember(target, member).queue();
                    event.getGuild().getController().addSingleRoleToMember(target, moderator).queue();
                    promoteBroadcast(target, event.getMember(), event.getGuild().getTextChannelById("496906430679351296"));
                } else if(target.getRoles().contains(moderator)) {
                    event.getGuild().getController().removeSingleRoleFromMember(target, moderator).queue();
                    event.getGuild().getController().addSingleRoleToMember(target, coleader).queue();
                    promoteBroadcast(target, event.getMember(), event.getGuild().getTextChannelById("496906430679351296"));
                } else{
                    event.getChannel().sendMessage("This user seems to not have a role or already a coleader+").queue();
                }
            } else {
                event.getChannel().sendMessage("dont got any perms hoe").queue();
            }
        }

        if(args[0].equalsIgnoreCase(Info.PREFIX + "demote")) {
            Member target = event.getMessage().getMentionedMembers().get(0);
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if(member1.getRoles().contains(recruit)){
                    event.getChannel().sendMessage("This bitch is already a member just fucking kick him, he is useless...").queue();

                } else if(target.getRoles().contains(member)) {
                    event.getGuild().getController().removeSingleRoleFromMember(target, member).queue();
                    event.getGuild().getController().addSingleRoleToMember(target, recruit).queue();
                    demoteBroadcast(target, event.getMember(), event.getGuild().getTextChannelById("496906430679351296"));

                } else if(target.getRoles().contains(moderator)) {
                    event.getGuild().getController().removeSingleRoleFromMember(target, moderator).queue();
                    event.getGuild().getController().addSingleRoleToMember(target, member).queue();
                    demoteBroadcast(target, event.getMember(), event.getGuild().getTextChannelById("496906430679351296"));
                } else if(target.getRoles().contains(coleader)) {
                    if(event.getMember().getRoles().contains(leader)) {
                        event.getGuild().getController().removeSingleRoleFromMember(target, coleader).queue();
                        event.getGuild().getController().addSingleRoleToMember(target, moderator).queue();
                        demoteBroadcast(target, event.getMember(), event.getGuild().getTextChannelById("496906430679351296"));
                    }

                } else{
                    event.getChannel().sendMessage("This user is not able to be demoted because he is recruit or coleader+! Fuck your stupid").queue();
                }

            } else {
                event.getChannel().sendMessage("dont got any perms hoe").queue();
            }
        }


    }

    public void promoteBroadcast(Member member, Member promoter, TextChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setTitle(":confetti_ball: Congrats on the Promotion! :confetti_ball: ");
        builder.addField("User Promoted: ", member.getAsMention(), false);
        builder.addField("Admin: ", promoter.getAsMention(), false);
        builder.setColor(Color.GREEN);
        builder.setFooter("Promoted on | " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
    }
    public void demoteBroadcast(Member member, Member promoter, TextChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setTitle(":no_entry_sign:  Congrats on the Demotion! :no_entry_sign:  ");
        builder.addField("User Demoted: ", member.getAsMention(), false);
        builder.addField("Admin: ", promoter.getAsMention(), false);
        builder.setColor(Color.RED);
        builder.setFooter("Demoted on | " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
    }
}
