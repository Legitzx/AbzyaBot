package me.legitzx.bot.modTools;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public class PurgeSystem extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e){

        if(e.getMessage().getContentRaw().startsWith(".purge")) {
            if (e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                String[] argu = e.getMessage().getContentRaw().split(" ");
                if (e.getMessage().getContentRaw().equals(".purge")) {
                    System.out.println("Missing amount of messages to delete");
                    e.getChannel().sendMessage("You must type .clear <number>").queue();
                    return;
                }
                String delString = argu[1];
                int delNum = Integer.parseInt(delString) + 1;
                if (delNum < 100) {
                    List<Message> msgs = e.getTextChannel().getHistory().retrievePast(delNum).complete();
                    e.getTextChannel().deleteMessages(msgs).queue();
                    System.out.println("Deleted " + delString + " messages");
                } else {
                    e.getChannel().sendMessage("The maximum number is 99").queue();
                    return;
                }
            } else {
                e.getChannel().sendMessage("You dont have perms!").queue();
            }
        }
    }
}
