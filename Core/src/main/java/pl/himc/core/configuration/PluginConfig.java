package pl.himc.core.configuration;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.diorite.cfg.annotations.CfgCollectionStyle;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgExclude;
import org.diorite.cfg.annotations.CfgStringStyle;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class PluginConfig {

    @CfgComment("Konfiguracja bazy danych")
    public database database = new database();

    @CfgComment("Lokalizacja spawna")
    public String spawnLocation = "world,0.5,75.0,0.5,-0.3000008,1.7999699";
    @CfgExclude
    public Location cfgSpawnLocation;
    @CfgComment("Lokalizacja sprawdzarki")
    public String sprawdzLocation = "world,0.5,75.0,0.5,-0.3000008,1.7999699";
    @CfgExclude
    public Location cfgSprawdzLocation;

    @CfgComment("Czas teleportacji (w sekundach)")
    public int tpTimeSpawn = 5;
    public int tpTimeWarp = 5;
    public int tpTimeHome = 5;
    public int tpTimeTpa = 10;

    @CfgComment("Limity dla gracza")
    public int limitKoxPlayer = 2;
    public int limitRefilPlayer = 10;
    public int limitPerlaPlayer = 3;

    @CfgComment("Limity dla rangi z uprawnieniem *.limits.evip")
    public int limitKoxEVip = 3;
    public int limitRefilEVip = 11;
    public int limitPerlaEVip = 4;

    @CfgComment("Czy czat ma być włączony?")
    public boolean enableChat = true;
    @CfgComment("Od któego poziomu kopania można pisać na czacie?")
    public int chatWriteLevel = 5;
    @CfgComment("Co jaki czas można pisać na czacie? (np. 5s - 5 sekund)")
    public String chatCooldown = "5s";
    @CfgExclude
    public long cfgChatCooldown;
    @CfgComment("Co jaki czas można pisać na /helpop? (np. 30s - 30 sekund)")
    public String helpopCooldown = "30s";
    @CfgExclude
    public long cfgHelpopCooldown;

    @CfgComment("Jakie słowa nie są traktowane jako reklama?")
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> reklamaAllowWords = Arrays.asList("himc.pl", "himc", "youtube", "youtube.com", "gaming", "live");
    @CfgComment("Słowa traktowane jako reklama")
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> reklamaBannedWords = Arrays.asList(".pl", "keyhc", "bighard", "craftplay", "crazysv", "stormplay", "zethc", ".eu",
      ".com.pl", ".com", ".net", "watermc", "owncraft", "icedrop", "naczosmc", "zapraszam na serwer",
      "wbijac na serwer", "easysv.eu", ".tasrv", ".ench", ".ench.pl", "stormplay.pl", "liteplay.pl", "skytop.pl", "skytop");

    @CfgComment("Słowa traktowane jako przekleństwa")
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> curseWords = Arrays.asList("kurwa",
            "chuj", "cipa", "jebac", "cfel", "cwel", "penis", "dildos", "kutas", "dick", "lagi", "huj",
            "dupe", "dupa", "kurwo", "jebana", "jebany", "skurwiel", "skurwysyn", "pedal", "pedale", "szmato", "szmata", "dziwka", "dzifka", "dziwko",
            "pierdolona", "jebane", "seks", "ruchanie", "jebanie", "pierdolenie");

    @CfgComment("Czy TNT można stawiać od danej kratki Y?")
    public boolean tntPlaceY = true;
    @CfgComment("Od której kratki można stawiać TNT?")
    public int tntPlaceKoordY = 50;

    @CfgComment("Konfiguracja losowych koordów")
    public int randomTpMin = -490;
    public int randomTpMax = 490;

    @CfgComment("Ile ma trwać anty-logout")
    public int antyLogoutTime = 30;
    @CfgComment("Jakie komendy można używać podczas anty-logouta?")
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> antylogoutEnableCommands = Arrays.asList("helpop", "msg", "r", "efekty", "efekt", "ec", "enderchest");

    @CfgComment("Czy siła II ma być włączona w /efekty?")
    public boolean strenght2InEffect = true;
    @CfgComment("Czy cobblexy mają być włączone?")
    public boolean enableCobblex = true;
    @CfgComment("Czy luckyboxy mają być włączone?")
    public boolean enableLuckyBox = true;

    @CfgComment("O ile większy ma być drop dla gracza z uprawnieniem *.drop.mvip?")
    public double dropMVip = 5.0;
    @CfgComment("Ile EXPa ma dodać turboexp")
    public int turboDropExp = 40;
    @CfgComment("Ile % dropu ma dodać turbodrop")
    public double turboDropChance = 10.0;

    @CfgComment("Na ile czasu ma być ban za przyznanie się do cheatów?")
    public String sprawdzBanPrzyznanie = "3d";
    @CfgExclude
    public long cfgSprawdzBanPrzyznanie;
    @CfgComment("Jak długi może być fałszywy nick? (/nick)")
    public int fakeNameLenght = 25;

    @CfgComment("Konfiguracja zablokowanych komend")
    public String blockedCmdsMessage = "&cNie ma takiej komendy. Wpisz /pomoc";
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    @CfgStringStyle(CfgStringStyle.StringStyle.ALWAYS_QUOTED)
    public List<String> blockedCmdsList = Arrays.asList("/pl", "/plugins", "/help", "/?", "/about",
    "/bukkit:about", "/bukkit:me", "/bukkit:pl", "/bukkit:plugins", "/me", "/minecraft:me", "/minecraft:about", "/minecraft:plugins");

    @CfgComment("Lista warpów")
    public Map<String, String> warps = ImmutableMap.of(
            "test", "world,-37.00179198855285,77.0,0.576808214741487,90.17116,-7.2869205",
            "test2", "world,0.5,75.0,0.5,-0.3000008,1.7999699");

    @CfgComment("Własne komendy")
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public Map<String, List<String>> customCommands = ImmutableMap.of(
            "toolsy", Arrays.asList("&a&lSiema", "&7&l:D"));

    public void sendCustomCommand(PlayerCommandPreprocessEvent event){
        if(customCommands.get(event.getMessage().replace("/", "").toLowerCase()) !=null){
            event.setCancelled(true);
            for(String s : customCommands.get(event.getMessage().replace("/", "").toLowerCase())){
                ChatUtil.sendMessage(event.getPlayer(), s);
            }
        }
    }

    public static class database {
        @CfgComment("Co ile minut ma być zapis danych do bazy?")
        public int autoSave = 30;
        @CfgComment("Nazwa tabelki z danymi graczy")
        public String tableUsers = "toolsUsers";
        @CfgComment("Nazwa tabelki z danymi backupów")
        public String tableBackups = "backups";
        @CfgComment("Nazwa tabelki z danymi banów")
        public String tableBans = "bans";
        @CfgComment("Nazwa tabelki z danymi wyciszeń")
        public String tableMutes = "mutes";
        @CfgComment("Nazwa tabelki z danymi generatorów")
        public String tableGenerators = "generators";
        @CfgComment("Nazwa tabelki z danymi bazarów")
        public String tableBazar = "bazars";
    }

    public void loadValues() {
        this.cfgSpawnLocation = StringUtil.locationFromString(this.spawnLocation);
        this.cfgSprawdzLocation = StringUtil.locationFromString(this.sprawdzLocation);
        this.cfgChatCooldown = TimeUtil.getTimeWithString(this.chatCooldown);
        this.cfgHelpopCooldown = TimeUtil.getTimeWithString(this.helpopCooldown);
        this.cfgSprawdzBanPrzyznanie = TimeUtil.getTimeWithString(this.sprawdzBanPrzyznanie);
    }
}
