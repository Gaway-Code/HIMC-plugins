package pl.himc.core.base.effect;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public final class EffectVip {

    private String name;
    private ItemStack itemInGui;
    private boolean useEffect;
    private ItemStack itemCost;
    private PotionEffectType effectType;
    private int duration;
    private int amplifier;
    private int invX;
    private int invY;

    public EffectVip(String name){
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

    public int getInvX(){
        return invX;
    }
    public int getInvY(){
        return invY;
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

    public EffectVip setName(String name) {
        this.name = name;
        return this;
    }

    public EffectVip setItemInGui(ItemStack itemInGui) {
        this.itemInGui = itemInGui;
        return this;
    }

    public EffectVip setUseEffect(boolean useEffect) {
        this.useEffect = useEffect;
        return this;
    }

    public EffectVip setItemCost(ItemStack is) {
        this.itemCost = is;
        return this;
    }

    public EffectVip setEffectType(PotionEffectType effectType) {
        this.effectType = effectType;
        return this;
    }

    public EffectVip setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public EffectVip setAmplifier(int amplifier) {
        this.amplifier = amplifier;
        return this;
    }

    public EffectVip setInvX(int invX){
        this.invX = invX;
        return this;
    }

    public EffectVip setInvY(int invY){
        this.invY = invY;
        return this;
    }
}
