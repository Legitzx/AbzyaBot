package me.legitzx.bot.wallChecks;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class BufferChecks extends ListenerAdapter {
    public Timer timer;
    public boolean count;
    public int counter = 0;
    public String fileName1 = "C:\\Users\\lelspam01\\Desktop\\AbzyaBot\\consoleclient\\Console\\serverchat.txt"; //C:\Users\legitzx\Desktop\AbzyaBot\consoleclient\Console\se
    public String fileName2 = "C:\\Users\\lelspam01\\Desktop\\AbzyaBot\\consoleclient\\Console\\wallcheck.txt";

    public String message = "";

    private static String uri = "mongodb+srv://admin:theluch55@royalbot-fbwir.mongodb.net/admin";
    private static MongoClientURI clientURI = new MongoClientURI(uri);
    private static MongoClient mongoClient = new MongoClient(clientURI);

    private static MongoDatabase mongoDatabase = mongoClient.getDatabase("AbzyaBot");
    private static MongoCollection collection = mongoDatabase.getCollection("wallchecksWhitelist");
    private MongoCollection coll = mongoDatabase.getCollection("wallchecks");



    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        Role checker = event.getGuild().getRolesByName("Wall Checkers", true).get(0);


        if (args[0].equalsIgnoreCase(Info.PREFIX + "bclear")) { //clear gateway
            if(event.getMember().getRoles().contains(checker)) {
                checkedEmbed(event.getGuild().getTextChannelById("498395782605438976"), event.getMember().getEffectiveName(), 1234567);
                update(event.getGuild().getTextChannelById("498395782605438976"), event.getMember().getEffectiveName());
            } else {
                event.getChannel().sendMessage("Stop trying to check walls nerd, you fucking bad LUL!").queue();
            }
        }







        if(event.getMember().hasPermission(Permission.ADMINISTRATOR)) { //setup stuff
            if (args[0].equalsIgnoreCase(Info.PREFIX + "setup")) {
                try {
                    PrintWriter writer = new PrintWriter(fileName1);
                    writer.print("");
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            File file = new File(fileName1);
                            System.out.println(file.getAbsolutePath());
                            if (file.exists() && file.canRead()) {
                                long fileLength = file.length();
                                readFile(file, 0L, event.getGuild().getTextChannelById("498395782605438976"));
                                while (true) {

                                    if (fileLength < file.length()) {
                                        readFile(file, fileLength, event.getGuild().getTextChannelById("498395782605438976"));
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
                setTimer(event.getGuild().getTextChannelById("498395782605438976"), "Setup Mode");
            }
        }

    }

    public void readFile(File file, Long fileLength, TextChannel channel) throws IOException {
        String line = null;

        BufferedReader in = new BufferedReader(new java.io.FileReader(file));
        in.skip(fileLength);
        while ((line = in.readLine()) != null) {
            String[] args = line.split(" ");
            //System.out.println(line);
            if(line.contains(".bclear")) {
                Document found = (Document) collection.find(new Document("ign", args[1])).first();
                if(found != null) {
                    Boolean access = found.getBoolean("access");
                    if(access) {


                        //leaderboard incre
                        Document found1 = (Document) coll.find(new Document("id", args[1])).first();

                        if(found1 != null) {
                            Bson updatedvalue = new Document("checks", 2);
                            Bson updateoperation = new Document("$inc", updatedvalue);
                            coll.updateOne(found1, updateoperation);

                        } else {
                            Document document = new Document("id", args[1]);
                            document.append("checks", 1);
                            coll.insertOne(document);
                        }
                        //leaderboard incre


                        //clear logic
                        checkedEmbed(channel, args[1], found1.getInteger("checks"));
                        update(channel, args[1]);
                    } else if(!access) {
                        System.out.println(args[1] + " attempted to check buffer.");
                    }
                }
            }
        }
        in.close();
    }


    public void update(TextChannel channel, String IGN) {
        timer.cancel();
        count = true;
        counter = 0;
        //checkedEmbed(channel, member);
        setTimer(channel, IGN);
    }

    public void setTimer(TextChannel channel, String IGN) {
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                if(count) {
                    System.out.println("set timer");
                } else if(!count) {
                    counter++;
                    if(counter == 1) {

                        checkEmbed(channel, IGN);
                    } else if(counter == 2) {
                        checkEmbed2(channel, IGN);
                    }
                }
                count = false;
            }
        };
        timer = new Timer();
        timer.schedule(timerTask1, 100, 1800000); //600k = 10mins
    }


    public void checkEmbed(TextChannel channel, String IGN) { //TELLS USERS TO CHECK WALLS
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("THE BUFFER HAS NOT been checked for 30 minutes!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.CYAN);
        builder.setAuthor("Abzya Buffer Checks");
        builder.setDescription("**Check Buffer ASAP** THE BUFFER HAS NOT been checked for 30 minutes!! Do ``.bclear`` ingame if walls are clear!! Do .weewoo if we are getting raided!");
        builder.setFooter("Time: " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
    }
    public void checkEmbed2(TextChannel channel, String IGN) { //TELLS USERS TO CHECK WALLS
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("THE BUFFER HAS NOT been checked for 1+ hour!!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.YELLOW);
        builder.setAuthor("Abzya Buffer Checks");
        builder.setDescription("**Check walls ASAP** THE BUFFER HAS NOT been checked for 1+ hour``` Do ``.bclear`` ingame if walls are clear!! Do .weewoo if we are getting raided!");
        builder.setFooter("Time: " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
    }


    public void checkedEmbed(TextChannel channel, String IGN, Integer score) { //TELLS USERS WALLS HAVE BEEN CHECKED
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("Buffer has been checked!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.CYAN);
        builder.setAuthor("Abzya Buffer Checks");
        builder.setDescription("Buffer has been checked using .bclear ingame");
        builder.addField("Checked by(IGN): ", IGN, true);
        if(score == 1234567) {
            builder.addField("Score: ", "N/A Checked In Discord", true);
        } else {
            builder.addField("Score: ", score.toString(), true);
        }
        builder.setFooter("Checked at: " + stf.format(date), "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png" );
        channel.sendMessage(builder.build()).queue();
    }



}
