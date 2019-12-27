package me.legitzx.bot.core;

import me.legitzx.bot.applications.Accept;
import me.legitzx.bot.applications.ApplicationCommand;
import me.legitzx.bot.applications.Deny;
import me.legitzx.bot.ftop.FtopCommand;
import me.legitzx.bot.listeners.LeaveJoin;
import me.legitzx.bot.mclisteners.BankListener;
import me.legitzx.bot.mclisteners.ServerChat;
import me.legitzx.bot.modTools.*;
import me.legitzx.bot.wallChecks.BufferChecks;
import me.legitzx.bot.wallChecks.WallChecks;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class Main extends ListenerAdapter {
    public static void main(String[] args)
            throws LoginException, RateLimitedException, InterruptedException {
        JDA jda = new JDABuilder(AccountType.BOT).setToken("NDk2NDg4ODEyNzM0NzA5Nzg0.DpRW1w.SZ_9bd79i9c4-fDcpzVVvgx9G44").buildBlocking();
        jda.addEventListener(new Main());
        jda.addEventListener(new ServerChat());
        //jda.addEventListener(new Accept());
        //jda.addEventListener(new Deny());
        //jda.addEventListener(new ApplicationCommand());
        jda.addEventListener(new FtopCommand());
        //jda.addEventListener(new LeaveJoin());
        //jda.addEventListener(new checkWarns());
        jda.addEventListener(new BankListener());
        //jda.addEventListener(new KickBanSystem());
        //jda.addEventListener(new MuteSystem());
        //jda.addEventListener(new PurgeSystem());
        //jda.addEventListener(new WarnSystem());
        //jda.addEventListener(new HelpCommand());
        //jda.addEventListener(new PromoteDemoteCommands());
        jda.addEventListener(new WallChecks());
        jda.addEventListener(new BufferChecks());
        jda.getPresence().setGame(Game.playing(".help"));
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String content = ((Message) message).getContentRaw();
        MessageChannel channel = event.getChannel();

        if (content.startsWith(".ping")) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Ping: ");
            builder.setDescription(event.getJDA().getPing() + "ms");
            builder.setColor(Color.green);
            channel.sendMessage(builder.build()).queue();
        }
    }


}
