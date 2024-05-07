package pl.himc.auth.api;

import pl.himc.auth.AuthPlugin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public final class PluginAuth {

    private static AuthPlugin instance;

    public static void setInstance(final AuthPlugin api){
        PluginAuth.instance = api;
    }

    public static AuthPlugin getPlugin(){
        return instance;
    }


    public static boolean isPremium(final String playerName){
        try{
            final Scanner scanner = new Scanner(new URL("https://api.ashcon.app/mojang/v2/user/" + playerName).openStream());
            scanner.close();
            System.out.println("Gracz " + playerName + " jest graczem PREMIUM!");
            return true;
        }catch (FileNotFoundException ex){
            System.out.println("Gracz " + playerName + " jest graczem NON-PREMIUM!");
            return false;
        } catch (IOException ex) {
            System.out.println("Blad podczas sprawdzania gracza: " + playerName);
            System.out.println("IOException: " + ex.getMessage());
        }
        System.out.println("Gracz " + playerName + " jest graczem NON-PREMIUM!");
        return false;
    }
}
