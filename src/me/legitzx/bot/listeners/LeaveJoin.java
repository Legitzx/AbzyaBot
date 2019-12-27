package me.legitzx.bot.listeners;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaveJoin extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        User user = event.getUser();
        event.getJDA().getTextChannelById("423612102012436480").sendMessage(":white_check_mark: " + event.getUser().getAsMention() + " Joined the Server!").queue();
        user.openPrivateChannel().queue((channel) ->
        {
            infoEmbed(event.getMember(), channel);
        });

    }
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        event.getJDA().getTextChannelById("423612102012436480").sendMessage(":no_entry_sign: " + event.getUser().getName() + " Left the Server!").queue();
    }
    public void infoEmbed(Member member, PrivateChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.CYAN);
        builder.setTitle("Welcome to Abzya Official Discord!");
        builder.setDescription("If you want to apply, please do .apply in the #commands channel to start the application process in your PMs!");
        builder.setFooter("Joined on " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();

    }
}
