package pl.himc.guilds.base.guild;

import org.bukkit.Material;

public enum GuildPermission {

    BLOCK_PLACE(Material.GRASS, "Stawiania blokow"),
    BLOCK_BREAK(Material.DIAMOND_PICKAXE, "Niszczenia blokow"),
    TNT_PLACE(Material.TNT, "Stawiania tnt"),
    FLUID_PLACEMENT(Material.BUCKET, "Rozlewania wody/lavy"),
    TELEPORTATION_USE(Material.NETHER_STAR, "Uzywania /tpaccept na terenie gildii"),
    TELEPORT_HOME(Material.ENDER_PORTAL_FRAME, "Teleportacji do bazy gildii"),
    BEACON(Material.BEACON, "Zarzadzania beaconem"),
    OPEN_CHEST(Material.CHEST, "Otwierania skrzynek"),
    OPEN_TREASURE(Material.ENDER_CHEST, "Otwierania skarbca gildii"),
    MEMBER_INVITE(Material.PAPER, "Dodawanie graczy do gildii"),
    MEMBER_KICK(Material.BARRIER, "Wyrzucanie graczy z gildii"),
    GUILD_EXPIRED(Material.BONE, "Przedluzania waznosci gildii");

    private Material permissionIcon;
    private String permissionName;

    GuildPermission(Material permissionIcon, String permissionName) {
        this.permissionIcon = permissionIcon;
        this.permissionName = permissionName;
    }

    public static GuildPermission byName(String str) {
        for (GuildPermission permission : values())
            if (permission.name().equalsIgnoreCase(str))
                return permission;
        return null;
    }

    public Material getPermissionIcon() {
        return permissionIcon;
    }

    public String getPermissionName() {
        return permissionName;
    }

}
