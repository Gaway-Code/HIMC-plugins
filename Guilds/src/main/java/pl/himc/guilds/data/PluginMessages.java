package pl.himc.guilds.data;

import org.bukkit.entity.Player;
import org.diorite.cfg.annotations.CfgCollectionStyle;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.TitleAPI;

import java.util.Arrays;
import java.util.List;

public class PluginMessages {

    public String createGuildToOwner = "&8[&aGILDIE&8] &7Stworzyles gildie &2{GUILD} &8(&a{TAG}&8)&7. Gildia jest wazna do &e{DATE}&7. Przedluz gildie uzywajac komendy &6/g przedluz";
    public String deleteGuildSuccess = "&8[&aGILDIE&8] &7Usunales gildie &c{GUILD} &8(&c{TAG}&8)&7!";
    public String regionEnlargeSuccess = "&8[&aGILDIE&8] &7Powiekszono teren gildii! Aktualnie wynosi on &2{LENGHT}&8x&2{LENGHT} &7kratek.";
    public String setHomeGuildSuccess = "&8[&aGILDIE&8] &aUstawiono dom gildii!";
    public String validitySuccess = "&8[&aGILDIE&8] &7Gildia zostala przedluzona do &2{DATA}&7!";

    public String joinMember = "&8[&aGILDIE&8] &7Dolaczyles do gildii &2{TAG}&7!";
    public String joinMemberToOwner = "&8[&aGILDIE&8] &7Gracz &a{PLAYER} &7dolaczyl do &aTwojej &7gildii! Ustaw mu uprawnienia pod &e/g panel&7.";
    public String kickFromGuildToKicked = "&8[&cGILDIE&8] &7Zostales &4wyrzucony &7z gildii &c{TAG}&7!";
    public String kickMemberFromGuild = "&8[&aGILDIE&8] &7Wyrzucono gracza &2{PLAYER} &7z gildii!";
    public String leaveFromGuild = "&8[&aGILDIE&8] &7Opusciles gildie &2{GUILD} &8(&a{TAG}&8)&7!";
    public String inviteCancelledInvite = "&8[&cGILDIE&8] &cZaproszenie zostalo cofniete!";
    public String inviteSendSuccess = "&8[&aGILDIE&8] &7Wyslano zaproszenie do gildii graczowi &2{PLAYER}&7!";
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> invteSendToInvited = Arrays.asList(
            "&8&m------------&a &lGILDIE&8 &m------------",
            " &8&l%> &7Zostales zaproszony do gildii &2{GUILD} &8(&a{TAG}&8)",
            " &8&l%> &7Wpisz &a&l/g dolacz {TAG} &7aby dolaczyc do gildii!",
            "&8&m------------&a &lGILDIE&8 &m------------");
    public String modRemoveSuccess = "&8[&aGILDIE&8] &7Usunieto gracza &2{PLAYER} &7z roli moderatora gildii!";
    public String modRemoveSuccessToDegraded = "&8[&cGILDIE&8] &7Zostales &4zdegradowany &7z roli moderatora w gildii &c{TAG}&7!";
    public String modAddSuccessToNewMod = "&8[&aGILDIE&8] &7Przyznano Ci role &2moderatora &7w gildii &a{TAG}&7!";
    public String ownerSetSuccessToNewOwner = "&8[&aGILDIE&8] &7Przyznano Ci role &2wlasciciela &7gildii &a{TAG}&7!";
    public String modAddSuccess = "&8[&aGILDIE&8] &7Nadano moderatora gildii graczowi &a{PLAYER}&7!";
    public String ownerSetSuccess = "&8[&aGILDIE&8] &7Nadano &2wlasciciela &7gildii graczowi &a{PLAYER}&7!";
    public String enterToRegionEnemyHasProtection = "&8[&cGILDIE&8] &cWkroczyles na teren gildii &4{TAG}&c. Gildia posiada ochrone przez &4{DATA}";
    public String allySuccessToOwners = "&8[&aGILDIE&8] &7Twoja gildia nazwiazala sojusz z gildia &2{GUILD} &8(&a{TAG}&8)&7!";
    public String allySendToInvite = "&8[&aGILDIE&8] &7Zaprosiles gildie &2{ALLY-GUILD} &8(&a{ALLY-TAG}&8) &7do sojuszu!";
    public String allyBreakToBreaked = "&8[&cGILDIE&8] &7Zerwano sojusz z gildia &4{GUILD} &8(&c{TAG}&8)&7!";
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> allySendToInvited = Arrays.asList("&8&m------------&6 &lSOJUSZ&8 &m------------",
            " &8&l%> &7Twoja gildia zostala zaproszona do sojuszu z gildia &a{TAG}",
            " &8&l%> &7Wpisz &e&l/g sojusz {TAG} &7aby zaakceptowac sojusz!",
            "&8&m------------&6 &lSOJUSZ&8 &m------------");
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> mainHelpGuild = Arrays.asList(
            "&8&m--------------&8[ &6&lGildie &8]&m--------------",
            " &8&l%> &e/g zaloz [tag] [nazwa] &8- &7Tworzy gildie",
            " &8&l%> &e/g usun &8- &7Usuwa gildie",
            " &8&l%> &e/g zapros [gracz] &8- &7Zaprasza gracza do gildii",
            " &8&l%> &e/g dolacz [tag] &8- &7Przyjmuje zaproszenie do gildii",
            " &8&l%> &e/g wyrzuc [gracz] &8- &7Wyrzuca gracza z gildii",
            " &8&l%> &e/g opusc &8- &7Opuszcza gildie",
            " &8&l%> &e/g dom &8- &7Teleportuje do domu gildii",
            " &8&l%> &e/g ustawdom &8- &7Ustawia dom gildii",
            " &8&l%> &e/g lider [gracz] &8- &7Oddaje wlasciciela gildii",
            " &8&l%> &e/g mod [gracz] &8- &7Nadaje moderatora czlonkowi gildii",
            " &8&l%> &e/g powieksz &8- &7Powieksza teren gildii",
            " &8&l%> &e/g przedluz &8- &7Przedluza waznosc gildii",
            " &8&l%> &e/g pvp &8- &7Zmienia status pvp w gildii",
            " &8&l%> &e/g sojusz [tag] &8- &7Nawiazanie sojuszu z gildia",
            " &8&l%> &e/g rozwiaz [tag] &8- &7Rozwiazuje sojusz gildii",
            " &8&l%> &e/g info [tag] &8- &7Informacje o gildii",
            " &8&l%> &e/gracz [gracz] &8- &7Informacje o rankingu gracza",
            " &8&l%> &e/g skarbiec &8- &7Skarbiec gildii",
            " &8&l%> &e/g panel &8- &7Zarzadzanie gildia");
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> guildInfo = Arrays.asList(
            " &8&l%> &7Gildia: &6{GUILD} &8[&e{TAG}&8]",
            " &8&l%> &7Zalozyciel: &6{OWNER}",
            " &8&l%> &7Punkty: &6{POINTS}",
            " &8&l%> &7K/D Ratio: &6{KDR}",
            " &8&l%> &7Miejsce: &6{POSITION}",
            " &8&l%> &7Zabojstwa: &6{KILLS}",
            " &8&l%> &7Smierci: &6{DEATHS}",
            " &8&l%> &7Zycia: &6{LIVES}",
            " &8&l%> &7Waznosc: &6{VALIDITY}",
            " &8&l%> &7Mozliwosc ataku: &6{PROTECTION}",
            " &8&l%> &7Czlonkowie: &7{MEMBERS}",
            " &8&l%> &7Sojusze: &e{ALLIANCES}");
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> guildInfoHologram = Arrays.asList(
            " &8&l%> &7Gildia: &6{GUILD} &8[&e{TAG}&8]",
            " &8&l%> &7Zalozyciel: &6{OWNER}",
            " &8&l%> &7Punkty: &6{POINTS}",
            " &8&l%> &7K/D Ratio: &6{KDR}",
            " &8&l%> &7Miejsce: &6{POSITION}",
            " &8&l%> &7Zabojstwa: &6{KILLS}",
            " &8&l%> &7Smierci: &6{DEATHS}",
            " &8&l%> &7Zycia: &6{LIVES}",
            " &8&l%> &7Waznosc: &6{VALIDITY}",
            " &8&l%> &7Mozliwosc ataku: &6{PROTECTION}",
            " &8&l%> &7Czlonkowie: &7{MEMBERS}",
            " &8&l%> &7Sojusze: &e{ALLIANCES}");
    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> playerInfo = Arrays.asList(
            " &8&l%> &7Gracz: &6{PLAYER}",
            " &8&l%> &7Punkty: &6{POINTS}",
            " &8&l%> &7Zabojstwa: &6{KILLS}",
            " &8&l%> &7Smierci: &6{DEATHS}",
            " &8&l%> &7K/D Ratio: &6{KDR}",
            " &8&l%> &7Gildia: &6{GUILD} &8(&6{TAG}&8)",
            " &8&l%> &7Za zabicie tego gracza dostaniesz: &a+{KILLPOINTS} pkt",
            " &8&l%> &7Jesli gracz Cie zabije utracisz: &c-{LOSEPOINTS} pkt");
    public String playerNotHasPermission = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do tej komendy!";
    public String badArgs = "&8[&cGILDIE&8] &cPodano zla komende! Wpisz &6/gildia &caby uzyskac pomoc!";
    public String playerIsNotExists = "&8[&cGILDIE&8] &cTen gracz nie istnieje!";
    public String playerIsOffline = "&8[&cGILDIE&8] &cTen gracz nie jest online!";
    public String invalidInteger = "&8[&cGILDIE&8] &cPodany argument nie jest liczba!";
    public String invalidTime = "&8[&cGILDIE&8] &cPodano nieprawidlowy czas!";
    public String disableCreateGuild = "&8[&cGILDIE&8] &cGILDIE sa obecnie &4wylaczone&c!";
    public String pvpIsOffInGuild = "&8[&cGILDIE&8] &cPVP w gildii jest wylaczone!";
    public String pvpIsOffInAlly = "&8[&cGILDIE&8] &cPVP w &6sojuszu &cjest wylaczone!";
    public String lastKillByKiller = " &4✞ &cOstatnio zabiles tego gracza. Punkty nie zostana przyznane!";
    public String victimHasSameIpKiller = " &4✞ &cZabity gracz ma to samo IP co ty. Punkty nie zostana przyznane!";
    public String regionDisableCommand = "&8[&cGILDIE&8] &cTa komenda jest wylaczona na terenie wrogiej gildii!";
    public String regionEnemyGuildBuild = "&8[&cGILDIE&8] &cNie mozna budowac na terenie wrogiej gildii!";
    public String regionCenterBuild = "&8[&cGILDIE&8] &cNie mozna budowac w centrum gildii!";
    public String regionExplodeBuild = "&8[&cGILDIE&8] &cNa terenie gildii wybuchlo &4TNT&c! Nie mozna budowac przez &4{TIME}&c.";

    public String ownerIsAlreadyOwner = "&8[&cGILDIE&8] &cTen gracz jest juz wlascicielem gildii!";
    public String dontHaveGuild = "&8[&cGILDIE&8] &cNie posiadasz gildii!";
    public String youHaveGuild = "&8[&cGILDIE&8] &cPosiadasz juz gildie!";
    public String youDontOwnerGuild = "&8[&cGILDIE&8] &cNie jestes wlascicielem gildii!";
    public String youDontOwnerOrModGuild = "&8[&cGILDIE&8] &cNie jestes wlascicielem lub moderatorem gildii!";
    public String guildNotExists = "&8[&cGILDIE&8] &cGildia o podanym tagu nie istnieje!";

    public String allySame = "&8[&cGILDIE&8] &cNie mozesz miec sojuszu z wlasna gildia!";
    public String allyIsAlready = "&8[&cGILDIE&8] &cPosiadasz juz sojusz z ta gildia!";
    public String yourGuildMaxAlly = "&8[&cGILDIE&8] &cPosiadasz juz maksymalna ilosc sojuszy! &8(&cmax. {LENGHT}&8)";
    public String otherGuildMaxAlly = "&8[&cGILDIE&8] &cTa gildia posiada maksymalna ilosc sojuszy! &8(&cmax. {LENGHT}&8)";
    public String playerNotHasGuildWar = "&8[&cGILDIE&8] &cMusisz posiadac gildie aby zaatakowac inna!";
    public String guildHasProtectWar = "&8[&cGILDIE&8] &cTa gildia posiada ochrone! Mozna ja zaatakowac za &4{TIME}&c!";
    public String allyOwnerGuildIsNotOnline = "&8[&cGILDIE&8] &cWlasciciel tej gildii nie jest online!";
    public String yourGuildNotHasAlly = "&8[&cGILDIE&8] &cNie posiadasz sojuszu z ta gildia!";
    public String allyGuildWar = "&8[&cGILDIE&8] &cNie mozna atakowac sojuszniczej gildii!";
    public String nameGuildMin = "&8[&cGILDIE&8] &cNazwa gildii musi posiadac minimum &6{LENGHT} znaki&c!";
    public String nameGuildMax = "&8[&cGILDIE&8] &cNazwa gildii musi posiadac do &6{LENGHT} znakow&c!";
    public String tagGuildMin = "&8[&cGILDIE&8] &cTag gildii musi posiadac minimum &6{LENGHT} znaki&c!";
    public String tagGuildMax = "&8[&cGILDIE&8] &cTag gildii musi posiadac do &6{LENGHT} znakow&c!";
    public String nameAndTagGuildOnlyLetters = "&8[&cGILDIE&8] &cNazwa gildii i tag moga posiadac tylko litery!";
    public String guildNameAlreadyExists = "&8[&cGILDIE&8] &cGildia o podanej nazwie juz istnieje!";
    public String guildTagAlreadyExists = "&8[&cGILDIE&8] &cGildia o podanym tagu juz istnieje!";
    public String createGuildDistanceSpawn = "&8[&cGILDIE&8] &cJestes za blisko spawnu!";
    public String createGuildDistanceOtherGuild = "&8[&cGILDIE&8] &cJestes za blisko innej gildii!";
    public String regionMaxSize = "&8[&cGILDIE&8] &cOsiagnieto maksymalny rozmiar cuboida! &8(&c{LENGHT}x{LENGHT}&8)";
    public String invitedPlayerHasGuild = "&8[&cGILDIE&8] &cTen gracz posiada juz gildie!";
    public String yourGuildMaxMembers = "&8[&cGILDIE&8] &cPosiadasz maksymalna ilosc czlonkow w gildii!";
    public String guildMaxMembers = "&8[&cGILDIE&8] &cTa gildia posiada maksymalna ilosc czlonkow!";
    public String joinNotInvited = "&8[&cGILDIE&8] &cNie posiadasz zaproszenia od tej gildii!";
    public String playerIsNotMemberInYourGuild = "&8[&cGILDIE&8] &cTen gracz nie jest czlonkiem Twojej gildii!";
    public String kickPlayerIsOwnerGuild = "&8[&cGILDIE&8] &cNie mozesz wyrzucic wlasciciela gildii!";
    public String leaveGuildOwner = "&8[&cGILDIE&8] &cNie mozesz opuscic gildii, poniewaz jestes jej wlascicielem!";
    public String playerIsOwnerGuild = "&8[&cGILDIE&8] &cTen gracz jest wlascicielem gildii!";
    public String maxMods = "&8[&cGILDIE&8] &cOsiagnieto maksymalna ilosc moderatorow gildii! &8(&c{LENGHT}&8)";
    public String setHomeOutSideRegion = "&8[&cGILDIE&8] &cNie mozesz ustawic domu gildii poza jej regionem!";
    public String validityGuildMax = "&8[&cGILDIE&8] &cGILDIE mozesz przedluzc za &6{TIME}&c!";
    public String deleteGuildFirst = "&8[&cGILDIE&8] &7Napierw musisz wpisac &c/g usun&7!";

    public String noHasPermBuild = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4stawiania blokow &cna terenie gildii! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermTntPlace = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4stawiania tnt &cna terenie gildii! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermBreak = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4niszczenia blokow &cna terenie gildii! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermOpenChest = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4otwierania skrzynek &cna terenie gildii! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermOpenBeacon = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4korzystania z beacona &cna terenie gildii! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermOpenTreasure = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4otwierania skarbca gildii&c! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermTpHome = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4teleportacji do bazy gildii&c! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermFluid = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4uzywania wiaderka&c! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermTpaccept = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4uzywania /tpaccept&c! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermAddMembers = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4dodawania czlonkow do gildii&c! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermKickMembers = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4wyrzucania czlonkow z gildii&c! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";
    public String noHasPermGuildValidity = "&8[&cGILDIE&8] &cNie posiadasz uprawnien do &4przedluzania waznosci gildii&c! Zglos sie do &6wlasciciela gildii &co dodatkowe uprawnienia.";

    public String warAttackedGuildToMembers = "&4&lUWAGA! &cTwoja gildia zostala zaatakowana przez gildie &c{TAG}&c! Twoja gildia zostala pozbawiona jednego zycia.";
    public String warAttackedGuildLoseToMembers = "&4&lUWAGA! &cTwoja gildia przegrala wojne z gildia &c{TAG}&c! Gildia zostala zniszczona.";
    public String warAttackerGuildToMembers = "&4&lUWAGA! &cTwoja gildia zaatakowala gildie &c{TAG}&c!";
    public String warAttackerWinToMembers = "&4&lUWAGA! &cTwoja gildia wygrala wojne z gildia &c{TAG}&c! &aZyskujecie jedno zycie!";
    public String deleteConfirm = "&4&lUWAGA! &7Potwierdz usuniecie gildii: &c/g usun potwierdz";

    public String guildDeleteBroadcast = "&8[&cGILDIE&8] &7Gracz &c{PLAYER} &7usunal gildie &4{GUILD} &8(&c{TAG}&8)&7!";
    public String guildExpiredBroadcast = "&8[&cGILDIE&8] &7Gildia &4{GUILD} &8(&c{TAG}&8) &7wygasla! Jej baza znajduje sie na koordach: X: &2{X}&7, Y: &2{Y}&7, Z: &2{Z}&7!";
    public String warWinGuildBroadcast = "&8[&cGILDIE&8] &7Gildia &c{LOSE-TAG} &7przegrala wojne z gildia &c{WIN-TAG}&7!";
    public String warGuildBroadcast = "&8[&cGILDIE&8] &7Gildia &c{LOSE-TAG} &7zostala zaatakowana przez gildie &c{WIN-TAG}&7!";
    public String createGuildBroadcast = "&8[&aGILDIE&8] &7Gracz &2{PLAYER} &7stworzyl gildie &a{GUILD} &8(&a{TAG}&8)&7!";
    public String kickFromGuildBroadcast = "&8[&cGILDIE&8] &7Gracz &c{PLAYER} &7zostal wyrzucony z gildii &4{TAG}&7!";
    public String joinToGuildBroadcast = "&8[&aGILDIE&8] &7Gracz &2{PLAYER} &7dolaczyl do gildii &a{TAG}&7!";
    public String allySuccessBroadcast = "&8[&aGILDIE&8] &7Gildia &2{GUILD} &8(&a{TAG}&8) &7nazwiazala sojusz z gildia &2{ALLY-GUILD} &8(&a{ALLY-TAG}&8)&7!";
    public String allyBreakBroadcast = "&8[&cGILDIE&8] &7Gildia &4{GUILD} &8(&c{TAG}&8) &7zerwala sojusz z gildia &4{ALLY-GUILD} &8(&c{ALLY-TAG}&8)&7!";
    public String leavePlayerFromGuildBroadcast = "&8[&cGILDIE&8] &7Gracz &c{PLAYER} &7opuscil gildie &4{GUILD} &8(&c{TAG}&8)&7!";
    public String pvpOnToMembers = "&8[&aGILDIE&8] &7PVP w gildii zostalo &2&lWLACZONE&7!";
    public String pvpOffToMembers = "&8[&cGILDIE&8] &7PVP w gildii zostalo &4&lWYLACZONE&7!";
    public String explodeTntToMembers = "&8[&cGILDIE&8] &cNa terenie gildii wybuchlo TNT! Nie mozna budowac przez &4{TIME}&c!";
    public String killAsistBroadcast = " &6[ASYSTA] {TAG}&e{PLAYER} ({+})";
    public String killPlayerBroadcast = " &4✞ {VTAG}&6{VICTIM} &8[&c-{-}&8] &czostal zabity przez {KTAG}&6{KILLER} &8[&a+{+}&8]";

    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> guildAdminHelp = Arrays.asList("&8&m----------&8[ &a&lPOMOC DLA ADMINISTRATORA &8]&M----------",
            " &8&l%> &6/ga tp [tag] &8- &7Teleportuje do gildii",
            " &8&l%> &6/ga waznosc [tag] [waznosc] &8- &7Ustawia waznosc gildii",
            " &8&l%> &6/ga wyrzuc [gracz] &8- &7Wyrzuca gracza z gildii",
            " &8&l%> &6/ga dodaj [nick] [tag] &8- &7Dodaje gracza do gildii",
            " &8&l%> &6/ga usun [tag] &8- &7Usuwa gildie",
            " &8&l%> &6/ga zabojstwa [nick] [ilosc] &8- &7Ustawia liczbe zabojstw",
            " &8&l%> &6/ga punkty [nick] [ilosc] &8- &7Ustawia liczbe zgonow",
            " &8&l%> &6/ga unban [tag] &8- &7Odbanowuje gildie",
            " &8&l%> &6/ga zycia [tag] [ilosc] &8- &7Ustawia zycia gildii",
            " &8&l%> &6/ga lider [nick] &8- &7Ustawia lidera gildii",
            " &8&l%> &6/ga ban [tag] [czas] [powod] &8- &7Banuje gildie",
            " &8&l%> &6/ga itemy [nick] &8- &7Daje itemy na gildie",
            " &8&l%> &6/ga turbodrop [tag] &8- &7Daje turbodrop gildii",
            " &8&l%> &6/ga turboexp [tag] &8- &7Daje turboexp gildii",
            "&8&m----------&8[ &a&lPOMOC DLA ADMINISTRATORA &8]&M----------");
    public String gaBadArgs = "&8[&cGuildAdmin&8] &4Podano zly argument! Wpisz &c/ga";
    public String gaNumberGreaterThanZero = "&8[&cGuildAdmin&8] &cPodana liczba musi byc wieksza niz zero!";
    public String gaKickOwnerGuild = "&8[&cGuildAdmin&8] &cNie mozesz wyrzucic wlasciciela gildii!";
    public String gaPlayerHasGuild = "&8[&cGuildAdmin&8] &cTen gracz posiada juz gildie!";
    public String gaPlayerNotHasGuild = "&8[&cGuildAdmin&8] &cTen gracz nie posiada gildii!";
    public String gaPlayerAlreadyOwnerGuild = "&8[&cGuildAdmin&8] &cGracz jest juz wlascicielem gildii!";
    public String gaPlayerDeathsSet = "&8[&aGuildAdmin&8] &7Ustawiono ilosc zgonow gracza &6{PLAYER} &7na &6{DEATHS}&7!";
    public String gaPlayerKillsSet = "&8[&aGuildAdmin&8] &7Ustawiono ilosc zabojstw gracza &6{PLAYER} &7na &6{KILLS}&7!";
    public String gaPlayerPointsSet = "&8[&aGuildAdmin&8] &7Ustawiono ilosc punktow gracza &6{PLAYER} &7na &6{POINTS}&7!";
    public String gaGuildNotExists = "&8[&cGuildAdmin&8] &cNie znaleziono gildii o takim tagu!";
    public String gaGuildLivesSet = "&8[&aGuildAdmin&8] &7Ustawiono ilosc zyc gildii &6{GUILD} &8(&6{TAG}&8) &7na &6{LIVES}&7!";
    public String gaGuildOwnerSet = "&8[&aGuildAdmin&8] &7Ustawiono nowego wlasciciela gildii &6{GUILD} &8(&6{TAG}&8) &7graczowi &6{PLAYER}&7!";
    public String gaGuildValiditySet = "&8[&aGuildAdmin&8] &7Ustawiono waznosc gildii &6{GUILD} &8(&6{TAG}&8) &7na &6{DATA}&7!";
    public String gaGuildDeleteSuccess = "&8[&aGuildAdmin&8] &7Usunieto gildie &6{GUILD} &8(&6{TAG}&8)&7!";
    public String gaGuildTeleportSuccess = "&8[&aGuildAdmin&8] &7Przeniesiono do gildii &6{GUILD} &8(&6{TAG}&8)&7!";
    public String gaGiveItems = "&8[&aGuildAdmin&8] &7Przedmioty na gildie zostaly dane graczowi &a{PLAYER}&7!";
    public String gaGuildSetWar = "&8[&aGuildAdmin&8] &7Ustawiono ochrone gildii &6{GUILD} &8(&6{TAG}&8) &7na &6{DATA}&7!";

    public String enterToRegionEnemy = "1;40;1;&7;&8* &cWkroczyłeś na teren gildii &c{TAG} &8*";
    public String enterToRegionSendEnemys = "1;40;1;&6;&8* &c&oWróg &4&o{PLAYER} &c&owkroczył na teren Twojej gildii! &8*";
    public String leaveFromRegionEnemy = "1;40;1;&8;&8* &7&oOpuściłeś teren gildii &4&o{TAG} &8*";
    public String leaveFromRegionMember = "1;40;1;&7;&8* &7&oOpuściłeś teren &a&oswojej gildii &8*";
    public String leaveFromRegionAlly = "1;40;1;&7;&8* &7&oOpuściłeś teren sojuszu &6&o{TAG} &8*";
    public String enterRegionMember = "1;40;1;&7;&8* &7&oWkroczyłeś na teren &a&oswojej gildii &8*";
    public String enterToRegionAlly = "1;40;1;&8;&8* &7&oWkroczyłeś na teren sojuszu &e&o{TAG} &8*";
    public String enterToRegionSendAllys = "1;40;1;&2;&8* &7&oSojusznik &e&o{PLAYER} &7&owkroczył na teren Twojej gildii &8*";
    public String killAsistTitle = "1;47;1;&4✞;&7&oAsystowales przy zabiciu gracza &a&o{PLAYER} &8&o[&a&o+{+} pkt&8&o]";
    public String killPlayerTitle = "1;47;1;&4✞;&7&oZabiłeś gracza &a&o{PLAYER} &8&o[&a&o+{+} pkt&8&o]";

    public void parseTitle(Player player, String lang) {
        String[] parse = lang.split(";");
        int fadeIn = Integer.parseInt(parse[0]);
        int stay = Integer.parseInt(parse[1]);
        int fadeOut = Integer.parseInt(parse[2]);
        String title = ChatUtil.fixColor(parse[3]);
        String subtitle = ChatUtil.fixColor(parse[4]);
        TitleAPI.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
    }
}
