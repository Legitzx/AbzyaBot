package me.legitzx.bot.applications;

import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Deny extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Role admin = event.getGuild().getRolesByName("Co Leader", true).get(0);
        Role leader = event.getGuild().getRolesByName("Leader", true).get(0);

        TextChannel channel = event.getChannel();
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Info.PREFIX + "deny")) {
            if (event.getMember().getRoles().contains(admin) || event.getMember().getRoles().contains(leader)) {
                if(event.getChannel().getName().startsWith("application")) {

                    if (args.length <= 1) {
                        sendErrorMessage(channel, event.getMember()); //````````````````````````````
                    } else {
                        Member target = event.getMessage().getMentionedMembers().get(0);
                        User user = target.getUser();
                        denyMessage(target, event.getGuild().getTextChannelById("495031814469976064"));
                        event.getChannel().delete().queue();//````````````````````
                        user.openPrivateChannel().queue((channel1) ->
                        {
                            channel1.sendMessage("You have been **DENIED**. Cya.").queue();
                        });
                    }

                } else {
                    event.getChannel().sendMessage("You must be in a application channel to execute this command!").queue();
                }
            } else{
                event.getChannel().sendMessage("```You dont have perms to execute .deny, are you dumb or somethin like da fuck```").queue();
            }
        }
    }

    public void denyMessage(Member user, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.red);
        builder.setTitle(":no_entry_sign: You application has been denied! :no_entry_sign: ");
        builder.addField("Better luck next time, if there is a next time.", user.getAsMention(), false);
        builder.addField("Date Denied: ", sdf.format(date), false);
        channel.sendMessage(builder.build()).queue();
    }

    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.red);
        builder.setDescription("{} = required");
        builder.addField("Proper user: .deny {@user}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }
}
