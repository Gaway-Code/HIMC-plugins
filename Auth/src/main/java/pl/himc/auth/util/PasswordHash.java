package pl.himc.auth.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class PasswordHash {

    public static String sha256(final String msg) {
        try{
            final MessageDigest sha = MessageDigest.getInstance("SHA-256");
            sha.reset();
            sha.update(msg.getBytes());
            final byte[] digest = sha.digest();
            return String.format("%0" + (digest.length << 1) + "x", new BigInteger(1, digest));
        }catch (NoSuchAlgorithmException exception){
            exception.printStackTrace();
            return "";
        }
    }

    public static String shaSalted(final String message, final String salt) {
        return "$SHA$" + salt + "$" + sha256(sha256(message) + salt);
    }

    public static String createSalt(final int length){
        try{
            final SecureRandom rnd = new SecureRandom();
            final byte[] msg = new byte[40];
            rnd.nextBytes(msg);
            final MessageDigest sha = MessageDigest.getInstance("SHA1");
            sha.reset();
            final byte[] digest = sha.digest(msg);
            return String.format("%0" + (digest.length << 1) + "x", new BigInteger(1, digest)).substring(0, length);
        }catch (NoSuchAlgorithmException exception){
            exception.printStackTrace();
            return "";
        }
    }

    public static boolean cmpPassWithHash(final String pass, final String hash) {
        if (hash.contains("$")) {
            final String[] line = hash.split("\\$");
            if (line.length > 3 && line[1].equals("SHA")) {
                return hash.equals(shaSalted(pass, line[2]));
            }
        }
        return false;
    }
}
