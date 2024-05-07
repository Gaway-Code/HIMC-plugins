package pl.himc.core.command.player;

import org.bukkit.entity.Player;
import pl.himc.core.base.effect.EffectManager;
import pl.himc.api.api.command.PlayerCommand;

public final class EfektCommand extends PlayerCommand {

    public EfektCommand() {
        super("efekt", null, "","efekty");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        EffectManager.getInstance().openMain(player);
        return false;
    }
}
