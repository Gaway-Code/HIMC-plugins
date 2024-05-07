package pl.himc.antikillaura.configuration;

import org.diorite.cfg.annotations.CfgComment;
import org.diorite.cfg.annotations.CfgExclude;
import pl.himc.api.utils.TimeUtil;

public final class PluginConfig {

    @CfgComment("Po jakim czasie wygasa ostatnie uderzenie bota przez gracza")
    public String damageBotExpire_ = "2min";
    @CfgExclude
    public long damageBotExpire;
    @CfgComment("Ile razy trzeba uderzyć bota aby dostać bana?")
    public int damageBotAmount = 7;
    @CfgComment("Co ile sekund mają być sprawdzani gracze")
    public int checkPlayersDelay = 15;
    @CfgComment("Komenda do zbanowania gracza")
    public String banPlayerCommand = "tempban {PLAYER} 12h Cheaty (KillAura)";

    public void loadValues() {
        this.damageBotExpire = TimeUtil.getTimeWithString(this.damageBotExpire_);
    }
}
