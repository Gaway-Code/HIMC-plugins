package pl.himc.lobbycore.base.server;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.RandomUtil;
import pl.himc.lobbycore.LobbyPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerManager {

    private LobbyPlugin lobbyPlugin;
    private static List<Servers> serversList = new ArrayList<>();
    private static File file;
    private static YamlConfiguration yaml;

    public ServerManager(final LobbyPlugin plugin){
        this.lobbyPlugin = plugin;
    }

    public File getFile(){
        if(file == null){
            file = new File(LobbyPlugin.getPlugin().getDataFolder(), "servers.yml");
        }
        if(!file.exists()){
            LobbyPlugin.getPlugin().saveResource("servers.yml", false);
        }
        return file;
    }

    public YamlConfiguration getYaml(){
        if(yaml == null){
            yaml = YamlConfiguration.loadConfiguration(getFile());
        }
        return yaml;
    }

    public void getServersGui(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor(this.lobbyPlugin.getPluginConfig().serversInventoryName), 1);

        for(Servers server : serversList){
            int slot;
            if(server.isRandomslot()){
                slot = RandomUtil.getRandInt(0, 26);
            }else{
                slot = server.getSlot();
            }
            if(gui.getItem(slot) == null){
                gui.setItem(slot, new GuiItem(server.getItem().clone(), inventoryClickEvent -> connectToServer(player, server)));
            }
        }

        gui.open(player);
    }

    public void connectToServer(Player p, Servers server){
        p.closeInventory();
        ChatUtil.sendMessage(p,"&8&l%> &aLaczenie z &e" + server.getName() + "&a...");
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server.getServerconnector());
        } catch (IOException ignored) {}
        p.sendPluginMessage(LobbyPlugin.getPlugin(), "BungeeCord", b.toByteArray());
    }

    public void loadServers(){
        ConfigurationSection cs1 = getYaml().getConfigurationSection("servers");
        for(String s : cs1.getKeys(false)) {
            ConfigurationSection cs = cs1.getConfigurationSection(s);
            ItemStack is;

            Material material = Material.matchMaterial(cs.getString("item"));
            if(material == null){
                LobbyPlugin.getPlugin().getLog().error("Zla wartosc w " + s + " -> 'item'");
                continue;
            }

            is = new ItemStack(material);
            if(cs.getString("id") != null){
                is.setDurability((short)cs.getInt("id"));
            }

            ItemMeta im = is.getItemMeta();
            if(cs.getString("name") != null){
                im.setDisplayName(ChatUtil.fixColor(cs.getString("name")));
            }
            if(cs.getStringList("lore") != null){
                im.setLore(ChatUtil.fixColor(cs.getStringList("lore")));
            }

            Servers server = new Servers(s);
            if(cs.getString("serverconnector") != null){
                server.setServerconnector(cs.getString("serverconnector"));
            }
            server.setRandomslot(cs.getBoolean("randomslot"));
            if(!cs.getBoolean("randomslot")){
                server.setSlot(cs.getInt("slot"));
            }

            is.setItemMeta(im);
            server.setItem(is);
            serversList.add(server);
        }
    }

    public void reloadServers(){
        serversList.clear();
        file = null;
        yaml = null;
        loadServers();
    }
}
