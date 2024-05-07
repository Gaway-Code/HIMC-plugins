package pl.himc.core.base.user;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.PluginApi;
import pl.himc.api.store.Entry;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.Playable;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.core.api.PluginCore;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.manager.EnderchestManager;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public final class User extends Entry implements Playable {

    private final String name;
    private String fakeName;

    private long writeChat;
    private long writeHelpop;
    private long turbodrop;
    private long turboexp;

    private int kox;
    private int refil;
    private int perla;
    private int eatKox;
    private int eatRefil;
    private int money;
    private int blocksNext;
    private int lvl;
    private int pointsMiner;
    private int stoneBreak;
    private int obsidianBreak;

    private final List<Player> tpa = new ArrayList<>();
    private Map<String, Long> kits = new HashMap<>();
    private Map<String, Location> homes = new HashMap<>();

    private boolean godMode;
    private boolean enableMsg;
    private boolean enableSocialspy;
    private boolean effectMsg;
    private boolean dropCobble;
    private boolean enableDropMsg;

    private Player lastMsg;

    private Inventory enderchest1;
    private Inventory enderchest2;
    private Inventory enderchest3;

    public User(Player player){
        this.name = player.getName();
        this.fakeName = player.getName();

        this.writeChat = System.currentTimeMillis();
        this.writeHelpop = System.currentTimeMillis();
        this.turbodrop = System.currentTimeMillis();
        this.turboexp = System.currentTimeMillis();

        this.kox = 0;
        this.refil = 0;
        this.perla = 0;
        this.eatKox = 0;
        this.eatRefil = 0;
        this.money = 0;
        this.blocksNext = 200;
        this.lvl = 0;
        this.pointsMiner = 0;
        this.stoneBreak = 0;
        this.obsidianBreak = 0;

        this.godMode = false;
        this.enableMsg = true;
        this.enableSocialspy = false;
        this.effectMsg = true;
        this.dropCobble = true;
        this.enableDropMsg = false;

        this.enderchest1 = EnderchestManager.createEnderchest(player, 1);
        this.enderchest2 = EnderchestManager.createEnderchest(player, 2);
        this.enderchest3 = EnderchestManager.createEnderchest(player, 3);

        this.changes();
    }

    public User(ResultSet rs) throws SQLException, IOException {
        this.name = rs.getString("NAME");
        this.fakeName = rs.getString("FAKENAME");

        this.kox = rs.getInt("KOXY");
        this.refil = rs.getInt("REFILE");
        this.perla = rs.getInt("PERLY");
        this.eatKox = rs.getInt("EATKOXY");
        this.eatRefil = rs.getInt("EATREFILE");
        this.money = rs.getInt("MONEY");
        this.blocksNext = rs.getInt("BLOCKSNEXT");
        this.lvl = rs.getInt("LVL");
        this.pointsMiner = rs.getInt("POINTSMINER");
        this.stoneBreak = rs.getInt("STONEBREAK");
        this.obsidianBreak = rs.getInt("OBSIDIANBREAK");

        this.kits = StringUtil.stringToKits(rs.getString("KITS"));
        this.homes = StringUtil.stringToHomes(rs.getString("HOMES"));

        this.enableSocialspy = rs.getBoolean("SOCIALSPY");
        this.enableMsg = rs.getBoolean("MSG");
        this.godMode = rs.getBoolean("GOD");
        this.effectMsg = rs.getBoolean("EFFECTMSG");
        this.dropCobble = rs.getBoolean("DROPCOBBLE");
        this.enableDropMsg = rs.getBoolean("DROPMSG");

        this.writeHelpop = rs.getLong("HELPOP");
        this.writeChat = rs.getLong("CHAT");
        this.turbodrop = rs.getLong("TURBODROP");
        this.turboexp = rs.getLong("TURBOEXP");

        this.enderchest1 = StringUtil.inventoryFromString(rs.getString("ENDERCHEST1"));
        this.enderchest2 = StringUtil.inventoryFromString(rs.getString("ENDERCHEST2"));
        this.enderchest3 = StringUtil.inventoryFromString(rs.getString("ENDERCHEST3"));

        this.setChanges(false);

        PluginCore.getCore().getUserManager().addUser(rs.getString("NAME"), this);
    }

    public String getInsert(){
        String s = "INSERT INTO `" + PluginCore.getCore().getPluginConfig().database.tableUsers + "` VALUES(" +
                "'%name%'," +
                "'%fakename%'," +
                "'%koxy%'," +
                "'%refile%'," +
                "'%perly%'," +
                "'%eatkoxy%'," +
                "'%eatrefile%'," +
                "'%money%'," +
                "'%blocksnext%'," +
                "'%lvl%'," +
                "'%pointsminer%'," +
                "'%stonebreak%'," +
                "'%obsidianbreak%'," +
                "'%kits%'," +
                "'%homes%'," +
                "'%socialspy%'," +
                "'%msg%'," +
                "'%god%'," +
                "'%effectmsg%'," +
                "'%dropcobble%'," +
                "'%dropmsg%'," +
                "'%helpop%'," +
                "'%chat%'," +
                "'%turbodrop%'," +
                "'%turboexp%'," +
                "'%enderchest1%'," +
                "'%enderchest2%'," +
                "'%enderchest3%'" +
                ") ON DUPLICATE KEY UPDATE " +
                "FAKENAME='%fakename%'," +
                "KOXY='%koxy%'," +
                "REFILE='%refile%'," +
                "PERLY='%perly%'," +
                "EATKOXY='%eatkoxy%'," +
                "EATREFILE='%eatrefile%'," +
                "MONEY='%money%'," +
                "BLOCKSNEXT='%blocksnext%'," +
                "LVL='%lvl%'," +
                "POINTSMINER='%pointsminer%'," +
                "STONEBREAK='%stonebreak%'," +
                "OBSIDIANBREAK='%obsidianbreak%'," +
                "KITS='%kits%'," +
                "HOMES='%homes%'," +
                "SOCIALSPY='%socialspy%'," +
                "MSG='%msg%'," +
                "GOD='%god%'," +
                "EFFECTMSG='%effectmsg%'," +
                "DROPCOBBLE='%dropcobble%'," +
                "DROPMSG='%dropmsg%'," +
                "HELPOP='%helpop%'," +
                "CHAT='%chat%'," +
                "TURBODROP='%turbodrop%'," +
                "TURBOEXP='%turboexp%'," +
                "ENDERCHEST1='%enderchest1%'," +
                "ENDERCHEST2='%enderchest2%'," +
                "ENDERCHEST3='%enderchest3%';";
        s = s.replace("%name%", this.name);
        s = s.replace("%fakename%", this.fakeName);
        s = s.replace("%koxy%", Integer.toString(this.kox));
        s = s.replace("%refile%", Integer.toString(this.refil));
        s = s.replace("%perly%", Integer.toString(this.perla));
        s = s.replace("%eatkoxy%", Integer.toString(this.eatKox));
        s = s.replace("%eatrefile%", Integer.toString(this.eatRefil));
        s = s.replace("%money%", Integer.toString(this.money));
        s = s.replace("%blocksnext%", Integer.toString(this.blocksNext));
        s = s.replace("%lvl%", Integer.toString(this.lvl));
        s = s.replace("%pointsminer%", Integer.toString(this.pointsMiner));
        s = s.replace("%stonebreak%", Integer.toString(this.stoneBreak));
        s = s.replace("%obsidianbreak%", Integer.toString(this.obsidianBreak));
        s = s.replace("%kits%", StringUtil.kitsToString(this.kits));
        s = s.replace("%homes%", StringUtil.homesToString(this.homes));
        s = s.replace("%socialspy%", Boolean.toString(this.enableSocialspy));
        s = s.replace("%msg%", Boolean.toString(this.enableMsg));
        s = s.replace("%god%", Boolean.toString(this.godMode));
        s = s.replace("%effectmsg%", Boolean.toString(this.effectMsg));
        s = s.replace("%dropcobble%", Boolean.toString(this.dropCobble));
        s = s.replace("%dropmsg%", Boolean.toString(this.enableDropMsg));
        s = s.replace("%helpop%", Long.toString(this.writeHelpop));
        s = s.replace("%chat%", Long.toString(this.writeChat));
        s = s.replace("%turbodrop%", Long.toString(this.turbodrop));
        s = s.replace("%turboexp%", Long.toString(this.turboexp));
        s = s.replace("%enderchest1%", StringUtil.inventoryToString(this.enderchest1));
        s = s.replace("%enderchest2%", StringUtil.inventoryToString(this.enderchest2));
        s = s.replace("%enderchest3%", StringUtil.inventoryToString(this.enderchest3));
        return s;
    }

    // gets

    public String getName() {
        return this.name;
    }
    public String getFakeName() {
        return this.fakeName;
    }
    public long getWriteChat() {
        return this.writeChat;
    }
    public long getWriteHelpop() {
        return this.writeHelpop;
    }
    public long getKit(String kit) {
        if (!this.kits.containsKey(kit.toLowerCase())) {
            return 0L;
        }
        return this.kits.get(kit.toLowerCase());
    }
    public Location getHome(String name){
        return this.homes.entrySet().stream().filter(entry -> entry.getKey().equalsIgnoreCase(name)).map(Map.Entry::getValue).findFirst().orElse(null);
    }
    public int getHomes(){
        return this.homes.size();
    }
    public String getHomeList(){
        return this.homes.keySet().stream().map(location -> "" + location).collect(joining(", "));
    }
    public String getFirstHomeName(){
        return this.homes.entrySet().stream().findFirst().get().getKey();
    }
    public Inventory getEnderchest(int ender){
        if(ender == 1){
            return this.enderchest1;
        }else if(ender == 2){
            return this.enderchest2;
        }else if(ender == 3){
            return this.enderchest3;
        }
        return null;
    }
    public Player getLastMsg() {
        return this.lastMsg;
    }
    public List<Player> getTpa() {
        return this.tpa;
    }
    public String getDurationTruboDrop(){
        if(!isTurboDrop()){
            return "";
        }
        return TimeUtil.getDuration(this.turbodrop - System.currentTimeMillis());
    }
    public String getDurationTruboExp(){
        if(!isTurboExp()){
            return "";
        }
        return TimeUtil.getDuration(this.turboexp - System.currentTimeMillis());
    }
    public int getKox() {
        return this.kox;
    }
    public int getRefil() {
        return this.refil;
    }
    public int getPerla() {
        return this.perla;
    }
    public int getEatKox() {
        return this.eatKox;
    }
    public int getEatRefil() {
        return this.eatRefil;
    }
    public int getLvl(){
        return this.lvl;
    }
    public int getPointsMiner(){
        return this.pointsMiner;
    }
    public int getBlocksToNext(){
        return this.blocksNext;
    }
    public int getObsidianBreak(){
        return this.obsidianBreak;
    }
    public int getStoneBreak(){
        return this.stoneBreak;
    }
    public int getMoney() {
        return money;
    }
    public double getChance(double chance){
        chance += this.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".drop.mvip") ? PluginCore.getCore().getPluginConfig().dropMVip : 0;
        chance += this.isTurboDrop() ? PluginCore.getCore().getPluginConfig().turboDropChance : 0;
        Guild guild = PluginGuild.getPlugin().getGuildManager().getPlayerGuild(this.getPlayer());
        chance += (guild != null ? (guild.isTurbodrop() ? PluginCore.getCore().getPluginConfig().turboDropChance : 0) : 0);

        return chance;
    }

    // booleans
    public boolean isOnline(){
        return Bukkit.getPlayer(this.name) !=null;
    }
    public boolean isFakeName(){
        return !this.name.equals(this.fakeName);
    }
    public boolean isWriteChat(){
        return this.writeChat <= System.currentTimeMillis();
    }
    public boolean isHelpop(){
        return this.writeHelpop <= System.currentTimeMillis();
    }
    public boolean isKit(String kit) {
        return this.kits.containsKey(kit.toLowerCase()) && this.kits.get(kit.toLowerCase()) >= System.currentTimeMillis();
    }
    public boolean isGodMode(){
        return this.godMode;
    }
    public boolean hasHome(){
        return !this.homes.isEmpty();
    }
    public boolean isEnableMsg(){
        return this.enableMsg;
    }
    public boolean isEnableSocialspy(){
        return this.enableSocialspy;
    }
    public boolean isEffectMsg(){
        return this.effectMsg;
    }
    public boolean isTurboDrop(){
        return this.turbodrop > System.currentTimeMillis();
    }
    public boolean isTurboExp(){
        return this.turboexp > System.currentTimeMillis();
    }
    public boolean isDropCobble(){
        return this.dropCobble;
    }
    public boolean isDropMsg(){
        return this.enableDropMsg;
    }

    // sets
    public void setFakeName(String name){
        this.fakeName = name;
        this.changes();
    }
    public void setWriteChat(long chat){
        this.writeChat = chat;
        this.changes();
    }
    public void setWriteHelpop(long helpop){
        this.writeHelpop = helpop;
        this.changes();
    }
    public void setMoney(int money){
        this.money = money;
        this.changes();
    }
    public void setKit(String kit, Long delay) {
        this.kits.put(kit.toLowerCase(), delay);
        this.changes();
    }
    public void toggleGodMode(){
        this.godMode = !this.godMode;
        this.changes();
    }
    public void toggleEnableMsg(){
        this.enableMsg = !this.enableMsg;
        this.changes();
    }
    public void toggleDropMsg(){
        this.enableDropMsg = !this.enableDropMsg;
        this.changes();
    }
    public void toggleDropCobble(){
        this.dropCobble = !this.dropCobble;
        this.changes();
    }
    public void toggleEffectMsg(){
        this.effectMsg = !this.effectMsg;
        this.changes();
    }
    public void toggleSocialspy(){
        this.enableSocialspy = !this.enableSocialspy;
        this.changes();
    }
    public void setHome(String name, Location location){
        if(this.getHome(name) !=null){
            this.homes.remove(name);
        }
        this.homes.put(name, location);
        this.changes();
    }
    public void setLastMsg(Player player){
        this.lastMsg = player;
    }
    public void setBlocksToNext(int next){
        this.blocksNext = next;
        this.changes();
    }
    public void setTurboDrop(long czas){
        if(this.isTurboDrop()){
            this.turbodrop += czas;
        }else{
            this.turbodrop = czas + System.currentTimeMillis();
        }
        this.changes();
    }
    public void setTurboExp(long czas){
        if(this.isTurboExp()){
            this.turboexp += czas;
        }else{
            this.turboexp = czas + System.currentTimeMillis();
        }
        this.changes();
    }

    // remove
    public void removeHome(String name){
        if(this.getHome(name) != null){
            this.homes.remove(name);
        }
        this.changes();
    }
    public void removeFakeName(){
        this.fakeName = this.name;
        this.changes();
    }
    public void removeKoxy(int toRemove){
        this.kox -= toRemove;
        this.changes();
    }

    public void removeRefile(int toRemove){
        this.refil -= toRemove;
        this.changes();
    }

    public void removePerly(int toRemove){
        this.perla -= toRemove;
        this.changes();
    }
    public void removeBlockToNext(){
        this.blocksNext -= 1;
        this.changes();
    }
    public void removeMoney(int toRemove){
        if(toRemove > money) return;
        this.money -= toRemove;
        this.changes();
    }

    // others
    public void createEnderchest(int ender, ItemStack[] items){
        if(ender == 1){
            this.enderchest1 = null;
            this.enderchest1 = EnderchestManager.createEnderchest(this.getPlayer(), 1);
            this.enderchest1.setContents(items);
        }else if(ender == 2){
            this.enderchest2 = null;
            this.enderchest2 = EnderchestManager.createEnderchest(this.getPlayer(), 2);
            this.enderchest2.setContents(items);
        }else if(ender == 3){
            this.enderchest3 = null;
            this.enderchest3 = EnderchestManager.createEnderchest(this.getPlayer(), 3);
            this.enderchest3.setContents(items);
        }
    }
    public void addKoxy(int toAdd){
        this.kox += toAdd;
        this.changes();
    }
    public void addRefile(int toAdd){
        this.refil += toAdd;
        this.changes();
    }
    public void addPerly(int toAdd){
        this.perla += toAdd;
        this.changes();
    }
    public void addStone(int toAdd){
        this.stoneBreak += toAdd;
        this.removeBlockToNext();
        this.checkDropLevelUp();
        this.changes();
    }
    public void addObsidian(int toAdd){
        this.obsidianBreak += toAdd;
        this.changes();
    }
    public void addLvl(){
        this.lvl += 1;
        this.changes();
    }
    public void addPointsMiner(int toAdd){
        this.pointsMiner += toAdd;
        this.changes();
    }
    public void addEatKox(int toAdd){
        this.eatKox += toAdd;
        this.changes();
    }
    public void addEatRefil(int toAdd){
        this.eatRefil += toAdd;
        this.changes();
    }
    public void addMoney(int toAdd){
        this.money += toAdd;
        this.changes();
    }

    public void checkDropLevelUp(){
        if (this.getBlocksToNext() <= 0) {
            this.addLvl();
            double blocks = this.getLvl() * 200 * 1.1;
            this.setBlocksToNext((int) blocks);

            final PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            this.getPlayer().playEffect(this.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
            this.getPlayer().playSound(this.getPlayer().getLocation(), Sound.LEVEL_UP, 20.0f, 20.0f);
            this.sendTitle(messageConfiguration.titleLevelUp, messageConfiguration.subtitleLevelUp.replace("{LEVEL}", Integer.toString(this.getLvl())), 50);
            TitleAPI.sendTitle(this.getPlayer(), messageConfiguration.titleLevelUp, messageConfiguration.subtitleLevelUp.replace("{LEVEL}", Integer.toString(this.getLvl())), 10, 40);
            ChatUtil.sendMessage(this.getPlayer(), messageConfiguration.dropLevelUp.replace("{LEVEL}", Integer.toString(this.getLvl())));
            ChatUtil.sendBroadcast(messageConfiguration.levelMinerUpBroadcast.replace("{PLAYER}", this.getPlayer().getName()).replace("{LEVEL}", Integer.toString(this.getLvl())));
        }
    }
}