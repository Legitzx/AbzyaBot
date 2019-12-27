package me.legitzx.bot.applications;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.awt.*;
import java.util.EnumSet;
import java.util.HashMap;

public class ApplicationCommand extends ListenerAdapter {
    public int count = 1;
    public int counting = 0;

    public String question1;
    public String question2;
    public String question3;
    public String question4;
    public String question5;
    public String question6;
    public String question7;
    public String question8;
    public String question9;
    public String question10;
    public String question11;
    public String question12;
    public String question13;
    public String question14;
    public String question15;
    public String question16;
    public String question17;
    public String question18;
    public String question19;
    public String question20;
    public String question21;
    public String question22;
    public String question23;

    public String content;

    public Member member;


    public HashMap<String, String> q1 = new HashMap<>();
    public HashMap<String, String> q2 = new HashMap<>();
    public HashMap<String, String> q3 = new HashMap<>();
    public HashMap<String, String> q4 = new HashMap<>();
    public HashMap<String, String> q5 = new HashMap<>();
    public HashMap<String, String> q6 = new HashMap<>();
    public HashMap<String, String> q7 = new HashMap<>();
    public HashMap<String, String> q8 = new HashMap<>();
    public HashMap<String, String> q9 = new HashMap<>();
    public HashMap<String, String> q10 = new HashMap<>();
    public HashMap<String, String> q11 = new HashMap<>();
    public HashMap<String, String> q12 = new HashMap<>();
    public HashMap<String, String> q13 = new HashMap<>();
    public HashMap<String, String> q14 = new HashMap<>();
    public HashMap<String, String> q15 = new HashMap<>();
    public HashMap<String, String> q16 = new HashMap<>();
    public HashMap<String, String> q17 = new HashMap<>();
    public HashMap<String, String> q18 = new HashMap<>();
    public HashMap<String, String> q19 = new HashMap<>();
    public HashMap<String, String> q20 = new HashMap<>();
    public HashMap<String, String> q21 = new HashMap<>();
    public HashMap<String, String> q22 = new HashMap<>();
    public HashMap<String, String> q23 = new HashMap<>();
    public HashMap<String, String> q24 = new HashMap<>();

    public HashMap<String, Member> memberHash = new HashMap<>();
    public Event event2;

    public String uri = "mongodb+srv://admin:theluch55@royalbot-fbwir.mongodb.net/admin";
    public MongoClientURI clientURI = new MongoClientURI(uri);
    public MongoClient mongoClient = new MongoClient(clientURI);

    public MongoDatabase mongoDatabase = mongoClient.getDatabase("AbzyaBot");
    public MongoCollection collection = mongoDatabase.getCollection("application");
    //create switch statement that has all the questions
    //every time a user answers a question it creates a entry in a hashmap or mongodb / all questions are stored in vars or mongodbs
    public void onGuildMessageReceived(GuildMessageReceivedEvent event1) {
        String[] args = event1.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(Info.PREFIX + "apply")) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Just slid into your DMs");
            builder.setColor(Color.green);
            event1.getChannel().sendMessage(builder.build()).queue();
            User user = event1.getAuthor();
            user.openPrivateChannel().queue((channel) ->
            {
                infoEmbed(channel);
                channel.sendMessage("Please respond with [read guidelines] to begin the application.").queue();
            });

            event2 = event1;
            member = event1.getMember();
            memberHash.put(event1.getAuthor().getId(), event1.getMember());

        }

        if(args[0].equalsIgnoreCase(Info.PREFIX + "submitapp")) {
            /*
            old code that works
            TextChannel channel1 = event1.getGuild().getTextChannelsByName("application-" + event1.getAuthor().getId(), true).get(0);
            String channelID = channel1.getId();
            System.out.println(channelID);
            sendEmbed(event1.getGuild().getTextChannelById(channelID), event1.getAuthor().getId());
            */
            sendEmbed(event1.getGuild().getTextChannelById(event1.getGuild().getTextChannelsByName("application-" + event1.getAuthor().getId(), true).get(0).getId()), event1.getAuthor().getId());
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.RED);
            builder.setTitle("Application Submitted!");
            event1.getChannel().sendMessage(builder.build()).queue();
        }
    }


    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        System.out.println("Database Connected");

        String id = event.getAuthor().getId();

        Document found = (Document) collection.find(new Document("id", id)).first();
        if(!event.getAuthor().isBot()) {
            if(found != null) {
                if(count <= 33) {
                    System.out.println("Found user");
                    Bson updatedvalue = new Document("count", 1);
                    Bson updateoperation = new Document("$inc", updatedvalue);
                    collection.updateOne(found, updateoperation);
                    System.out.println("User Updated!");
                    count = found.getInteger("count");
                    count = count + 1;

                    System.out.println(count);
                }
            } else {
                counting = counting + 1;
                System.out.println("didnt find user, creating user now");
                Document document = new Document("id", id);
                document.append("count", counting);

                collection.insertOne(document);
                counting = counting - 1;
                count = 1;

            }






            User user = event.getAuthor();

            String message = event.getMessage().getContentRaw().toLowerCase();


            switch (count) {
                case 1:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Please type the following code in: 4539").queue();
                        //question1 = message;
                        q1.put(event.getAuthor().getId(), message);
                        //System.out.println(q1.get(event.getAuthor().getId()));
                        //count++;
                    });
                    break;
                //question
                case 2:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("What is your IGN- ").queue();
                        //question2 = message;
                        q2.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 3:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Discord Tag : (eg. UltraPhoenix#2523)- ").queue();
                        question3 = message;
                        q3.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 4:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Age- ").queue();
                        question4 = message;
                        q4.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 5:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Timezone : ").queue();
                        question5 = message;
                        q5.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 6:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Previous Factions : (Include: Name, Rank in Faction, Servers Won) ").queue();
                        question6 = message;
                        q6.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 7:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Do you have a working mic and are you talkative/can have some banter? ").queue();
                        question7 = message;
                        q7.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 8:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Are you friends with anyone already in the faction? / Can anyone vouch for you? ").queue();
                        question8 = message;
                        q8.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 9:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("How active are you? (Rough estimates Weekdays/Weekends) ").queue();
                        question9 = message;
                        q9.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 10:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Rate yourself /10 for Cannoning,PvP,Base Building ").queue();
                        question10 = message;
                        q10.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 11:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Do you have and know how to use schematica (ie. building a cactus farm or printing sand walls/filters) ").queue();
                        question11 = message;
                        q11.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 12:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Are you able to purchase ranks and/or keys for any server we play (Not Required) ").queue();
                        question12 = message;
                        q12.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question
                case 13:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Anything else you can bring to Abzya? ").queue();
                        question13 = message;
                        q13.put(event.getAuthor().getId(), message);
                        //count++;
                    });
                    break;
                //question

                case 14:
                    user.openPrivateChannel().queue((channel) ->
                    {

                        q14.put(event.getAuthor().getId(), message);
                        channel.sendMessage("Please type in **.submitapp** in #commands TextChannel in the @AbzyaDiscord").queue();
                        Category category = event.getJDA().getGuildById("328176064599687168").getCategoriesByName("Applications", true).get(0);

                        //get the public role
                        Role role = event.getJDA().getGuildById("328176064599687168").getPublicRole();
                        EnumSet<Permission> allow = EnumSet.of(Permission.MESSAGE_WRITE);
                        EnumSet<Permission> deny = EnumSet.of(Permission.MESSAGE_READ);


                        //co owner
                        Role dev = event.getJDA().getGuildById("328176064599687168").getRolesByName("Co Leader", true).get(0);
                        EnumSet<Permission> allowD = EnumSet.of(Permission.MESSAGE_WRITE, Permission.MESSAGE_READ);
                        EnumSet<Permission> denyD = EnumSet.of(Permission.MESSAGE_TTS);

                        //owner
                        Role leader = event.getJDA().getGuildById("328176064599687168").getRolesByName("Leader", true).get(0);
                        EnumSet<Permission> allowL = EnumSet.of(Permission.MESSAGE_WRITE, Permission.MESSAGE_READ);
                        EnumSet<Permission> denyL = EnumSet.of(Permission.MESSAGE_TTS);

                        //bots
                        Role bots = event.getJDA().getGuildById("328176064599687168").getRolesByName("Recruiter", true).get(0);
                        EnumSet<Permission> allowB = EnumSet.of(Permission.MESSAGE_WRITE, Permission.MESSAGE_READ);
                        EnumSet<Permission> denyB = EnumSet.of(Permission.MESSAGE_TTS);

                        //event.getMember
                        EnumSet<Permission> allow2 = EnumSet.of(Permission.MESSAGE_WRITE, Permission.MESSAGE_READ);
                        EnumSet<Permission> deny2 = EnumSet.of(Permission.MESSAGE_TTS);

                        //create channel and give specific perms
                        event.getJDA().getGuildById("328176064599687168").getController().createTextChannel("application-" + event.getAuthor().getId()).setParent(category).addPermissionOverride(role, allow, deny).addPermissionOverride(dev, allowD, denyD).addPermissionOverride(leader, allowL, denyL).addPermissionOverride(bots, allowB, denyB).addPermissionOverride(event.getJDA().getGuildById("328176064599687168").getMember(event.getAuthor()), allow2, deny2).queue();
                        //put all the code below this

                    });

                case 15:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        channel.sendMessage("Please type in **.submitapp** in #commands TextChannel in the @AbzyaDiscord").queue();
                    });
                    break;
                //question

                default:
                    user.openPrivateChannel().queue((channel) ->
                    {
                        if(count <= 32) {
                            channel.sendMessage("Your getting this message because you probably already applied! Please contact @LEGITZX#0001 if this is an error!").queue();
                            //count++;
                        }
                    });
                    break;
                //question
            }

        }

    }


    public void sendEmbed(TextChannel channel, String id) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Application Form");
        builder.setColor(Color.blue);
        //builder.addField("What is your in-game-name? (Include previous names): ", q1.get(id), false);
        builder.addField("What is your IGN: ", q3.get(id), false);
        builder.addField("Discord Tag : (eg. UltraPhoenix#2523)", q4.get(id), false);
        builder.addField("Age- ", q5.get(id), false);
        builder.addField("Timezone :", q6.get(id), false);
        builder.addField("Previous Factions : (Include: Name, Rank in Faction, Servers Won) ", q7.get(id), false);
        builder.addField("Do you have a working mic and are you talkative/can have some banter? ", q8.get(id), false);
        builder.addField("Are you friends with anyone already in the faction? / Can anyone vouch for you? ", q9.get(id), false);
        builder.addField("How active are you? (Rough estimates Weekdays/Weekends) ", q10.get(id), false);
        builder.addField("Rate yourself /10 for Cannoning,PvP,Base Building", q11.get(id), false);
        builder.addField("Do you have and know how to use schematica (ie. building a cactus farm or printing sand walls/filters)", q12.get(id), false);
        builder.addField("Are you able to purchase ranks and/or keys for any server we play (Not Required) ", q13.get(id), false);
        builder.addField("Anything else you can bring to Abzya? ", q14.get(id), false);
        builder.addField("Discord of Applicant:", memberHash.get(id).getAsMention(), false);
        channel.sendMessage(builder.build()).queue();
    }


    public void infoEmbed(PrivateChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.CYAN);
        builder.setTitle("**Abzya Application Process Started**");
        channel.sendMessage(builder.build()).queue();
    }
}
