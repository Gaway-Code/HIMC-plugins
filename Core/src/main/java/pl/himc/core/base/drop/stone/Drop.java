package pl.himc.core.base.drop.stone;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.util.HashSet;
import java.util.Set;

public final class Drop {

    private String name;
    private ItemStack item;
    private double chance;
    private int exp;
    private int points;
    private boolean fortune;
    private String message;
    private int minY;
    private int maxY;
    private int minAmount;
    private int maxAmount;
    private Set<Player> disabled$drop = new HashSet<>();

    public Drop(String name){
        this.name = name;

        DropManager.addDrop(this);
    }

    public String getName() {
        return name;
    }

    public String getGuiName() {
        return ChatUtil.fixColor("&e&l" + name);
    }

    public ItemStack getItem() {
        return item;
    }

    public double getChance() {
        return chance;
    }

    public int getExp() {
        return exp;
    }

    public boolean isFortune() {
        return fortune;
    }

    public String getMessage() {
        return message;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public int getPoints(){
        return this.points;
    }
    public boolean isDisableDrop(Player player){
        return disabled$drop.contains(player);
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setFortune(boolean fortune) {
        this.fortune = fortune;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void changeStatusDrop(Player player){
        if(disabled$drop.contains(player)){
            disabled$drop.remove(player);
        }else{
            disabled$drop.add(player);
        }
    }
    public void setStatusDrop(Player p, boolean status){
        if(status){
            disabled$drop.remove(p);
        }else{
            disabled$drop.add(p);
        }
    }
}
