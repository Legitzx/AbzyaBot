package me.legitzx.bot.mclisteners;

import me.legitzx.bot.Info;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class ServerChat extends ListenerAdapter {
    public static String str;
    public static int counter = 0;
    public String fileName1 = "C:\\Users\\lelspam01\\Desktop\\AbzyaBot\\consoleclient\\Console\\serverchat.txt"; //C:\Users\legitzx\Desktop\AbzyaBot\consoleclient\Console\serverchat.txt

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (event.getMessage().getAuthor().isBot()) return;

        if (args[0].equalsIgnoreCase(Info.PREFIX + "setup")) {
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                setTimer();
                //readTextFileUsingScanner(fileName, event.getGuild().getTextChannelById("497238046031216651"));
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            File file = new File(fileName1);
                            System.out.println(file.getAbsolutePath());
                            if (file.exists() && file.canRead()) {
                                long fileLength = file.length();
                                readFile(file, 0L, event.getGuild().getTextChannelById("497552902785597450"));
                                while (true) {

                                    if (fileLength < file.length()) {
                                        readFile(file, fileLength, event.getGuild().getTextChannelById("497552902785597450"));
                                        fileLength = file.length();
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Thread thread = new Thread(myRunnable);
                thread.start();
            }
        }
    }

    public void setTimer() {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                try {
                    PrintWriter writer = new PrintWriter(fileName1);
                    writer.print("");
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer("Timer");

        long delay  = 100000L;
        long period = 100000L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);

    }

    public static void readFile(File file, Long fileLength, TextChannel channel) throws IOException {
        String line = null;

        BufferedReader in = new BufferedReader(new java.io.FileReader(file));
        in.skip(fileLength);
        while ((line = in.readLine()) != null) {
            //System.out.println(line);
            channel.sendMessage("`" + line + "`").queue();
        }
        in.close();
    }
}

/*
package me.legitzx.bot.mclisteners;

import me.legitzx.bot.Info;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class ServerChat extends ListenerAdapter {
    public static String str;
    public static int counter = 0;
    public String fileName1 = "C:\\Users\\Luciano\\Desktop\\Programming\\JAVA ROOT\\ClientsDiscordBots\\AbzyaBot\\consoleclient\\Console\\serverchat.txt"; //C:\Users\legitzx\Desktop\AbzyaBot\consoleclient\Console\serverchat.txt

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (event.getMessage().getAuthor().isBot()) return;

        if (args[0].equalsIgnoreCase(Info.PREFIX + "setup")) {
            //readTextFileUsingScanner(fileName, event.getGuild().getTextChannelById("497238046031216651"));
            try {
                File file = new File(fileName1);
                System.out.println(file.getAbsolutePath());
                if (file.exists() && file.canRead()) {
                    long fileLength = file.length();
                    readFile(file, 0L, event.getGuild().getTextChannelById("497552902785597450"));
                    while (true) {

                        if (fileLength < file.length()) {
                            readFile(file, fileLength, event.getGuild().getTextChannelById("497552902785597450"));
                            fileLength = file.length();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void readFile(File file, Long fileLength, TextChannel channel) throws IOException {
        String line = null;

        BufferedReader in = new BufferedReader(new java.io.FileReader(file));
        in.skip(fileLength);
        while ((line = in.readLine()) != null) {
            //System.out.println(line);
            channel.sendMessage("`" + line + "`").queue();
        }
        in.close();
    }
}
*/