package me.legitzx.bot.wallChecks;

import com.mongodb.*;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

public class WallChecks extends ListenerAdapter {
    public Timer timer;
    public boolean count;
    public int counter = 0;
    public String fileName1 = "C:\\Users\\lelspam01\\Desktop\\AbzyaBot\\consoleclient\\Console\\serverchat.txt"; //C:\Users\legitzx\Desktop\AbzyaBot\consoleclient\Console\se
    public String fileName2 = "C:\\Users\\lelspam01\\Desktop\\AbzyaBot\\consoleclient\\Console\\wallcheck.txt";

    public String message = "";

    public int found2;

    private static String uri = "mongodb+srv://admin:theluch55@royalbot-fbwir.mongodb.net/admin";
    private static MongoClientURI clientURI = new MongoClientURI(uri);
    private static MongoClient mongoClient = new MongoClient(clientURI);

    private static MongoDatabase mongoDatabase = mongoClient.getDatabase("AbzyaBot");
    private static MongoCollection collection = mongoDatabase.getCollection("wallchecksWhitelist");
    private MongoCollection coll = mongoDatabase.getCollection("wallchecks");

    public LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

    public HashMap<String, Integer> checks = new HashMap<String, Integer>();


    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        Role checker = event.getGuild().getRolesByName("Wall Checkers", true).get(0);


        if (args[0].equalsIgnoreCase(Info.PREFIX + "clear")) { //clear gateway
            if(event.getMember().getRoles().contains(checker)) {
                checkedEmbed(event.getGuild().getTextChannelById("477445977851035649"), event.getMember().getEffectiveName(), 1234567);
                update(event.getGuild().getTextChannelById("477445977851035649"), event.getMember().getEffectiveName());
            } else {
                event.getChannel().sendMessage("Stop trying to check walls nerd, you fucking bad LUL!").queue();
            }
        }

        if (args[0].equalsIgnoreCase(Info.PREFIX + "weewoo")) { //raid gateway
            if(event.getMember().getRoles().contains(checker)) {
                raidEmbed(event.getGuild().getTextChannelById("477445977851035649"), event.getMember().toString());
                update(event.getGuild().getTextChannelById("477445977851035649"), event.getMember().toString());
            } else {
                event.getChannel().sendMessage("Stop trying to check walls nerd, you fucking bad LUL!").queue();
            }
        }

        if(args[0].equalsIgnoreCase(Info.PREFIX + "top") || args[0].equalsIgnoreCase(Info.PREFIX + "leaderboards" )) {
            MongoCursor<Document> cursor = coll.find().iterator();
            try {
                while (cursor.hasNext()) {
                    String[] args1 = cursor.next().toJson().split(" ");

                    String value1 = args1[13].replace("\"", "");
                    String key1 = args1[10].replace("\"", "");
                    String key = key1.replace(",", "");
                    int value = Integer.parseInt(value1);
                    //System.out.println(key + " - "  + value);
                    checks.put(key, value);
                    //key = ign
                    //value = # of checks
                    reverseSortedMap = new LinkedHashMap<>();
                    //Use Comparator.reverseOrder() for reverse ordering
                    checks.entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

                    //System.out.println("Reverse Sorted Map   : " + reverseSortedMap);

                    reverseSortedMap.get(0);


                }
            } finally {
                cursor.close();
                System.out.println(reverseSortedMap);


                /*
                Set<String> keys = reverseSortedMap.keySet();
                String[] keysArray = keys.toArray(new String[keys.size()]);
                for (int i = 0; i < keysArray.length && i < 10; i++) {
                    System.out.println(reverseSortedMap.get(keysArray[i]));
                }
*/
                int counter = 0;
                String auth1 = "";
                String auth2 = "";
                String auth3 = "";
                String auth4 = "";
                String auth5 = "";
                String auth6 = "";
                String auth7 = "";
                String auth8 = "";
                String auth9 = "";
                String auth10 = "";
                for (Map.Entry<String, Integer> pair: reverseSortedMap.entrySet()) {
                    counter++;
                    if(counter == 1) {
                        auth1 = pair.getKey() + " - " + pair.getValue();
                        System.out.println(auth1);
                    } else if(counter == 2) {
                        auth2 = pair.getKey() + " - " + pair.getValue();
                    } else if(counter == 3) {
                        auth3 = pair.getKey() + " - " + pair.getValue();
                    } else if(counter == 4) {
                        auth4 = pair.getKey() + " - " + pair.getValue();
                    } else if(counter == 5) {
                        auth5 = pair.getKey() + " - " + pair.getValue();
                    } else if(counter == 6) {
                        auth6 = pair.getKey() + " - " + pair.getValue();
                    } else if(counter == 7) {
                        auth7 = pair.getKey() + " - " + pair.getValue();
                    } else if(counter == 8) {
                        auth8 = pair.getKey() + " - " + pair.getValue();
                    } else if(counter == 9) {
                        auth9 = pair.getKey() + " - " + pair.getValue();
                    } else if(counter == 10) {
                        auth10 = pair.getKey() + " - " + pair.getValue();
                    }

                    //System.out.format("key: %s, value: %d%n", pair.getKey(), pair.getValue());
                }

                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Top 10 Wall Checkers");
                builder.addField("1. " + auth1, "", false);
                builder.addField("2. "+ auth2, "", false);
                builder.addField("3. "+ auth3, "", false);
                builder.addField("4. "+ auth4, "", false);
                builder.addField("5. "+ auth5, "", false);
                builder.addField("6. "+ auth6, "", false);
                builder.addField("7. "+ auth7, "", false);
                builder.addField("8. "+ auth8, "", false);
                builder.addField("9. "+ auth9, "", false);
                builder.addField("10. "+ auth10, "", false);
                builder.setColor(Color.RED);
                event.getChannel().sendMessage(builder.build()).queue();

            }
        }


        if(args[0].equalsIgnoreCase(Info.PREFIX + "whitelistshow")) {
            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    String[] args1 = cursor.next().toJson().split(" ");
                    if(args1[13].equals("false")) {
                        System.out.println(args1[10] + " is false");
                    } else {
                        message += args1[10];
                    }
                    /*
                    String[] args1 = cursor.next().toJson().split(" ");
                    System.out.println(args1[10]);
                    message += args1[10];
                     */


                }
            } finally {
                cursor.close();

                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Whitelisted Users");
                builder.setColor(Color.CYAN);
                builder.setDescription("IGN: " + message);
                event.getChannel().sendMessage(builder.build()).queue();
                message = "";


            }

        }


        if(event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            if(args[0].equalsIgnoreCase(Info.PREFIX + "whitelistadd")) {
                if (args.length <= 1) {
                    sendErrorMessage(event.getChannel(), event.getMember());
                } else {
                    //whitelist add logic
                    Document found = (Document) collection.find(new Document("ign", args[1])).first();
                    if(found != null) {
                        Boolean access = found.getBoolean("access");
                        if(access) {
                            event.getChannel().sendMessage("User is already added to the whitelist!").queue();
                        } else if(!access) {
                            Bson updatedvalue = new Document("access", true);
                            Bson updateoperation = new Document("$set", updatedvalue);
                            collection.updateOne(found, updateoperation);
                            EmbedBuilder builder = new EmbedBuilder();
                            builder.setTitle(args[1] + " added to the wall check whitelist!");
                            builder.setColor(Color.GREEN);
                            event.getChannel().sendMessage(builder.build()).queue();
                        }
                    } else {
                        Document document = new Document("ign", args[1]);
                        document.append("access", true);
                        collection.insertOne(document);
                        EmbedBuilder builder = new EmbedBuilder();
                        builder.setTitle(args[1] + " added to the wall check whitelist!");
                        builder.setColor(Color.GREEN);
                        event.getChannel().sendMessage(builder.build()).queue();
                    }
                }
            }

            //------------------------------------------------------------------------------

            if(args[0].equalsIgnoreCase(Info.PREFIX + "whitelistremove")) {
                if (args.length <= 1) {
                    sendErrorMessage2(event.getChannel(), event.getMember());
                } else {
                    //logic for whitelistaremove
                    Document found = (Document) collection.find(new Document("ign", args[1])).first();

                    if(found != null) {
                        Boolean access = found.getBoolean("access");
                        if(access) {
                            System.out.println("Found user");
                            Bson updatedvalue = new Document("access", false);
                            Bson updateoperation = new Document("$set", updatedvalue);
                            collection.updateOne(found, updateoperation);
                            EmbedBuilder builder = new EmbedBuilder();
                            builder.setTitle(args[1] + " removed from the wall check whitelist!");
                            builder.setColor(Color.RED);
                            event.getChannel().sendMessage(builder.build()).queue();
                        } else if(!access) {
                            event.getChannel().sendMessage("User has already been removed from the whitelist!").queue();
                        }

                    } else {
                        event.getChannel().sendMessage("User not found in database!").queue();
                    }
                }
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
                                readFile(file, 0L, event.getGuild().getTextChannelById("477445977851035649"));
                                while (true) {

                                    if (fileLength < file.length()) {
                                        readFile(file, fileLength, event.getGuild().getTextChannelById("477445977851035649"));
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
                setTimer(event.getGuild().getTextChannelById("477445977851035649"), "Setup Mode");
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
            if(line.contains(".clear")) {
                Document found = (Document) collection.find(new Document("ign", args[1])).first();
                if(found != null) {
                    Boolean access = found.getBoolean("access");
                    if(access) {


                        //leaderboard incre
                        Document found1 = (Document) coll.find(new Document("id", args[1])).first();

                        if(found1 != null) {
                            Bson updatedvalue = new Document("checks", 1);
                            Bson updateoperation = new Document("$inc", updatedvalue);
                            coll.updateOne(found1, updateoperation);
                            found2 = found1.getInteger("checks");
                            found2 = found2 + 1;
                            checkedEmbed(channel, args[1], found2);
                        } else {
                            Document document = new Document("id", args[1]);
                            document.append("checks", 1);
                            coll.insertOne(document);

                            checkedEmbed(channel, args[1], 1);
                        }
                        //leaderboard incre


                        //clear logic





                        update(channel, args[1]);
                    } else if(!access) {
                        System.out.println(args[1] + " attempted to check walls.");
                    }
                }

            } else if(line.contains(".weewoo")){
                Document found = (Document) collection.find(new Document("ign", args[1])).first();
                if(found != null) {
                    Boolean access = found.getBoolean("access");
                    if(access) {
                        //weewoo logic
                        raidEmbed(channel, args[1]);
                        update(channel, args[1]);
                    } else if(!access) {
                        System.out.println(args[1] + " attempted to weeewoo walls");
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
                    } else if(counter == 3) {
                        checkEmbed3(channel, IGN);
                    } else if(counter == 4) {
                        checkEmbed4(channel, IGN);
                    } else if(counter == 5) {
                        checkEmbed5(channel, IGN);
                    } else if(counter >= 6) {
                        checkEmbed6(channel, IGN);
                    }

                }
                count = false;
            }
        };
        timer = new Timer();
        timer.schedule(timerTask1, 100, 300000); //600k = 10mins
    }


    public void checkEmbed(TextChannel channel, String IGN) { //TELLS USERS TO CHECK WALLS
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("Walls HAVE NOT been checked for 5 minutes!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.CYAN);
        builder.setAuthor("Abzya Wall Checks");
        builder.setDescription("**Check walls ASAP** Walls havent been checked for 5 minutes! Do ``.clear`` ingame if walls are clear!! Do .weewoo if we are getting raided!");
        builder.setFooter("Time: " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
        channel.sendMessage("@everyone @everyone @everyone CHECK NOW").queue();
    }
    public void checkEmbed2(TextChannel channel, String IGN) { //TELLS USERS TO CHECK WALLS
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("Walls HAVE NOT been checked for 10 minutes!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.YELLOW);
        builder.setAuthor("Abzya Wall Checks");
        builder.setDescription("**Check walls ASAP** Walls haven't been checked for ```10 minutes``` Do ``.clear`` ingame if walls are clear!! Do .weewoo if we are getting raided!");
        builder.setFooter("Time: " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
        channel.sendMessage("@everyone @everyone @everyone CHECK NOW").queue();
    }
    public void checkEmbed3(TextChannel channel, String IGN) { //TELLS USERS TO CHECK WALLS
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("Walls HAVE NOT been checked for 15 minutes!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.ORANGE);
        builder.setAuthor("Abzya Wall Checks");
        builder.setDescription("**Check walls ASAP** Walls haven't been checked for ```15 minutes``` Do ``.clear`` ingame if walls are clear!! Do .weewoo if we are getting raided!");
        builder.setFooter("Time: " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
        channel.sendMessage("@everyone @everyone @everyone CHECK NOW").queue();
    }
    public void checkEmbed4(TextChannel channel, String IGN) { //TELLS USERS TO CHECK WALLS
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("Walls HAVE NOT been checked for 20 minutes!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.RED);
        builder.setAuthor("Abzya Wall Checks");
        builder.setDescription("**Check walls ASAP** Walls haven't been checked for ```20 minutes``` Do ``.clear`` ingame if walls are clear!! Do .weewoo if we are getting raided!");
        builder.setFooter("Time: " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
        channel.sendMessage("@everyone @everyone @everyone CHECK NOW").queue();
    }
    public void checkEmbed5(TextChannel channel, String IGN) { //TELLS USERS TO CHECK WALLS
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("Walls HAVE NOT been checked for 25 minutes!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.RED);
        builder.setAuthor("Abzya Wall Checks");
        builder.setDescription("**Check walls ASAP** Walls haven't been checked for ```25 minutes``` Do ``.clear`` ingame if walls are clear!! Do .weewoo if we are getting raided!");
        builder.setFooter("Time: " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
        channel.sendMessage("@everyone @everyone @everyone CHECK NOW").queue();
    }
    public void checkEmbed6(TextChannel channel, String IGN) { //TELLS USERS TO CHECK WALLS
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("Walls HAVE NOT been checked for 30+ minutes!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.RED);
        builder.setAuthor("Abzya Wall Checks");
        builder.setDescription("**Check walls ASAP** Walls haven't been checked for ```30+ minutes``` Do ``.clear`` ingame if walls are clear!! Do .weewoo if we are getting raided!");
        builder.setFooter("Time: " + date, "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png");
        channel.sendMessage(builder.build()).queue();
        channel.sendMessage("@everyone @everyone @everyone CHECK NOW").queue();
    }


    public void checkedEmbed(TextChannel channel, String IGN, Integer score) { //TELLS USERS WALLS HAVE BEEN CHECKED
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("Walls have been checked!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.CYAN);
        builder.setAuthor("Abzya Wall Checks");
        builder.setDescription("Walls have been checked using .clear ingame");
        builder.addField("Checked by(IGN): ", IGN, true);
        if(score == 1234567) {
            builder.addField("Score: ", "N/A Checked In Discord", true);
        } else {
            builder.addField("Score: ", score.toString(), true);
        }
        builder.setFooter("Checked at: " + stf.format(date), "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png" );
        channel.sendMessage(builder.build()).queue();
    }

    public void raidEmbed(TextChannel channel, String IGN) { //TELLS USERS WE ARE GETTING RAIDED
        try {
            PrintWriter writer = new PrintWriter(fileName2);
            writer.print("");
            writer.print("WE ARE GETTING RAIDED : ALARM SOUNDED!!!!!!!");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        EmbedBuilder builder = new EmbedBuilder();
        Date date = new Date(System.currentTimeMillis());
        builder.setColor(Color.red);
        builder.setAuthor("Abzya Raid Alerts!");
        builder.setDescription("We are being raided! @everyone We are being raided! GET THE FUCK ON AND DEFEND!");
        builder.addField("Alarm set by(IGN): ", IGN, false);
        builder.setFooter("Time: " + stf.format(date), "https://cdn.discordapp.com/attachments/496480932069507082/496486152702918669/Abzya.png" );
        int x = 1;
        channel.sendMessage(builder.build()).queue();
        while (x < 5) {
            x++;
            channel.sendMessage("@everyone get on we are being raided!").queue();
        }
    }

    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.red);
        builder.setDescription("{} = required");
        builder.addField("Proper user: .whitelistadd {@user}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }

    public void sendErrorMessage2(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.red);
        builder.setDescription("{} = required");
        builder.addField("Proper user: .whitelistremove {@user}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }





}
