package pl.himc.core.base.effect;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public final class EffectPlayer {

    private String name;
    private ItemStack itemInGui;
    private boolean useEffect;
    private ItemStack itemCost;
    private PotionEffectType effectType;
    private int duration;
    private int amplifier;
    private int invX;
    private int invY;

    public EffectPlayer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemInGui() {
        return itemInGui;
    }

    public boolean useEffect() {
        return useEffect;
    }

    public ItemStack getCostItem() {
        return itemCost;
    }

    public PotionEffectType getEffectType() {
        return effectType;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public int getInvX(){
        return invX;
    }
    public int getInvY(){
        return invY;
    }

    public EffectPlayer setName(String name) {
        this.name = name;
        return this;
    }

    public EffectPlayer setItemInGui(ItemStack itemInGui) {
        this.itemInGui = itemInGui;
        return this;
    }

    public EffectPlayer setUseEffect(boolean useEffect) {
        this.useEffect = useEffect;
        return this;
    }

    public EffectPlayer setItemCost(ItemStack is) {
        this.itemCost = is;
        return this;
    }

    public EffectPlayer setEffectType(PotionEffectType effectType) {
        this.effectType = effectType;
        return this;
    }

    public EffectPlayer setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public EffectPlayer setAmplifier(int amplifier) {
        this.amplifier = amplifier;
        return this;
    }

    public EffectPlayer setInvX(int invX){
        this.invX = invX;
        return this;
    }

    public EffectPlayer setInvY(int invY){
        this.invY = invY;
        return this;
    }
}
