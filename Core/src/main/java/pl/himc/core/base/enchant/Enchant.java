package pl.himc.core.base.enchant;

import org.bukkit.enchantments.Enchantment;

public final class Enchant {

	private String name;
	private Enchantment enchant;
	private int levelEnchant;
	private int expCost;
	private int needBooks;

	public Enchant(String name, Enchantment ench, int levelEnchant, int expCost, int needBooks) {
		this.name = name;
		this.enchant = ench;
		this.levelEnchant = levelEnchant;
		this.expCost = expCost;
		this.needBooks = needBooks;
		EnchantManager.addEnchant(this);
	}

	public String getName() {
		return name;
	}
	public Enchantment getEnchant() {
		return enchant;
	}

	public int getLevelEnchant() {
		return levelEnchant;
	}

	public int getExpCost() {
		return expCost;
	}

	public int getNeedBooks() {
		return needBooks;
	}
}
