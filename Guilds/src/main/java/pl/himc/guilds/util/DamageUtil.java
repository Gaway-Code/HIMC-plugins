package pl.himc.guilds.util;

import pl.himc.api.utils.IntegerRange;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.data.PluginConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DamageUtil {

    public static List<Damage> sort(Map<String, Double> damage) {
        if (damage.size() == 0) {
            return null;
        }
        List<Damage> map = new ArrayList<>();
        for (Map.Entry<String, Double> e : damage.entrySet()) {
            map.add(new Damage(e.getKey(), e.getValue()));
        }
        Collections.sort(map);
        return map;
    }

    public static String getAsist(List<Damage> damage, String killer) {
        if (damage == null || damage.isEmpty()) {
            return null;
        }
        if (!damage.get(0).getPlayer().equals(killer) && damage.get(0).getDamage() > 4.0) {
            return damage.get(0).getPlayer();
        }
        if (damage.size() <= 1) {
            return null;
        }
        if (!damage.get(1).getPlayer().equals(killer) && damage.get(1).getDamage() > 4.0) {
            return damage.get(1).getPlayer();
        }
        return null;
    }

    public static int[] getEloValues(int victimPoints, int attackerPoints) {
        PluginConfig config = PluginGuild.getPlugin().getPluginConfiguration();
        int[] rankChanges = new int[2];

        int aC = IntegerRange.inRange(attackerPoints, config.eloConstants, "ELO_CONSTANTS");
        int vC = IntegerRange.inRange(victimPoints, config.eloConstants, "ELO_CONSTANTS");

        rankChanges[0] = (int) Math.round(aC * (1 - (1.0D / (1.0D + Math.pow(config.eloExponent, (victimPoints - attackerPoints) / config.eloDivider)))));
        rankChanges[1] = (int) Math.round(vC * (0 - (1.0D / (1.0D + Math.pow(config.eloExponent, (attackerPoints - victimPoints) / config.eloDivider)))) * - 1);

        return rankChanges;
    }

    public static class Damage implements Comparable<Damage> {
        private String player;
        private double damage;

        public Damage(String damager, double damage) {
            this.player = damager;
            this.damage = damage;
        }

        @Override
        public int compareTo(Damage dmg) {
            int i = Double.compare(dmg.getDamage(), this.damage);
            if (i == 0) {
                if (this.player == null) {
                    return -1;
                }
                if (dmg.getPlayer() == null) {
                    return 1;
                }
                i = this.player.compareTo(dmg.getPlayer());
            }
            return i;
        }

        public double getDamage() {
            return this.damage;
        }

        public String getPlayer() {
            return this.player;
        }
    }
}
