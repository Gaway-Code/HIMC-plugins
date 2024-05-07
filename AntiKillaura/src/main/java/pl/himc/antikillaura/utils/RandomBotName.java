package pl.himc.antikillaura.utils;

import pl.himc.api.utils.RandomUtil;

public final class RandomBotName {

    public static String getBotName(){
        return "" + RandomUtil.getRandInt(1, 99999);
    }
}
