package me.legitzx.bot.modTools;

import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class KickBanSystem extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String[] args = event.getMessage().getContentRaw().split(" ");


        //which roles can access the commands
        Role admin = event.getGuild().getRolesByName("Co Leader", true).get(0);
        Role leader = event.getGuild().getRolesByName("Leader", true).get(0);



        if(args[0].equalsIgnoreCase(Info.PREFIX + "ban")) {
            if (event.getMember().getRoles().contains(admin) || event.getMember().getRoles().contains(leader)) {

                if (args.length <= 1) {
                    sendErrorMessageBan(event.getChannel(), event.getMember());
                } else {
                    Member target = event.getMessage().getMentionedMembers().get(0);
                    //Member target = event.getMessage().getMentionedMembers().get(0);
                    event.getGuild().getController().ban(target, 2).queue();
                    if (args.length >= 3) {
                        String reason = "";
                        for (int i = 2; i < args.length; i++) {
                            reason += args[i] + " ";
                        }
                        logBan(target, event.getMember(), reason, event.getGuild().getTextChannelById("496491292537126912"));
                    } else {
                        logBan(target, event.getMember(), "N/A", event.getGuild().getTextChannelById("496491292537126912"));
                    }
                }
            } else{
                event.getChannel().sendMessage("```You must be a dumb bitch, stop trying to fucking ban people slut!```").queue();
            }
        }
        if(args[0].equalsIgnoreCase(Info.PREFIX + "kick")) {
            if (event.getMember().getRoles().contains(admin) || event.getMember().getRoles().contains(leader)) {
                if (args.length <= 1) {
                    sendErrorMessageKick(event.getChannel(), event.getMember());
                } else {
                    Member target = event.getMessage().getMentionedMembers().get(0);
                    //Member target = event.getMessage().getMentionedMembers().get(0);
                    event.getGuild().getController().kick(target).queue();
                    if (args.length >= 3) {
                        String reason = "";
                        for (int i = 2; i < args.length; i++) {
                            reason += args[i] + " ";
                        }
                        logKick(target, event.getMember(), reason, event.getGuild().getTextChannelById("496491292537126912"));
                    } else {
                        logKick(target, event.getMember(), "N/A", event.getGuild().getTextChannelById("496491292537126912"));
                    }
                }
            } else{
                event.getChannel().sendMessage("```Your peasant ass don't have perms bitch!```").queue();
            }
        }
    }

    //ban embeds
    public void sendErrorMessageBan(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.red);
        builder.setDescription("{} = required");
        builder.addField("Proper user: .ban {@user} {reason}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }
    public void logBan(Member muted, Member muter, String reason, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Ban Report");
        builder.setColor(Color.blue);
        builder.addField("Banned User: ", muted.getAsMention(), false);
        builder.addField("Admin:", muter.getAsMention(), false);
        builder.addField("Reason:", reason, false);
        builder.addField("Date:", sdf.format(date), false);
        builder.addField("Time:", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();
    }

    //kick embeds
    public void sendErrorMessageKick(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.red);
        builder.setDescription("{} = required");
        builder.addField("Proper user: .kick {@user} {reason}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }
    public void logKick(Member muted, Member muter, String reason, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Kick Report");
        builder.setColor(Color.blue);
        builder.addField("Kicked User: ", muted.getAsMention(), false);
        builder.addField("Admin:", muter.getAsMention(), false);
        builder.addField("Reason:", reason, false);
        builder.addField("Date:", sdf.format(date), false);
        builder.addField("Time:", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();
    }
}
