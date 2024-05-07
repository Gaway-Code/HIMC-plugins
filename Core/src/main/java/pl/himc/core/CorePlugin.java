package pl.himc.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.ConfigHelper;
import pl.himc.api.utils.ConsoleSender;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.backup.BackupManager;
import pl.himc.core.base.ban.BanManager;
import pl.himc.core.base.craft.CraftManager;
import pl.himc.core.base.craft.stoniarka.StoniarkaData;
import pl.himc.core.base.drop.stone.DropManager;
import pl.himc.core.base.effect.EffectManager;
import pl.himc.core.base.enchant.EnchantManager;
import pl.himc.core.base.itemshop.ItemshopData;
import pl.himc.core.base.kit.KitConfig;
import pl.himc.core.base.mute.MuteData;
import pl.himc.core.base.regions.RegionData;
import pl.himc.core.base.shop.ShopData;
import pl.himc.core.base.user.UserManager;
import pl.himc.core.base.warp.WarpData;
import pl.himc.core.command.admin.*;
import pl.himc.core.command.player.*;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.hook.PluginHook;
import pl.himc.core.listener.*;
import pl.himc.core.listener.block.BlockBreak;
import pl.himc.core.listener.block.BlockPiston;
import pl.himc.core.listener.block.BlockPlace;
import pl.himc.core.listener.player.*;
import pl.himc.core.manager.AntyLogout;
import pl.himc.core.task.AutosaveTask;
import pl.himc.core.task.LimitTask;
import pl.himc.core.task.RefreshRank;
import pl.himc.core.task.VanishTask;

import java.io.File;


public final class CorePlugin extends JavaPlugin {

    private ConsoleSender consoleSender;

    private File configFile;
    private PluginConfig pluginConfig;
    private PluginMessages pluginMessages;

    private UserManager userManager;
    private MuteData muteManager;
    private BanManager banManager;
    private BackupManager backupManager;
    private ItemshopData itemshopData;
    private WarpData warpData;
    private RegionData regionData;
    private ShopData shopData;

    @Override
    public void onDisable() {
        AntyLogout.disablePlugin();
        Bukkit.getScheduler().cancelTasks(this);
        this.saveAll();
    }

    @Override
    public void onEnable() {
        new PluginCore(this);
        this.consoleSender = new ConsoleSender(this);
        this.consoleSender.send("Plugin uruchamia sie! Wersja v" + this.getDescription().getVersion());

        this.loadConfigs();
        this.loadCommands();
        this.loadListeners();
        this.loadManagers();
        this.registerTasks();
        this.loadData();
        PluginHook.init();
    }


    private void loadCommands(){
        String prefix = PluginApi.getApi().getPluginConfig().prefixPermission;

        new AdminChatCommand(prefix + ".cmd.adminchat").register();
        new BackupCommand(prefix + ".cmd.backup").register();
        new BanCommand(prefix + ".cmd.ban").register();
        new BanInfoCommand(prefix + ".cmd.baninfo").register();
        new BroadcastCommand(prefix + ".cmd.broadcast").register();
        new BuyCommand(this,prefix + ".cmd.buy");
        new ChatCommand(prefix + ".cmd.chat").register();
        new ClearCommand(prefix + ".cmd.clear").register();
        new CreateKitCommand(prefix + ".cmd.createkit").register();
        new DajYTCommand(prefix + ".cmd.dajyt").register();
        new DayCommand(prefix + ".cmd.day").register();
        new DeleteKitCommand(prefix + ".cmd.deletekit").register();
        new DelWarpCommand(prefix + ".cmd.delwarp").register();
        new EditKitCommand(prefix + ".cmd.editkit").register();
        new EnchantCommand(prefix + ".cmd.enchant").register();
        new FlyCommand(prefix + ".cmd.fly").register();
        new GamemodeCommand(prefix + ".cmd.gamemode").register();
        new GcCommand(prefix + ".cmd.gc").register();
        new GiveCommand(prefix + ".cmd.give").register();
        new GodCommand(prefix + ".cmd.god").register();
        new HeadCommand(prefix + ".cmd.head").register();
        new HealCommand(prefix + ".cmd.heal").register();
        new InvseeCommand(prefix + ".cmd.invsee").register();
        new KickCommand(prefix + ".cmd.kick").register();
        new ListCommand(prefix + ".cmd.list").register();
        new MuteCommand(prefix + ".cmd.mute").register();
        new MuteInfoCommand(prefix + ".cmd.muteinfo").register();
        new NightCommand(prefix + ".cmd.night").register();
        new RegionCommand(prefix + ".cmd.region").register();
        new LuckyBoxCommand(prefix + ".cmd.luckybox").register();
        new SetSpawnCommand(prefix + ".cmd.setspawn").register();
        new SetWarpCommand(prefix + ".cmd.setwarp").register();
        new SocialSpyCommand(prefix + ".cmd.socialspy").register();
        new SprawdzCommand(prefix + ".cmd.sprawdz").register();
        new TeleportCommand(prefix + ".cmd.teleport").register();
        new TempbanCommand(prefix + ".cmd.tempban").register();
        new TempMuteCommand(prefix + ".cmd.tempmute").register();
        new TitleCommand(prefix + ".cmd.title").register();
        new TphereCommand(prefix + ".cmd.tphere").register();
        new TrollMessageCommand(prefix + ".cmd.trollmessage").register();
        new TurboDropCommand(prefix + ".cmd.turbodrop").register();
        new TurboExpCommand(prefix + ".cmd.turboexp").register();
        new UnBanAllCommand(prefix + ".cmd.unbanall").register();
        new UnbanCommand(prefix + ".cmd.unban").register();
        new UnMuteAllCommand(prefix + ".cmd.unmuteall").register();
        new UnmuteCommand(prefix + ".cmd.unmute").register();
        new VanishCommand(prefix + ".cmd.vanish").register();
        new VoucherCommand(prefix + ".cmd.voucher").register();

        new BlokiCommand(prefix + ".cmd.bloki").register();
        new CobblexCommand().register();
        new CraftingiCommand().register();
        new DelHomeCommand(prefix + ".cmd.home").register();
        new DropCommand().register();
        new EfektCommand().register();
        new EnderchestCommand(prefix + ".cmd.enderchest").register();
        new FeedCommand(prefix + ".cmd.feed").register();
        new HelpopCommand(prefix + ".cmd.helpop").register();
        new HomeCommand(prefix + ".cmd.home").register();
        new IgnoreCommand(prefix + ".cmd.ignore").register();
        new KitCommand(prefix + ".cmd.kit").register();
        new KoszCommand().register();
        new LobbyCommand(this);
        new MsgCommand(prefix + ".cmd.msg").register();
        new NaprawItemCommand().register();
        new NickCommand(prefix + ".cmd.nick").register();
        new PomocCommand().register();
        new RepairCommand(prefix + ".cmd.repair").register();
        new ReplyCommand(prefix + ".cmd.msg").register();
        new SchowekCommand(prefix + ".cmd.schowek").register();
        new SetHomeCommand(prefix + ".cmd.home").register();
        new SklepCommand(this);
        new SpawnCommand(prefix + ".cmd.spawn").register();
        new TeczowyCommand(prefix + ".cmd.teczowy").register();
        new TopkiCommand().register();
        new TpacceptCommand(prefix + ".cmd.tpa").register();
        new TpaCommand(prefix + ".cmd.tpa").register();
        new WarpCommand(prefix + ".cmd.warp").register();
        new WiadomosciCommand(prefix + ".cmd.wiadomosci").register();
        new WorkbenchCommand(prefix + ".cmd.workbench").register();
        new CrasherCommand(prefix+ " .cmd.crasher").register();
    }

    private void loadListeners(){
        PluginManager pm = Bukkit.getPluginManager();

        new PlayerBorder(this);
        new AnvilListener(this);
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new BlockPlace(), this);
        pm.registerEvents(new BlockPiston(), this);
        pm.registerEvents(new CraftItem(), this);
        pm.registerEvents(new EntityExplode(), this);
        new InventoryClick(this);
        pm.registerEvents(new InventoryClose(), this);
        pm.registerEvents(new PlayerChat(), this);
        new PlayerCommand(this);
        new PlayerDamage(this);
        pm.registerEvents(new PlayerDeath(), this);
        pm.registerEvents(new PlayerDropItem(), this);
        pm.registerEvents(new PlayerEat(), this);
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new PlayerPickupItem(), this);
        new PlayerPreLogin(this);
        pm.registerEvents(new PlayerQuit(), this);
        new RegionListener(this);
        pm.registerEvents(new SignChange(), this);
        pm.registerEvents(new WeatherChange(), this);
    }

    private void registerTasks(){
        new LimitTask(this);
        new AutosaveTask(this);
        new RefreshRank(this);
        new VanishTask(this);
    }

    private void loadManagers(){
        this.userManager = new UserManager();
        this.muteManager = new MuteData(this);
        this.banManager = new BanManager(this);
        this.backupManager = new BackupManager(this);
        this.itemshopData = new ItemshopData(this);
        this.warpData = new WarpData(this);
        this.regionData = new RegionData(this);
        this.shopData = new ShopData(this);
    }

    private void loadData(){
        this.userManager.loadUsers();
        this.banManager.loadBans();
        this.muteManager.loadMutes();
        this.backupManager.loadBackups();
        this.warpData.loadWarps();
        this.regionData.loadRegions();
        this.shopData.loadShop();
        this.shopData.loadBazar();
        this.itemshopData.loadShop();
        CraftManager.loadCrafts();
        StoniarkaData.loadGenerators();
        EnchantManager.loadEnchantments();
        KitConfig.loadKits();
        EffectManager.getInstance().loadEffectPlayer();
        EffectManager.getInstance().loadEffectVip();
        DropManager.loadDrops();
    }

    private void loadConfigs(){
        if(!this.getDataFolder().exists()) this.getDataFolder().mkdir();
        this.reloadPluginConfig();
        this.reloadPluginMessages();
    }

    public ConsoleSender getConsoleSender(){
        return this.consoleSender;
    }

    public PluginConfig getPluginConfig(){
        return this.pluginConfig;
    }

    public PluginMessages getPluginMessages(){
        return this.pluginMessages;
    }

    public void savePluginConfiguration(){
        ConfigHelper.saveConfig(this.configFile, this.pluginConfig);
    }

    public void reloadPluginConfig() {
        this.configFile = null;
        this.configFile = new File(this.getDataFolder(), "config.yml");
        this.pluginConfig = ConfigHelper.loadConfig(this.configFile, PluginConfig.class);
        this.pluginConfig.loadValues();
    }

    public void reloadPluginMessages() {
        this.pluginMessages = ConfigHelper.loadConfig(new File(this.getDataFolder(), "messages.yml"), PluginMessages.class);
    }

    public void saveAll(){
        this.userManager.saveUsers();
        this.backupManager.saveBackups();
        StoniarkaData.saveGenerators();
        this.shopData.saveBazar();
    }

    public UserManager getUserManager(){
        return this.userManager;
    }
    public MuteData getMuteManager(){
        return this.muteManager;
    }
    public BanManager getBanManager(){
        return this.banManager;
    }
    public BackupManager backupManager() {
        return backupManager;
    }
    public ItemshopData getItemshopData() {
        return itemshopData;
    }
    public WarpData getWarpData() {
        return warpData;
    }
    public RegionData getRegionData() {
        return regionData;
    }
    public ShopData getShopData() {
        return shopData;
    }
}
