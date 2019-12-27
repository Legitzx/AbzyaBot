package me.legitzx.bot.mclisteners;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import me.legitzx.bot.Info;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.awt.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class BankListener extends ListenerAdapter {
    public LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

    public HashMap<String, Integer> checks = new HashMap<String, Integer>();

    public int count = 1;
    public static String str;
    public static int counter = 0;
    public String fileName1 = "C:\\Users\\lelspam01\\Desktop\\AbzyaBot\\consoleclient\\Console\\deposit.txt"; //C:\Users\legitzx\Desktop\AbzyaBot\consoleclient\Console\serverchat.txt

    private static String uri = "mongodb+srv://admin:theluch55@royalbot-fbwir.mongodb.net/admin";
    private static MongoClientURI clientURI = new MongoClientURI(uri);
    private static MongoClient mongoClient = new MongoClient(clientURI);

    private static MongoDatabase mongoDatabase = mongoClient.getDatabase("AbzyaBot");
    private static MongoCollection collection = mongoDatabase.getCollection("deposit");


    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (event.getMessage().getAuthor().isBot()) return;

        if (args[0].equalsIgnoreCase(Info.PREFIX + "setup1")) {
            try {
                PrintWriter writer = new PrintWriter(fileName1);
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //readTextFileUsingScanner(fileName, event.getGuild().getTextChannelById("497238046031216651"));
            Runnable myRunnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        File file = new File(fileName1);
                        System.out.println(file.getAbsolutePath());
                        if (file.exists() && file.canRead()) {
                            long fileLength = file.length();
                            readFile(file, 0L, event.getGuild().getTextChannelById("497238046031216651"));
                            while (true) {

                                if (fileLength < file.length()) {
                                    readFile(file, fileLength, event.getGuild().getTextChannelById("497238046031216651"));
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


        if(args[0].equalsIgnoreCase(Info.PREFIX + "dtop")) {
            MongoCursor<Document> cursor = collection.find().iterator();
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
                        auth1 = pair.getKey() + " - " + "$" + pair.getValue();
                        System.out.println(auth1);
                    } else if(counter == 2) {
                        auth2 = pair.getKey() + " - " + "$" + pair.getValue();
                    } else if(counter == 3) {
                        auth3 = pair.getKey() + " - " + "$" + pair.getValue();
                    } else if(counter == 4) {
                        auth4 = pair.getKey() + " - " + "$" + pair.getValue();
                    } else if(counter == 5) {
                        auth5 = pair.getKey() + " - " + "$" + pair.getValue();
                    } else if(counter == 6) {
                        auth6 = pair.getKey() + " - " + "$" + pair.getValue();
                    } else if(counter == 7) {
                        auth7 = pair.getKey() + " - " + "$" + pair.getValue();
                    } else if(counter == 8) {
                        auth8 = pair.getKey() + " - " + "$" + pair.getValue();
                    } else if(counter == 9) {
                        auth9 = pair.getKey() + " - " + "$" + pair.getValue();
                    } else if(counter == 10) {
                        auth10 = pair.getKey() + " - " + "$" + pair.getValue();
                    }

                    //System.out.format("key: %s, value: %d%n", pair.getKey(), pair.getValue());
                }

                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Deposit Scoreboard");
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
                builder.setColor(Color.GREEN);
                event.getChannel().sendMessage(builder.build()).queue();

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
            //logic so you can add it to scoreboard
            String[] args1 = line.split(" ");


            String value = args1[args1.length -4];
            String value2 = value.replace(".", "").replace("$", "");

            //money = value2
            String name = "";
            int money = 0;
            int valueGet = 0;
            int total = 0;
            if(args1[1].contains("gave")) {
                name = args1[0];
            } else if(args1[2].contains("gave")) {
                name = args1[1];
            } else if(args1[3].contains("gave")) {
                name = args1[2];
            } else if(args1[4].contains("gave")) {
                name = args1[3];
            }
            String updateName = name;

            if(name.contains("-")) {
                updateName = name.replace("-", "");
            } else if(name.contains("+")) {
                updateName = name.replace("+", "");
            } else if(name.contains("*")) {
                updateName = name.replace("*", "");
            } else if(name.contains("**")) {
                updateName = name.replace("**", "");
            } else if(name.contains("***")) {
                updateName = name.replace("***", "");
            }


            System.out.println(updateName + " deposited: " + value2);
            money = Integer.parseInt(value2);

            //money = amount of money deposited
            //updateName = IGN


            Document found = (Document) collection.find(new Document("ign", updateName)).first();

            if(found != null) {

                System.out.println("Found user");
                valueGet = found.getInteger("amount");
                total = valueGet + money;
                Bson updatedvalue = new Document("amount", total);
                Bson updateoperation = new Document("$set", updatedvalue);
                collection.updateOne(found, updateoperation);
                System.out.println("User Updated!");
            } else {
                System.out.println("didnt find user, creating user now");
                Document document = new Document("ign", updateName);
                document.append("amount", money);

                collection.insertOne(document);
            }



            //end adding to scoreboard
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
import java.io.*;
import java.io.File;
import java.io.IOException;


public class BankListener extends ListenerAdapter {
    public static String str;
    public static int counter = 0;
    public String fileName = "C:\\Users\\Luciano\\Desktop\\Programming\\JAVA ROOT\\ClientsDiscordBots\\AbzyaBot\\consoleclient\\Console\\deposit.txt"; // C:\Users\legitzx\Desktop\AbzyaBot\consoleclient\Console\deposit.txt

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (event.getMessage().getAuthor().isBot()) return;

        if (args[0].equalsIgnoreCase(Info.PREFIX + "setup")) {
            //readTextFileUsingScanner(fileName, event.getGuild().getTextChannelById("497238046031216651"));
            try {
                File file = new File(fileName);
                System.out.println(file.getAbsolutePath());
                if (file.exists() && file.canRead()) {
                    long fileLength = file.length();
                    readFile(file, 0L, event.getGuild().getTextChannelById("497238046031216651"));
                    while (true) {

                        if (fileLength < file.length()) {
                            readFile(file, fileLength, event.getGuild().getTextChannelById("497238046031216651"));
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
            System.out.println(line);
            channel.sendMessage("`" + line + "`").queue();
        }
        in.close();
    }


}
 */