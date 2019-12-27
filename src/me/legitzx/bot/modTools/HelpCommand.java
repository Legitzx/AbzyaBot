package me.legitzx.bot.modTools;

import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class HelpCommand extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if(args[0].equalsIgnoreCase(Info.PREFIX + "help")) {
            System.out.println("Embed waiting");
            helpEmbed(event.getChannel());
            System.out.println("Embed sent");
        }
    }
    public void helpEmbed(TextChannel channel) {

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(":tools: Help :tools: ");
        builder.setColor(Color.RED);
        builder.addField("Server Administration", ".mute {@user} {reason} - This command mutes a member\n.unmute {@user} - This command unmutes a member\n.warn {@user} {reason} - This command warns a member(check rules for warn punishments)\n.checkwarns {@user} - This command checks how many warns a user has\n.ban {@user} - This command bans a user\n.kick {@user} - This command kicks a user", false);
        builder.addField("Faction Administration", ".accept {@user} - This command gives a member recruit roles, and sends a welcome message.\n.deny {@user} - This command deny's a applicant from the faction.\n.promote {@user} - This command will promote someone to the next rank!\n.demote {@user} - This command will demote someone to the rank below them.", false);
        builder.addField("Faction Commands", ".apply - This command will start the application process in your PMS.", false);
        builder.addField("Wall Check Commands", ".whitelistadd IGN - Adds a user to the wall check whitelist\n.whitelistremove IGN - Removes a user from the wall check whitelist\n.whitelistshow - Shows the users who are added to the whitelist\n.bclear - Clears the buffer, can be used either ingame or discord(to use the ingame commands, message AbzyaDiscord the command)\n.clear - Clears the walls, can be used either ingame or discord(to use the ingame commands, message AbzyaDiscord the command)\n.weewoo - Sounds the alarm, can be used either ingame or discord(to use the ingame commands, message AbzyaDiscord the command)\n.top | .leaderboards - Shows the top 10 wall checkers!", false);
        builder.addField("Other Commands", ".ping - This command shows the bots ping to the server.", false);
        builder.setFooter("Bot made by: LEGITZX", "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();

        /*
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(":tools: Help :tools: ");
        builder.setColor(Color.green);
        builder.addField(".mute {@user} {reason}", "This command mutes a member", false);
        builder.addField(".tempmute {@user} {time(ex. 40s)} {reason}", "This command temp mutes a member.(Not finished)", false);
        builder.addField(".unmute {@user}", "This command unmutes a member", false);
        builder.addField(".warn {@user} {reason}", "This command warns a member(3 warns = kick)", false);
        builder.addField(".checkwarns {@user}", "This command checks how many warns a user has.", false);
        builder.addField(".accept {@user}", "This command gives a member recruit roles, and sends a welcome message.", false);
        builder.addField(".deny {@user}", "This command deny's a applicant from the faction.", false);
        builder.addField(".interview", "Use this command when you are waiting for an interview.", false);
        builder.addField(".ping", "This command shows the bots ping to the server.", false);
        builder.addField(".reportbug {reason}", "This command allows you to report bugs.", false);
        builder.setFooter("Refer to #recruitment for rules/more info.", "https://cdn.discordapp.com/attachments/481991909342969869/483387137732116501/NewRoyalLogo.png");
        channel.sendMessage(builder.build()).queue();
        */
    }
}
