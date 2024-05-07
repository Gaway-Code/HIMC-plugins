package pl.himc.core.base.kit;

import pl.himc.core.api.PluginCore;
import pl.himc.core.base.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.api.utils.bukkit.TitleAPI;

import java.util.*;

public final class KitData {

	private static KitData kit;
	private PluginMessages messageConfiguration;

	private ArrayList<Kit> kits = new ArrayList<>();
	private Map<String, Kit> edited = new HashMap<>();

	public KitData(){
		kit = this;
		this.messageConfiguration = PluginCore.getCore().getPluginMessages();
	}

	public Kit get(String name) {
		return this.kits.stream().filter(kit -> kit.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	public void addKit(Kit kit) {
		if(get(kit.getName()) == null) {
			this.kits.add(kit);
		}
	}

	public void addNewKit(Player p, ItemStack icon, String name, int delay) {
		if(get(name) == null) {
			new Kit(icon, name, Arrays.asList(new ItemBuilder(Material.STONE).setName(ChatUtil.fixColor("&cTo jest defaultowy przedmiot po stworzeniu zestawu!")).getItem()), delay, true);
		}
		startEditKit(p, get(name), delay);
	}
	
	public void delKit(Kit kit) {
		if(get(kit.getName()) != null) {
			this.kits.remove(kit);
		}
		KitConfig.getKitYaml().set("config.kits." + kit.getName(), null);
		KitConfig.saveKitFile();
	}
	
	public ArrayList<Kit> getKits() {
		return this.kits;
	}

	public void getKit(Kit kit, Player player){
		if(!kit.isEnable()){
			ChatUtil.sendMessage(player, messageConfiguration.kitIsDisable);
			return;
		}
		if(!player.hasPermission(kit.getPermission())) {
			ChatUtil.sendMessage(player, messageConfiguration.playerNotHasPermission.replace("{PERM}", kit.getPermission()));
			return;
		}
		User u = PluginCore.getCore().getUserManager().getUser(player);
		if(u.isKit(kit.getName())) {
			ChatUtil.sendMessage(player, messageConfiguration.kitCooldownTime.replace("{TIME}", TimeUtil.getDuration(u.getKit(kit.getName()) - System.currentTimeMillis())));
			return;
		}
		if(!player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".kit.time.bypass")) {
			u.setKit(kit.getName(), TimeUtil.addTime(kit.getDelay()));
		}
		kit.getItems().forEach(item -> ItemsUtil.giveItem(player, item.clone()));
		ChatUtil.sendMessage(player, PluginCore.getCore().getPluginMessages().kitGiveSuccess.replace("{KIT}", kit.getName()));
		player.closeInventory();
		TitleAPI.sendTitle(player, messageConfiguration.titleGiveKit, messageConfiguration.subTitleGiveKit.replace("{KIT}", kit.getName()), 5, 60);
	}

	public void startEditKit(Player p, Kit kit, int delay) {
		this.edited.remove(p.getName());
		this.edited.put(p.getName(), kit);
		if(delay > 0) {
			kit.setDelay(delay);
		}

		Inventory inv = Bukkit.createInventory(p, 27, ChatUtil.fixColor("&c&lEdytowanie zestawu"));
		for(ItemStack items : kit.getItems()) {
			inv.addItem(items.clone());
		}
		p.openInventory(inv);
	}

	public void endEditKit(Player p, List<ItemStack> items) {
		if(!this.edited.containsKey(p.getName())) {
			PluginCore.getCore().getConsoleSender().error("Nie udalo sie zapisac zestawu! (nie edytuje kitu)");
			return;
		}
		Kit kit = this.edited.get(p.getName());
		if(kit == null) {
			PluginCore.getCore().getConsoleSender().error("Nie udalo sie zapisac zestawu! (brak kitu)");
			return;
		}
		kit.setItems(items);
		saveKit(kit);

		ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().kitEditedSuccess.replace("{KIT}", kit.getName()));
		this.edited.remove(p.getName());
	}

	public void saveKit(Kit kit){
		KitConfig.getKitYaml().set("config.kits." + kit.getName() + ".delay", kit.getDelay());
		KitConfig.getKitYaml().set("config.kits." + kit.getName() + ".item-in-gui", kit.getIcon().getType().toString());
		KitConfig.getKitYaml().set("config.kits." + kit.getName() + ".enable", kit.isEnable());
		KitConfig.getKitYaml().set("config.kits." + kit.getName() + ".items", kit.getItems());
		KitConfig.saveKitFile();
		PluginCore.getCore().getConsoleSender().info("Zestaw o nazwie " + kit.getName() + " zostal zapisany!");
	}

	public static KitData getInstance(){
		if(kit == null){
			return new KitData();
		}
		return kit;
	}
}
