package me.legitzx.bot.modTools;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.legitzx.bot.Info;
import me.legitzx.bot.core.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WarnSystem extends ListenerAdapter {
    public int warning;
    public String reason;

    Main main = new Main();
    public static int warnings = 0;
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Role admin = event.getGuild().getRolesByName("Co Leader", true).get(0);
        Role moderator = event.getGuild().getRolesByName("Leader", true).get(0);

        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(Info.PREFIX + "warn")) {
            if (event.getMember().getRoles().contains(admin) || event.getMember().getRoles().contains(moderator)) {
                if (args.length <= 1) {
                    sendErrorMessage(event.getChannel(), event.getMember());
                } else {
                    if (args.length >= 3) {
                        reason = "";
                        for (int i = 2; i < args.length; i++) {
                            reason += args[i] + " ";
                        }
                        //log(target, event.getMember(), reason, event.getGuild().getTextChannelById("482762118848839690"));
                    }
                    Member target = event.getMessage().getMentionedMembers().get(0);
                    checkPlayer(target.getUser().getId(), event.getGuild().getTextChannelById("496491292537126912"), target, event.getMember(), reason);


                }
            } else{
                event.getChannel().sendMessage("```Bitch you DO NOT have perms for .warn get da fuck out!```").queue();
            }
        }
    }


    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.red);
        builder.setDescription("{} = required");
        builder.addField("Proper user: .warn {@user} {reason}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public void checkPlayer(String id, TextChannel channel, Member target, Member admin, String info) {
        String uri = "mongodb+srv://admin:theluch55@royalbot-fbwir.mongodb.net/admin";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("AbzyaBot");
        MongoCollection collection = mongoDatabase.getCollection("warningsCollection");

        System.out.println("Database Connected");

        Document found = (Document) collection.find(new Document("id", id)).first();

        if(found != null) {
            System.out.println("Found user");
            Bson updatedvalue = new Document("warnings", 1);
            Bson updateoperation = new Document("$inc", updatedvalue);
            collection.updateOne(found, updateoperation);
            System.out.println("User Updated!");
            int warning = found.getInteger("warnings");
            warning = warning + 1;
            if(warning <= 1){
                warningMessage(channel, target, warning, admin, info);

            } if(warning == 2) {
                warningMessage2(channel, target, warning, admin, info);
            } if(warning >= 3) {
                warningMessage3(channel, target, warning, admin, info);
            }
            System.out.println(warning);
        } else {
            warnings = warnings + 1;
            System.out.println("didnt find user, creating user now");
            Document document = new Document("id", id);
            document.append("warnings", warnings);
            warningMessage(channel, target, warnings, admin, info);

            collection.insertOne(document);
            warnings = warnings - 1;
        }
    }

    public void warningMessage(TextChannel channel, Member member,int warning, Member admin, String info) {
        String x = Integer.toString(warning);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(":no_entry_sign: Warn Report :no_entry_sign: ");
        builder.addField("Warned User:", member.getAsMention(), false);
        builder.addField("Admin:", admin.getAsMention(), false);
        builder.addField("Reason:", info, false);
        builder.addField("Warns: ", x, false);
        builder.addField("Date:", sdf.format(date), false);
        builder.addField("Time:", stf.format(date), false);
        builder.setColor(Color.blue);
        channel.sendMessage(builder.build()).queue();
    }

    public void warningMessage2(TextChannel channel, Member member,int warning, Member admin, String info) {
        String x = Integer.toString(warning);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(":no_entry_sign: Warn Report 2 :no_entry_sign: ");
        builder.addField("Warned User:", member.getAsMention(), false);
        builder.addField("Admin:", admin.getAsMention(), false);
        builder.addField("Reason:", info, false);
        builder.addField("Warns: ", x, false);
        builder.addField("Date:", sdf.format(date), false);
        builder.addField("Time:", stf.format(date), false);
        builder.setColor(Color.orange);
        channel.sendMessage(builder.build()).queue();
    }

    public void warningMessage3(TextChannel channel, Member member,int warning, Member admin, String info) {
        String x = Integer.toString(warning);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(":no_entry_sign: USER HAD REACHED 3 WARNS :no_entry_sign: ");
        builder.addField("Warned User:", member.getAsMention(), false);
        builder.addField("Admin:", admin.getAsMention(), false);
        builder.addField("Reason:", info, false);
        builder.addField("Warns: ", x, false);
        builder.addField("Date:", sdf.format(date), false);
        builder.addField("Time:", stf.format(date), false);
        builder.setColor(Color.red);
        channel.sendMessage(builder.build()).queue();
    }

    public void log(Member muted, Member muter, String reason, TextChannel channel) {
        String y = Integer.toString(warning);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Warn Report");
        builder.setColor(Color.blue);
        builder.addField("Warned User: ", muted.getAsMention(), false);
        builder.addField("Admin:", muter.getAsMention(), false);
        builder.addField("Reason:", reason, false);
        builder.addField("Warns: ", y, false);
        builder.addField("Date:", sdf.format(date), false);
        builder.addField("Time:", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();

    }
}
