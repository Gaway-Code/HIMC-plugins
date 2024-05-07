package pl.himc.core.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.api.api.PluginApi;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.managers.PermissionsManager;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;

public final class TrollMessageCommand extends PlayerCommand {

    public TrollMessageCommand(String permission) {
        super("trollmsg", permission,"/trollmsg <Gracz> <Wiadomosc>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        Player fakePlayer = Bukkit.getPlayer(args[0]);

        if(!fakePlayer.isOnline()){
            return ChatUtil.sendMessage(player, "&cGracz musi byc online!");
        }
        String format = fakePlayer.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".admin.rank") ? messageConfiguration.chatFormatAdmin : messageConfiguration.chatFormatPlayer;

        User fakeUser = PluginCore.getCore().getUserManager().getUser(args[0]);
        if(fakeUser == null){
            return ChatUtil.sendMessage(player, "&cNie znaleziono gracza!");
        }

        format = format.replace("{NAME}", fakeUser.isFakeName() ? fakeUser.getFakeName() : fakeUser.getName());
        format = format.replace("{MESSAGE}", StringUtil.stringBuilder(args, 1));
        format = format.replace("{PREFIX}", PermissionsManager.getInstance().getPrefix(fakePlayer) != null ? PermissionsManager.getInstance().getPrefix(fakePlayer) : "");
        format = format.replace("{SUFFIX}", PermissionsManager.getInstance().getSuffix(fakePlayer) != null ? PermissionsManager.getInstance().getSuffix(fakePlayer) : "");

        GuildUser gFakeUser = PluginGuild.getPlugin().getUserManager().getUser(fakePlayer);
        format = format.replace("{TAG}", gFakeUser.hasGuild() ? PluginGuild.getPlugin().getPluginConfiguration().chatTagFormat.replace("{TAG}", gFakeUser.getGuild().getTag()) : "");
        format = format.replace("{POINTS}", PluginGuild.getPlugin().getPluginConfiguration().chatPointsFormat.replace("{POINTS}", Integer.toString(gFakeUser.getRank().getPoints())));

        return ChatUtil.sendBroadcast(format);
    }
}
