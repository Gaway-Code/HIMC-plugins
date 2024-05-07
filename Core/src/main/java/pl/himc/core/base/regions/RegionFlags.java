package pl.himc.core.base.regions;

import java.util.ArrayList;
import java.util.List;

public class RegionFlags {

    public static Flag flagFromString(final String value) {
        Flag flag;
        flag = Flag.valueOf(value);
        return flag;
    }

    public static List<String> flagsListToStringList(final List<Flag> flags) {
        final List<String> newflags = new ArrayList<>();
        for (Flag flag : flags) {
            newflags.add(flag.toString());
        }
        return newflags;
    }

    public static List<Flag> stringListToFlagList(final List<String> flags) {
        final List<Flag> newflags = new ArrayList<>();
        for (String flag : flags) {
            newflags.add(flagFromString(flag));
        }
        return newflags;
    }

    public enum Flag {
        use("use"),
        blockbreak("blockbreak"),
        blockplace("blockplace"),
        paintingedit("paintingedit"),
        leafdecay("leafdecay"),
        explode("explode"),
        bucket("bucket"),
        commands("commands"),
        pvp("pvp"),
        enderpearl("enderpearl"),
        stoniarka("stoniarka");

        Flag(final String s) {}
    }
}
