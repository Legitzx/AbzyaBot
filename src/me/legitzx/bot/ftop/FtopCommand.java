package me.legitzx.bot.ftop;

import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class FtopCommand extends ListenerAdapter {

    public static String fina = "";
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getMessage().getAuthor().isBot()) return;
        String filename = "C:\\Users\\lelspam01\\Desktop\\AbzyaBot\\consoleclient\\Console\\output.txt"; //C:\Users\legitzx\Desktop\AbzyaBot\consoleclient\Console\output.txt

        String[] args = event.getMessage().getContentRaw().split(" ");


        if(event.getMessage().getAuthor().isBot()) return;


        Role role = event.getGuild().getRolesByName("Developer", true).get(0);
        if(args[0].equalsIgnoreCase(Info.PREFIX + "setup")) {
            if(event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                event.getChannel().sendMessage("FTop Command SETUP!").queue();
                int MINUTES = 30; // The delay in minutes
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() { // Function runs every MINUTES minutes.
                        // Run the code you want here
                        readTextFileUsingScanner(filename, event.getGuild().getTextChannelById("485769261516587029"));// If the function you wanted was static
                    }
                }, 0, 1000 * 60 * MINUTES);
                // 1000 milliseconds in a second * 60 per minute * the MINUTES variable.
            } else {
                event.getChannel().sendMessage("NOPE BITCH").queue();
            }
        }






    }


    public static void readTextFileUsingScanner(String fileName, TextChannel channel) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNext()) {
                String str = sc.nextLine();
                System.out.println(str);
                fina += str + "\n";
            }
            sc.close();
            embed(channel, fina);
            fina = "";
        } catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }


        }
    }

    public static void embed(TextChannel channel, String msg) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Top Factions");
        builder.setColor(Color.RED);
        builder.setDescription(msg);
        channel.sendMessage(builder.build()).queue();
        msg = "";

    }

}
