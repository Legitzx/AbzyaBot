package me.legitzx.bot.modTools;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bson.Document;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class checkWarns extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Info.PREFIX + "checkwarns")) {
            if (args.length <= 1) {
                sendErrorMessage(event.getChannel(), event.getMember()); //````````````````````````````
            } else {
                Member target = event.getMessage().getMentionedMembers().get(0);
                checkWarns(target.getUser().getId(), target, event.getChannel());
            }
        }
    }

    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.red);
        builder.setDescription("{} = required");
        builder.addField("Proper user: .checkwarns {@user}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public void checkWarns(String id, Member member, TextChannel channel) {
        String uri = "mongodb+srv://admin:theluch55@royalbot-fbwir.mongodb.net/admin";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("AbzyaBot");
        MongoCollection collection = mongoDatabase.getCollection("warningsCollection");

        System.out.println("Database Connected");

        Document found = (Document) collection.find(new Document("id", id)).first();



        if(found != null) {
            int warning = found.getInteger("warnings");
            foundWarns(member, channel, warning);
        } else {
            noWarns(member, channel);
        }

    }

    public void foundWarns(Member member, TextChannel channel, int warns) {
        String x = Integer.toString(warns);
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Warns");
        builder.addField("User: ", member.getAsMention(), false);
        builder.addField("Warns: ", x, false);
        builder.setColor(Color.red);
        channel.sendMessage(builder.build()).queue();
    }

    public void noWarns(Member member, TextChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Warns");
        builder.addField("You currently have no warns!", member.getAsMention(), false);
        builder.setColor(Color.green);
        channel.sendMessage(builder.build()).queue();
    }
}
