package pl.himc.lobbycore.data;

import org.bukkit.Location;
import org.diorite.cfg.annotations.CfgCollectionStyle;
import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgExclude;
import pl.himc.api.utils.StringUtil;

import java.util.Arrays;
import java.util.List;

public final class PluginConfig {

    @CfgComment("Nazwa gui z wyborem serwerów")
    public String serversInventoryName = "&6&lHI&f&lMC&7&l.PL";
    @CfgComment("Informacja na actionbarze")
    public String actionbarInfo = "&a&lDZIĘKUJEMY ZA GRĘ NA &6&lHI&f&lMC&7&l.PL";
    @CfgComment("Format czatu dla administratora")
    public String chatFormat = "{PREFIX} {NAME}&8&l: {SUFFIX}{MESSAGE}";
    @CfgComment("Włączone komendy na lobby")
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> allowCommands = Arrays.asList("/l", "/login", "/reg", "/register", "/changepass", "/changepassword", "/captcha");
    @CfgComment("Lokalizacja spawna")
    public String spawnLocation = "";
    @CfgExclude
    public Location cfgSpawnLocation;
    @CfgComment("Nazwa sidebara")
    public String sidebarName = "&6&lHI&f&lMC&7&l.PL";
    @CfgComment("Linie sidebara")
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> sidebarLines = Arrays.asList(" &8%> &7Witaj &e{PLAYER}&7!",
            "&7",
            " &8%> &7Sklep: &ewww.himc.pl",
            " &8%> &7Discord: &edc.himc.pl",
            " &8%> &7Facebook: &efb.himc.pl",
            " &8");

    public void loadValues(){
        this.cfgSpawnLocation = StringUtil.locationFromString(this.spawnLocation);
    }
}
