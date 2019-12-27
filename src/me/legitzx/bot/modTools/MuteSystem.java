package me.legitzx.bot.modTools;

import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MuteSystem extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        Role admin = event.getGuild().getRolesByName("Co Leader", true).get(0);
        Role leader = event.getGuild().getRolesByName("Leader", true).get(0);


        String[] args = event.getMessage().getContentRaw().split(" ");
        MessageChannel channel = event.getChannel();

        if(args[0].equalsIgnoreCase(Info.PREFIX + "mute")) {
            if (event.getMember().getRoles().contains(admin) || event.getMember().getRoles().contains(leader)) {
                if (args.length <= 1) {
                    sendErrorMessage(event.getChannel(), event.getMember());
                } else {
                    Member target = event.getMessage().getMentionedMembers().get(0);

                    Role muted = event.getGuild().getRolesByName("Muted", true).get(0);
                    event.getGuild().getController().addSingleRoleToMember(target, muted).queue();
                    event.getGuild().getController().setMute(target, true).queue();

                    if (args.length >= 3) {
                        String reason = "";
                        for (int i = 2; i < args.length; i++) {
                            reason += args[i] + " ";
                        }
                        log(target, event.getMember(), reason, event.getGuild().getTextChannelById("496491292537126912"));
                    } else {
                        log(target, event.getMember(), "N/A", event.getGuild().getTextChannelById("496491292537126912"));
                    }
                }
            } else{
                event.getChannel().sendMessage("```I think you need to stop trying to mute people, AND SHUT THE FUCK UP YOURSELF BITCH!!! ultra isnt funny xd```").queue();
            }
        }

        if(args[0].equalsIgnoreCase(Info.PREFIX + "unmute")) {
            if (event.getMember().getRoles().contains(admin) || event.getMember().getRoles().contains(leader)) {
                if (args.length <= 1) {
                    sendErrorMessage2(event.getChannel(), event.getMember());
                } else {
                    Member target = event.getMessage().getMentionedMembers().get(0);

                    Role muted = event.getGuild().getRolesByName("Muted", true).get(0);
                    event.getGuild().getController().removeSingleRoleFromMember(target, muted).queue();
                    event.getGuild().getController().setMute(target, false).queue();

                    log2(target, event.getMember(), event.getGuild().getTextChannelById("496491292537126912"));
                }
            } else{
                event.getChannel().sendMessage("```Do you think you have fucking perms to unmute people, NO U FUCKING DONT. Fuck off before i grab your ip and fry your router dumb bitch!```").queue();
            }
        }
    }
    //mute embeds
    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.red);
        builder.setDescription("{} = required");
        builder.addField("Proper user: .mute {@user} {reason}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }
    public void log(Member muted, Member muter, String reason, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Mute Report");
        builder.setColor(Color.blue);
        builder.addField("Muted User: ", muted.getAsMention(), false);
        builder.addField("Muter:", muter.getAsMention(), false);
        builder.addField("Reason:", reason, false);
        builder.addField("Date:", sdf.format(date), false);
        builder.addField("Time:", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();
    }

    //unmute embeds
    public void sendErrorMessage2(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.red);
        builder.setDescription("{} = required");
        builder.addField("Proper user: .unmute {@user}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }
    public void log2(Member muted, Member muter, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Unmute Report");
        builder.setColor(Color.blue);
        builder.addField("Unmuted User: ", muted.getAsMention(), false);
        builder.addField("Unmuter:", muter.getAsMention(), false);
        builder.addField("Date:", sdf.format(date), false);
        builder.addField("Time:", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();
    }
}
