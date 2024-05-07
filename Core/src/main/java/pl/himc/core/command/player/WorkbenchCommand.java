package pl.himc.core.command.player;

import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;

public final class WorkbenchCommand extends PlayerCommand {

	public WorkbenchCommand(String permission) {
		super("workbench", permission,"/workbench");
	}

	@Override
	public boolean onCommand(Player player, String[] args) {
		player.openWorkbench(player.getLocation(), true);
		return false;
	}
}
