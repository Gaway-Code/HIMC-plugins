package pl.himc.core.listener.player;

import pl.himc.core.api.PluginCore;
import pl.himc.core.manager.AntyReklamaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.himc.core.base.mute.Mute;
import pl.himc.core.base.user.User;
import pl.himc.core.command.admin.SprawdzCommand;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.PluginApi;
import pl.himc.api.managers.PermissionsManager;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;

public final class PlayerChat implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        if (e.getMessage().startsWith("!!")) {
            GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
            if (user.hasGuild()) {
                e.setCancelled(true);
                Guild guild = user.getGuild();
                String message = PluginGuild.getPlugin().getPluginConfiguration().chatGuildAllianceFormat;
                message = message.replace("{NAME}", player.getName());
                message = message.replace("{TAG}", guild.getTag());
                message = message.replace("{MESSAGE}", e.getMessage().replaceFirst("!!", ""));
                guild.sendMembers(message);
                guild.sendAlliances(message);
                return;
            }
        } else if (e.getMessage().startsWith("!")) {
            GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
            if (user.hasGuild()) {
                e.setCancelled(true);
                Guild guild = user.getGuild();
                String message = PluginGuild.getPlugin().getPluginConfiguration().chatGuildMemberFormat;
                message = message.replace("{NAME}", player.getName());
                message = message.replace("{MESSAGE}", e.getMessage().replaceFirst("!", ""));
                guild.sendMembers(message);
                return;
            }
        }

        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(PluginCore.getCore().getMuteManager().getMute(player.getName()) != null){
            Mute mute = PluginCore.getCore().getMuteManager().getMute(player.getName());
            if(mute.isPerm()){
                ChatUtil.sendMessage(player, messageConfiguration.permMuteDesign.replace("{ADMIN}", mute.getAdmin()).replace("{REASON}", mute.getReason()));
            }else{
                if(!mute.isExpire()){
                    ChatUtil.sendMessage(player, messageConfiguration.tempMuteDesign.replace("{ADMIN}", mute.getAdmin()).replace("{DATA}", TimeUtil.getDate(mute.getTime())).replace("{REASON}", mute.getReason()));
                }
            }
            e.setCancelled(true);
            return;
        }

        User u = PluginCore.getCore().getUserManager().getUser(player);
        if(!u.isWriteChat()){
            e.setCancelled(true);
            ChatUtil.sendMessage(player, messageConfiguration.chatCooldown.replace("{TIME}", TimeUtil.getDuration(u.getWriteChat() - System.currentTimeMillis())));
            return;
        }
        if(!player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.chat")){
            if(!pluginConfiguration.enableChat){
                e.setCancelled(true);
                ChatUtil.sendMessage(player, messageConfiguration.chatIsDisable);
                return;
            }
            if(u.getLvl() < pluginConfiguration.chatWriteLevel){
                e.setCancelled(true);
                ChatUtil.sendMessage(player, messageConfiguration.errorChatLevel.replace("{LEVEL}", Integer.toString(pluginConfiguration.chatWriteLevel)));
                return;
            }
            AntyReklamaManager.checkMessage(e);
            u.setWriteChat(System.currentTimeMillis() + pluginConfiguration.cfgChatCooldown);
        }
        if(SprawdzCommand.isSuspect(player)){
            String format = messageConfiguration.chatFormatSuspect;
            format = format.replace("{PLAYER}", e.getPlayer().getName());
            format = format.replace("{MESSAGE}", "%2$s");

            e.setFormat(ChatUtil.fixColor(format));
            return;
        }
        String format = player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".admin.rank") ? messageConfiguration.chatFormatAdmin : messageConfiguration.chatFormatPlayer;
        format = format.replace("{NAME}", u.isFakeName() ? u.getFakeName() : player.getName());
        format = format.replace("{MESSAGE}", "%2$s");
        format = format.replace("{PREFIX}", PermissionsManager.getInstance().getPrefix(player) != null ? PermissionsManager.getInstance().getPrefix(player) : "");
        format = format.replace("{SUFFIX}", PermissionsManager.getInstance().getSuffix(player) != null ? PermissionsManager.getInstance().getSuffix(player) : "");
        if(e.getMessage().toLowerCase().contains("lagi")){
            e.setMessage(e.getMessage().toLowerCase().replace("lagi", "&fKocham was! &4❤ &f"));
        }

        GuildUser gUser = PluginGuild.getPlugin().getUserManager().getUser(player);
        format = format.replace("{TAG}", gUser.hasGuild() ? PluginGuild.getPlugin().getPluginConfiguration().chatTagFormat.replace("{TAG}", gUser.getGuild().getTag()) : "");
        format = format.replace("{POINTS}", PluginGuild.getPlugin().getPluginConfiguration().chatPointsFormat.replace("{POINTS}", Integer.toString(gUser.getRank().getPoints())));
        if(player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".chat.color.special")){
            e.setMessage(ChatUtil.fixColor(e.getMessage()));
        }else if(player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".chat.color")){
            e.setMessage(ChatUtil.fixColor(e.getMessage().replace("&l", "").replace("&k", "").replace("&n", "").replace("&m", "").replace("&o", "")));
        }
        e.setFormat(ChatUtil.fixColor(format));
    }
}
