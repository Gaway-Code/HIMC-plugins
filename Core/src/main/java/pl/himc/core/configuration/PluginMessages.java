package pl.himc.core.configuration;

import org.diorite.cfg.annotations.CfgCollectionStyle;

import java.util.Arrays;
import java.util.List;

public final class PluginMessages {

    public String backupRestore = "&8&l[&a&l!&8&l] &aBackup zostal &2przywrocony&a!";
    public String clearEq = "&8&l[&a&l!&8&l] &7Twoj ekwipunek zostal &2wyczyszczony&7!";
    public String clearEqOtherToPlayer = "&8&l[&a&l!&8&l] &7Twoj ekwipunek zostal &2wyczyszczony &7przez &6{ADMIN}&7!";
    public String clearEqOtherToAdmin = "&8&l[&a&l!&8&l] &7Ekwipunek gracza &6{PLAYER} &7zostal &2wyczyszczony&7!";
    public String enchantSuccess = "&8&l[&a&l!&8&l] &7Na Twoj przedmiot zostalo dodane zaklecie &2{ENCHANT}&7!";
    public String flySetEnable = "&8&l[&a&l!&8&l] &7Tryb latania zostal &2wlaczony&7!";
    public String flySetDisable = "&8&l[&a&l!&8&l] &7Tryb latania zostal &4wylaczony&7!";
    public String flySetEnableOtherToPlayer = "&8&l[&a&l!&8&l] &7Tryb latania zostal Ci &2wlaczony &7przez &6{ADMIN}&7!";
    public String flySetDisableOtherToPlayer = "&8&l[&a&l!&8&l] &7Tryb latania zostal Ci &4wylaczony &7przez &6{ADMIN}&7!";
    public String flySetEnableOtherToAdmin = "&8&l[&a&l!&8&l] &2Wlaczyles &7tryb latania graczowi &6{PLAYER}&7!";
    public String flySetDisableOtherToAdmin = "&8&l[&a&l!&8&l] &4Wylaczyles &7tryb latania graczowi &6{PLAYER}&7!";
    public String gamemodeSet = "&8&l[&a&l!&8&l] &7Twoj tryb gry zostal zmieniony na &6{GAMEMODE}&7!";
    public String gamemodeSetOtherToPlayer = "&8&l[&a&l!&8&l] &7Twoj tryb gry zostal zmieniony na &6{GAMEMODE} &7przez &6{ADMIN}&7!";
    public String gamemodeSetOtherToAdmin = "&8&l[&a&l!&8&l] &7Zmieniles tryb gry gracza &6{PLAYER} &7na &6{GAMEMODE}&7!";
    public String giveItemSuccess = "&8&l[&a&l!&8&l] &7Otrzymales przedmiot &6{ITEM} x{AMOUNT} &7od &6{ADMIN}&7!";
    public String giveSkullItem = "&8&l[&a&l!&8&l] &7Otrzymales glowe gracza &2{PLAYER}&7!";
    public String healSuccess = "&8&l[&a&l!&8&l] &7Zostales &2uleczony&7!";
    public String healSuccessOtherToPlayer = "&8&l[&a&l!&8&l] &7Zostales &2uleczony &7przez &6{ADMIN}&7!";
    public String healSuccessOtherToAdmin = "&8&l[&a&l!&8&l] &7Uleczyles gracza &6{PLAYER}&7!";
    public String teleportToPlayer = "&8&l[&a&l!&8&l] &7Zostales przeniesiony do gracza &2{PLAYER}&7!";
    public String teleportPlayerToPlayer = "&8&l[&a&l!&8&l] &7Zostales przeniesiony do gracza &2{DOKOGO} &7przez &6{ADMIN}&7!";
    public String teleportPlayerToPlayerAdmin = "&8&l[&a&l!&8&l] &7Przeniesiono gracza &6{KTO} &7do gracza &6{DOKOGO}&7!";
    public String teleportKoords = "&8&l[&a&l!&8&l] &7Zostales przeniesiony na koordy: X: &2{X}&7, Y: &2{Y}&7, Z: &2{Z}&7!";
    public String teleportKoordsOtherToPlayer = "&8&l[&a&l!&8&l] &7Zostales przeniesiony na koordy: X: &2{X}&7, Y: &2{Y}&7, Z: &2{Z} &7przez &6{ADMIN}&7!";
    public String teleportKoordsOtherToAdmin = "&8&l[&a&l!&8&l] &7Przeniesiono gracza &6{PLAYER} &7na koordy: X: &2{X}&7, Y: &2{Y}&7, Z: &2{Z}&7!";
    public String teleportHere = "&8&l[&a&l!&8&l] &7Zostales przeniesiony do administratora &6{ADMIN}&7!";
    public String teleportHereAdmin = "&8&l[&a&l!&8&l] &7Przeniesiono gracza &6{PLAYER}&7!";
    public String spawnSetNewLocation = "&8&l[&a&l!&8&l] &7Ustawiono nowe miejsce spawnu na koordach: X: &2{X}&7, Y: &2{Y}&7, Z: &2{Z}&7!";
    public String spawnTeleportOtherToPlayer = "&8&l[&a&l!&8&l] &7Zostales przeniesiony na spawn przez &6{ADMIN}&7!";
    public String spawnTeleportOtherToAdmin = "&8&l[&a&l!&8&l] &7Przeniesiono gracza &6{PLAYER} &7na spawn!";
    public String helpopSendSuccess = "&8&l[&a&l!&8&l] &a&lWyslano wiadomosc do administracji!";
    public String repairItemsSuccess = "&8&l[&a&l!&8&l] &7Naprawiono &a{ITEMS} &7przedmiotow!";
    public String repairItemSuccess = "&8&l[&a&l!&8&l] &7Przedmiot w Twojej rece zostal &2naprawiony&7!";
    public String repairItemXPError = "&8&l[&4&l!&8&l] &cAby naprawic przedmiot musisz miec 50 XP.";
    public String kitGiveSuccess = "&8&l[&a&l!&8&l] &7Otrzymales zestaw &a{KIT}&7!";
    public String kitEditedSuccess = "&8&l[&a&l!&8&l] &7Zapisano zestaw &a{KIT}&7!";
    public String kitDeleteSuccess = "&8&l[&a&l!&8&l] &7Zestaw &a{KIT} &7zostal &4usuniety&7!";
    public String warpCreateSuccess = "&8&l[&a&l!&8&l] &7Warp o nazwie &2{WARP} &7zostal utworzony!";
    public String warpRemoveSuccess = "&8&l[&a&l!&8&l] &7Warp o nazwie &6{WARP} &7zostal &4usuniety&7!";
    public String godModeEnable = "&8&l[&a&l!&8&l] &7Niesmiertelnosc zostala &2wlaczona&7!";
    public String godModeDisable = "&8&l[&a&l!&8&l] &7Niesmiertelnosc zostala &4wylaczona&7!";
    public String godModeEnableOther = "&8&l[&a&l!&8&l] &7Niesmiertelnosc zostala &2wlaczona &7dla &6{PLAYER}&7!";
    public String godModeDisableOther = "&8&l[&a&l!&8&l] &7Niesmiertelnosc zostala &4wylaczona &7dla &6{PLAYER}&7!";
    public String vanishToggleOn = "&5&lVANISH &8&l[&a&l!&8&l] &7Jestes teraz &2niewidzialny&7!";
    public String vanishToggleOff = "&5&lVANISH &8&l[&a&l!&8&l] &7Jestes teraz &4widoczny&7!";
    public String blokiSuccess = "&8&l[&a&l!&8&l] &2&lPomyslnie &7wymieniono &6{ITEM} &7na bloki!";

    public String homeTeleportToHomeOther = "&8&l[&a&l!&8&l] &7Zostales &2przeniesiony &7do domu gracza &6{PLAYER}&7!";
    public String delHomeSuccess = "&8&l[&a&l!&8&l] &7Twoj dom zostal usuniety!";
    public String delHomeOtherPlayerSuccess = "&8&l[&a&l!&8&l] &7Dom gracza &6{PLAYER} &7o nazwie &6{HOME} &7zostal usuniety!";
    public String setHomeSuccess = "&8&l[&a&l!&8&l] &7Dom zostal &2ustawiony&7!";
    public String setHomeOtherPlayer = "&8&l[&a&l!&8&l] &7Ustawiono dom graczowi &6{PLAYER} &7o nazwie &6{HOME}&7!";
    public String setHomeNameSuccess = "&8&l[&a&l!&8&l] &7Dom o nazwie &6{HOME} &7zostal &2ustawiony&7!";
    public String feedSuccess = "&8&l[&a&l!&8&l] &7Twoj glod zostal &2uzupelniony&7!";
    public String feedOtherToPlayer = "&8&l[&a&l!&8&l] &7Twoj glod zostal &2uzupelniony &7przez &6{ADMIN}&7!";
    public String feedOtherToAdmin = "&8&l[&a&l!&8&l] &7Glod gracza &6{PLAYER} &7zostal &2uzupelniony&7!";
    public String msgEnable = "&8&l[&a&l!&8&l] &7Prywatne wiadomosci zostaly &2wlaczone&7!";
    public String msgDisable = "&8&l[&a&l!&8&l] &7Prywatne wiadomosci zostaly &4wylaczone&7!";
    public String socialspyEnable = "&8&l[&a&l!&8&l] &7Socialspy zostalo &2wlaczone&7!";
    public String socialspyDisable = "&8&l[&a&l!&8&l] &7Socialspy zostalo &4wylaczone&7!";
    public String tpacceptAcceptToPlayerInvited = "&8&l[&a&l!&8&l] &7Zaakceptowales prosbe o teleportacje gracza &6{PLAYER}&7!";
    public String tpacceptAcceptToPlayerSender = "&8&l[&a&l!&8&l] &7Gracz &6{PLAYER} &7zaakceptowal Twoja prosbe o teleportacje!";
    public String tpaSendSuccess = "&8&l[&a&l!&8&l] &7Wyslano prosbe o teleportacje do gracza &2{PLAYER}&7!";
    public String fakenameSet = "&8&l[&a&l!&8&l] &7Twoj nowy nick: &6{NICK}";
    public String fakenameUnSet = "&8&l[&a&l!&8&l] &7Usunieto Twoj nick!";
    public String fakenameUnSetOther = "&8&l[&a&l!&8&l] &7Usunieto nick gracza &6{PLAYER}&7!";
    public String fakenameSetOther = "&8&l[&a&l!&8&l] &7Nowy nick gracza &6{PLAYER} &7to &6{NICK}&7!";
    public String randomTpSuccess = "&8&l[&a&l!&8&l] &aZostales przeniesiony w losowe koordy!";
    public String withdrawKoxy = "&8&l[&a&l!&8&l] &7Wyplacono &a{LIMIT} &7koxow!";
    public String withdrawRefile = "&8&l[&a&l!&8&l] &7Wyplacono &a{LIMIT} &7refili!";
    public String withdrawPerly = "&8&l[&a&l!&8&l] &7Wyplacono &a{LIMIT} &7perel!";
    public String setSprawdzarka = "&8&l[&a&l!&8&l] &7Ustawiles nowe miejsce sprawdzania graczy X: &2{X}&7, Y: &2{Y}&7, Z: &2{Z}&7!";
    public String buyEffect = "&8&l[&a&l!&8&l] &7Kupiles efekt &2{EFFECT} &7na &2{TIME} minut!";
    public String turboDropPlayerToAdmin = "&8&l[&a&l!&8&l] &7Aktywowano turbodrop graczowi &6{PLAYER} &7na &6{DATA}";
    public String turboExpPlayerToAdmin = "&8&l[&a&l!&8&l] &7Aktywowano turboexp graczowi &6{PLAYER} &7na &6{DATA}";
    public String cobblexGive = "&8&l[&a&l!&8&l] &7Otrzymales cobblexa!";
    public String placeStoniarka = "&8&l[&a&l!&8&l] &aPostawiles generator &8kamienia&a!";
    public String throwRzucanetnt = "&8&l[&a&l!&8&l] &aRzuciles TNT!";
    public String placeBoyfarmer = "&8&l[&a&l!&8&l] &7Postawiles generator &9obsydianu&7!";
    public String placeSandfarmer = "&8&l[&a&l!&8&l] &7Postawiles generator &epiasku&7!";
    public String placeKopaczFosy = "&8&l[&a&l!&8&l] &7Postawiles &bkopacz fosy&7!";
    public String selectEnchantSuccess = "&8&l[&a&l!&8&l] &7Przedmiot zostal &6zaczarowany&7!";
    public String luckyBoxGive = "&8&l[&a&l!&8&l] &7Dales &eLucky&6Boxa &7x{AMOUNT} &7graczowi &6{PLAYER}&7!";
    public String regionGetStick = "&a&lREGIONY &8&l%> &7Otrzymales patyk do zaznaczenia regionu!";
    public String regionPosOneSet = "&a&lREGIONY &8&l%> &aPozycja 1 ustawiona!";
    public String regionPosTwoSet = "&a&lREGIONY &8&l%> &aPozycja 2 ustawiona!";
    public String regionDeleteSuccess = "&a&lREGIONY &8&l%> &aRegion zostal usuniety!";
    public String regionCreate = "&a&lREGIONY &8&l%> &aRegion &6{REGION} &azostal stworzony!";

    public String chatCooldown = "&8&l[&e&l!&8&l] &7Na czacie mozna pisac co &6{TIME}&7!";
    public String helpopCooldown = "&8&l[&e&l!&8&l] &7Na helpop mozna pisac co &6{TIME}&7!";
    public String warpList = "&8&l[&e&l!&8&l] &7Lista warpow: &a{WARPS}";
    public String banPlayerOnline = "&8&l[&e&l!&8&l] &7Zbanowano gracza &2online&7!";
    public String banPlayerOffline = "&8&l[&e&l!&8&l] &7Zbanowano gracza &4offline&7!";
    public String homeList = "&8&l[&e&l!&8&l] &7Twoje domy: &a{HOMES}";
    public String homeListOther = "&8&l[&e&l!&8&l] &7Domy gracza &6{PLAYER}&7: &a{HOMES}";
    public String antyLogoutIn = "&cWALKA TRWA &8- &3{TIME} sekund";
    public String antylogutOut = "&a&lMozesz sie wylogowac!";
    public String limitMaxKoxy = "&8&l[&e&l!&8&l] &7Przekroczyles limit &6koxow&7! Maksymalny limit to &c{LIMIT}&7, reszta zostala dodana do &c/schowek&7!";
    public String limitMaxRefile = "&8&l[&e&l!&8&l] &7Przekroczyles limit &erefili&7! Maksymalny limit to &c{LIMIT}&7, reszta zostala dodana do &c/schowek&7!";
    public String limitMaxPerly = "&8&l[&e&l!&8&l] &7Przekroczyles limit &9perel&7! Maksymalny limit to &c{LIMIT}&7, reszta zostala dodana do &c/schowek&7!";
    public String checkAllowLogoutSuspect = "&cZostales wyrzucony przez administratora sprawdzajacego.";
    public String dropLevelUp = "&8&l[&e&l!&8&l] &7Awansowales na &2{LEVEL} poziom &7kopania!";

    public String newYoutuberBroadcast = "&8[&7&lYou&c&lTuber&8] &aGracz &6{PLAYER} &aotrzymal range /&7y&ct&7!";
    public String newYoutuberPlusBroadcast = "&8[&7&lYou&c&lTuber&d&l+&8] &aGracz &6{PLAYER} &aotrzymal range /&7y&ct&dplus&7!";
    public String daySetBroadcast = "&7Na serwerze zostal ustawiony &adzien&7!";
    public String nightSetBroadcast = "&7Na serwerze zostala ustawiona &9noc&7!";
    public String cooldownChatBroadcast = "&8&l[&b&l!&8&l] &a&oPisać na czacie można co &7{COOLDOWN}&a&o! Zmiany dokonał administrator &6{PLAYER}&a&o.";
    public String levelChatBroadcast = "&8&l[&b&l!&8&l] &a&oPisać na czacie można od &7{LEVEL} poziomu kopania! &a&oZmiany dokonal administrator &6{PLAYER}&a&o.";
    public String enableChatBroadcast = "&8&l[&b&l!&8&l] &a&oCzat został odblokowany przez &6{PLAYER}&a&o!";
    public String disableChatBroadcast = "&8&l[&b&l!&8&l] &c&oCzat został zablokowany przez &6{PLAYER}&c&o!";
    public String clearChatBroadcast = "&8&l[&b&l!&8&l] &a&oCzat został wyczyszczony przez &6{PLAYER}&a&o!";
    public String vanishToggleOnToAdmins = "&7Administrator &6&l{PLAYER} &7jest teraz &a&lUKRYTY&7!";
    public String vanishToggleOffToAdmins = "&7Administrator &6&l{PLAYER} &7jest teraz &4&lWIDOCZNY&7!";
    public String permBanBroadcast = "&4&lBAN &8%> &cGracz &6{PLAYER} &czostal zbanowany przez &6{ADMIN}&c! &cPowod: &6{REASON}&c.";
    public String tempBanBroadcast = "&4&lBAN &8%> &cGracz &6{PLAYER} &czostal zbanowany przez &6{ADMIN}&c! &cPowod: &6{REASON}&c. Ban wygasa &6{DATA}&c.";
    public String unBanBroadcast = "&4&lBAN &8%> &cGracz &6{PLAYER} &czostal odbanowany przez &6{ADMIN}&c!";
    public String unBanAllBroadcast = "&4&lBAN &8%> &cAdministrator &6{ADMIN} &codbanowal wszystkich graczy!";
    public String bannedPlayerIsLogin = "&4&lBAN &8%> &cGracz &6{PLAYER} &cprobowal wejsc na serwer ale jest zbanowany. Informacje o banie &6/baninfo {PLAYER}";
    public String bannedPlayerLoginExpire = "&4&lBAN &8%> &cGraczowi &6{PLAYER} &cwygasl tymczasowy ban!";
    public String permMuteBroadcast = "&4&lMUTE &8%> &cGracz &6{PLAYER} &czostal wyciszony przez &6{ADMIN}&c! &cPowod: &6{REASON}&c.";
    public String tempMuteBroadcast = "&4&lMUTE &8%> &cGracz &6{PLAYER} &czostal wyciszony przez &6{ADMIN}&c! &cPowod: &6{REASON}&c. Wygasa &6{DATA}&c.";
    public String unMuteBroadcast = "&4&lMUTE &8%> &cGraczowi &6{PLAYER} &czostal odblokowany czat przez &6{ADMIN}&c!";
    public String unMuteAllBroadcast = "&4&lMUTE &8%> &cAdministrator &6{ADMIN} &codblokowal kazdemu czat!";
    public String kickPlayerBroadcast = "&4&lKICK &8%> &cAdministrator &6{ADMIN} &cwyrzucil gracza &6{PLAYER} &cz serwera! Powod: &6{REASON}";
    public String antylogoutPlayerLogout = "&4AntyLogout &8%> &cGracz &6{PLAYER} &cwylogowal sie podczas walki!";
    public String checkSuspectBroadcast = "&4Sprawdzanie &8%> &7Gracz &6{PLAYER} &7jest podejrzany o cheaty! Administrator sprawdzajacy &6{ADMIN}&7.";
    public String checkSuspectBanBroadcast = "&4Sprawdzanie &8%> &7Gracz &6{PLAYER} &7zostal zbanowany za cheaty! Administrator sprawdzajacy &6{ADMIN}&7.";
    public String checkSuspectClearBroadcast = "&4Sprawdzanie &8%> &7Gracz &6{PLAYER} &7nie posiada cheatow! Administrator sprawdzajacy &6{ADMIN}&7.";
    public String checkSuspectLogoutBroadcast = "&4Sprawdzanie &8%> &7Gracz &6{PLAYER} &7wylogowal sie podczas sprawdzania!";
    public String buyEffectBroadcast = "&9Efekty &8%> &7Gracz &6{PLAYER} &7zakupil efekt &6{EFFECT} &7na &6{TIME} minut&7!";
    public String levelMinerUpBroadcast = "&8%> &7Gracz &e{PLAYER} &7awansowal na &2{LEVEL} poziom &7kopania!";
    public String cobblexOpen = "&a&lCobbleX &8%> &7Wylosowales &2{ITEM} &7x&2{AMOUNT}&7.";
    public String luckyBoxOpen = "&e&lLucky&6&lBox &8%> &7Wylosowales &6{ITEM} &7x&6{AMOUNT}&7.";

    public String playerNotHasPermission = "&8&l[&4&l!&8&l] &cNie posiadasz uprawnien do tej komendy! &8(&6{PERM}&8)";
    public String playerIsOffline = "&8&l[&4&l!&8&l] &cTen gracz jest &4offline&c!";
    public String playerIsNotExists = "&8&l[&4&l!&8&l] &cTego gracza nie bylo na serwerze!";
    public String backupNull = "&8&l%> &cNie znaleziono backupa!";
    public String invalidInteger = "&8&l[&a&l!&8&l] &cPodany argument nie jest liczba!";
    public String invalidTime = "&8&l[&4&l!&8&l] &cPodany argument jest zlym czasem!";
    public String enchantNotFound = "&8&l[&4&l!&8&l] &cNie znaleziono takiego zaklecia!";
    public String enchantNotNullItem = "&8&l[&4&l!&8&l] &cMusisz trzymac w reku przedmiot ktory da sie zenchantowac!";
    public String gamemodeInvalid = "&8&l[&4&l!&8&l] &cPodano zly tryb gry!";
    public String giveInvalidItem = "&8&l[&4&l!&8&l] &cNie znaleziono takiego przedmiotu!";
    public String giveItemError = "&8&l[&4&l!&8&l] &cNie udalo sie otrzymac przedmiotu! Sprobuj ponownie.";
    public String vanishDropItems = "&8&l[&4&l!&8&l] &cNie mozesz wyrzucac przedmiotow na VANISHU!";
    public String invSeeSamePlayer = "&8&l[&4&l!&8&l] &cNie mozesz edytowac swojego ekwipunku!";
    public String teleportYourselfError = "&8&l[&4&l!&8&l] &cNie mozesz teleportowac sam siebie!";
    public String itemShopNotFound = "&8&l[&4&l!&8&l] &cNie znaleziono takiej uslugi!";
    public String chatIsAlreadyEnable = "&8&l[&4&l!&8&l] &cCzat jest juz &awlaczony&c!";
    public String chatIsAlreadyDisable = "&8&l[&4&l!&8&l] &cCzat jest juz &4wylaczony&c!";
    public String chatIsDisable = "&8&l[&4&l!&8&l] &cCzat jest aktualnie &4wylaczony&c!";
    public String repairItemsNull = "&8&l[&4&l!&8&l] &cZadne przedmioty nie wymagaja naprawy.";
    public String repairItemNull = "&8&l[&4&l!&8&l] &cNie mozna naprawic tego przedmiotu!";
    public String setHomeBedrockError = "&8&l[&a&l!&8&l] &cNie mozesz ustawic domu na bedrocku!";
    public String kitIsNotExists = "&8&l[&4&l!&8&l] &cZestaw o takiej nazwie nie istnieje!";
    public String kitIsAlreadyExists = "&8&l[&4&l!&8&l] &cZestaw o takiej nazwie juz istnieje!";
    public String kitNameOnlyLettersAndNumers = "&8&l[&4&l!&8&l] &cNazwa zestawu powinna zawierac tylko litery oraz cyfry!";
    public String kitIconNull = "&8&l[&4&l!&8&l] &cMusisz trzymac w dloni przedmiot ktory bedzie ikona pod /kit";
    public String kitIsDisable = "&8&l[&4&l!&8&l] &cTen zestaw jest &4wylaczony&c!";
    public String kitCooldownTime = "&8&l[&4&l!&8&l] &cTen zestaw mozesz odebrac za &4{TIME}&c!";
    public String warpAlreadyExists = "&8&l[&4&l!&8&l] &cWarp o takiej nazwie juz istnieje!";
    public String warpNotExists = "&8&l[&4&l!&8&l] &cWarp o takiej nazwie nie istnieje!";
    public String playerIsAlreadyBanned = "&8&l[&4&l!&8&l] &cTen gracz jest juz zbanowany! Informacje o banie &6/baninfo {PLAYER}";
    public String playerIsNotBanned = "&8&l[&4&l!&8&l] &cTen gracz nie jest zbanowany!";
    public String playerIsAlreadyMuted = "&8&l[&4&l!&8&l] &cTen gracz jest juz wyciszony! Informacje o wyciszeniu &6/muteinfo {PLAYER}";
    public String playerIsNotMuted = "&8&l[&4&l!&8&l] &cTen gracz nie jest wyciszony!";
    public String blokiNoItems = "&8&l[&4&l!&8&l] &cNie posidasz potrzebnych przedmiotow na wymiane!";
    public String homeNoSet = "&8&l[&4&l!&8&l] &cNie posidasz ustawionego domu!";
    public String homeNotExists = "&8&l[&4&l!&8&l] &cDom o takiej nazwie nie istnieje!";
    public String homeNameOnlyLetters = "&8&l[&4&l!&8&l] &cNazwa domu powinna zawierac tylko litery oraz liczby!";
    public String enderchestOpenError = "&8&l[&4&l!&8&l] &cWystapil blad podczas otwierania enderchesta. Zmienila sie jego wielkosc przez co niektore przedmioty mogly zostac usuniete.";
    public String msgPlayerHasDisable = "&8&l[&4&l!&8&l] &cTen gracz ma wylaczone prywatne wiadomosci!";
    public String replyPlayerOffile = "&8&l[&4&l!&8&l] &cNie masz komu odpisac!";
    public String tpacceptNull = "&8&l[&4&l!&8&l] &cNie posiadasz prosby o teleportacje!";
    public String tpacceptPlayerNull = "&8&l[&4&l!&8&l] &cTen gracz nie wyslal Ci prosby o teleportacje!";
    public String tpaSamePlayer = "&8&l[&4&l!&8&l] &cNie mozna wyslac do siebie prosby o teleportacje!";
    public String tpaIsSendToThisPlayer = "&8&l[&4&l!&8&l] &cWyslales juz prosbe o teleportacje do tego gracza!";
    public String fakenameTooLength = "&8&l[&4&l!&8&l] &cNick nie moze byc dluzszy niz &6{LENGHT} &cznakow!";
    public String argnameOnlyLettersAndNumers = "&8&l[&4&l!&8&l] &cNick moze zawierac tylko litery oraz liczby!";
    public String antycrashLever = "&8&l[&4&l!&8&l] &cDzwignie mozna uzywac co &65 sekund&c!";
    public String antycrashBlock = "&8&l[&4&l!&8&l] &cTen przedmiot zostal zablokowany.";
    public String placeTntKoordY = "&8&l[&4&l!&8&l] &cTnt dziala od Y: &6{Y} &cpoziomu w dol!";
    public String randomTpOcean = "&8&l[&4&l!&8&l] &cTrafiles na ocean! Sprobuj ponownie.";
    public String antylogoutDisableCommand = "&8&l[&4&l!&8&l] &cTa komenda jest zablokowana podczas walki!";
    public String antyLogoutEnderchest = "&8&l[&4&l!&8&l] &cNie mozna otwierac enderchesta podczas walki!";
    public String enoughKoxy = "&8&l[&4&l!&8&l] &cNie posiadasz &6{LIMIT} &ckoxow!";
    public String enoughRefile = "&8&l[&4&l!&8&l] &cNie posiadasz &6{LIMIT} &crefili!";
    public String enoughPerly = "&8&l[&4&l!&8&l] &cNie posiadasz &6{LIMIT} &cperel!";
    public String nullKoxy = "&8&l[&4&l!&8&l] &cNie posiadasz &6koxow &cdo wyplacenia!";
    public String nullRefile = "&8&l[&4&l!&8&l] &cNie posiadasz &erefili &cdo wyplacenia!";
    public String nullPerly = "&8&l[&4&l!&8&l] &cNie posiadasz &9perel &cdo wyplacenia!";
    public String checkErrorYourSelf = "&8&l[&4&l!&8&l] &cNie mozesz sprawdzic sam siebie!";
    public String checkSuspectIsChecked = "&8&l[&4&l!&8&l] &cTen gracz jest juz sprawdzany!";
    public String checkSuspectIsNotChecked = "&8&l[&4&l!&8&l] &cTen gracz nie jest sprawdzany!";
    public String checkSuspectNotExecuteCommands = "&8&l[&4&l!&8&l] &cMozesz uzywac tylko komendy &6/helpop&c!";
    public String checkSuspectNotBuild = "&8&l[&4&l!&8&l] &cJestes sprawdzany! Nie mozesz budowac.";
    public String checkSuspectNotDropItems = "&8&l[&4&l!&8&l] &cJestes sprawdzany! Nie mozesz wyrzucac przedmiotow.";
    public String strenghtIsDisable = "&8&l[&4&l!&8&l] &cSila II w efektach jest teraz wylaczona!";
    public String hasEffect = "&8&l[&4&l!&8&l] &cPosiadasz juz ten efekt!";
    public String noPermissionVipEffect = "&8&l[&4&l!&8&l] &cNie posiadasz uprawnien do efektow &9M&6VIPa&c!";
    public String errorChatLevel = "&8&l[&4&l!&8&l] &cNa czacie mozna pisac od &6{LEVEL} poziomu &ckopania.";
    public String cobblexErrorCobblestone = "&8&l[&4&l!&8&l] &cNie posiadasz 9x64 cobblestone!";
    public String cobblexIsDisable = "&8&l[&4&l!&8&l] &cCobblexy sa aktualnie &4wylaczone&c!";
    public String luckyboxIsDisable = "&8&l[&4&l!&8&l] &cLuckyBox jest aktualnie &4wylaczony&c!";
    public String enchantOpenError = "&8&l[&4&l!&8&l] &cWystapil blad podczas otwierania enchantu! Zglos to do administratora!";
    public String enchantErrorLevel = "&8&l[&4&l!&8&l] &7Ten przedmiot posiada juz to zaklecie na &6wyzszym &7badz &6rownym &7poziomie!";
    public String enchantAddError = "&8&l[&4&l!&8&l] &cNie mozesz juz dodac tego &6zaklecia&c!";
    public String enchantErrorRequiredExp = "&8&l[&4&l!&8&l] &cMusisz miec &6{LEVEL} poziom EXP &caby zaklac ten przedmiot.";
    public String enchantErrorRequiredBook = "&8&l[&4&l!&8&l] &cNie wystarczajaca ilosc biblioteczek otacza &2stol do zaklinania&7. &7Aby to zrobic potrzebujesz jeszcze: &2{BOOKS} &7biblioteczek wokol stolu.";
    public String enchantErrorNullItem = "&8&l[&4&l!&8&l] &cW rece musisz miec przedmiot, ktory chesz zaklac!";
    public String anvilEditItemError = "&8&l[&4&l!&8&l] &cTego przedmiotu nie mozna edytowac!";
    public String regionNotExists = "&8&l[&4&l!&8&l] &cRegion o takiej nazwie nie istnieje!";
    public String regionDenyInteract = "&8&l[&4&l!&8&l] &cTa czynnosc jest zablokowana na regionie &6{REGION}&c!";
    public String regionDenyBuild = "&8&l[&4&l!&8&l] &cBudowanie jest zablokowane na regionie &6{REGION}&c!";
    public String regionDenyPaintingEdit = "&8&l[&4&l!&8&l] &cEdytowanie obrazow i ramek jest zablokowane na regionie &6{REGION}&c!";
    public String regionDenyCommands = "&8&l[&4&l!&8&l] &cKomendy sa wylaczone na regionie &6{REGION}&c!";
    public String regionDenyEnderPearl = "&8&l[&4&l!&8&l] &cTeleportacja perlami na region &6{REGION} &cjest wylaczona!";
    public String regionNotMarkedPoints = "&a&lREGIONY &8&l%> &cMusisz zaznaczyc dwa punkty LPM oraz PPM.";

    public String adminChatFormat = "&8[&6&lADMIN-CHAT&8] &c{PLAYER} &8&l%> &c{MESSAGE}";
    public String broadcastFormat = "&8[&aOGLOSZENIE&8] &c{MESSAGE}";
    public String helpopFormat = "&8[&4&lHELPOP&8] &c{PLAYER} &8&l%> &c{MESSAGE}";
    public String msgFormat = "&8[&b{PLAYER1} &8-> &b{PLAYER2}&8] &b{MESSAGE}";
    public String socialspyFormat = "&6&l[SS] &8[&7{PLAYER1} &8-> &7{PLAYER2}&8] &7{MESSAGE}";
    public String chatFormatSuspect = "&8[&d&lSPRAWDZANY&8] &7{PLAYER} &8&l%> &7{MESSAGE}";
    public String chatFormatPlayer = "{TAG}{POINTS}{PREFIX}{NAME} &8&l%> {SUFFIX}{MESSAGE}";
    public String chatFormatAdmin = "{PREFIX}{NAME} &8&l%> {SUFFIX}{MESSAGE}";


    public String titleJoinPlayer = "&8[ &6&lHI&f&lMC&7&l.PL &8]";
    public String subTitleJoinPlayer = "&6&lNOWY SEZON";
    public String titleBroadcast = "&8[ &6&lHI&f&lMC&7&l.PL &8]";
    public String subTitleBroaccast = "&8* &6{MESSAGE} &8*";
    public String titleGiveKit = "&8* &2&lZESTAWY &8*";
    public String subTitleGiveKit = "&7Otrzymales zestaw &a{KIT}&7!";
    public String titleToSuspect = "&4&lJESTES SPRAWDZANY!";
    public String subtitleToSuspect = "&c&lCzytaj czat";
    public String titleLevelUp = "&8[ &2&lGratulacje &8]";
    public String subtitleLevelUp = "&7Awansowales na &2{LEVEL} &7poziom kopania!";
    public String titleLuckyBoxGive = "&8[ &eLucky&6Box &8]";
    public String subTitleLuckyBoxGive = "&7Kazdy otrzymal &elucky&6boxa &6x{AMOUNT}szt.";
    public String titleVoucherUsage = "&8[ &5VOUCHER &8]";
    public String subTitleVoucherEVipUsage = "&7Uzyles vouchera na range &3E&6VIP&7!";
    public String subTitleVoucherTurboDropUsage = "&7Uzyles vouchera na &d&lTurbo&b&lDrop&7!";
    public String subTitleVoucherTurboExpUsage = "&7Uzyles vouchera na &d&lTurbo&b&lExp&7!";
    public String titleBackup = "&c&lBackup";
    public String subTitleBackup = "&7Twoje przedmioty zostaly &2przywrocone&7!";
    public String titleSwitchBallToAttacer = "";
    public String subTitleSwitchBallToAttacer = "&7Trafiles gracza &6{PLAYER} &7zamieniacie sie miejscami!";
    public String titleSwitchBallToAttacked = "";
    public String subTitleSwitchBallToAttacked = "&7Dostales sniezka od &6{PLAYER} &7zamieniacie sie miejscami!";
    public String subTitleBorderTeleportError = "&cNie mozesz wyrzucac perel poza border mapy!";
    public String subTitleBorderBoatError = "&cNie mozesz stawiac lodki blisko bordera mapy!";
    public String subTitleBorderBlockPlaceError = "&cNie mozesz stawiac blokow blisko bordera mapy!";

    @CfgCollectionStyle(CfgCollectionStyle.CollectionStyle.ALWAYS_NEW_LINE)
    public List<String> onlineList = Arrays.asList(
            "&7Gracze &a&lonline &8(&a{ONLINE-PLAYERS}&8)",
            "&a{LIST-ONLINE-PLAYERS}",
            "&7",
            "&7Administracja &a&lonline &8(&a{ONLINE-ADMINS}&8)",
            "&a{LIST-ONLINE-ADMINS}");
    public String permBanDesign =
            "&7Zostales &4zbanowany &7przez &6{ADMIN}\n" +
            "&7Powod: &c{REASON}\n" +
            "&7Blokada konta jest &cna zawsze\n" +
            "&7\n" +
            "&7Discord: &6https://dc.himc.pl\n" +
            "&7Itemshop: &6https://himc.pl";
    public String tempBanDesign =
            "&7Zostales &4zbanowany &7przez &6{ADMIN}\n" +
            "&7Powod: &c{REASON}\n" +
            "&7Blokada wygasa: &c{DATA}\n" +
            "&7\n" +
            "&7Discord: &6https://dc.himc.pl\n" +
            "&7Itemshop: &6https://himc.pl";
    public String permMuteDesign =
            "&7\n" +
            "&7Zostales &4wyciszony &7przez &6{ADMIN}\n" +
            "&7Powod: &c{REASON}\n" +
            "&7Blokada pisania jest &cna zawsze\n" +
            "&7\n";
    public String tempMuteDesign =
            "&7\n" +
            "&7Zostales &4wyciszony &7przez &6{ADMIN}\n" +
            "&7Powod: &c{REASON}\n" +
            "&7Blokada pisania jest do &c{DATA}\n" +
            "&7\n";
    public String reklamaNotifyAdmin =
            "&8========&4 &lAntyReklama&8 ========\n" +
            "&7Gracz &c{PLAYER} &7probowal reklamowac serwer.\n" +
            "&7Jego wiadomosc: &c{MESSAGE}\n" +
            "&8========&4 &lAntyReklama&8 ========";
    public String kickPlayerDesign =
            "&7Zostales &4wyrzucony &7z serwera przez &6{ADMIN}&7!\n" +
            "&7Powod: &c{REASON}\n";
    public String tpaSendToRecipient = "&8\n" +
            " &8%> &7Gracz &6{PLAYER} &7wyslal Ci prosbe o teleportacje!\n" +
            " &8%> &7Wpisz &2/tpaccept {PLAYER} &7aby zaakceptowac teleportacje\n" +
            " &8%> &7Prosba jest wazna przez &c60 sekund&7!\n" +
            "&8";
    public String sprawdzHelp =
            "&8[&7#&8]&8 &m----------------&5 &lSprawdz&8 &m----------------&8 [&7#&8]\n" +
            "&8* &7/sprawdz gracz <Nick> &8- &aTeleportuje gracza do sprawdzarki\n" +
            "&8* &7/sprawdz cheaty <Nick> &8- &aBanuje gracza za cheaty\n" +
            "&8* &7/sprawdz przyznanie <Nick> &8- &aBanuje gracza za przyznanie do cheatow\n" +
            "&8* &7/sprawdz czysty <Nick> &8- &aOczyszcza gracza\n" +
            "&8* &7/sprawdz brakwspolpracy <Nick> &8- &aBanuje gracza za cheaty\n" +
            "&8* &7/sprawdz wyloguj <Nick> &8- &aMozliwosc wylogowania sie gracza podczas sprawdzania\n" +
            "&8[&7#&8]&8 &m----------------&5 &lSprawdz&8 &m----------------&8 [&7#&8]";
    public String checkSendToSuspect =
            "&7\n" +
            "&7\n" +
            "&7\n" +
            "&7\n" +
            " &8%> &7Jestes sprawdzany przez &6{ADMIN}\n" +
            " &8%> &7Sluchaj sie administratora!\n" +
            " &8%> &cWylogowanie sie = &4&lPERM\n" +
            " &8%> &cPrzyznanie sie do cheatow = &4&lBAN 3 DNI\n" +
            "&7\n" +
            "&7";
}
