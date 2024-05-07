package pl.himc.core.base.kit;

import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.util.List;

public final class Kit {

	private ItemStack icon;
	private String name;
	private List<ItemStack> items;
	private int delay;
	private boolean enable;
	
	public Kit(ItemStack icon, String name, List<ItemStack> items, int delay, boolean enable){
		this.icon = icon;
		this.name = name;
		this.items = items;
		this.delay = delay;
		this.enable = enable;
		KitData.getInstance().addKit(this);
	}

	public ItemStack getIcon(){
		return icon;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ItemStack> getItems() {
		return items;
	}
	public void setItems(List<ItemStack> items) {
		this.items = items;
	}

	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}

	public boolean isEnable(){
		return this.enable;
	}

	public void toggleEnable(){
		this.enable = !this.enable;
		KitData.getInstance().saveKit(this);
	}

	public String getItemInInventoryName() {
		return ChatUtil.fixColor("&8* &7Zestaw &6" + this.name + " &8*");
	}
	
	public String getPermission() {
		return PluginApi.getApi().getPluginConfig().prefixPermission + ".kit." + this.name;
	}
}
