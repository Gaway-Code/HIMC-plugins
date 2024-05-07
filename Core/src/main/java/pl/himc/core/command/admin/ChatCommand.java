package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;
import pl.himc.api.api.command.CommandAPI;

public final class ChatCommand extends CommandAPI {

    public ChatCommand(String permission) {
        super("chat", permission, "\n /chat on\n" +
                "/chat off\n" +
                "/chat clear lub /chat cc\n" +
                "/chat cooldown <czas np. 5s - 5 sekund>\n" +
                "/chat level lub /chat lvl <poziom kopania>\n" +
                "/chat cenzura <add, remove, list>\n" +
                "/chat antyreklama <add, remove, list>","c", "czat");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(sender, this.getUsage());
        }
        if(args.length == 2){
            switch(args[0]){
                case "cooldown": {
                    long time = TimeUtil.getTimeWithString(args[1]);
                    pluginConfiguration.chatCooldown = args[1];
                    pluginConfiguration.cfgChatCooldown = time;
                    PluginCore.getCore().savePluginConfiguration();
                    return ChatUtil.sendBroadcast(messageConfiguration.cooldownChatBroadcast.replace("{COOLDOWN}", TimeUtil.getDuration(TimeUtil.getTimeWithString(args[1]))).replace("{PLAYER}", sender.getName()));
                }
                case "lvl":
                case "level": {
                    if(!IntegerUtil.isInteger(args[1])){
                        return ChatUtil.sendMessage(sender, messageConfiguration.invalidInteger);
                    }
                    pluginConfiguration.chatWriteLevel = Integer.parseInt(args[1]);
                    PluginCore.getCore().savePluginConfiguration();

                    return ChatUtil.sendBroadcast(messageConfiguration.levelChatBroadcast.replace("{LEVEL}", args[1]).replace("{PLAYER}", sender.getName()));
                }
                case "cenzura": {
                    if (args[1].equalsIgnoreCase("list")) {
                        ChatUtil.sendMessage(sender, "&c&oLista przeklenstw:");
                        pluginConfiguration.curseWords.forEach(curse -> ChatUtil.sendMessage(sender, curse));
                        return false;
                    }
                    return ChatUtil.sendUsage(sender, this.getUsage());
                }
                case "antyreklama": {
                    if (args[1].equalsIgnoreCase("list")) {
                        ChatUtil.sendMessage(sender, "&c&oLista zbanowanych slow:");
                        pluginConfiguration.reklamaBannedWords.forEach(curse -> ChatUtil.sendMessage(sender, curse));
                        return false;
                    }
                    return ChatUtil.sendUsage(sender, this.getUsage());
                }
                default: {
                    return ChatUtil.sendUsage(sender, this.getUsage());
                }
            }
        }else if(args.length == 1){
            switch (args[0]){
                case "on": {
                    if(pluginConfiguration.enableChat){
                        return ChatUtil.sendMessage(sender, messageConfiguration.chatIsAlreadyEnable);
                    }
                    pluginConfiguration.enableChat = true;
                    PluginCore.getCore().savePluginConfiguration();
                    return ChatUtil.sendBroadcast(messageConfiguration.enableChatBroadcast.replace("{PLAYER}", sender.getName()));
                }
                case "off": {
                    if(!pluginConfiguration.enableChat){
                        return ChatUtil.sendMessage(sender, messageConfiguration.chatIsAlreadyDisable);
                    }
                    pluginConfiguration.enableChat = false;
                    PluginCore.getCore().savePluginConfiguration();
                    return ChatUtil.sendBroadcast(messageConfiguration.disableChatBroadcast.replace("{PLAYER}", sender.getName()));
                }
                case "cc":
                case "clear" : {
                    Bukkit.getOnlinePlayers().forEach(players -> {
                        for (int i = 0; i < 100; ++i) {
                            players.sendMessage("       ");
                        }
                    });
                    return ChatUtil.sendBroadcast(messageConfiguration.clearChatBroadcast.replace("{PLAYER}", sender.getName()));
                }
                default: {
                    return ChatUtil.sendUsage(sender, this.getUsage());
                }
            }
        }else if(args.length == 3){
            switch (args[0]){
                case "cenzura": {
                    switch (args[1]){
                        case "add": {
                            if(pluginConfiguration.curseWords.contains(args[2].toLowerCase())){
                                return ChatUtil.sendMessage(sender, "&c&oTo slowo juz zostalo dodane!");
                            }
                            pluginConfiguration.curseWords.add(args[2].toLowerCase());
                            PluginCore.getCore().savePluginConfiguration();
                            return ChatUtil.sendMessage(sender, "&a&oSlowo zostalo dodane!");
                        }
                        case "remove": {
                            if(!pluginConfiguration.curseWords.contains(args[2].toLowerCase())){
                                return ChatUtil.sendMessage(sender, "&c&oTego slowa nie ma na liscie!");
                            }
                            pluginConfiguration.curseWords.remove(args[2].toLowerCase());
                            PluginCore.getCore().savePluginConfiguration();
                            return ChatUtil.sendMessage(sender, "&a&oSlowo zostalo usuniete!");
                        } default: {
                            return ChatUtil.sendUsage(sender, this.getUsage());
                        }
                    }
                }
                case "antyreklama": {
                    switch (args[1]){
                        case "add": {
                            if(pluginConfiguration.reklamaBannedWords.contains(args[2].toLowerCase())){
                                return ChatUtil.sendMessage(sender, "&c&oTo slowo juz zostalo dodane!");
                            }
                            pluginConfiguration.reklamaBannedWords.add(args[2].toLowerCase());
                            PluginCore.getCore().savePluginConfiguration();
                            return ChatUtil.sendMessage(sender, "&a&oSlowo zostalo dodane!");
                        }
                        case "remove": {
                            if(!pluginConfiguration.reklamaBannedWords.contains(args[2].toLowerCase())){
                                return ChatUtil.sendMessage(sender, "&c&oTego slowa nie ma na liscie!");
                            }
                            pluginConfiguration.reklamaBannedWords.remove(args[2].toLowerCase());
                            PluginCore.getCore().savePluginConfiguration();
                            return ChatUtil.sendMessage(sender, "&a&oSlowo zostalo usuniete!");
                        } default: {
                            return ChatUtil.sendUsage(sender, this.getUsage());
                        }
                    }
                }
            }
        }
        return false;
    }
}
