package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class UtilHashing {
    private static final Random RANDOM = new SecureRandom();

    private UtilHashing() {
    }

    public static String getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return bytesToHex(salt);
    }

    public static String saltedHash(String password, String salt) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            UtilLibLogger.logMessageSEVERE(UtilHashing.class, e.toString());
        }
        String comb = password + salt;
        if (digest == null) {
            return null;
        }
        return bytesToHex(digest.digest(comb.getBytes(StandardCharsets.UTF_8)));
    }

    public static boolean identicalHash(String password, String hash, String salt) {
        String sh = saltedHash(password, salt);
        if (sh == null) {
            return false;
        }

        return sh.equals(hash);
    }


    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte hash1 : hash) {
            String hex = Integer.toHexString(0xff & hash1);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String generateRandomPassword(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int c = RANDOM.nextInt(62);
            if (c <= 9) {
                sb.append(String.valueOf(c));
            } else if (c < 36) {
                sb.append((char) ('a' + c - 10));
            } else {
                sb.append((char) ('A' + c - 36));
            }
        }
        return sb.toString();
    }
}
