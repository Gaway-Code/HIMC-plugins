package pl.himc.bungee.data;

import net.md_5.bungee.api.plugin.Plugin;
import pl.himc.bungee.configuration.ConfigurationSource;
import pl.himc.bungee.configuration.CustomConfiguration;

import java.util.Arrays;
import java.util.List;

public final class MessageConfiguration extends CustomConfiguration {

    public MessageConfiguration(Plugin plugin) {
        super(plugin, "messages.yml");
        this.save();
        this.load();
    }

    @ConfigurationSource(path = "usageCommand")
    public String usageCommand = "&8&l[&4&l!&8&l] &7Prawidłowe użycie komendy: &c{USAGE}";
    @ConfigurationSource(path = "commandNotFound")
    public String commandNotFound = "&8&l[&4&l!&8&l] &cTa komenda nie istnieje!";
    @ConfigurationSource(path = "noHasPermission")
    public String noHasPermission = "&8&l[&4&l!&8&l] &cNie posiadasz uprawnień &6{PERMISSION}&c!";
    @ConfigurationSource(path = "reloadConfiguration")
    public String reloadConfiguration = "&8&l[&2&l!&8&l] &aKonfiguracja została przeładowana!";
    @ConfigurationSource(path = "help.mainBungee")
    public List<String> mainHelpBungee = Arrays.asList("&7",
            " &8* &a/bungee adminchat &7<wiadomosc> &8- &7Wysłanie wiadomości na czat administracji",
            " &8* &a/bungee reload &8- &7Przeładowanie konfiguracji oraz wiadomości pluginu",
            " &8* &a/bungee perms &8- &7Zarządzanie uprawnieniami bungeecord",
            " &8* &a/bungee broadcast &7<wiadomosc> &8- &7Wysyła wiadomość do każdego gracza",
            " &8* &a/bungee whitelisthelp &8- &7Zadządzanie białą listą",
            " &8* &a/bungee blacklisthelp &8- &7Zadządzanie czarną listą");
    @ConfigurationSource(path = "help.perms")
    public List<String> permsHelp = Arrays.asList("&7",
            " &8* &a/bungee creategroup &7<Nazwa grupy> &8- &7Tworzy nową grupe",
            " &8* &a/bungee deletegroup &7<Nazwa grupy> &8- &7Usuwa grupe",
            " &8* &a/bungee renamegroup &7<Stara nazwa grupy> <Nowa nazwa grupy> &8- &7Zmienia nazwe grupy",
            " &8* &a/bungee addpermgroup &7<Uprawnienie> <Nazwa grupy> &8- &7Dodanie uprawnienia do grupy ",
            " &8* &a/bungee removepermgroup &7<Uprawnienie> <Nazwa grupy> &8- &7Usuwa uprawnienie z grupy ",
            " &8* &a/bungee setusergroup &7<Gracz> <Nazwa grupy> &8- &7Dodanie gracza do grupy",
            "&7");
    @ConfigurationSource(path = "help.whitelist")
    public List<String> whitelistHelp = Arrays.asList("&7",
            " &8* &a/whitelist <serwer> on &8- &7Włącza białą liste",
            " &8* &a/whitelist <serwer> off &8- &7Wyłącza białą liste",
            " &8* &a/whitelist <serwer> add &7<Gracz> &8- &7Dodaje gracza do białej listy",
            " &8* &a/whitelist <serwer> remove &7<Gracz> &8- &7Usuwa gracza z białej listy",
            " &8* &a/whitelist <serwer> list &8- &7Lista osób na białej liście",
            " &8* &a/whitelist <serwer> message set &7<Wiadomosc> &8- &7Powód wyrzucenia gracza gdy nie jest na białej liście",
            " &8* &a/whitelist <serwer> message get &8- &7Wyświetla aktualny powód wyrzucenia za białą liste");

    @ConfigurationSource(path = "group.create")
    public String createGroupSuccess = "&8&l[&2&l!&8&l] &7Grupa &a{GROUP} &7została &a&lstworzona&7!";
    @ConfigurationSource(path = "group.delete")
    public String deleteGroupSuccess = "&8&l[&2&l!&8&l] &7Grupa &a{GROUP} &7została &c&lusunieta&7!";
    @ConfigurationSource(path = "group.alreadyExists")
    public String groupAlreadyExists = "&8&l[&4&l!&8&l] &cGrupa o nazwie &6{GROUP} &cjuż istnieje!";
    @ConfigurationSource(path = "group.notExists")
    public String groupIsNotExists = "&8&l[&4&l!&8&l] &cGrupa o nazwie &6{GROUP} &cnie istnieje!";
    @ConfigurationSource(path = "group.rename")
    public String groupRenameSuccess = "&8&l[&2&l!&8&l] &7Nazwa grupy &a{OLDGROUP} &7została zmieniona na &a&l{NEWGROUP}&7!";
    @ConfigurationSource(path = "group.addPerm")
    public String addPermToGroupSuccess = "&8&l[&2&l!&8&l] &7Uprawnienie &a{PERMISSION} &7zostało dodanie do grupy &a&l{GROUP}&7!";
    @ConfigurationSource(path = "group.removePerm")
    public String removePermFromGroupSuccess = "&8&l[&2&l!&8&l] &7Uprawnienie &3{PERMISSION} &7zostało usunięte z grupy &a&l{GROUP}&7!";
    @ConfigurationSource(path = "group.addPlayerToGroup")
    public String userSetGroupSuccess = "&8&l[&2&l!&8&l] &7Gracz &a{PLAYER} &7został dodany do grupy &a&l{GROUP}&7!";

    @ConfigurationSource(path = "whitelist.serverNotFound")
    public String whitelistServerNotFound = "&8&l[&4&l!&8&l] &cSerwer o takiej nazwie nie istnieje!";

    @ConfigurationSource(path = "whitelist.alreadyEnable")
    public String whitelistIsEnable = "&8&l[&4&l!&8&l] &cBiała lista jest już włączona!";
    @ConfigurationSource(path = "whitelist.enable")
    public String whitelistEnableSuccess = "&8&l[&2&l!&8&l] &7Biała lista została &awłączona&7!";

    @ConfigurationSource(path = "whitelist.disable")
    public String whitelistDisableSuccess = "&8&l[&2&l!&8&l] &7Biała lista została &cwyłączona&7!";
    @ConfigurationSource(path = "whitelist.alreadyDisable")
    public String whitelistIsDisable = "&8&l[&4&l!&8&l] &cBiała lista jest już wyłączona!";

    @ConfigurationSource(path = "whitelist.listPlayers")
    public String whitelistList = "&8&l[&3&l!&8&l] &7Lista osób na białej liście: &a{LIST}";

    @ConfigurationSource(path = "whitelist.alreadyAddPlayer")
    public String whitelistAlreadyAdd = "&8&l[&4&l!&8&l] &cGracz jest już na białej liście!";

    @ConfigurationSource(path = "whitelist.alreadyRemovePlayer")
    public String whitelistAlreadyRemove = "&8&l[&4&l!&8&l] &cGracz nie jest na białej liście!";

    @ConfigurationSource(path = "whitelist.addPlayer")
    public String whitelistAddSuccess = "&8&l[&4&l!&8&l] &7Gracz &a{PLAYER} &7został &adodany &7do białej listy!";

    @ConfigurationSource(path = "whitelist.removePlayer")
    public String whitelistRemoveSuccess = "&8&l[&2&l!&8&l] &7Gracz &a{PLAYER} &7został &cusunięty &7z białej listy!";

    @ConfigurationSource(path = "whitelist.getKickMessage")
    public String whitelistGet = "&8&l[&3&l!&8&l] &7Powód kicka za białą liste: &f{REASON}";

    @ConfigurationSource(path = "whitelist.setKickMessage")
    public String whitelistReasonSet = "&8&l[&2&l!&8&l] &7Kick za białą listę został ustawiony: &f{REASON}";

    @ConfigurationSource(path = "broadcast.format")
    public String broadcastFormat = "&8[&4&lOGLOSZENIE&8] &f{MESSAGE}";
    @ConfigurationSource(path = "broadcast.adminChatFormat")
    public String adminchatFormat = "&8[&e&lAdminChat&8] &f{PLAYER} &8&l%> &c{MESSAGE}";
}
