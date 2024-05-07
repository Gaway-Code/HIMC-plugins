package pl.himc.lobbycore.data;

import org.diorite.cfg.annotations.CfgComment;

public class PluginMessages {

    @CfgComment("Wiadomości o zablokowaniu")
    public String denySendCommands = "&8&l%> &cKomendy sa zablokowane!";
    public String denyChatWrite = "&8&l%> &cPisanie na czacie jest zablokowane!";
    public String denyBlockBreak = "&8&l%> &cNiszczenie blokow jest zablokowane!";
    public String denyBlockPlace = "&8&l%> &cStawianie blokow jest zablokowane!";
    public String denyDropItem = "&8&l%> &cWyrzucanie przedmiotow jest zablokowane!";

    @CfgComment("Wiadomości o powodzeniu")
    public String setSpawnSuccess = "&8&l%> &aNowe miejsce spawnu zostalo &2ustawione&a!";
    public String reloadServersSuccess = "&8&l%> &aSerwery zostaly &2przeladowane&a!";

    @CfgComment("Title")
    public String playerJoinTitle = "&8&l[ &7&lx&9&lHTC&7&l.PL &8&l]";
    public String playerJoinSubTitle = "&8* &aWybierz serwer za pomoca &bksiazki&a! &8*";
}
