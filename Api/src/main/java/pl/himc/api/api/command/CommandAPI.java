package pl.himc.api.api.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class CommandAPI extends Command {

    private String name;
    private String usage;
    private String permission;
    private List<String> aliases;

    public CommandAPI(String name, String permission, String usage, String... aliases) {
        super(name, "", usage, Arrays.asList(aliases));
        this.name = name;
        this.usage = usage;
        this.permission = permission;
        this.aliases = Arrays.asList(aliases);
    }

    public boolean execute(CommandSender sender, String command, String[] args) {
        if (this.getPermission() != null && !sender.hasPermission(this.getPermission())) {
            return ChatUtil.sendMessage(sender, PluginApi.getApi().getPluginMessages().commandPlayerNotHasPermission.replace("{PERM}", this.permission));
        }
        return this.onCommand(sender, args);
    }

    public abstract boolean onCommand(CommandSender s, String[] args);

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUsage() {
        return this.usage;
    }

    public String getPermission() {
        return this.permission;
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    public void register() {
        try {
            if (this.getName() == null) {
                return;
            }
            Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap cmap = (CommandMap) f.get(Bukkit.getServer());
            cmap.register(this.getName(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}