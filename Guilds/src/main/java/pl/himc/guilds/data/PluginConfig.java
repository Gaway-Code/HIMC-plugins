package pl.himc.guilds.data;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.diorite.cfg.annotations.CfgCollectionStyle;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgExclude;
import org.diorite.cfg.annotations.CfgName;
import pl.himc.api.utils.IntegerRange;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.MaterialUtils;
import pl.himc.api.utils.bukkit.item.ItemsUtil;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PluginConfig {

    @CfgComment("Konfiguracja bazy danych")
    public database database = new database();

    @CfgComment("Czy tworzenie gildii ma być włączone?")
    public boolean enableCreateGuild = true;
    @CfgComment("Na jakim poziomie Y ma tworzyc sie pomieszczenie gildii?")
    public int createCenterY = 36;
    @CfgComment("Minimalna długość nazwy gildii")
    public int createMinGuildName = 4;
    @CfgComment("Maksymalna długość nazwy gildii")
    public int createMaxGuildName = 22;
    @CfgComment("Minimalna długość tagu gildii")
    public int createMinGuildTag = 2;
    @CfgComment("Maksymalna długość tagu gildii")
    public int createMaxGuildTag = 4;
    @CfgComment("Ile kratek od miejsca spawnu można założyć gildie?")
    public int createGuildDistanceSpawn = 170;
    @CfgComment("Ile kratek od innej gildii można założyć gildie?")
    public int createGuildDistanceOtherGuild = 70;
    @CfgComment("Przedmioty jakie gracz potrzebuje do założenia gildii")
    @CfgName("createGuildItemsPlayer")
    public String createGuildItemsPlayer_ = "130:0-16;133:0-32;1:0-64;61:0-64;65:0-64;116:0-24;76:0-64;";
    @CfgExclude
    public List<ItemStack> createGuildItemsPlayer;
    @CfgComment("Przedmioty jakie gracz z uprawnieniem (*.guild.vip) potrzebuje do założenia gildii")
    @CfgName("createGuildItemsVip")
    public String createGuildItemsVip_ = "130:0-8;133:0-16;1:0-32;61:0-32;65:0-32;116:0-12;76:0-32;";
    @CfgExclude
    public List<ItemStack> createGuildItemsVip;
    @CfgComment("Ilość żyć gildii")
    public int createGuildLives = 3;
    @CfgComment("Początkowa wielkość terenu gildii (w każdą stronę od środka gildii)")
    public int createRegionSize = 15;
    @CfgComment("Czas ochrony gildii po jej założeniu (np. 12h - 12 godzin)")
    @CfgName("createGuildProtection")
    public String createGuildProtection_ = "12h";
    @CfgExclude
    public long createGuildProtection;
    @CfgComment("Ważność gildii po jej założeniu (np. 5d - 5 dni)")
    @CfgName("createGuildValidity")
    public String createGuildValidity_ = "5d";
    @CfgExclude
    public long createGuildValidity;
    @CfgComment("Maksymalna ilość sojuszy")
    public int guildMaxAlly = 5;
    @CfgComment("Ochrona gildii po zaatakowaniu jej przez gildie wroga")
    @CfgName("guildProtectionAfterWar")
    public String guildProtectionAfterWar_ = "12h";
    @CfgExclude
    public long guildProtectionAfterWar;

    @CfgComment("Maksymalna wielkość terenu gildii (w każdą stronę od środka gildii)")
    public int guildMaxRegionSize = 35;
    @CfgComment("Ile kratek ma się powiększać gildia")
    public int guildEnlargeRegionBlocks = 5;
    @CfgComment("Przedmioty potrzebne do powiększenia gildii")
    @CfgName("guildEnlargeItems")
    public String guildEnlargeItems_ = "388:0-16;";
    @CfgExclude
    public List<ItemStack> guildEnlargeItems;
    @CfgComment("Czy eksplozje mają działać tylko na terenie gildii?")
    public boolean guildExplosionOnlyRegionGuild = true;
    @CfgComment("Ile czasu po wybuchu nie można budowac na terenie gildii? (np. 1min - 1 minuta)")
    @CfgName("guildAfterExplodeBuild")
    public String guildAfterExplodeBuild_ = "1min";
    @CfgExclude
    public long guildAfterExplodeBuild;
    @CfgComment("Od której TNT ma być włączone?")
    public String tntProtectionStart = "22:00";
    @CfgExclude
    public LocalTime tntProtectionStartTime;
    @CfgComment("Do której TNT ma być wyłączone?")
    public String tntProtectionEnd = "10:00";
    @CfgExclude
    public LocalTime tntProtectionEndTime;
    @CfgExclude
    public boolean guildTNTProtectionPassingMidnight;
    @CfgComment("Jaki ma być zasięg pobieranych przedmiotów po wybuchu (jeżeli chcesz wyłączyć, wpisz 0)")
    public int guildExplodeRadius = 3;

    @CfgComment("Szansa na zniszczenie bloku po wybuchu w %")
    @CfgName("explodeMaterials")
    public Map<String, Double> explodeMaterials_ = ImmutableMap.of(
            "ender_chest", 20.0,
            "enchantment_table", 20.0,
            "obsidian", 20.0,
            "water", 33.0,
            "lava", 33.0);
    @CfgExclude
    public Map<Material, Double> explodeMaterials;
    @CfgComment("Wyłączone komendy na terenie wrogiej gildii")
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> regionDisableCommands = Arrays.asList("sethome", "tpa", "tpaccept");

    @CfgComment("Na ile czasu jest przedłużenie gildii? (np. 5d - 5 dni)")
    @CfgName("guildValidityLong")
    public String guildValidityLong_ = "5d";
    @CfgExclude
    public long guildValidityLong;
    @CfgComment("Na ile czasu maksymalnie można przedłużyć gildie? (np. 10d - 10 dni)")
    @CfgName("guildValidityMaxLong")
    public String guildValidityMaxLong_ = "10d";
    @CfgExclude
    public long guildValidityMaxLong;
    @CfgComment("Przedmioty potrzebne do przedłużenia ważności gildii")
    @CfgName("validityItems")
    public String validityItems_ = "388:0-16;";
    @CfgExclude
    public List<ItemStack> validityItems;

    @CfgComment("Przedmioty potrzebne do dołączenia do gildii")
    @CfgName("guildJoinItems")
    public String guildJoinItems_ = "388:0-5";
    @CfgExclude
    public List<ItemStack> guildJoinItems;
    @CfgComment("Maksymalna ilość członków w gildii")
    public int guildMaxMembers = 20;
    @CfgComment("Maksymalna ilość moderatorów gildii")
    public int guildMaxMods = 5;

    @CfgComment("Konfiguracja systemu punktów")
    public int playerStartPoints = 1000;
    @CfgName("eloConstants")
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> eloConstants_ = Arrays.asList("0-1999 32", "2000-2400 24", "2401-* 16");
    @CfgExclude
    public Map<IntegerRange, Integer> eloConstants;
    @CfgComment("Dzielnik obliczen rankingowych ELO - im mniejszy, tym wieksze zmiany rankingu | Dzielnik powinien byc liczba dodatnia, niezerowa")
    public double eloDivider = 400.0;
    @CfgComment("Wykladnik potegi obliczen rankingowych ELO - im mniejszy, tym wieksze zmiany rankingu | Wykladnik powinien byc liczba dodatnia, niezerowa")
    public double eloExponent = 10.0;

    @CfgComment("Format scoreboarda")
    public String scoreboardFormat = "&8[{COLOR}{TAG}&8] {COLOR}";
    public String scoreboardColorNoGuild = "&r";
    public String scoreboardColorFriend = "&a";
    public String scoreboardColorEnemy = "&c";
    public String scoreboardColorAlliance = "&6";

    @CfgComment("Format czatu")
    public String chatTagFormat = "&8[&c{TAG}&8]";
    public String chatPointsFormat = "&7[{POINTS}]";
    public String chatGuildMemberFormat = "&a[GILDIA] {NAME} &8%> &7{MESSAGE}";
    public String chatGuildAllianceFormat = "&6[Sojusz] &8(&e{TAG}&8) &6{NAME} &8%> &7{MESSAGE}";

    @CfgComment("Format wyglądu taga na tabliście")
    public String tablistTopFormatGuild = "&7{TAG} &8(&2{POINTS}&8)";
    public String tablistTopFormatPlayer = "&7{NAME} &8(&2{POINTS}&8)";

    public static class database {
        @CfgComment("Co ile minut mają zapisywać się dane do bazy?")
        public int autoSave = 30;
        @CfgComment("Tabelka z graczami")
        public String tableUsers = "guildUsers";
        @CfgComment("Tabelka z gildiami")
        public String tableGuilds = "guilds";
    }

    public void loadValues() {
        this.guildEnlargeItems = ItemsUtil.getItems(this.guildEnlargeItems_, 1);
        this.createGuildItemsPlayer = ItemsUtil.getItems(this.createGuildItemsPlayer_, 1);
        this.createGuildItemsVip = ItemsUtil.getItems(this.createGuildItemsVip_, 1);
        this.guildJoinItems = ItemsUtil.getItems(this.guildJoinItems_, 1);
        this.createGuildProtection = TimeUtil.getTimeWithString(this.createGuildProtection_);
        this.createGuildValidity = TimeUtil.getTimeWithString(this.createGuildValidity_);
        this.guildValidityLong = TimeUtil.getTimeWithString(this.guildValidityLong_);
        this.guildValidityMaxLong = TimeUtil.getTimeWithString(this.guildValidityMaxLong_);
        this.validityItems = ItemsUtil.getItems(this.validityItems_, 1);
        this.guildAfterExplodeBuild = TimeUtil.getTimeWithString(this.guildAfterExplodeBuild_);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        this.tntProtectionStartTime = LocalTime.parse(tntProtectionStart, timeFormatter);
        this.tntProtectionEndTime = LocalTime.parse(tntProtectionEnd, timeFormatter);
        this.guildTNTProtectionPassingMidnight = this.tntProtectionStartTime.isAfter(this.tntProtectionEndTime);

        this.guildProtectionAfterWar = TimeUtil.getTimeWithString(this.guildProtectionAfterWar_);

        Map<Material, Double> map = new EnumMap<>(Material.class);
        for (Map.Entry<String, Double> entry : this.explodeMaterials_.entrySet()) {
            double chance = entry.getValue();
            if (chance < 0) {
                continue;
            }

            Material material = MaterialUtils.parseMaterial(entry.getKey(), true);
            if (material == null || material == Material.AIR) {
                continue;
            }

            map.put(material, chance);
        }
        this.explodeMaterials = map;

        Map<IntegerRange, Integer> parsedData = new HashMap<>();
        for (Map.Entry<IntegerRange, String> entry : IntegerRange.parseIntegerRange(this.eloConstants_, false).entrySet()) {
            try {
                parsedData.put(entry.getKey(), Integer.parseInt(entry.getValue()));
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        this.eloConstants = parsedData;
    }
}
