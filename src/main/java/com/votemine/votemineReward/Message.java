package com.votemine.votemineReward;

import co.aikar.commands.PaperCommandManager;
import co.aikar.locales.MessageKey;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {

    private static PaperCommandManager manager;
    private static Message instance = null;

    public static void init(PaperCommandManager manager){
        if (instance == null) {
            instance = new Message(manager);
        }
    }

    private Message(PaperCommandManager manager){
        this.manager = manager;
    }

    public static void send(String path, CommandSender sender, String... values){
        send(path,sender,false, values);
    }

    private static void send(String path, CommandSender sender, boolean addHeader, String... values){
        String msg =  manager.getLocales().getMessage(
                manager.getCommandIssuer(sender),
                MessageKey.of(path));
        for(int i = 0; i < values.length; i++){
            msg = msg.replace("{"+i+"}", values[i]);
        }
        if (addHeader){
            msg = ChatColor.GOLD+"Votemine "+ChatColor.GRAY+">> "+ChatColor.RESET+msg;
        }
        sender.sendMessage(msg);
    }
    public static void sendWithHeader(String path, CommandSender sender, String... values){
        send(path,sender,true, values);
    }
}
