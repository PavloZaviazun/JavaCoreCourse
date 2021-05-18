package main.Practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {

    public static void main(String[] args) {
        System.out.println(hash("asdf", "MD5"));
        System.out.println(hash("asdf", "SHA-256"));
    }

    public static String hash(String input, String algorithm) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] bytes = input.getBytes();
            digest.update(bytes);
            byte[] hash = digest.digest();
            for(byte el : hash) {
                String string = Integer.toHexString(0xff & el);
                string = (string.length() == 1) ? "0" + string.toUpperCase() : string.toUpperCase();
                sb.append(string);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}

