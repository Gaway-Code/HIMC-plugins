package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.api.utils.bukkit.item.ItemsUtil;

import java.util.Arrays;

public final class SchowekCommand extends PlayerCommand {

	public SchowekCommand(String permission) {
		super("schowek", permission,"/schowek", "depo", "depozyt");
	}

	@Override
	public boolean onCommand(Player player, String[] args) {
		open(player);
		return false;
	}

	private void open(Player player){
		PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
		User user = PluginCore.getCore().getUserManager().getUser(player);
		GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&eSchowek"), 1);
		gui.setCloseEvent(inventoryCloseEvent -> gui.unregister());
		GuiItem yellow = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)4).setName("&8*").getItem());

		gui.setItem(0, yellow);
		GuiItem kox = new GuiItem(new ItemBuilder(Material.GOLDEN_APPLE, 1, (short)1).setName("&8* &6&lKoxy &8*").setLore(Arrays.asList(
				" &8&l%> &7Limit: &a" + pluginConfiguration.limitKoxPlayer + " &8(&3E&6VIP: " + pluginConfiguration.limitKoxEVip + "&8)",
				" &8&l%> &7Posiadasz w schowku: &a" + user.getKox(),
				" &8&l%> &7Kliknij &a&nPPM&7 aby wyplacic &a&nlimit",
				" &8&l%> &7Kliknij &a&nLPM&7 aby wyplacic &c&nwszystko"
		)).getItem(), this::event);
		gui.setItem(1, kox);
		gui.setItem(2, yellow);
		GuiItem refil = new GuiItem(new ItemBuilder(Material.GOLDEN_APPLE, 1).setName("&8* &e&lRefile &8*").setLore(Arrays.asList(
				" &8&l%> &7Limit: &a" + pluginConfiguration.limitRefilPlayer + " &8(&3E&6VIP: " + pluginConfiguration.limitRefilEVip + "&8)",
				" &8&l%> &7Posiadasz w schowku: &a" + user.getRefil(),
				" &8&l%> &7Kliknij &a&nPPM&7 aby wyplacic &a&nlimit",
				" &8&l%> &7Kliknij &a&nLPM&7 aby wyplacic &c&nwszystko"
		)).getItem(), this::event);
		gui.setItem(3, refil);
		gui.setItem(4, yellow);
		GuiItem perla = new GuiItem(new ItemBuilder(Material.ENDER_PEARL, 1).setName("&8* &9&lPerly &8*").setLore(Arrays.asList(
				" &8&l%> &7Limit: &a" + pluginConfiguration.limitPerlaPlayer + " &8(&3E&6VIP: " + pluginConfiguration.limitPerlaEVip + "&8)",
				" &8&l%> &7Posiadasz w schowku: &a" + user.getPerla(),
				" &8&l%> &7Kliknij &a&nPPM&7 aby wyplacic &a&nlimit",
				" &8&l%> &7Kliknij &a&nLPM&7 aby wyplacic &c&nwszystko"
		)).getItem(), this::event);
		gui.setItem(5, perla);
		gui.setItem(6, yellow);

		GuiItem all = new GuiItem(new ItemBuilder(Material.NETHER_STAR).setName("&8* &a&lWYPLAC WSZYSTKO &8*").setLore(Arrays.asList(
				" &8%> &7Wyplaca limity:",
				"    &8* &7Koxy: &a" + pluginConfiguration.limitKoxPlayer + " &8(&3E&6VIP: " + pluginConfiguration.limitKoxEVip + "&8)",
				"    &8* &7Refile: &a" + pluginConfiguration.limitRefilPlayer + " &8(&3E&6VIP: " + pluginConfiguration.limitRefilEVip + "&8)",
				"    &8* &7Perly: &a" + pluginConfiguration.limitPerlaPlayer + " &8(&3E&6VIP: " + pluginConfiguration.limitPerlaEVip + "&8)",
				" &8%> &a&nKliknij aby wyplacic limit"
		)).getItem(), this::event);
		gui.setItem(7, all);
		gui.setItem(8, yellow);
		gui.open(player);
	}

	private void event(InventoryClickEvent e){
		PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
		PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
		Player p = (Player) e.getWhoClicked();
		boolean evip = p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".limits.evip");
		ItemMeta im = e.getCurrentItem().getItemMeta();

		if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &a&lWYPLAC WSZYSTKO &8*"))){
			int limitkox = evip ? pluginConfiguration.limitKoxEVip : pluginConfiguration.limitKoxPlayer;
			int limitrefil = evip ? pluginConfiguration.limitRefilEVip : pluginConfiguration.limitRefilPlayer;
			int limitperla = evip ? pluginConfiguration.limitPerlaEVip : pluginConfiguration.limitPerlaPlayer;

			User user = PluginCore.getCore().getUserManager().getUser(p);
			if(user.getKox() < limitkox){
				ChatUtil.sendMessage(p, messageConfiguration.enoughKoxy.replace("{LIMIT}", Integer.toString(limitkox)));
			}else{
				user.removeKoxy(limitkox);
				ItemsUtil.giveItem(p, new ItemStack(Material.GOLDEN_APPLE, limitkox, (short)1));
			}

			if(user.getRefil() < limitrefil){
				ChatUtil.sendMessage(p, messageConfiguration.enoughRefile.replace("{LIMIT}", Integer.toString(limitrefil)));
			}else{
				user.removeRefile(limitrefil);
				ItemsUtil.giveItem(p, new ItemStack(Material.GOLDEN_APPLE, limitrefil));
			}

			if(user.getPerla() < limitperla){
				ChatUtil.sendMessage(p, messageConfiguration.enoughPerly.replace("{LIMIT}", Integer.toString(limitperla)));
			}else{
				user.removePerly(limitperla);
				ItemsUtil.giveItem(p, new ItemStack(Material.ENDER_PEARL, limitperla));
			}
			open(p);
		}
		if(e.getClick().isRightClick()){
			if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &6&lKoxy &8*"))){
				int limit = evip ? pluginConfiguration.limitKoxEVip : pluginConfiguration.limitKoxPlayer;

				User user = PluginCore.getCore().getUserManager().getUser(p);
				if(user.getKox() < limit){
					ChatUtil.sendMessage(p, messageConfiguration.enoughKoxy.replace("{LIMIT}", Integer.toString(limit)));
					return;
				}
				ItemsUtil.giveItem(p, new ItemStack(Material.GOLDEN_APPLE, limit, (short)1));
				user.removeKoxy(limit);
				ChatUtil.sendMessage(p, messageConfiguration.withdrawKoxy.replace("{LIMIT}", Integer.toString(limit)));
				open(p);
			}
			if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &e&lRefile &8*"))){
				int limit = evip ? pluginConfiguration.limitRefilEVip : pluginConfiguration.limitRefilPlayer;

				User user = PluginCore.getCore().getUserManager().getUser(p);
				if(user.getRefil() < limit){
					ChatUtil.sendMessage(p, messageConfiguration.enoughRefile.replace("{LIMIT}", Integer.toString(limit)));
					return;
				}
				ItemsUtil.giveItem(p, new ItemStack(Material.GOLDEN_APPLE, limit, (short)0));
				user.removeRefile(limit);
				ChatUtil.sendMessage(p, messageConfiguration.withdrawRefile.replace("{LIMIT}", Integer.toString(limit)));
				open(p);
			}
			if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &9&lPerly &8*"))){
				int limit = evip ? pluginConfiguration.limitPerlaEVip : pluginConfiguration.limitPerlaPlayer;

				User user = PluginCore.getCore().getUserManager().getUser(p);
				if(user.getPerla() < limit){
					ChatUtil.sendMessage(p, messageConfiguration.enoughPerly.replace("{LIMIT}", Integer.toString(limit)));
					return;
				}
				ItemsUtil.giveItem(p, new ItemStack(Material.ENDER_PEARL, limit));
				user.removePerly(limit);
				ChatUtil.sendMessage(p, messageConfiguration.withdrawPerly.replace("{LIMIT}", Integer.toString(limit)));
				open(p);
			}
		}else if(e.getClick().isLeftClick()){
			if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &6&lKoxy &8*"))){
				User user = PluginCore.getCore().getUserManager().getUser(p);
				int limit = user.getKox();

				if(limit <= 0){
					ChatUtil.sendMessage(p, messageConfiguration.nullKoxy);
					return;
				}
				ItemsUtil.giveItem(p, new ItemStack(Material.GOLDEN_APPLE, limit, (short)1));
				user.removeKoxy(limit);
				ChatUtil.sendMessage(p, messageConfiguration.withdrawKoxy.replace("{LIMIT}", Integer.toString(limit)));
				open(p);
			}
			if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &e&lRefile &8*"))){
				User user = PluginCore.getCore().getUserManager().getUser(p);
				int limit = user.getRefil();

				if(limit <= 0){
					ChatUtil.sendMessage(p, messageConfiguration.nullRefile);
					return;
				}
				ItemsUtil.giveItem(p, new ItemStack(Material.GOLDEN_APPLE, limit, (short)0));
				user.removeRefile(limit);
				ChatUtil.sendMessage(p, messageConfiguration.withdrawRefile.replace("{LIMIT}", Integer.toString(limit)));
				open(p);
			}
			if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &9&lPerly &8*"))){
				User user = PluginCore.getCore().getUserManager().getUser(p);
				int limit = user.getPerla();

				if(limit <= 0){
					ChatUtil.sendMessage(p, messageConfiguration.nullPerly);
					return;
				}
				ItemsUtil.giveItem(p, new ItemStack(Material.ENDER_PEARL, limit));
				user.removePerly(limit);
				ChatUtil.sendMessage(p, messageConfiguration.withdrawPerly.replace("{LIMIT}", Integer.toString(limit)));
				open(p);
			}
		}
	}
}
