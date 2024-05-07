package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class NickCommand extends CommandAPI {

    public NickCommand(String permission) {
        super("nick", permission,"/nick <Gracz> <Nowy nick>");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length == 2 && !(s instanceof Player)){
            User user = PluginCore.getCore().getUserManager().getUser(args[0]);
            if(user == null){
                return ChatUtil.sendMessage(s, messageConfiguration.playerIsNotExists);
            }
            if(args[1].equalsIgnoreCase("-") || args[1].equalsIgnoreCase(user.getName())){
                user.removeFakeName();
                ChatUtil.sendMessage(s, messageConfiguration.fakenameUnSetOther.replace("{PLAYER}", user.getName()));
            }else{
                user.setFakeName(args[1]);
                ChatUtil.sendMessage(s, messageConfiguration.fakenameSetOther.replace("{PLAYER}", user.getName()).replace("{NICK}", args[1]));
            }
            return false;
        }
        Player p = (Player)s;
        if(args.length == 0){
            ChatUtil.sendUsage(p, this.getUsage());
            return false;
        }else if(args.length == 1){
            if(args[0].length() > pluginConfiguration.fakeNameLenght){
                return ChatUtil.sendMessage(p, messageConfiguration.fakenameTooLength.replace("{LENGHT}", Integer.toString(pluginConfiguration.fakeNameLenght)));
            }
            if(args[0].toLowerCase().contains("&k")){
                return ChatUtil.sendMessage(p, messageConfiguration.argnameOnlyLettersAndNumers);
            }
            User user = PluginCore.getCore().getUserManager().getUser(p);
            if(args[0].equalsIgnoreCase("-") || args[0].equalsIgnoreCase(user.getName())){
                user.removeFakeName();
                ChatUtil.sendMessage(p, messageConfiguration.fakenameUnSet);
            }else{
                user.setFakeName(args[0]);
                ChatUtil.sendMessage(p, messageConfiguration.fakenameSet.replace("{NICK}", user.getFakeName()));
            }
        }else if(args.length == 2){
            if(!p.hasPermission(this.getPermission() + ".others")){
                ChatUtil.sendMessage(p, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
            }
            if(args[1].length() > pluginConfiguration.fakeNameLenght){
                return ChatUtil.sendMessage(p, messageConfiguration.fakenameTooLength.replace("{LENGHT}", Integer.toString(pluginConfiguration.fakeNameLenght)));
            }
            if(args[1].contains("&k")){
                return ChatUtil.sendMessage(p, messageConfiguration.argnameOnlyLettersAndNumers);
            }
            User user = PluginCore.getCore().getUserManager().getUser(args[0]);
            if(user == null){
                return ChatUtil.sendMessage(p, messageConfiguration.playerIsNotExists);
            }
            if(args[1].equalsIgnoreCase("-") || args[1].equalsIgnoreCase(user.getName())){
                user.removeFakeName();
                ChatUtil.sendMessage(p, messageConfiguration.fakenameUnSetOther.replace("{PLAYER}", user.getName()));
            }else{
                user.setFakeName(args[1]);
                ChatUtil.sendMessage(p, messageConfiguration.fakenameSetOther.replace("{PLAYER}", user.getName()).replace("{NICK}", args[1]));
            }
        }else{
            ChatUtil.sendUsage(p, this.getUsage());
        }
        return false;
    }
}