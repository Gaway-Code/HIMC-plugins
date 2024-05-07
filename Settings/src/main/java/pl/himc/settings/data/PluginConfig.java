package pl.himc.settings.data;

import org.diorite.cfg.annotations.CfgComment;

public final class PluginConfig {

    @CfgComment("Czy craftowanie diamentowych itemów ma być włączone?")
    public boolean craftDiamondItems = true;
    @CfgComment("Czy TNT ma być włączone?")
    public boolean tntExplode = true;

}
