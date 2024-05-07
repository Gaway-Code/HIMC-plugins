package pl.himc.core.base.enchant;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;

public final class EnchantAction {

	public static void onClick(InventoryClickEvent event, String enchname, int books) {
		Player p = (Player)event.getWhoClicked();
		Enchant ench = EnchantManager.get(enchname);
		if(ench == null) {
			p.closeInventory();
			ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().enchantOpenError);
			return;
		}
		ItemStack item = p.getItemInHand();
		if (item != null) {
			if (books >= ench.getNeedBooks()) {
				if (p.getLevel() >= ench.getExpCost() || p.getGameMode() == GameMode.CREATIVE) {
					if (EnchantManager.isAllowedEnchant(item, ench.getEnchant())) {
						if (item.containsEnchantment(ench.getEnchant())) {
							if (item.getEnchantmentLevel(ench.getEnchant()) >= ench.getLevelEnchant()) {
								ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().enchantErrorLevel);
							} else {
								item.addUnsafeEnchantment(ench.getEnchant(), ench.getLevelEnchant());
								ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().selectEnchantSuccess);
								if (p.getGameMode() != GameMode.CREATIVE) {
									p.setLevel(p.getLevel() - ench.getExpCost());
								}
							}
						} else {
							item.addUnsafeEnchantment(ench.getEnchant(), ench.getLevelEnchant());
							ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().selectEnchantSuccess);
							if (p.getGameMode() != GameMode.CREATIVE) {
								p.setLevel(p.getLevel() - ench.getExpCost());
							}
						}
					} else {
						ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().enchantAddError);
					}
				} else {
					ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().enchantErrorRequiredExp.replace("{LEVEL}", Integer.toString(ench.getExpCost())));
				}
			} else {
				ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().enchantErrorRequiredBook.replace("{BOOKS}", Integer.toString((ench.getNeedBooks() - books))));
			}
		} else {
			ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().enchantErrorNullItem);
		}
	}
}
